package com.currencyservice;

import com.dataaccess.dao.CurrencyExchangeDao;
import com.dataaccess.model.CurrencyExchangeRate;

import java.util.List;

/**
 * @link CurrencyExchangeService implementation.
 */
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private CurrencyExchangeDao currencyExchangeDao;

    @Override
    public  List<CurrencyExchangeRate> listCurrency() {
        List<CurrencyExchangeRate> currencyList = currencyExchangeDao.list();
        return currencyList;
    }

    @Override
    public CurrencyExchangeRate searchCurrency(String currency) {
        CurrencyExchangeRate currencyList = currencyExchangeDao.search(currency);
        return currencyList;
    }

    @Override
    public String convertCurrency(String from, String to, Double amount) {
        CurrencyExchangeRate fromCurrency = currencyExchangeDao.search(from);
        CurrencyExchangeRate toCurrency = currencyExchangeDao.search(to);

        double sourceCurrency  = Double.parseDouble(fromCurrency.getConversionRate());
        double sourceCurrencyToEquivalentUSD = (1/sourceCurrency) * amount;

        double targetCurrencyExchangeRate  = Double.parseDouble(toCurrency.getConversionRate());
        System.out.println("Target Currency exchange rate (to USD)" + targetCurrencyExchangeRate);

        double answer = sourceCurrencyToEquivalentUSD * targetCurrencyExchangeRate;
        System.out.println("a*target currency )" +answer);

        return Double.toString(answer);
    }

    public void setCurrencyExchangeDao(CurrencyExchangeDao currencyExchangeDao) {
        this.currencyExchangeDao = currencyExchangeDao;
    }
}
