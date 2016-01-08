package com.dataaccess.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Database entity for Currency. It contains base currency, exchange rate against the base currency and timestamp
 * when data is synced with Virtual Currency Exchange Rate Publishing System.
 */
@Entity
@Table(name = "currency_exchange_rate")
public class CurrencyExchangeRate {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    /**
     * Currency code name
     */
    @Column(name = "currency_name")
    private String currencyName;

    /**
     *  exchange rate against the base currency
     */
    @Column(name = "conversion_rate")
    private String conversionRate;

    /**
     * Base currency is the primary currency.
     */
    @Column(name = "base_currency")
    private String baseCurrency;

    /**
     * timestamp when data is synced with Virtual Currency Exchange Rate Publishing System.
     */
    @Column(name = "sync_date")
    private Date syncDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(String conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    @Override
    public String toString() {
        return "CurrencyExchangeRate{" +
                "id=" + id +
                ", currencyName='" + currencyName + '\'' +
                ", conversionRate='" + conversionRate + '\'' +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", syncDate=" + syncDate +
                '}';
    }
}
