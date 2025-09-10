package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
<<<<<<< HEAD
	features="src/test/resources/Features/order.feature",
	glue="com.stepDefinitions",
	plugin= {"pretty","html:reports/cucumber-html-report.html"}
    
)
public class TestRunner extends AbstractTestNGCucumberTests {
	

}
=======
	    features = "src/test/resources/Features",
	    glue = "com.stepDefinition",
	    plugin = {"pretty","html:reports/cucumber-html-report.html"}
	)

	public class TestRunner extends AbstractTestNGCucumberTests { }

>>>>>>> 825a72480ba566cd572f1affd3f4e98dc04ca366
