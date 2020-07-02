package com.Automation.Config;


import com.Automation.Util.TestBase;

public class ConfigFileReader  extends TestBase   {

	public String getReportPath() {
		
		String ReportPath=prop.getProperty("ReportPath");
		if (ReportPath!=null) return ReportPath;
		else throw new RuntimeException("Report Config Path not specified in the config.properties for the key: ReportPath");
	}

	public String getUserName() {
		return prop.getProperty("Username");
	}

	public String getPassword() {
		return prop.getProperty("Password");
	}

	public String getWebsite() {
		return prop.getProperty("Website");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplcitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("ExplicitWait"));
	}
	
	public String getDbType() {
		return prop.getProperty("DataBase.Type");
	}

	public String getDbConnStr() {
		return prop.getProperty("DtaBase.ConnectionStr");
	}

	public String getBrowser() {
		return prop.getProperty("browser");
		
	}

	
}
