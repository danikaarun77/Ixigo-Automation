package com.stepDefinition;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.pages.Searchingpage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class searchingdefinitions {

    WebDriver driver = Hooks.driver;
    ExtentTest extTest = Hooks.extTest;
    Searchingpage search;

    @Given("the user is on the ixigo homepage")
    public void the_user_is_on_the_ixigo_homepage() {
        driver.get("https://www.ixigo.com/");
        System.out.println("✅ Ixigo homepage launched successfully");
    }

    @Given("the user selects the from as {string} and to as {string}")
    public void the_user_selects_the_from_as_and_to_as(String from, String to) {
        search = new Searchingpage(driver, extTest);
        search.clickOneWay();
        search.selectFromTo(from, to);
    }

    @Given("the user selects the departure date")
    public void the_user_selects_the_departure_date() {
        search.selectDeparture();
    }

    @Given("the user clicks the travellers icon and selects travellers and class")
    public void the_user_clicks_the_travellers_icon_and_selects_travellers_and_class() {
        search.selectTravellersAndClass();
    }

    @Then("the user clicks the search button")
    public void the_user_clicks_the_search_button() {
        search.clickSearch();
    }
}
