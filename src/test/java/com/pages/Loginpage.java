package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectrepository.Locators;
import com.parameters.Reporter;
import com.setup.Base;

public class Loginpage {

    WebDriver driver;
    WebDriverWait wait;
    ExtentTest extTest;

    public Loginpage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.extTest = extTest;
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.loginbutton)).click();
    }

    public boolean enterMobileNumber(String mobile) {
        clickLoginButton();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.mobile)).sendKeys(mobile);
            Reporter.generateReport(driver, extTest, Status.PASS, "Mobile number entered");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter mobile number");
            return false;
        }
    }

    public boolean clickContinueForMobile() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(Locators.continuebutton)).click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Continue for mobile");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Continue");
            return false;
        }
    }

    public boolean enterOtpManually() {
        try {
        	handlePopupIfExists();
            System.out.print("Enter OTP from SMS: ");
            java.util.Scanner sc = new java.util.Scanner(System.in);
            String otp = sc.nextLine();
            sc.close();
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.otpInputs)).sendKeys(otp);
            Reporter.generateReport(driver, extTest, Status.PASS, "OTP entered");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter OTP");
            return false;
        }
    }

    public boolean navigatedpage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='One Way']")));
            Reporter.generateReport(driver, extTest, Status.PASS, "Flight details displayed");
            return true;
        } catch (TimeoutException te) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Navigation failed");
            return false;
        }
    }

    public void invalidNumber(String number) {
        handlePopupIfExists(); // Close any popups first

        // Open login modal
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.loginbutton));
        loginBtn.click();

        // Wait for mobile input to be visible
        WebElement mobileInput = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.mobile));

        // Clear and focus input using JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mobileInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", mobileInput);

        // Try sending keys first
        try {
            mobileInput.sendKeys(number);
        } catch (Exception e) {
            // Fallback: JS typing
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1];", mobileInput, number);
        }

        // Verify the value
        String typedValue = mobileInput.getAttribute("value");
        if (!typedValue.equals(number)) {
            // Force via JS again if still wrong
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1];", mobileInput, number);
        }

        // Click login/submit button with safe JS fallback
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.loginbutton));
        try {
            submitBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
        }
    }


    public void verifyErrorMessage(String expectedMessage) {
        WebElement errorMsg = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[text()='Please enter a valid phone number']")));
        Assert.assertTrue(errorMsg.isDisplayed(), "Please enter a valid phone number");
    }

    // ✅ Handle unexpected popups
    public void handlePopupIfExists() {
        try {
            WebElement closeBtn = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.id("closeButton")));
            closeBtn.click();
            new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.invisibilityOf(closeBtn));
        } catch (Exception ignore) {}

        try {
            List<WebElement> iframes = driver.findElements(By.id("wiz-iframe-intent"));
            if (!iframes.isEmpty()) {
                driver.switchTo().frame("wiz-iframe-intent");
                WebElement closeBtn = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(By.id("closeButton")));
                closeBtn.click();
                new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.invisibilityOf(closeBtn));
                driver.switchTo().defaultContent();
            }
        } catch(Exception e) {
            try { driver.switchTo().defaultContent(); } catch(Exception ex) {}
        }
    }
    
}
