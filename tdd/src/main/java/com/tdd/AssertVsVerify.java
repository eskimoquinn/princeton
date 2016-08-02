package com.tdd;

/**
 * Created by tgreen on 7/31/16.
 */
public class AssertVsVerify {

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    Dependency dependency;

    public void verifyMe(){
        dependency.doSomething("1234");
        dependency.doSomething("1234");
    }
}
