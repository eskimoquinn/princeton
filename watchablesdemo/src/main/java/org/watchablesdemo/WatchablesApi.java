package org.watchablesdemo;

import org.cirrostratus.sequoia.dipswitches.DipSwitch;
import org.cirrostratus.sequoia.dipswitches.DipSwitchFactory;
import org.cirrostratus.sequoia.watchable.Watchable;
import org.cirrostratus.sequoia.watchable.WatchableListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tgreen on 7/27/16.
 */
@RequestMapping(value = "/watchableDemo")
@Controller
public class WatchablesApi {

    @Autowired
    DipSwitchFactory dsf;

    DipSwitch dipSwitch;

    @RequestMapping(value = "/pv", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, String> pvDemo(){
        return new HashMap<>();
    }


    @RequestMapping(value = "/dipswitch", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, String> dipswitchDemo(){
        Map<String, String> returnValue = new HashMap<>();
        returnValue.put("Dipswitch BEAR:", dipSwitch.getBit().toString());
        return returnValue;
    }

    @PostConstruct
    public void initialization() {
        initDipswitch();
    }

    private void initDipswitch() {
        dipSwitch = dsf.newDipSwitch("bear", true);
        dipSwitch.subscribe(new WatchableListener() {
            @Override
            public void trigger(Watchable watchable) {
                System.out.println("My Dipswitch is " + (dipSwitch.getBit().get() ? "enabled" : "disabled"));
            }
        });
    }
}
