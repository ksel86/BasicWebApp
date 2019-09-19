package com.develogical;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class QueryProcessorTest {

    QueryProcessor queryProcessor = new QueryProcessor();

    @Test
    public void returnsEmptyStringIfCannotProcessQuery() throws Exception {
        assertThat(queryProcessor.process("test"), is(""));
    }

    @Test
    public void knowsAboutShakespeare() throws Exception {
        assertThat(queryProcessor.process("Shakespeare"), containsString("playwright"));
    }

    @Test
    public void isNotCaseSensitive() throws Exception {
        assertThat(queryProcessor.process("shakespeare"), containsString("playwright"));
    }

    @Test
    public void isScycloAfraid() throws Exception {
        assertThat(queryProcessor.process("gunko"), containsString("afraid"));
    }

    @Test
    public void isScycloBrave() throws Exception {
        assertThat(queryProcessor.process("gunko"), not(containsString("brave")));
    }

    @Test
    public void isNameReturned() throws Exception {
        assertThat(queryProcessor.process("c04c44b0: what is your name"), Matchers.is("TwoBakers"));
    }

    @Test
    public void isPlusQuery() throws Exception {
        assertThat(queryProcessor.process("324dfsf3: what is 20 plus 30"), is("50"));
    }

//    @Test
//    public void getLargest() throws Exception {
//        assertThat(queryProcessor.process("25296ca0: which of the following numbers is the largest: 15, 106, 932, 29"),
//                is("932"));
//    }
}
