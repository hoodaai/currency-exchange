package com.dataaccess.dao;

import com.dataaccess.model.CurrencyExchangeRate;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class encompasses data access methods for CurrencyExchangeRate object.
 */
public class CurrencyExchangeDao {

    private SessionFactory sessionFactory;

    /**
     *  list of all currency along with their respective exchange rate with base currency
     * @return List of {@link CurrencyExchangeRate}
     */
    @Transactional
    public List<CurrencyExchangeRate> list() {
        List<CurrencyExchangeRate> currencyList = (List<CurrencyExchangeRate>) sessionFactory.getCurrentSession()
                .createCriteria(CurrencyExchangeRate.class)
                .add(Restrictions.ge("syncDate", calculateDate()))
                //.add(Restrictions.or(Restrictions.eq("syncDate", calculateDate()), Restrictions.ge("syncDate", calculateDate())))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return currencyList;
    }


    /**
     * Search currency by currency code name
     * @param currency
     * @return {@link CurrencyExchangeRate}
     */
    @Transactional
    public CurrencyExchangeRate search(String currency) {

        CurrencyExchangeRate currencyList = (CurrencyExchangeRate) sessionFactory.getCurrentSession()
                .createCriteria(CurrencyExchangeRate.class)
                .add(Restrictions.eq("currencyName", currency))
                .add(Restrictions.ge("syncDate", calculateDate()))
                .uniqueResult();
        return currencyList;
    }

    /**
     * save {@link CurrencyExchangeRate} into database
     * @param currency
     * @return id
     */
    @Transactional
    public int save(CurrencyExchangeRate currency){
        Integer id = (Integer)sessionFactory.getCurrentSession().save(currency);
        return id;
    }

    /**
     * deletes those {@link CurrencyExchangeRate} from database having syncdate lesser than the given date.
     */
    @Transactional
    public void delete(){
        StringBuilder sb = new StringBuilder("delete from CurrencyExchangeRate ");
        sb.append(" where syncDate<= :syncDate");

        sessionFactory.getCurrentSession()
                .createQuery(sb.toString())
                .setDate("syncDate", calculateDate())
                .executeUpdate();
    }

    /**
     * utility function to prepare date
     * @return
     */
    private Date calculateDate() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date todayWithZeroTime = null;
        try {
            todayWithZeroTime = formatter.parse(formatter.format(gregorianCalendar.getTime()));
        }catch (ParseException e){
            e.printStackTrace();
        }
        return todayWithZeroTime;
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
