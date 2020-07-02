package com.Automation.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

 

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.paulhammant.ngwebdriver.NgWebDriver;

 

import cucumber.api.Scenario;

public class TestBase {

	public static WebDriver driver;
	public static NgWebDriver ngDriver;
	public static Properties prop;
	public ExtentTest test;

	ExtentHtmlReporter reporter;

	public static ExtentReports extent;

	public TestBase() {
		try {
			File src = new File("./src\\main\\java\\com\\Automation\\Config\\Config.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
			String log4jconfPath = prop.getProperty("log4j");
			PropertyConfigurator.configure(log4jconfPath);

			// System.out.println("Property class loaded");
			// System.out.println(prop.getProperty("browser"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	  
//	  public void initialization(String portNO, String appURL) throws
//	  InterruptedException, MalformedURLException { if
//	  (portNO.equalsIgnoreCase("4545")) {
//	  System.out.println("FireFox Browser Test Enviournment created");
//	  DesiredCapabilities cap = new DesiredCapabilities();
//	  cap.setBrowserName("firefox"); cap.setPlatform(Platform.WINDOWS);
//	  FirefoxOptions options = new FirefoxOptions(); 
//	  nodeURL = "http://192.168.80.75:4545/wd/hub"; try { driver = new RemoteWebDriver(new
//	  URL(nodeURL), options); } catch (MalformedURLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
//	  
//	  driver.manage().window().maximize(); //
//	  driver.get(prop.getProperty("browser"));
//	  
//	  driver.get(url); driver.manage().deleteAllCookies();
//	  System.out.println("**Cookies Deleted**");
//	  driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,
//	  TimeUnit.SECONDS);
//	  driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,
//	  TimeUnit.SECONDS);
//	  
//	  } else if (portNO.equalsIgnoreCase("4546")) {
//	  System.out.println("Chrome Browser Test Enviournment created");
//	  DesiredCapabilities cap = new DesiredCapabilities();
//	  cap.setBrowserName("chrome"); cap.setPlatform(Platform.WINDOWS);
//	  ChromeOptions options = new ChromeOptions(); nodeURL =
//	  "http://192.168.80.75:4546/wd/hub"; try { driver = new RemoteWebDriver(new
//	  URL(nodeURL), options); } catch (MalformedURLException e) { // TODO  Auto-generated catch block e.printStackTrace(); }
//	  
//	  driver.manage().window().maximize(); //
//	  driver.get(prop.getProperty("browser")); driver.get(url);
//	  driver.manage().deleteAllCookies();
//	  System.out.println("**Cookies Deleted**");
//	  driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,
//	  TimeUnit.SECONDS);
//	  driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,
//	  TimeUnit.SECONDS); } else if (portNO.equalsIgnoreCase("4547")) {
//	  System.out.println("Edge Browser Test Enviournment created");
//	  DesiredCapabilities cap = new DesiredCapabilities();
//	  cap.setBrowserName("edge"); cap.setPlatform(Platform.WINDOWS); EdgeOptions
//	  options = new EdgeOptions(); nodeURL = "http://192.168.80.75:4547/wd/hub";
//	  try { driver = new RemoteWebDriver(new URL(nodeURL), options); } catch
//	  (MalformedURLException e) { // TODO Auto-generated catch block
//	  e.printStackTrace(); }
//	  
//	  driver.manage().window().maximize(); //
//	  driver.get(prop.getProperty("browser")); driver.get(url);
//	  driver.manage().deleteAllCookies();
//	  System.out.println("**Cookies Deleted**");
//	  driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,
//	  TimeUnit.SECONDS);
//	  driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,
//	  TimeUnit.SECONDS); }
//	  
//	  }
	 

	//@BeforeTest
	public static void Parameter_initialization(String browserName) {
		// String browserName = prop.getProperty("browser");
		System.out.println("prop value::   " + browserName);

		if (browserName.equals("Chrome")) {
			System.out.println("This is testing");
			System.setProperty("webdriver.chrome.driver","./src\\main\\java\\Driver\\chromedriver.exe");

			driver = new ChromeDriver();
			JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
			ngDriver = new NgWebDriver(jsDriver);

		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver","./src\\main\\java\\Driver\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}

		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		driver.manage().deleteAllCookies();
		System.out.println("**Cookies Deleted**");
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		System.out.println("prop value::   " + prop.getProperty("browser"));

		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver","./src\\main\\java\\Driver\\chromedriver.exe");
			System.out.println("This is testing");
			driver = new ChromeDriver();
			JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
			ngDriver = new NgWebDriver(jsDriver);

		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "./src\\main\\java\\Driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		driver.manage().deleteAllCookies();
		System.out.println("**Cookies Deleted**");
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

	}

	public void ExtentRepoter() {
		// Create Object of ExtentHtmlReporter and provide the path where you want to
		// generate the report
		// I used (.) in path where represent the current working directory
		reporter = new ExtentHtmlReporter("./Reports/learn_automation1.html");

	}

	public void tearDownDriver(Scenario scenario) throws Exception {
		scenario.write("Finshed Scenario!!");
		if (scenario.isFailed()) {
			System.out.println("Internal blocked Started");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			String TimeStamp = new SimpleDateFormat("yyyyMMDD_HHMMSS").format(Calendar.getInstance().getTime());
			try {

				File destinationPath = new File(System.getProperty("user.dir")+ "\\src\\main\\resources\\Defect_ScreenShots\\" + scenario.getName() + TimeStamp + ".png");
				Files.copy(scrFile, destinationPath);
				Reporter.addScreenCaptureFromPath(destinationPath.toString());

				if (scenario.isFailed()) {
					// This takes a screenshot from the driver at save it to the specified location
					TakesScreenshot ts = (TakesScreenshot) driver;
					byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
					scenario.embed(screenshot, "image/png");
				}

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Exception: " + e);
			}
		}
		System.out.println("Test Enviournment Destroyed");
		System.out.println("********************************************************************");
		driver.manage().deleteAllCookies();
		if (driver == null) {

			return;
		}
		driver.quit();
		driver = null;
		System.out.println("{Driver Closed}");

	}

}
