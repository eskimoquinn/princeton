package org.watchablesdemo;

import org.apache.commons.lang.StringUtils;
import org.cirrostratus.sequoia.persistentvariable.PersistentVariable;
import org.cirrostratus.sequoia.persistentvariable.PersistentVariableFactory;
import org.cirrostratus.sequoia.watchable.Watchable;
import org.cirrostratus.sequoia.watchable.WatchableListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by tgreen on 7/30/16.
 */
@Component
public class WatchablesPvWorker {
    private PersistentVariableFactory pvf;

    PersistentVariable rooster = null;

    public PersistentVariable getRooster(){
        return rooster;
    }

    @Autowired
    public void setPvf(PersistentVariableFactory pvf) {
        this.pvf = pvf;
    }

    @PostConstruct
    public void init(){
        PersistentVariable pv = pvf.newPersistentVariable("rooster", "string", "bigRooster");
        if (pv == null || (StringUtils.isBlank(pv.getValue()))) {
            System.out.println("PV not configured");
        }
        else {
            rooster =pv;
            rooster.subscribe(new WatchableListener() {
                @Override
                public void trigger(Watchable watchable) {
                    System.out.println("My PV is : " + pv.getValue());
                }
            });
        }
    }

}
