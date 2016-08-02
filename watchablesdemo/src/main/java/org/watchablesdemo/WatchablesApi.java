package org.watchablesdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tgreen on 7/27/16.
 */
@RequestMapping(value = "/watchableDemo")
@Controller
public class WatchablesApi {

    @Autowired
    WatchablesDipswitchWorker dipswitchWorker;

    @Autowired
    WatchablesPvWorker pvWorker;

    @Autowired
    WatchablesRetryHelperWorker watchablesRetryHelperWorker;

    @Autowired
    WatchablesCircuitBreaker watchablesCircuitBreaker;

    @RequestMapping(value = "/pv", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, String> pvDemo(){
        Map<String, String> response = new HashMap<>();
        response.put("roosterPV", pvWorker.getRooster().getValue());
        return response;
    }

    @RequestMapping(value = "/dipswitch", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, String> dipswitchDemo(){
        Map<String, String> returnValue = new HashMap<>();
        returnValue.put("Dipswitch BEAR: " , dipswitchWorker.getDipswitchString());
        if (dipswitchWorker.dipSwitch.getBit().get()){
            //do real thing
        }else {
            //return not yet implemented
        }
        return returnValue;
    }

    @RequestMapping(value = "/retryHelper", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, String> fireRetryMethod(){
        watchablesRetryHelperWorker.retryMethod();
        return new HashMap<>();
    }

    @RequestMapping(value = "/circuitBreaker", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, String> fireCB(){
        String returnValue = watchablesCircuitBreaker.cbMethod();
        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("MyCB", returnValue);
        return returnMap;
    }

}
