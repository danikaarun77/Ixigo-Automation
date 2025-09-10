package com.pages;

<<<<<<< HEAD
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
=======
import org.openqa.selenium.By;
>>>>>>> 825a72480ba566cd572f1affd3f4e98dc04ca366
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
<<<<<<< HEAD

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectrepository.Locators;
import com.parameters.Reporter;

public class Searchingpage {

	WebDriver driver;
	WebDriverWait wait;
	ExtentTest extTest;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Searchingpage(WebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		this.extTest = extTest;
	}

	// Handle popup safely
	private void handlePopupIfExists() {
		try {
			List<WebElement> popups = driver.findElements(By.id("wiz-iframe-intent"));
			if (!popups.isEmpty()) {
				driver.switchTo().frame(popups.get(0));
				driver.findElement(By.id("closeButton")).click();
				driver.switchTo().defaultContent();
				Reporter.generateReport(driver, extTest, Status.INFO, "Closed popup successfully");
			}
		} catch (Exception ignore) {
			// not critical if popup is absent
		}
	}


	public void openFlightsTab() {
		try {
			handlePopupIfExists();
			wait.until(ExpectedConditions.elementToBeClickable(Locators.flight)).click();
			Reporter.generateReport(driver, extTest, Status.PASS, "Opened Flights tab");
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to open Flights tab: " + e.getMessage());
		}
	}

	public void selectRoundTrip() {
		try {
			handlePopupIfExists();
			wait.until(ExpectedConditions.elementToBeClickable(Locators.round)).click();
			Reporter.generateReport(driver, extTest, Status.PASS, "Selected Round Trip");
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL,
					"Round Trip selection failed (may be absent): " + e.getMessage());
		}
	}

	public void enterBoardingPlace(String from) {
		try {

			handlePopupIfExists();
			wait.until(ExpectedConditions.elementToBeClickable(Locators.from)).click();
			driver.findElement(Locators.click_from).sendKeys(from);

			List<WebElement> results = new WebDriverWait(driver, Duration.ofSeconds(15))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By
							.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]")));

			if (!results.isEmpty()) {
				results.get(0).click();
			} else {
				driver.findElement(Locators.click_from).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			}
		} catch (Exception e) {

			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter origin: " + from);

		}
	}

	public void enterLandingPlace(String to) {
		try {

			//handlePopupIfExists();

			wait.until(ExpectedConditions.elementToBeClickable(Locators.to));
			wait.until(ExpectedConditions.elementToBeClickable(Locators.click_to)).sendKeys(to);

			List<WebElement> results = new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
							By.xpath("//span[@class='block truncate' and text()='" + to + "']")));

			if (!results.isEmpty()) {
				results.get(0).click();
			} else {
				driver.findElement(Locators.click_from).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			}
		} catch (Exception e) {

			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter departure: " + to);

		}
	}
	


	public void setTravellersAndClass(int adults, int children, int infants, String travelClass) {
		try {
			handlePopupIfExists();
			wait.until(ExpectedConditions.elementToBeClickable(Locators.travellersPanel)).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='" +adults+ "']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='" +children+ "']"))).click();

			/*// increase adults (default = 1)
			for (int i = 1; i < adults; i++) {
				wait.until(ExpectedConditions.elementToBeClickable(Locators.adultsPlusBtn)).click();
			}

			for (int i = 0; i < children; i++) {
				wait.until(ExpectedConditions.elementToBeClickable(Locators.childrenPlusBtn)).click();
			}

			for (int i = 0; i < infants; i++) {
				wait.until(ExpectedConditions.elementToBeClickable(Locators.infantsPlusBtn)).click();
			}*/

			// select travel class
			try {
				wait.until(ExpectedConditions.elementToBeClickable(Locators.travelClassDropdown)).click();
				By classOption = Locators.travelClassOption(travelClass);
				wait.until(ExpectedConditions.elementToBeClickable(classOption)).click();
			} catch (Exception ex) {
				Reporter.generateReport(driver, extTest, Status.WARNING,
						"Travel class selection skipped: " + ex.getMessage());
			}

			// apply travellers
			try {
				wait.until(ExpectedConditions.elementToBeClickable(Locators.travellersApplyBtn)).click();
			} catch (Exception ex) {
				Reporter.generateReport(driver, extTest, Status.WARNING,
						"Apply button not clicked: " + ex.getMessage());
			}

			Reporter.generateReport(driver, extTest, Status.PASS,
					"Travellers set: A" + adults + " C" + children + " I" + infants + " Class:" + travelClass);
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to set travellers/class: " + e.getMessage());
		}
	}

	public void clickSearch() {
		try {
			handlePopupIfExists();
			wait.until(ExpectedConditions.elementToBeClickable(Locators.searchButton)).click();
			Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Search");
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Search: " + e.getMessage());
		}
	}

	public boolean areResultsDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.resultsContainer));
			Reporter.generateReport(driver, extTest, Status.PASS, "Search results displayed");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Search results not detected: " + e.getMessage());
			return false;
		}
	}
=======
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
>>>>>>> 825a72480ba566cd572f1affd3f4e98dc04ca366
}
