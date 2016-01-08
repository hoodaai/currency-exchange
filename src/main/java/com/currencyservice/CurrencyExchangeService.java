package com.currencyservice;

import com.dataaccess.model.CurrencyExchangeRate;

import java.util.List;

/**
 * Currency Exchange service contains all allowed operations performed in Virtual Currency Exchange Rate Publishing System
 */
public interface CurrencyExchangeService {

    /**
     * gets all currency along with exchange rate.
     * @return
     */
    public List<CurrencyExchangeRate> listCurrency();

    /**
     * Search currency by name
     * @param currency
     * @return
     */
    public CurrencyExchangeRate searchCurrency(String currency);

    /**
     * Converts currency from one to another
     * @param from
     * @param to
     * @param amount
     * @return
     */
    public String convertCurrency(String from, String to, Double amount);
}
