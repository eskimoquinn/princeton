package com.tdd;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by tgreen on 7/12/16.
 */
public class TrivialParserTest {

    TrivialParser trivialParser;

    @Before
    public void setup(){
       trivialParser = new TrivialParser();
    }

    @Test
    public void getListOfStrings_nullString_returnsNotNullList(){
        //Act
        List<String> actual = trivialParser.getListOfStrings(null);

        //Assert
        assertNotNull(actual);
    }

    @Ignore
    @Test
    public void getListOfStrings_oneWord_returnsListWithWord(){
        List<String> actual = trivialParser.getListOfStrings("one");

        assertThat("Should Return a list of one word", actual.get(0), is("one"));
    }

    @Ignore
    @Test
    public void getListOfStrings_twoWords_returnsListWithTwoWords(){
        List<String> actual = trivialParser.getListOfStrings("bear snake");

        assertThat(actual.get(0), is("bear"));
        assertThat(actual.get(1), is("snake"));
    }

    @Ignore
    @Test
    public void getListOfStrings_twoWordsWithTwoSpacesAsDelimiter_returnsListWithTwoWords(){
        List<String> actual = trivialParser.getListOfStrings("rooster  crow");

        assertThat(actual.get(0), is("rooster"));
        assertThat(actual.get(1), is("crow"));
        assertThat(actual.size(), is(2));
    }

//    TrivialParser trivialParser;
//    List<String> actualStringsList;
//
//    @Before
//    public void setup(){
//        trivialParser = new TrivialParser();
//        actualStringsList = new ArrayList<>();
//    }
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
