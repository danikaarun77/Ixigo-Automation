package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import com.aventstack.extentreports.ExtentTest;
import com.setup.Base;

import java.time.Duration;

public class Stories {
    WebDriver driver;
    ExtentTest extTest;

    // Locators
    private By storiesHeader = By.xpath("//h2[contains(text(),'Stories') or contains(text(),'News')]");
    private By newsBackdrops = By.cssSelector("div.news_newsBackDrop__4Zd5o");
    private By storyCaptions = By.cssSelector("p.body-sm");

    public Stories(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.extTest = extTest;
    }

    public boolean isStoriesHeaderVisible() {
        return !driver.findElements(storiesHeader).isEmpty();
    }

    public void clickFirstStory() {
        driver.findElement(newsBackdrops).click();
        extTest.info("Clicked on first story backdrop div");
    }

    public boolean clickStoryByCaption(String caption) {
        List<WebElement> captions = driver.findElements(storyCaptions);
        for (WebElement story : captions) {
            if (story.getText().contains(caption)) {
                story.click();
                extTest.info("Clicked on story with caption: " + caption);
                return true;
            }
        }
        return false;
    }

    public String switchToStoryTab() {
        String originalTab = driver.getWindowHandle();
        
        // Wait for new tab to open
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getWindowHandles().size() > 1);
        
        // Get all tabs
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        
        // Find the new tab (not the original)
        for (String tab : tabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                String url = driver.getCurrentUrl();
                extTest.info("Switched to story tab → " + url);
                return url;
            }
        }
        
        // Fallback if no new tab
        extTest.info("No new tab opened, staying on current tab");
        return driver.getCurrentUrl();
    }


    public void closeCurrentTabAndSwitchBack() {
        List<String> tabs = new java.util.ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            //driver.close();
        	Base.sleep();
            driver.switchTo().window(tabs.get(0));
        }
    }
}
