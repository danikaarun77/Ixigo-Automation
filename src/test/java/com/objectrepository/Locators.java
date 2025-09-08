package com.objectrepository;

import org.openqa.selenium.By;

public class Locators {
	public static By loginbutton=By.xpath("//button[text()='Log in/Sign up']");
    public static By mobile=By.xpath("//input[@placeholder='Enter Mobile Number']");
    public static By continuebutton=By.xpath("//button[text()='Continue']");
    public static By otpInputs=By.xpath("//input[@type='tel' or @inputmode='numeric']");
    public static By verify = By.xpath("//*[text()='Verify' or normalize-space()='Verify']");
    		
}