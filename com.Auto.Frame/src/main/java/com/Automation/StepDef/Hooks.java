package com.Automation.StepDef;

import java.io.File;

import org.junit.AfterClass;

import com.Automation.Util.FileReaderManager;
import com.Automation.Util.TestBase;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	TestBase testbase;

	public Hooks() {

		testbase = new TestBase();
	}
 
	  @Before 
	  public void initialisation() {
	    TestBase.initialization(); }
	 

	@After(order = 2)
	public void Teardown(Scenario scenario) throws Exception {
		testbase.tearDownDriver(scenario);
	}

	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportPath()));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", "Windows 10" + "64 Bit");
		Reporter.setSystemInfo("Selenium", "3.7.0");
		Reporter.setSystemInfo("Maven", "3.5.2");
		Reporter.setSystemInfo("Java Version", "1.8.0_231");
	}

}
