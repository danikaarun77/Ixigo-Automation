package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = "com.stepDefinitions",
	    plugin = {
	        "pretty",
	        "html:reports/cucumber-html-report.html",
	        "json:reports/cucumber-report.json"
	    }
	)
public class TestRunner extends AbstractTestNGCucumberTests{

}