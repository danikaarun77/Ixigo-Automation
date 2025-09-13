package com.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pages.Searchingpage;
import com.setup.Base;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class searchingdefinitions {

    WebDriver driver = Hooks.driver;
    ExtentTest extTest = Hooks.extTest;
    Searchingpage search;
    static String[][] excelData;

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        // Hooks setup already launched the site
        search = new Searchingpage(driver, extTest);

        // Optionally validate URL
        String currentUrl = Base.driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("ixigo.com"),
                "❌ Unexpected homepage URL: " + currentUrl);
    }
    @When("the user selects {string} trip type")
    public void the_user_selects_trip_type(String tripType) {
        if (tripType.equalsIgnoreCase("One Way")) {
            search.selectoneway();
        } else {
            throw new IllegalArgumentException("❌ Only OneWay trip is supported, but got: " + tripType);
        }
    }


    @When("the user enters origin as {string}")
    public void the_user_enters_origin_as(String from) {
    	int row = Hooks.currentrow;
    	Hooks.excelData[row][0] ="Chennai" ;      // from
        search.enterBoardingPlace(from);
    }

    @When("the user enters destination as {string}")
    public void the_user_enters_destination_as(String to) {
    	int row = Hooks.currentrow;
    	to = Hooks.excelData[row][1];  
        search.enterLandingPlace(to);
        search.enteringdate("29-09-2025");
    }

    @When("the user sets travellers as {string} adults, {string} children, {string} infants and class as {string}")
    public void the_user_sets_travellers_and_class(String adults, String children, String infants, String travelClass) {
    	int row = Hooks.currentrow;
    	adults = Hooks.excelData[row][2];    // column "adults"
    	children = Hooks.excelData[row][3];  // column "children"
    	infants = Hooks.excelData[row][4];   // column "infants"
    	travelClass = Hooks.excelData[row][5]; // column "travel_class"
        int a = Integer.parseInt(adults.trim());
        int c = Integer.parseInt(children.trim());
        int i = Integer.parseInt(infants.trim());
        search.setTravellersAndClass(a, c, i, travelClass);
    }

    @When("the user clicks Search")
    public void the_user_clicks_search() {
        search.clickSearch();
    }

    @Then("search results should be displayed")
    public void search_results_should_be_displayed() {
        boolean res = search.areResultsDisplayed();
        Assert.assertTrue(res, "❌ Expected search results to be displayed");
    }
}
