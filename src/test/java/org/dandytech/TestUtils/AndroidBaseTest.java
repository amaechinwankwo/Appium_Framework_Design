package org.dandytech.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.dandytech.PageObjects.andriod.FormPage;
import org.dandytech.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AndroidBaseTest extends AppiumUtils{

	public AndroidDriver driver;
	
	public FormPage formPage; //first to land POF
	
	@BeforeClass(alwaysRun=true)
	public void ConfigureAppium() throws IOException {
		//Grab the server ip and port form data file in resources
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//dandytech//resources//data.properties");
		//check maven command if IP address in in the paramater
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		prop.load(fis);
		//String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		
		//pass the data to startAppium method
		service = startAppiumServer(ipAddress, Integer.parseInt(port)); //called from AppiumUtils
		

		// set capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		//options.setDeviceName("Android Device"); //to run test on real device plugged to the PC
		
		//Hybrid App to have knowledge of ChromeDriver, use compactible version 
		options.setChromedriverExecutable(System.getProperty("user.dir")+"//src//test//java//org//dandytech//testApps//chromedriver");
		
		// options.setApp("//Users//user//eclipse-workspace//AppiumTest//src//test//java//resources//ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir")+"//src//test//java//org//dandytech//testApps//General-Store.apk");

		
		// instantiate driver
		//driver = new AndroidDriver(service.getUrl(), options);

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

		// waiting time before test failure
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		formPage = new FormPage(driver);//object for first to land POF
	
	}

	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
