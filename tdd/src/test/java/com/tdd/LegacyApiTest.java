package com.tdd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tgreen on 7/31/16.
 */
public class LegacyApiTest {

    LegacyApi api;

    private MockMvc mockMvc;

    @Rule //must be public
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup(){
        api = new LegacyApi();
        mockMvc = MockMvcBuilders.standaloneSetup(api).build();
    }

    // Testing Spring MVC and response Values
    @Test
    public void myLegacyMethod_invalidInput_setsResponseToInternalServerError() throws Exception{
        String urlTemplate = "/legacy/legacyCode?bookId=&retrievalMethod=";

        MvcResult result = mockMvc
                .perform(get(urlTemplate))
                .andExpect(status().is(501))
                .andReturn();

        assertThat(result.getResponse().getContentAsString(),
                is("{\"Error\":\"java.lang.IllegalArgumentException\"}"));
    }

    @Test
    public void myLegacyMethod_validInput_correctResponse() throws Exception{
        String urlTemplate = "/legacy/legacyCode?bookId=1234&retrievalMethod=db";

        MvcResult result = mockMvc
                .perform(get(urlTemplate))
                .andExpect(status().is(200))
                .andReturn();

        assertThat(result.getResponse().getContentAsString(),
                is("{\"Book Response\":\"Book Returned from DB\"}"));
    }


    // Testing Expected Exceptions
    @Test
    public void getBookFromS3orFromDBorFromFileSystem_nullRetrieval_throwsIllegalArgumentException(){
        //Violates Arrange Act Assert.
        thrown.expectMessage("Retrevial Method Cannot be Null");
        thrown.expect(IllegalArgumentException.class);

        api.getBookFromS3orFromDBorFromFileSystem(null, null, null, null);
    }

    @Test
    public void getBookFromS3orFromDBorFromFileSystem_s3FileNameEmpty_throwsIllegalArgumentException(){
        //Violates Arrange Act Assert.
        thrown.expectMessage("Filename Cannot be Null");
        thrown.expect(IllegalArgumentException.class);

        api.getBookFromS3orFromDBorFromFileSystem("s3", null, null, null);
    }

    @Test
    public void getBookFromS3orFromDBorFromFileSystem_dbBookIdIsEmpty_throwsIllegalArgumentException(){
        //Violates Arrange Act Assert.
        thrown.expectMessage("for DB you must supply a book ID and a DB Name");
        thrown.expect(IllegalArgumentException.class);

        api.getBookFromS3orFromDBorFromFileSystem("db", null, null, null);
    }

    @Test
    public void getBookFromS3orFromDBorFromFileSystem_invalidMethod_throwsIllegalArgumentException(){
        //Violates Arrange Act Assert.
        thrown.expectMessage("Retrieval Method Not Supported");
        thrown.expect(IllegalArgumentException.class);

        api.getBookFromS3orFromDBorFromFileSystem("invalid", null, null, null);
    }

    // Characterization Test
    @Test
    public void getBookFromS3orFromDBorFromFileSystem_s3WithFileName_bookReturned(){
        String actualResponse =
                api.getBookFromS3orFromDBorFromFileSystem("s3", null, "Book.txt", null);

        assertThat(actualResponse, is("Book Returned from s3"));
    }

    @Test
    public void getBookFromS3orFromDBorFromFileSystem_dbWithId_bookReturned(){
        String actualResponse =
                api.getBookFromS3orFromDBorFromFileSystem("db", "1234", null, "bookDb");

        assertThat(actualResponse, is("Book Returned from DB"));
    }

}
