package com.otus.pages;

import com.otus.annotations.UrlPrefix;
import org.openqa.selenium.WebDriver;

@UrlPrefix("/")
public class CoursePage extends AbsBasePage<CoursePage> {

    public CoursePage(WebDriver driver) {
        super(driver);
    }

}
