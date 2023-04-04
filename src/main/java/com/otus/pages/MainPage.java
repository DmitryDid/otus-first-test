package com.otus.pages;

import com.otus.annotations.UrlPrefix;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@UrlPrefix("/")
public class MainPage extends AbsBasePage<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".footer2__container-box a[href=\"/lessons/\"].footer2__link")
    WebElement catalog;

    String agreeButtonLocator = "div[class=\"cookies\"] button";

    public CatalogPage openLessonsCatalog() {
        standardWaiter.waitElement(catalog).click();
        return new CatalogPage(driver);
    }

    public MainPage agreeCookies() {
        WebElement target = standardWaiter.waitElement(By.cssSelector(agreeButtonLocator));
        target.click();

        return this;
    }
}
