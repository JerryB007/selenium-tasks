package com.gitlab.rmarzec.task;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Task1Test extends BaseTest {
    public static final String TEST_PAGE_URL = "https://www.onet.pl/";

    @Test
    public void givenOnetPl_whenGetTitle_thenTitleCorrect() {
        webDriver.get(TEST_PAGE_URL);

        final String title = webDriver.getTitle();
        assertTrue(title.startsWith("Onet"));
    }
}
