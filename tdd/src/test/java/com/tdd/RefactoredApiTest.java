package com.tdd;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tgreen on 7/31/16.
 */
public class RefactoredApiTest {

    @InjectMocks
    RefactoredApi api;

    @Mock
    RefactoredApiWorker refactoredApiWorker;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(api).build();
    }

    // Testing Spring MVC and response Values
    @Test
    public void myRefactoredMethod_invalidInput_setsResponseToInternalServerError() throws Exception{
        String urlTemplate = "/refactored/refactoredCode?bookId=&retrievalMethod=";
//        when(refactoredApiWorker
//                .getBookFromS3orFromDBorFromFileSystem(
//                        any(), any(), any(), any()))
//                .thenThrow(new IllegalArgumentException());

        when(refactoredApiWorker
                .getBookFromS3orFromDBorFromFileSystem(
                        any(), any(), any(), any()))
                .thenReturn("myString");

        MvcResult result = mockMvc
                .perform(get(urlTemplate))
                .andExpect(status().is(500))
                .andReturn();

        assertThat(result.getResponse().getContentAsString(),
                is("{\"Error\":\"java.lang.IllegalArgumentException\"}"));
    }

    @Test
    public void myRefactoredMethod_validInput_correctResponse() throws Exception{
        String urlTemplate = "/refactored/refactoredCode?bookId=1234&retrievalMethod=db";
        when(refactoredApiWorker
                .getBookFromS3orFromDBorFromFileSystem(
                        any(), any(), any(), any()))
                .thenReturn("Book Returned from DB");

        MvcResult result = mockMvc
                .perform(get(urlTemplate))
                .andExpect(status().is(200))
                .andReturn();

        assertThat(result.getResponse().getContentAsString(),
                is("{\"Book Response\":\"Book Returned from DB\"}"));
    }

    //Add mocking



}
