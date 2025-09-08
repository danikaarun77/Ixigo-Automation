package com.stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pages.Loginpage;
import com.setup.Base;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;

public class Logindefinitions {

    WebDriver driver = Hooks.driver;
    ExtentTest extTest = Hooks.extTest;
    Loginpage loginPage;  // Page Object

    // ----------------- GIVEN -----------------
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage = new Loginpage(driver, extTest); // initialize page object

        String expectedUrl = "https://www.ixigo.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "User is not on Ixigo login page!");
        System.out.println("✅ The website has been launched successfully");
    }

    // ----------------- POSITIVE FLOW -----------------
    @When("the user enters mobileno as {string}")
    public void the_user_enters_mobileno_as(String mobileNo) {
        boolean isEntered = loginPage.enterMobileNumber(mobileNo);
        Assert.assertTrue(isEntered, "Failed to enter mobile number!");
        loginPage.clickContinueForMobile();
    }

    @When("enters the correct OTP")
    public void enters_the_correct_otp() {
        loginPage.enterOtpManually();
        Base.sleep();  // could replace with explicit wait for OTP confirmation
    }

    // ----------------- STEP 1: Stay on home page after login -----------------
    @Then("the user should see the home page after login")
    public void the_user_should_see_home_page_after_login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ✅ Verify login success — stay on home page
        WebElement heyIxigoer = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Hey')]"))
        );
        Assert.assertTrue(heyIxigoer.isDisplayed(), "❌ 'Hey ixigoer' not visible — login might have failed!");
        System.out.println("✅ User successfully logged in — still on home page");
    }

    // ----------------- STEP 2: Verify booking section -----------------
    @Then("the booking section should be visible")
    public void the_booking_section_should_be_visible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ✅ Verify booking section (One Way button)
        WebElement oneWayBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'One Way')]"))
        );
        Assert.assertTrue(oneWayBtn.isDisplayed(), "❌ Booking section not visible on homepage!");
        System.out.println("✅ Booking section (One Way) is visible on homepage");
    }
}
