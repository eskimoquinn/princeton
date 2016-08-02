package org.watchablesdemo;

import org.cirrostratus.sequoia.retryhelper.RetryHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by tgreen on 7/30/16.
 */
@Component
public class WatchablesRetryHelperWorker {



    @RetryHelper(name = "myRetry", numberOfRetries = 3, delay = 2000L, exponentialBackoff = true)
    public void retryMethod(){
        System.out.println("Retry attempt at: " + LocalDateTime.now());
        myLongMethod();
    }

    private void myLongMethod(){
        throw new IllegalArgumentException();
    }
}
