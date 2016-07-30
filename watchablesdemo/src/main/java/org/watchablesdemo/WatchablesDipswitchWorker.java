package org.watchablesdemo;

import org.cirrostratus.sequoia.dipswitches.DipSwitch;
import org.cirrostratus.sequoia.dipswitches.DipSwitchFactory;
import org.cirrostratus.sequoia.watchable.Watchable;
import org.cirrostratus.sequoia.watchable.WatchableListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by tgreen on 7/30/16.
 */
@Component
public class WatchablesDipswitchWorker {

    DipSwitch dipSwitch;

    @Autowired
    DipSwitchFactory dsf;

    public String getDipswitchString() {
        return (dipSwitch.getBit().toString());
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
