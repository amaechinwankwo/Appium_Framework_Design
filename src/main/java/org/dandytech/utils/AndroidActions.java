package org.dandytech.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {

	AndroidDriver driver;

	public AndroidActions(AndroidDriver driver) {
		 //super(); //initialize driver from Parent AppiumUtils
		this.driver = driver;

	}

	public void longPressAction(WebElement ele) {

		// perform the long press gesture on the above element ele
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "durration", 2000));

	}

	public void scrollToEnd() throws InterruptedException {
		// use below when you have no prior idea where to stop
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
					ImmutableMap.of("left", 100, // Adjust as per your screen's coordinate
							"top", 100, // Adjust as per your screen's coordinate
							"width", 200, // Adjust as per your screen's coordinate
							"height", 600, // Adjust as per your screen's coordinate
							"direction", "down", "percent", 4.0 // Adjust to ensure a reasonable scroll distance
					));

			Thread.sleep(1000); // Wait for 1 second

		} while (canScrollMore);
	}

	public void scrollText(String text) //scroll method
	{
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))"));
		
	}
	
	// swipe
	public void swipeAction(WebElement ele, String direction) {

		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "direction", direction, "percent", 0.1));

	}
	
	
	
	

}
