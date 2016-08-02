package com.tdd;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by tgreen on 7/31/16.
 */
public class RefactoredApiWorkerTest {

    RefactoredApiWorker refactoredApiWorker = new RefactoredApiWorker();

    @Rule //must be public
    public ExpectedException thrown = ExpectedException.none();


    // Testing Expected Exceptions
    @Test
    public void getBookFromS3orFromDBorFromFileSystem_nullRetrieval_throwsIllegalArgumentException(){
        //Violates Arrange Act Assert.
        thrown.expectMessage("Retrevial Method Cannot be Null");
        thrown.expect(IllegalArgumentException.class);

        refactoredApiWorker.getBookFromS3orFromDBorFromFileSystem(null, null, null, null);
    }

    @Test
    public void getBookFromS3orFromDBorFromFileSystem_s3FileNameEmpty_throwsIllegalArgumentException(){
        //Violates Arrange Act Assert.
        thrown.expectMessage("Filename Cannot be Null");
        thrown.expect(IllegalArgumentException.class);

        refactoredApiWorker.getBookFromS3orFromDBorFromFileSystem("s3", null, null, null);
    }

    @Test
    public void getBookFromS3orFromDBorFromFileSystem_dbBookIdIsEmpty_throwsIllegalArgumentException(){
        //Violates Arrange Act Assert.
        thrown.expectMessage("for DB you must supply a book ID and a DB Name");
        thrown.expect(IllegalArgumentException.class);

        refactoredApiWorker.getBookFromS3orFromDBorFromFileSystem("db", null, null, null);
    }

    @Test
    public void getBookFromS3orFromDBorFromFileSystem_invalidMethod_throwsIllegalArgumentException(){
        //Violates Arrange Act Assert.
        thrown.expectMessage("Retrieval Method Not Supported");
        thrown.expect(IllegalArgumentException.class);

        refactoredApiWorker.getBookFromS3orFromDBorFromFileSystem("invalid", null, null, null);
    }

    // Characterization Test
    @Test
    public void getBookFromS3orFromDBorFromFileSystem_s3WithFileName_bookReturned(){
        String actualResponse =
                refactoredApiWorker.getBookFromS3orFromDBorFromFileSystem("s3", null, "Book.txt", null);

        assertThat(actualResponse, is("Book Returned from s3"));
    }

    @Test
    public void getBookFromS3orFromDBorFromFileSystem_dbWithId_bookReturned(){
        String actualResponse =
                refactoredApiWorker.getBookFromS3orFromDBorFromFileSystem("db", "1234", null, "bookDb");

        assertThat(actualResponse, is("Book Returned from DB"));
    }


}