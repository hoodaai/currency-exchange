package com.rest.spring;

import com.currencyservice.CurrencyExchangeService;
import com.dataaccess.model.CurrencyExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Currency Exchange controller contains REST interface to access Currency Exchange services.
 * <p>Below are the available currency exchange services:</p>
 * <ul>
 *     <li>list of exchange rates for different currencies</li>
 *     <li>search for the exchange rate for a specific currency</li>
 *     <li>currency converter to convert a specific amount of money from a currency to another</li>
 * </ul>
 * GET: /api/currency
 * GET: /api/currency/search?currencyName=<name>
 * GET: /api/currency/convert?source=<>&target=<>&amount=<>
 *
 */

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeService currencyService;

    /**
     * API to get all currency
     * @return Map<String, Object>
     */
   // @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value="/api/currency", method= RequestMethod.GET)
    public Map<String, Object> listCurrency(HttpServletResponse response) {
        List<CurrencyExchangeRate> currencyList = currencyService.listCurrency();

        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK);
        map.put("result", currencyList);

        return map;
    }

    /**
     *  API to search concurrency by name
     * @param currencyName
     * @return Map<String, Object>
     */
    //@CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value="/api/currency/{currencyName}", method= RequestMethod.GET)
    public  Map<String, Object> searchCurrency(@PathVariable String currencyName) {
        CurrencyExchangeRate currency = null;
        Map<String, Object> map = new HashMap<>();
        try{
            currency = currencyService.searchCurrency(currencyName.toUpperCase());
            System.out.println(currency);
            if(currency != null) {
                map.put("status", HttpStatus.OK);
                map.put("result", currency);
            } else {
                map.put("status", HttpStatus.NOT_FOUND);
                map.put("result", new CurrencyExchangeRate());
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("status", HttpStatus.NOT_FOUND);
            map.put("result", new CurrencyExchangeRate());
        }

        return map;
    }

    /**
     * API to convert currency
     * @param from source currency
     * @param to target currency
     * @param amount amount to be converted
     * @return Map<String, Object>
     */
    //@CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value="/api/currency/convert", method= RequestMethod.GET)
    public  Map<String, Object> convertCurrency(@RequestParam("source") String from,
                                                @RequestParam("target") String to,
                                                @RequestParam("amount") String amount) {

        String convertedAmount = currencyService.convertCurrency(from.toUpperCase(), to.toUpperCase(), Double.parseDouble(amount));
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK);
        map.put("result", convertedAmount);
        return map;
    }


    public void setCurrencyService(CurrencyExchangeService currencyService) {
        this.currencyService = currencyService;
    }
}
