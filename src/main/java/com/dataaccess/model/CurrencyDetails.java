package com.dataaccess.model;

import java.util.HashMap;

/**
 * Represents Currency rate data received from Virtual Currency Exchange Rate Publishing System.
 */
public class CurrencyDetails {

    /**
     * timestamp when data is received
     */
    private String timestamp;

    /**
     * base currency is the primary currency against the received currency list
     */
    private String base;

    /**
     * Key value mapping for received currency along with their rate
     * ex: rates": {"AED": 3.67302, "AFN": 67.089998}
     */
    private HashMap<String,String> rates;


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public HashMap<String, String> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, String> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CurrencyDetails{" +
                "timestamp='" + timestamp + '\'' +
                ", base='" + base + '\'' +
                ", rates=" + rates +
                '}';
    }
}

