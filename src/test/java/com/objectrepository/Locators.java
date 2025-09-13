package com.objectrepository;

import org.openqa.selenium.By;

public class Locators {
	public static By loginbutton=By.xpath("//button[text()='Log in/Sign up']");
    public static By mobile=By.xpath("//input[@placeholder='Enter Mobile Number']");
    public static By continuebutton=By.xpath("//button[text()='Continue']");
    public static By otpInputs=By.xpath("//input[@type='tel' or @inputmode='numeric']");
    public static By verify = By.xpath("//*[text()='Verify' or normalize-space()='Verify']");
    public static By flight = By.xpath("//a[@href='/flights']");
    public static By oneway = By.xpath("//button[text()='One Way']");	
    public static By from = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div");

    public static By click_from = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div/div/div[2]/input");

    public static By to = By.xpath("(//div[contains(@class,'autocompleter')])[2]");

    public static By click_to = By.xpath("(//input[contains(@class,'outline-none') and contains(@class,'text-primary')])[2]");
    public static By searchButton = By.xpath("//button[normalize-space()='Search']");

    public static By travellersPanel = By.xpath("//p[text()='1 Traveller,  Economy']");
   // public static By adultsPlusBtn = By.xpath("//button[text()='"+adults+"']");
    public static By childrenPlusBtn = By.xpath("(//button[contains(.,'+')])[2]");
    public static By infantsPlusBtn = By.xpath("(//button[contains(.,'+')])[3]");
    public static By travellersApplyBtn = By.xpath("//button[normalize-space()='Apply' or normalize-space()='Done']");

    public static By travelClassDropdown = By.xpath("//div[contains(@class,'cabin') or contains(.,'Class') or contains(@data-testid,'cabin')]");
    // results container generic
    public static By resultsContainer = By.xpath("//p[text()='Filters']");
    public static By dept=By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[2]/div/div");
    public static By ret=By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div/div");
    
    //selecting date
    public static By monthYearHeader = By.xpath("//div[contains(@class,'DayPicker-Caption')]");
    public static By calendarNextBtn = By.xpath("//span[@aria-label='Next Month']");
    public static By calendarPrevBtn = By.xpath("//span[@aria-label='Previous Month']");
    public static By dayCell(String day) {
        return By.xpath("//div[contains(@class,'DayPicker-Day') and not(contains(@class,'disabled'))]//p[text()='" + day + "']");
    }
    public static By dayBlock(String day) {
        return By.xpath("//div[contains(@class,'DayPicker-Day') and not(contains(@class,'disabled'))]//p[text()='" + day + "']/..");
    }
    public static By dayPrice(String day) {
        return By.xpath("//div[contains(@class,'DayPicker-Day') and not(contains(@class,'disabled'))]//p[text()='" + day + "']/following-sibling::p");
    }
    public static By yearLabel = By.xpath("//div[contains(@class,'DayPicker-Caption')]");

    
   //----------------------------------------------------
    public static By selectionPageMarker = By.xpath(
    	    "//div[contains(@class,'selection-page')]//p[contains(text(),'Your Flight Selection')]");

    public static By travelClassOption(String cls) {
        String xpath = String.format(
            "//li[normalize-space(.)='%s'] | " +
            "//div[contains(@class,'option') and normalize-space(.)='%s'] | " +
            "//button[normalize-space(.)='%s']",
            cls, cls, cls
        );
        return By.xpath(xpath);
    }
 // Header text "News And Travel Stories"
    public static By storiesHeader = By.xpath("//h2[contains(text(),'News And Travel Stories')]");

    // All story items (anchors with data-testid='listItem')
    public static By storiesItems = By.cssSelector("a[data-testid='listItem']");

    // Story caption text inside each story
    public static By storiesCaption = By.cssSelector("a[data-testid='listItem'] p.body-sm");

    // First story (direct access)
    public static By firstStory = By.xpath("(//a[@data-testid='listItem'])[1]");

}

