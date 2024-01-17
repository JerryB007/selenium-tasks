package com.gitlab.rmarzec.task;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Task1Test extends BaseTest {
    private static final String ONET_PAGE_URL = "https://www.onet.pl/";

    @Test
    void givenOnetPl_whenGetTitle_thenTitleStartsWithOnet() {
        webDriver.get(ONET_PAGE_URL);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("article"), 10));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'cmp-intro_acceptAll')]")));

        final String title = webDriver.getTitle();
        assertTrue(title.startsWith("Onet"));
    }
}
