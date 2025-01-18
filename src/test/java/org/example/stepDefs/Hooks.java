package org.example.stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Hooks {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Before
    public void setupBrowser() throws IOException {
        if (driver.get() == null) {
            ConfigLoader.loadConfig("src/main/resources/config/configFile.properties");
            String browser = ConfigLoader.getProperty("browserType");
            String url = ConfigLoader.getProperty("url");
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
            } else {
                throw new IllegalArgumentException("Unsupported browser type: " + browser);
            }

            driver.get().manage().window().maximize();
            driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get().navigate().to(url);
        }
    }

    @After
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Remove the driver instance for this thread.
        }
    }
}
