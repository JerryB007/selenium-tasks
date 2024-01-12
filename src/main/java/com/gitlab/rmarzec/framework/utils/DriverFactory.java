package com.gitlab.rmarzec.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public WebDriver initDriver() {
        // WebDriverManager.getInstance(FirefoxDriver.class).driverVersion("0.34.0").setup();
        WebDriver webDriver = new FirefoxDriver();
        tlDriver.set(webDriver);
        return getDriver();
    }
}
