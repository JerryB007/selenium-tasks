package com.gitlab.rmarzec.task;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Task3Test extends BaseTest {
    public static final String GOOGLE_PAGE_URL = "https://www.google.com/";
    private static final String W3_PAGE_URL = "https://www.w3schools.com/tags/tag_select.asp";

    private WebDriverWait wait;

    @BeforeMethod
    void beforeTest() {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
    }

    @Test
    public void givenHappyShotPage_whenTryItYoutself_thenOK() {
        webDriver.get(GOOGLE_PAGE_URL);
        acceptCookieByButtonClick(By.id("L2AGLb"));

        WebElement searchBox = webDriver.findElement(By.id("APjFqb"));
        WebElement luckyButton = webDriver.findElement(By.name("btnI"));

        final By w3SchoolAcceptCookieBtn = By.id("accept-choices");
        searchBox.sendKeys("HTML select tag - W3Schools");
        luckyButton.submit();

        try {
            wait.until(ExpectedConditions.urlContains("tag_select.asp"));
        } catch (TimeoutException e) {
            String currentUrl = webDriver.getCurrentUrl();
            if (!W3_PAGE_URL.equals(currentUrl)) {
                System.out.println(MessageFormat.format("Expected URL ''{0}'' not equals currentUrl: ''{1}''", W3_PAGE_URL, currentUrl));
                webDriver.get(W3_PAGE_URL);
            }
        }

        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(W3_PAGE_URL, currentUrl);

        acceptCookieByButtonClick(w3SchoolAcceptCookieBtn);

        WebElement firstTryIt = webDriver.findElement(By.xpath("//a[@href='tryit.asp?filename=tryhtml_select']"));
        firstTryIt.click();

        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));

        wait.until(ExpectedConditions.urlContains("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select"));

        webDriver.switchTo().frame("iframeResult");

        WebElement headerH1 = webDriver.findElement(By.tagName("h1"));
        System.out.println("headerH1.text: " + headerH1.getText());
        assertEquals("The select element", headerH1.getText());

        WebElement selectCars = webDriver.findElement(By.id("cars"));
        selectCars.sendKeys("opel");

        WebElement optionOpel = selectCars.findElement(By.xpath("//option[@value='opel']"));
        assertTrue(optionOpel.isSelected());
        System.out.println("optionOpel.value: " + optionOpel.getAttribute("value") + "; optionOpel.text: " + optionOpel.getText());
    }

    private void acceptCookieByButtonClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
