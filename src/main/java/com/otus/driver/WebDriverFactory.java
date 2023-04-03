package com.otus.driver;

import com.otus.exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class WebDriverFactory {

    private final String browserType = System.getProperty("browser");

    public EventFiringWebDriver getDriver() throws DriverTypeNotSupported {
        switch (this.browserType.trim().toLowerCase(Locale.ROOT)) {
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                return new EventFiringWebDriver(new FirefoxDriver());
            }
            case "opera": {
                WebDriverManager.operadriver().setup();
                return new EventFiringWebDriver(new OperaDriver());
            }
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                return new EventFiringWebDriver(new ChromeDriver());
            }
            default:
                throw new DriverTypeNotSupported(this.browserType);
        }
    }
}
