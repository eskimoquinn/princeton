package org.watchablesdemo;

import org.cirrostratus.sequoia.circuitbreaker.CircuitBreaker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by tgreen on 7/30/16.
 */
@Component
public class WatchablesCircuitBreaker {

    @CircuitBreaker(name = "simpleCB", sleepWindowInMilliseconds = 10000 )
    public String cbMethod(){
        System.out.println("in CB method at: " + LocalDateTime.now());
        longMethod();
        return  "CB";
    }

    @CircuitBreaker(name = "simpleCB", sleepWindowInMilliseconds = 10000 )
    public String cb2Method(){
        System.out.println("in CB method at: " + LocalDateTime.now());
        longMethod();
        return  "CB";
    }

    private void longMethod(){
    }
}
