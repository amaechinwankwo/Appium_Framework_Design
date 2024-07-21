package org.dandytech.PageObjects.IOS;

import org.dandytech.utils.IOSActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends IOSActions {

	IOSDriver driver;

	public HomePage(IOSDriver driver) // one time driver setting
	{
		super(driver); // super is used to call the parent constructor driver
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(accessibility = "Alert Views")
	private WebElement alertViews;
	// driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();

	
	public TextViewPage selectAlertView() {
		alertViews.click();
		return new TextViewPage(driver);
	}
}
