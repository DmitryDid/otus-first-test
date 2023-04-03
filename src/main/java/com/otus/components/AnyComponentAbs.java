package com.otus.components;

import com.otus.annotations.Component;
import com.otus.pageobject.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AnyComponentAbs<T> extends PageObject {

    {
        this.standardWaiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(getComponentLocator()));
    }

    protected String baseLocator;

    public AnyComponentAbs(WebDriver driver) {
        super(driver);
    }

    private By getComponentLocator() {
        Component component = getClass().getAnnotation(Component.class);
        if (component != null) {
            baseLocator = component.value();
            if (baseLocator.startsWith("/")) {
                return By.xpath(baseLocator);
            }
            return By.cssSelector(baseLocator);
        }
        return null;
    }
}
