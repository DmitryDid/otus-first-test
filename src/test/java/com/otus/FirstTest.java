package com.otus;

import com.otus.annotations.Driver;
import com.otus.data.ConditionData;
import com.otus.extentions.UIExtension;
import com.otus.pages.CatalogPage;
import com.otus.pages.CoursePage;
import com.otus.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;


@ExtendWith(UIExtension.class)
public class FirstTest {

    @Driver
    public WebDriver driver;

    @Test
    public void filterByCourseName() {
        String filter = "Python";

        new MainPage(driver)
                .open()
                .checkHeader("Авторские онлайн‑курсы для профессионалов")
                .openLessonsCatalog();
        new CatalogPage(driver)
                .checkHeader("Каталог")
                .filterCoursesByText(filter)
                .checkCoursesNamesContains(filter);
    }

    @Test
    public void chooseLateJavaCourse() {
        new MainPage(driver)
                .open()
                .agreeCookies()
                .checkHeader("Авторские онлайн‑курсы для профессионалов")
                .openLessonsCatalog();
        new CatalogPage(driver)
                .checkHeader("Каталог")
                .filterCoursesByText("java")
                .agreeCookies()
                .chooseCourseByCondition(ConditionData.LATEST);
        new CoursePage(driver)
                .checkHeader("JavaScript Developer. Professional");
    }

    @Test
    public void chooseAnEarlyJavaCourse() {
        new MainPage(driver)
                .open()
                .agreeCookies()
                .checkHeader("Авторские онлайн‑курсы для профессионалов")
                .openLessonsCatalog();
        new CatalogPage(driver)
                .checkHeader("Каталог")
                .filterCoursesByText("java")
                .agreeCookies()
                .chooseCourseByCondition(ConditionData.EARLIEST);
        new CoursePage(driver)
                .checkHeader("Специализация Java-разработчик");
    }
}
