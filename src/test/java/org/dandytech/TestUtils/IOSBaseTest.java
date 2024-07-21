package org.dandytech.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.dandytech.PageObjects.IOS.HomePage;
import org.dandytech.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class IOSBaseTest extends AppiumUtils {

	public IOSDriver driver;

	public HomePage homePage; // first to land POF

	@BeforeClass(alwaysRun = true)
	public void ConfigureAppium() throws IOException {
		// Grab the server ip and port form data file in resources
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//org//dandytech//resources//data.properties");
		// check maven command if IP address in in the paramater
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
				: prop.getProperty("ipAddress");

		prop.load(fis);

		//String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");

		// pass the data to startAppium method
		service = startAppiumServer(ipAddress, Integer.parseInt(port)); // called from AppiumUtils

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 14");
		options.setApp(System.getProperty("user.dir") + "//src/test//java//org//dandytech//testApps//UIKitCatalog.app");
		// options.setApp("//Users//user//eclipse-workspace//AppiumTest//src//test//java//resources//TestApp
		// 3.app");

		options.setPlatformVersion("16.2"); // Simulator version
		// Appium - WebDriver Agent -> IOS Apps
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		// instantiate driver
		// driver = new AndroidDriver(service.getUrl(), options);
		driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);

		// waiting time before test failure
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		homePage = new HomePage(driver);// initialize homePage for POM
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
