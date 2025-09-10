package com.setup;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.parameters.PropertyReader;

public class Base {

    public static WebDriver driver;

    // ✅ return WebDriver instead of void
    public static WebDriver launchBrowser() {
        Properties prop = PropertyReader.readProperties();
        String browser = prop.getProperty("Browser");
        String url = prop.getProperty("URL");

        if ("Chrome".equalsIgnoreCase(browser)) {
            ChromeOptions chromeOptions = new ChromeOptions();
            Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromePrefs.put("profile.password_manager_leak_detection", false);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(chromeOptions);
        } else if ("Firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("❌ Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(url);

        return driver; // ✅ important
    }

    public static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
