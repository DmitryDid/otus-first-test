package com.otus.pages;

import com.otus.annotations.UrlPrefix;
import com.otus.pageobject.PageObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class BasePage<T> extends PageObject {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public T open() {
        driver.get(getBaseUrl() + getUrlPrefix());
        return (T) page(getClass());
    }

    public <T> T page(Class<T> clazz) {
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
