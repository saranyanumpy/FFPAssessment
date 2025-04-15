package com.testRunner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import com.utilities.ReadConfig;

@CucumberOptions(features = {"src/test/resources/features/"}, 
				glue = { "com.Hooks", "com.stepDefinition" },
				monochrome = true, 
				dryRun = false, 
				plugin = { "pretty","html:target/index.html","json:target/cucumber-reports/Cucumber.json",
						"html:target/cucumber-reports/index.html",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				 		"rerun:target/rerun.txt"
		})

public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
	
	@BeforeTest
    @Parameters({"browser"})
    public void browserFromTestNG(@Optional String browser) {
		ReadConfig readConfig = new ReadConfig();
		readConfig.setBrowserFromTestNG(browser);
    }
}