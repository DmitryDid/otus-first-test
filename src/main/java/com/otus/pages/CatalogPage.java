package com.otus.pages;

import com.otus.annotations.Component;
import com.otus.data.ConditionData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Component("/catalog/courses?categories=programming")
public class CatalogPage extends AbsBasePage<CatalogPage> {

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[type=\"search\"]")
    WebElement filter;

    String filteredLessonsSelector = "section a[href][class*=\"sc-1pyq5ti-0\"]";
    String startLessonsDateSelector = "div[class*=\"jDZyxI icwxwp-1\"] div";
    String filteredLessonsNamesSelector = " h6 div";
    String lessonsNamesSelector = filteredLessonsSelector + filteredLessonsNamesSelector;
    String agreeButtonSelector = "button[type]";

    public CatalogPage agreeCookies() {
        WebElement element = standardWaiter.waitElement(By.cssSelector(agreeButtonSelector));
        element.click();
        return this;
    }

    public CatalogPage filterCoursesByText(String text) {
        filter.sendKeys(text);

        List<WebElement> namesElements;
        int count = 0;
        do {
            if (count > 20) {
                Assertions.fail("ERROR! Courses after filtering are less than the test suggests.");
            }
            standardWaiter.easySleep(200);
            namesElements = driver.findElements(By.cssSelector(lessonsNamesSelector)).stream()
                    .filter(e -> e.getText().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
            count++;
        } while (namesElements.size() < 1);

        return this;
    }

    public CatalogPage checkCoursesNamesContains(String text) {
        driver.findElements(By.cssSelector(lessonsNamesSelector)).stream()
                .forEach(e -> Assertions.assertTrue(e.getText().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))));
        return this;
    }

    public CoursePage chooseCourseByCondition(ConditionData condition) {
        List<WebElement> coursesElements = standardWaiter.waitElements(By.cssSelector(filteredLessonsSelector));
        Map<LocalDate, WebElement> coursesMap = new HashMap<>();
        LocalDate startDate = null;

        for (WebElement element : coursesElements) {
            try {
                startDate = parseDate(element.findElement(By.cssSelector(startLessonsDateSelector)).getText());
            } catch (NoSuchElementException ignore) {
                continue;
            }
            if (startDate != null)
                coursesMap.put(startDate, element);
        }

        if (condition.equals(ConditionData.LATEST)) {
            startDate = coursesMap.keySet().stream()
                    .reduce((last, current) -> current.isAfter(last) ? current : last)
                    .get();
        }
        if (condition.equals(ConditionData.EARLIEST)) {
            startDate = coursesMap.keySet().stream()
                    .reduce((first, current) -> current.isBefore(first) ? current : first)
                    .get();
        }

        standardWaiter.waitElement(coursesMap.get(startDate)).click();

        return new CoursePage(driver);
    }

    private LocalDate parseDate(String data) {
        data = data.split(" · ")[0].replace(",", "");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", RU_LOCALE);
            return LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}