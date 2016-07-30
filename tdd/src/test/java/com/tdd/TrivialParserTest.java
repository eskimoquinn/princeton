package com.tdd;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by tgreen on 7/12/16.
 */
public class TrivialParserTest {
//    TrivialParser trivialParser;
//    private List<String> actualStringsList;
//
//    @Before
//    public void setup(){
//        trivialParser = new TrivialParser();
//    }

    TrivialParser trivialParser;

    @Before
    public void setup(){
        trivialParser = new TrivialParser();
    }

    @Test
    public void getListOfString_null_emptyList(){
        //Arrange
        //Act
        List<String> actualList = trivialParser.getListOfString(null);
        //Assert
        assertNotNull("actual list is null" , actualList);
    }

    @Test
    public void getListOfString_oneWord_returnsListWithCorrect(){

        List<String> actualList = trivialParser.getListOfString("testWord");

        assertThat("List should contain testWord", actualList.size(), is(1));
        assertThat(actualList.contains("testWord"), is(true));
    }

    @Test
    public void getListOfString_twoWords_returnsListWithTwoItems(){
        List<String> actualList = trivialParser.getListOfString("one two");

        assertThat("List should contain one two", actualList.size(), is(2));
        assertThat(actualList.contains("one"), is(true));
        assertThat(actualList.contains("two"), is(true));
    }

    @Test
    public void getListOfString_stringWithMultipleSpaces_returnsListWithTwoItems(){
        List<String> actualList = trivialParser.getListOfString("one  two");

        assertThat("List should contain one two", actualList.size(), is(2));
        assertThat(actualList.contains("one"), is(true));
        assertThat(actualList.contains("two"), is(true));


    }
//
//
//    @Test
//    public void methodUnderTest_inputValue_expectedOutput(){
//        //Arrange
//        //Act
//        actualStringsList = trivialParser.getListOfStrings(null);
//        //Assert
//        assertNotNull(actualStringsList);
//    }
//
//    //What should be next test?
//    @Test
//    public void getListOfStrings_oneWord_returnsListWithWord(){
//        //Refactor out trivial parser
//        actualStringsList =
//                trivialParser.getListOfStrings("one");
//
//        assertThat("List should contain the word 'one'",
//                actualStringsList.contains("one"), is(true));
//    }
//
//    //What should be next test?
//    @Test
//    public void getListOfStrings_twoWords_returnsListWithBothWords(){
//        actualStringsList =
//                trivialParser.getListOfStrings("my string");
//
//        assertThat(actualStringsList.
//                containsAll(Arrays.asList("my", "string")), is(true));
//    }
//
//    //What other cases? consecutive spaces, non ascii characters,
//    //numbers, etc
//
//    @Test
//    public void getListOfStrings_twoWordsTwoConsecutiveSpaces_returnsListWithOnlyTwoWords(){
//        actualStringsList =
//                trivialParser.getListOfStrings("two  spaces");
//
//        assertThat(actualStringsList.size(), is(2));
//    }

}
