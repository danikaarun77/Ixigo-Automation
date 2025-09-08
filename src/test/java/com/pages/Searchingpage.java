package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


import com.aventstack.extentreports.ExtentTest;

public class Searchingpage {

    WebDriver driver;
    ExtentTest test;

    public Searchingpage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    public void clickOneWay() {
        WebElement oneWayBtn = driver.findElement(By.xpath("//button[contains(text(),'One Way')]"));
        oneWayBtn.click();
        System.out.println("✅ One Way selected");
    }

    public void selectFromTo(String from, String to) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // ✅ Click From input box properly
        WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@placeholder='Enter city or airport' and @name='origin']")
        ));
        fromInput.click();
        fromInput.sendKeys(from);
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@class,'city-name') and contains(text(),'" + from + "')]")
        )).click();

        // ✅ Click To input box properly
        WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@placeholder='Enter city or airport' and @name='destination']")
        ));
        toInput.click();
        toInput.sendKeys(to);
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@class,'city-name') and contains(text(),'" + to + "')]")
        )).click();

        System.out.println("✅ Selected From: " + from + " To: " + to);
    }

    public void selectDeparture() {
        WebElement departure = driver.findElement(By.xpath("//p[@data-testid='departureDate']"));
        departure.click();
        // choose fixed date e.g. 10 Sep
        driver.findElement(By.xpath("//td[@aria-label='Mon Sep 10 2025']")).click();
        System.out.println("✅ Departure date selected");
    }

    public void selectTravellersAndClass() {
        WebElement travellerIcon = driver.findElement(By.xpath("//p[@data-testid='pax']"));
        travellerIcon.click();

        // Choose 1 traveller
        driver.findElement(By.xpath("//button[@data-testid='1']")).click();

        // Choose Economy
        driver.findElement(By.xpath("//span[contains(text(),'Economy')]")).click();

        // Click Done
        driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
        System.out.println("✅ Travellers & Class selected");
    }

    public void clickSearch() {
        WebElement searchBtn = driver.findElement(By.xpath("//button[contains(text(),'Search')]"));
        searchBtn.click();
        System.out.println("✅ Search clicked");
    }
}
