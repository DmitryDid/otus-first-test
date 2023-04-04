package com.otus.pages;

import com.otus.annotations.UrlPrefix;
import com.otus.pageobject.AbsPageObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public abstract class AbsBasePage<T> extends AbsPageObject {

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    protected static final Locale RU_LOCALE = new Locale("ru");

    public T open() {
        driver.get(getBaseUrl() + getUrlPrefix());
        return (T) page(getClass());
    }

    public T checkHeader(String text) {
        String defaultHeaderText = driver.findElement(By.cssSelector("h1")).getText();
        String alterHeaderText = null;
        try {
            alterHeaderText = driver.findElement(By.cssSelector("div[class=\"course-header2__title\"]")).getText();
        } catch (NoSuchElementException ignore) {

        }
        Assertions.assertTrue(text.equals(defaultHeaderText) || text.equals(alterHeaderText));
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
