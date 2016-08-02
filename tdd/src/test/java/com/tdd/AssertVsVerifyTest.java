package com.tdd;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by tgreen on 7/31/16.
 */
public class AssertVsVerifyTest {

    //SUT
    @InjectMocks
    AssertVsVerify assertVsVerify;

    @Mock
    Dependency dependency;

    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void verifyBehavior(){
        //act
        assertVsVerify.verifyMe();
        verify(dependency, times(2)).doSomething("12345");
        //assertThat(captor.getValue(), is("1234"));
    }

}