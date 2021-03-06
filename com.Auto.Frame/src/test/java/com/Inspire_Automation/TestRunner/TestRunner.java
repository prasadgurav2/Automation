package com.Inspire_Automation.TestRunner;

//import org.junit.runner.RunWith;
//import cucumber.api.junit.Cucumber;
//import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

import org.testng.ITest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Automation.Util.TestBase;

//@RunWith(Cucumber.class)


	@CucumberOptions(
			features = {"./src\\main\\java\\com\\Automation\\Feature"},
			glue={"com.Automation.StepDef"},

			plugin= {"pretty:STDOUT",
					"html:./src\\main\\resources\\Reports\\cucumber-pretty", 
					"json:./src\\main\\resources\\Reports\\Cucumber-json\\cucumber.json",
					"junit:./src\\main\\resources\\Reports\\Junit_Report\\cucumber.xml",
					"com.cucumber.listener.ExtentCucumberFormatter:./src\\main\\resources\\Reports\\Cucumber-extent\\ExtentReport.html"},
			monochrome = true, //display the console output in a proper readable format
			strict = false, //it will check if any step is not defined in step definition file
			dryRun = false //to check the mapping is proper between feature file and step def file
			,tags = {"@E2E"}		
			)
	 

public class TestRunner extends AbstractTestNGCucumberTests implements ITest {

	TestBase testbase = new TestBase();
	private TestNGCucumberRunner tcr;
	private String featureName;

	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		tcr = new TestNGCucumberRunner(this.getClass());
	}

	@BeforeTest
	@Parameters("browserName")
	public void beforetest(String browserName) {
		// TestBase.Parameter_initialization(browserName);
		
	}

	@BeforeMethod
	public void beforeMethod(Object[] params) {
		CucumberFeatureWrapper cucumberFeature = (CucumberFeatureWrapper) params[0];
		featureName = cucumberFeature.getCucumberFeature().getGherkinFeature().getName();
	}

	@Test(groups = "cucumber", description = "Runs CucumberFeature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		tcr.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@Override
	public String getTestName() {
		return featureName;
	}

	@DataProvider(parallel = true)
	public Object[][] features() {
		return tcr.provideFeatures();
	}
	
	

	 @AfterClass(alwaysRun = true) 
	 public void afterClass() {
	 
	 //tcr.finish();
	 
	 }
	
}
	