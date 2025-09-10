package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
	features="src/test/resources/Features/order.feature",
	glue="com.stepDefinitions",
	plugin= {"pretty","html:reports/cucumber-html-report.html"}
    
)
public class TestRunner extends AbstractTestNGCucumberTests {
	

}
