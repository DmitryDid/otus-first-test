package com.otus.components;

import com.otus.annotations.Component;
import com.otus.pages.Lessons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Component(".lessons")
public class FavouriteCourses extends AnyComponentAbs<FavouriteCourses> {

    @FindBy(css = ".lessons")
    private List<WebElement> lessons;

    public FavouriteCourses(WebDriver driver) {
        super(driver);
    }

    public Lessons clickLessonItem() {
        lessons.get(0).click();

        return new Lessons(driver);
    }

    public Lessons clickOnTheEarliest() {
        return new Lessons(driver);
    }

    public Lessons clickOnTheLatest() {
        return new Lessons(driver);
    }

    public Lessons clickByName(String name) {
        return new Lessons(driver);
    }
}
