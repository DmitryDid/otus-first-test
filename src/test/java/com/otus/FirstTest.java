package com.otus;

import com.otus.annotations.Driver;
import com.otus.components.FavouriteCourses;
import com.otus.extentions.UIExtension;
import com.otus.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(UIExtension.class)
public class FirstTest {

    @Driver
    public WebDriver driver;

    @Test
    public void popularCourseCheck() {
        new MainPage(driver).open();
        new FavouriteCourses(driver)
                .clickOnTheEarliest();
        // получить список популярных курсов
        // фильтруем по названию
        // фильтруем по раннему позднему
        // работаем мышью
        // подсвечиваем элемент перед, после убираем подсветку .. переопределить метод клик
    }

    @Test
    public void specializationCheck() {
        new MainPage(driver).open();
    }

    @Test
    public void recommendationCheck() {
        new MainPage(driver).open();
    }
}
