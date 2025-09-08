package com.stepDefinition;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.setup.Base;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class Hooks extends Base{

	static ExtentSparkReporter spark;
	static ExtentReports extReports;
	static ExtentTest extTest;
	
	@BeforeAll
	public static void ExtentRepotSetup() {
		spark=new ExtentSparkReporter("reports//ExtentReport.html");
		extReports=new ExtentReports();
		extReports.attachReporter(spark);
		
	}
	
	@AfterAll
	public static void afterAll() {
		extReports.flush();
	}
	
	@Before
	public void setup(Scenario scenario) {
		launchBrowser();
		extTest=extReports.createTest(scenario.getName());
		
	}
	
	@After
	public void teardown() {
		Base.sleep();
		//driver.quit();
	}
	

}
