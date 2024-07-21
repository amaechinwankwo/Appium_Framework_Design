package org.dandytech.PageObjects.IOS;

import org.dandytech.utils.IOSActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class TextViewPage extends IOSActions {

	IOSDriver driver;

	public TextViewPage(IOSDriver driver) // one time driver setting
	{
		super(driver); // super is used to call the parent constructor driver
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Text Entry'`]")
	private WebElement textEntryMenu;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
	private WebElement textEntryField;

	@iOSXCUITFindBy(accessibility = "OK")
	private WebElement OKButton;

	@iOSXCUITFindBy(iOSNsPredicate = "type=='XCUIElementTypeStaticText' AND value=='Confirm / Cancel'")
	private WebElement confirmAlert;

	@iOSXCUITFindBy(iOSNsPredicate = "value BEGINSWITH[c] 'A message' AND type=='XCUIElementTypeStaticText'")
	private WebElement titleEle;

	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
	private WebElement submit;

	public void fillTextLabel(String message) {
		textEntryMenu.click();
		textEntryMenu.sendKeys(message);
		OKButton.click();
	}

	//multi-level inheritance of getConfirmationText()
	// GrandParent(AppiumUtils) -> IOSActions ->TextViewPage
	public String getConfirmationText() {
		confirmAlert.click();
		return titleEle.getText();

	}

	public void submitForm() {
		submit.click();
	}
}
