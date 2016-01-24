package com.rest.spring;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by varun on 31/12/15.
 */
@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    long started = System.currentTimeMillis();

    @RequestMapping(value="/status", method= RequestMethod.GET)
    public  Map<String, Object> status() {

        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK);
        map.put("started", started);
        map.put("uptime", System.currentTimeMillis() - started);
        map.put("version", "0.1" );

        return map;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        return "index";
    }

}
