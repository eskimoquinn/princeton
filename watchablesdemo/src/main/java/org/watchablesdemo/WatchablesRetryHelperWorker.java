package org.watchablesdemo;

import org.cirrostratus.sequoia.retryhelper.RetryHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by tgreen on 7/30/16.
 */
@Component
public class WatchablesRetryHelperWorker {

    private int retry=1;

    @RetryHelper(name = "eme-service", numberOfRetries = 3, delay = 2000L, exponentialBackoff = true)
    public void retryMethod(){
        System.out.println("Retry attempt: " + retry + " at " + LocalDateTime.now());
        myLongMethod();
    }

    private void myLongMethod(){
        throw new IllegalArgumentException();
    }
}
