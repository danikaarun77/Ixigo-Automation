package com.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.pages.Stories;
import com.pages.Loginpage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class StoriesDefinitions {

    WebDriver driver = Hooks.driver;
    ExtentTest extTest = Hooks.extTest;
    Stories stories;
    Loginpage loginPage;

    @Given("the user is logged in and on the homepage with stories section")
    public void the_user_is_logged_in_and_on_the_homepage_with_stories_section() {
        loginPage = new Loginpage(driver, extTest);
        Assert.assertTrue(loginPage.navigatedpage(), "User should be on homepage after login");
        stories = new Stories(driver, extTest);
        Assert.assertTrue(stories.isStoriesHeaderVisible(), "Stories header should be visible");
    }

    @When("the user clicks on the first travel story")
    public void the_user_clicks_on_the_first_travel_story() {
        stories.clickFirstStory();
        loginPage.handlePopupIfExists();  

    }

    @When("the user clicks on the travel story with caption {string}")
    public void the_user_clicks_on_the_travel_story_with_caption(String caption) {
        Assert.assertTrue(stories.clickStoryByCaption(caption), "Story not found: " + caption);
        loginPage.handlePopupIfExists();  

    }

    @Then("the travel story should open in a new tab")
    public void the_travel_story_should_open_in_a_new_tab() {
        String storyUrl = stories.switchToStoryTab();
        Assert.assertTrue(storyUrl.contains("ixigo.com"), "Story page did not open correctly: " + storyUrl);
        stories.closeCurrentTabAndSwitchBack();
        loginPage.handlePopupIfExists();  

    }
}
