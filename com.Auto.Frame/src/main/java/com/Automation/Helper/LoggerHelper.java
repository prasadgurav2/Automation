package com.Automation.Helper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.Automation.Util.TestBase;

public class LoggerHelper extends TestBase {
	
	private static boolean root = false;
	
	public static Logger getLogger(Class<?> clas) {
		if(root)
			return Logger.getLogger(clas);
		
		PropertyConfigurator.configure(prop.getProperty("log4j"));
		root = true;
		return Logger.getLogger(clas);
	}

}
