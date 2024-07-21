package org.dandytech.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class AppiumUtils {

	public AppiumDriverLocalService service;

	public AppiumDriverLocalService startAppiumServer(String ipAddresss, int port) {
		// start Appium server by telling the IP address and port to start the server
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("//usr//local//lib//node_modules//appium//build/lib//main.js"))
				.withIPAddress(ipAddresss).usingPort(port).build();
		// withIPAddress("127.0.0.1").usingPort(4723).build();

		service.start();
		return service;
	}

	// Format Amount
	public Double getFormatedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}

	public void waitForElementToAppear(WebElement ele, AppiumDriver driver) // AppiumDriver driver, this prevents
																			// constuctor and super call
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));

	}

	// json data for dataProvider
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		// System.getProperty("user.dir")+"//src//test//java//org//dandytech//testData")

		// convert json file content to json string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		// String to hashMap Databind, add Jackson databind dependency on pom.xml
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}

}
