package com.otus.pages;

import org.openqa.selenium.WebDriver;

public class Lessons extends BasePage<Lessons> {

    public Lessons(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

}
