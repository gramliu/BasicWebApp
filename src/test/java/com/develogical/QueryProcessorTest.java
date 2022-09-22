package com.develogical;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
    public void knowsTeamName() throws Exception {
        assertThat(queryProcessor.process("what is your name"), containsString("dyno-saur"));
    }

    @Test
    public void isNotCaseSensitive() throws Exception {
        assertThat(queryProcessor.process("shakespeare"), containsString("playwright"));
    }

    @Test
    public void handlesSum() throws Exception {
        assertThat(queryProcessor.process("what is 2013 plus 2018"), containsString(String.valueOf(2013 + 2018)));
        assertThat(queryProcessor.process("what is 2013 + 2018"), containsString(String.valueOf(2013 + 2018)));
    }

    @Test
    public void handlesProduct() throws Exception {
        assertThat(queryProcessor.process("what is 2013 times 2018"), containsString(String.valueOf(2013 * 2018)));
        assertThat(queryProcessor.process("what is 2013 multiplied by 2018"), containsString(String.valueOf(2013 * 2018)));
    }

    @Test
    public void handlesLargest() throws Exception {
        assertThat(queryProcessor.process("which of the following is the largest: 3, 1, 4, 1234, 0"), containsString("1234"));
    }

    @Test
    public void handlesSmallest() throws Exception {
        assertThat(queryProcessor.process("which of the following is the smallest: 3, 1, 4, 1234, 0"), containsString("0"));
    }

}
