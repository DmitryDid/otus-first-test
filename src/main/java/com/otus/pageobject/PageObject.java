package com.otus.pageobject;

import com.otus.waiters.StandardWaiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class PageObject {

    protected WebDriver driver;
    protected Actions action;
    protected StandardWaiter standardWaiter;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        this.standardWaiter = new StandardWaiter(driver);
    }
}
