package com.restservice;

import com.dataaccess.model.CurrencyExchangeRate;
import com.syncservice.CleanUpService;
import com.syncservice.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by varun on 15/12/15.
 */
@RestController
public class SyncController {

    @Autowired
    private SyncService syncService;

    @Autowired
    private CleanUpService cleanUpService;

    /**
     * API to sync
     * @return Map<String, Object>
     */
    //@CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value="/api/sync", method= RequestMethod.GET)
    public Map<String, Object> sync() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            syncService.syncCurrencyExchangeData();
            map.put("status", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            map.put("status", HttpStatus.NOT_FOUND);
        }
        return map;
    }

    /**
     * API to sync
     * @return Map<String, Object>
     */
   // @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value="/api/cleanup", method= RequestMethod.GET)
    public Map<String, Object> cleanUp() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            cleanUpService.cleanOldCurrencyDetailRecords();
            map.put("status", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            map.put("status", HttpStatus.NOT_FOUND);
        }
        return map;
    }
}
