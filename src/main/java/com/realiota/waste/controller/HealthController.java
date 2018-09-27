package com.realiota.waste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @Autowired
    private Environment environment;

    @RequestMapping(value = "ping", method = RequestMethod.GET)
    public Map<String, String> ping() {
        Map<String, String> map = new HashMap<>();
        map.put("status", "alive");
        if (0 < this.environment.getActiveProfiles().length) {
            map.put("environment", this.environment.getActiveProfiles()[0]);
        }

        return map;
    }
}