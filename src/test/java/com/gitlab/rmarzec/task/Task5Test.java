package com.gitlab.rmarzec.task;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Task5Test extends BaseTest {
    private static final String TEST_PAGE = "https://www.selenium.dev/selenium/web/web-form.html";

    @Test
    void givenTestPage_whenGetMySelect_thenOK() {
        webDriver.get(TEST_PAGE);

        final By selectLocator = By.name("my-select");
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectLocator));

        final WebElement dropdown = webDriver.findElement(selectLocator);
        final WebElement option2 = dropdown.findElement(By.xpath("./option[@value=2]"));
        option2.click();
        assertTrue(option2.isSelected());

        Select dropdownSelect = new Select(webDriver.findElement(selectLocator));
        dropdownSelect.selectByValue("1");
        assertEquals(dropdownSelect.getFirstSelectedOption().getText(), "One");
        dropdownSelect.selectByVisibleText("Three");
        assertEquals(dropdownSelect.getFirstSelectedOption().getAttribute("value"), "3");

    }

    @Test
    void givenTestPage_whenTakeScreenshot_thenOK() throws IOException {
        webDriver.get(TEST_PAGE);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("form-check"), 2));

        final TakesScreenshot screenshot = (TakesScreenshot) webDriver;

        final File destFile = new File(System.getenv("HOME") + "/Pictures/Screenshots/" + System.currentTimeMillis() + ".png");
        FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE), destFile);

        assertTrue(destFile.exists());
    }

    @Test
    void givenTestPage_whenSelectGreen_thenGreenSelected() {
        webDriver.get(TEST_PAGE);

        final By colorBoxLocator = By.name("my-colors");
        wait.until(ExpectedConditions.visibilityOfElementLocated(colorBoxLocator));

        WebElement colorBox = webDriver.findElement(colorBoxLocator);
        System.out.println("selectedColor:" + colorBox.getAttribute("value"));

        // colorBox.sendKeys("#00ff00");
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].setAttribute('value', '#00ff00')", colorBox);

        System.out.println("selectedColor:" + colorBox.getAttribute("value"));
        assertEquals(colorBox.getAttribute("value"), "#00ff00");
    }

}
