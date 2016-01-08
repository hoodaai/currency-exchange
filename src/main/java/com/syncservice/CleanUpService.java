package com.syncservice;

import com.dataaccess.dao.CurrencyExchangeDao;

/**
 * <p> This data cleanup service is a cron job started using a cron expression defined in application
 * properties file. </p>
 * <p> It cleans old currency exchange rate data from database.</p>
 */
public class CleanUpService {

    private CurrencyExchangeDao currencyExchangeDao;

    /**
     * This method deletes old currency exchange rate record from database.
     */
    public void cleanOldCurrencyDetailRecords() {
        currencyExchangeDao.delete();
    }

    public void setCurrencyExchangeDao(CurrencyExchangeDao currencyExchangeDao) {
        this.currencyExchangeDao = currencyExchangeDao;
    }
}
