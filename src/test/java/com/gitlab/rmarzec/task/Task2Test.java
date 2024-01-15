package com.gitlab.rmarzec.task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class Task2Test extends BaseTest {
    private static final String WIKI_PAGE_URL = "https://pl.wikipedia.org/wiki/Wiki";

    @Test
    void givenWikiPage_whenGetLanguageList_thenListSizeCorrect() {
        webDriver.get(WIKI_PAGE_URL);

        final List<WebElement> langList = webDriver.findElements(By.cssSelector(".interlanguage-link"));
        assertEquals(langList.size(), 143);

        for (WebElement lang : langList) {
            final WebElement anchor = lang.findElement(By.tagName("a"));
            final WebElement span = anchor.findElement(By.tagName("span"));
            final String langName = span.getAttribute("innerHTML");
            System.out.println(langName.equalsIgnoreCase("english")
                    ? langName + "; url: " + anchor.getAttribute("href")
                    : langName);
        }
    }
}
