package com.otus.pageobject;

import com.otus.waiters.StandardWaiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class AbsPageObject {

    protected WebDriver driver;
    protected Actions action;
    protected StandardWaiter standardWaiter;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        this.standardWaiter = new StandardWaiter(driver);
        PageFactory.initElements(driver, this);
    }
}
