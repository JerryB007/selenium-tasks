package com.gitlab.rmarzec.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebElementUtils {

    public static WebElement findElementSafety(WebElement element, By locator) {
        if (element == null || locator == null) return null;
        try {
            return element.findElement(locator);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        }
    }
}
