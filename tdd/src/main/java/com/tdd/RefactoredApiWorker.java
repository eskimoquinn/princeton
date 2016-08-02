package com.tdd;

import org.springframework.stereotype.Component;

/**
 * Created by tgreen on 7/31/16.
 */
@Component
public class RefactoredApiWorker {

    public String getBookFromS3orFromDBorFromFileSystem(String retrievalMethod, String bookId, String filename, String dbName){
        if (null == retrievalMethod){
            throw new IllegalArgumentException("Retrevial Method Cannot be Null");
        }
        else {
            if (retrievalMethod.equalsIgnoreCase("s3")){
                if (null == filename || filename.isEmpty()){
                    throw new IllegalArgumentException("Filename Cannot be Null");
                }
                else {
                    return "Book Returned from s3";
                }
            }
            if (retrievalMethod.equalsIgnoreCase("db")){
                //Line Coverage Vs Branch Coverage
                if ((null == bookId || bookId.isEmpty()) || (null == dbName || dbName.isEmpty())){
                    throw new IllegalArgumentException("for DB you must supply a book ID and a DB Name");
                }
                else {
                    return "Book Returned from DB";
                }
            }
            else {
                throw new IllegalArgumentException("Retrieval Method Not Supported");
            }
        }
    }
}
