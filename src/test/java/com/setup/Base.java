package com.setup;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.parameters.PropertyReader;

<<<<<<< HEAD
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
=======


public class Base {

	static final int time = 2000;
	public static WebDriver driver;

	public void launchBrowser() {

		Properties prop = PropertyReader.readProperties();
		if (prop.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			Map<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("profile.password_manager_enabled", false);
			chromePrefs.put("profile.password_manager_leak_detection", false);
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			// Initialize ChromeDriver with the configured options
			driver = new ChromeDriver(chromeOptions);// we have to pass chrome option as arguement
			driver.manage().window().maximize();
			driver.get("https://www.ixigo.com/");
		} else if (prop.getProperty("Browser").equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		driver.get(prop.getProperty("URL"));
	}

	public static void sleep() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
>>>>>>> 825a72480ba566cd572f1affd3f4e98dc04ca366
}
