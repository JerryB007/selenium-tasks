package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver webDriver;
    protected WebDriverWait wait;

    @BeforeClass
    void beforeClass() {
        DriverFactory driverFactory = new DriverFactory();
        webDriver = driverFactory.initDriver();
    }

    @BeforeMethod
    void beforeTest() {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    @AfterClass
    void afterClass() {
        webDriver.quit();
    }

    protected void acceptCookieByClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

}
