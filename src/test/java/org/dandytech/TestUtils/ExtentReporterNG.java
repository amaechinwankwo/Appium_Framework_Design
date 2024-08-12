package org.dandytech.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	 static ExtentReports extent;
	
	 //static method can be accessed without creating its object
	public static ExtentReports getReporterObject() {

		// Note this two classes in building report : ExtentReports ,
		// ExtentSparkReporter

		String path = System.getProperty("user.dir") + "//reports//index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setReportName("Appium Automation Results");

		reporter.config().setDocumentTitle("Test Results");

		// main class ExtentReports to drive all report execution

		// ExtentReports extent = new ExtentReports();

		// Declare the class variable global to be called/access in another method

		extent = new ExtentReports();

		extent.attachReporter(reporter);

		extent.setSystemInfo("Tester", "Daniel Amaechi");
		
		return extent;
	}

}
