package com.tdd;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tgreen on 7/31/16.
 */
@RequestMapping( value = "/legacy")
public class LegacyApi {

    @RequestMapping(value = "/legacyCode", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, String> myLegacyMethod(@RequestParam String bookId, @RequestParam String retrievalMethod, HttpServletResponse response){
        Map<String, String> responseValue = new HashMap<String, String>();
        String bookResponse;
        try {
            bookResponse = getBookFromS3orFromDBorFromFileSystem(retrievalMethod, bookId, bookId + ".txt", "booksDB");
        } catch (IllegalArgumentException e){
            response.setStatus(501);
            responseValue.put("Error", e.getClass().getName());
            return responseValue;
        }
        responseValue.put("Book Response", bookResponse);
        return responseValue;
    }

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