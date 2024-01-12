package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {
    protected WebDriver webDriver;

    @BeforeClass
    void beforeClass() {
        DriverFactory driverFactory = new DriverFactory();
        webDriver = driverFactory.initDriver();
    }

    @AfterClass
    void afterClass() {
        webDriver.quit();
    }

}
