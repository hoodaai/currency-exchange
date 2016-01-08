package com.syncservice;

import com.dataaccess.dao.CurrencyExchangeDao;
import com.dataaccess.model.CurrencyDetails;
import com.dataaccess.model.CurrencyExchangeRate;
import org.apache.http.HttpEntity;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 *
 * <p>Sync service fetch Currency exchange rate from public Virtual Currency Exchange Rate Publishing System
 * openexchangerates.org</p>
 *
 * <p>This sync service is a cron job started using a cron expression defined in application
 * properties file.</p>
 */
public class SyncService {

    /**
     * App id created on openexchangerates.org
     */
    private String appId;

    /**
     * openexchangerates.org url to access Currency exchange rate data
     */
    private String exchangeServerUrl;

    private CurrencyExchangeDao currencyExchangeDao;

    /**
     * This method sync data from openexchangerates.org
     */
    public void syncCurrencyExchangeData() {
        System.out.println("Method executed at every 5 seconds. Please configure cron in applications.properties file");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(getUrl());
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();

            System.out.println(entity.getContent());
            String data = EntityUtils.toString(entity);
            //System.out.println(data);

            Gson gson = new Gson();
            CurrencyDetails currencyDetails =  gson.fromJson(data, CurrencyDetails.class);
            System.out.println(currencyDetails);
            saveExchangeRate(currencyDetails);
            EntityUtils.consume(entity);
        } catch (Exception e) {
           e.printStackTrace();
        }
        finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String getUrl() {
        StringBuilder url = new StringBuilder()
                .append(exchangeServerUrl)
                .append("?app_id=")
                .append(appId);
        return url.toString();
    }

    /**
     * This method save currency exchange data into database
     * @param currencyDetails
     */
    private void saveExchangeRate(CurrencyDetails currencyDetails){
        HashMap<String, String> rates =  currencyDetails.getRates();
        for(Map.Entry<String, String> entry: rates.entrySet()){
            CurrencyExchangeRate exchangeRate = new CurrencyExchangeRate();
            exchangeRate.setBaseCurrency(currencyDetails.getBase());
            exchangeRate.setCurrencyName(entry.getKey());
            exchangeRate.setConversionRate(entry.getValue());
            exchangeRate.setSyncDate(new Date());
            Integer id = currencyExchangeDao.save(exchangeRate);
            System.out.println("Record saved :" + id);
        }

    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setExchangeServerUrl(String exchangeServerUrl) {
        this.exchangeServerUrl = exchangeServerUrl;
    }

    public void setCurrencyExchangeDao(CurrencyExchangeDao currencyExchangeDao) {
        this.currencyExchangeDao = currencyExchangeDao;
    }
}
