package com.runner;

import org.testng.annotations.DataProvider;

import com.parameters.ExcelReader;
import com.stepDefinitions.Hooks;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/Features",
    glue = "com.stepDefinitions",
    plugin = {"pretty", "html:reports/cucumber-html-report.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        // Read Excel data once
        String[][] excelData = ExcelReader.readData();
        Hooks.excelData = excelData;   // ✅ make available globally

        Object[][] cucumberScenarios = super.scenarios();

        // Expand scenarios per Excel row
        Object[][] finalScenarios = new Object[excelData.length * cucumberScenarios.length][];
        int index = 0;

        for (int i = 0; i < excelData.length; i++) {
            Hooks.currentrow = i;   // ✅ track row index
            for (int j = 0; j < cucumberScenarios.length; j++) {
                finalScenarios[index++] = cucumberScenarios[j];
            }
        }

        return finalScenarios;
    }
}
