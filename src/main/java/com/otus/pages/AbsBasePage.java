package com.otus.pages;

import com.otus.annotations.UrlPrefix;
import com.otus.pageobject.AbsPageObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AbsBasePage<T> extends AbsPageObject {

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    public T open() {
        driver.get(getBaseUrl() + getUrlPrefix());
        return (T) page(getClass());
    }

    public T checkHeader(String text) {
        Assertions.assertEquals(text, driver.findElement(By.cssSelector("h1")).getText());
        return (T) this;
    }

    public T complexClick(WebElement element) {
        standardWaiter.waitForElementVisible(element);
        standardWaiter.waitForElementClickable(element);
        action.moveToElement(element).perform();
        element.click();
        return (T) this;
    }

    private <T> T page(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor(WebDriver.class);
            return clazz.cast(constructor.newInstance(driver));
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException
                 | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getBaseUrl() {
        return StringUtils.stripEnd(System.getProperty("webdriver.base.url"), "/");
    }

    private String getUrlPrefix() {
        UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
        if (urlAnnotation != null) {
            return urlAnnotation.value();
        }
        return "";
    }
}
