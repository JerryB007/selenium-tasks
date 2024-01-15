package com.gitlab.rmarzec.task;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Task3Test extends BaseTest {
    private static final String GOOGLE_PAGE_URL = "https://www.google.com/";
    private static final String W3_PAGE_URL = "https://www.w3schools.com/tags/tag_select.asp";

    @Test
    void givenHappyShotPage_whenTryItYourself_thenOK() {
        webDriver.get(GOOGLE_PAGE_URL);
        acceptCookieByClick(By.id("L2AGLb"));

        final WebElement searchBox = webDriver.findElement(By.id("APjFqb"));
        final WebElement luckyButton = webDriver.findElement(By.name("btnI"));

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

        final String currentUrl = webDriver.getCurrentUrl();
        assertEquals(W3_PAGE_URL, currentUrl);
        acceptCookieByClick(w3SchoolAcceptCookieBtn);

        final WebElement firstTryItButton = webDriver.findElement(By.xpath("//a[@href='tryit.asp?filename=tryhtml_select']"));
        firstTryItButton.click();

        final List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));

        wait.until(ExpectedConditions.urlContains("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iframeResult")));

        webDriver.switchTo().frame("iframeResult");

        final WebElement headerH1 = webDriver.findElement(By.tagName("h1"));
        System.out.println("headerH1.text: " + headerH1.getText());
        assertEquals("The select element", headerH1.getText());

        final WebElement carsComboBox = webDriver.findElement(By.id("cars"));
        carsComboBox.sendKeys("opel");

        final WebElement opelOption = carsComboBox.findElement(By.xpath("//option[@value='opel']"));
        assertTrue(opelOption.isSelected());
        System.out.println("option.value: " + opelOption.getAttribute("value") + "; option.text: " + opelOption.getText());
    }

}
