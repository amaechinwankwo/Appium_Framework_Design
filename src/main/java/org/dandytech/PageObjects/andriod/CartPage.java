package org.dandytech.PageObjects.andriod;

import java.time.Duration;
import java.util.List;

import org.dandytech.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) // one time driver setting
	{
		super(driver); // super is used to call the parent constructor driver
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement cartText;
	
	
	
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//	wait.until(ExpectedConditions.attributeContains(
//			driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

	// to ensure prices amount total
	//List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;
	
	
//	int count = productPrices.size();
//
//	double totalSum = 0;
//	for (int i = 0; i < count; i++) {
//		String amountString = productPrices.get(i).getText();
//		Double price = getFormatedAmount(amountString);
//		totalSum = totalSum + price; // 160.97 + 120 = 280.97
//
//	}

	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement displayedSumEle;
	
	//String displayedSum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	
	
	//Double displayFormatedSum = getFormatedAmount(displayedSum);
	//Assert.assertEquals(totalSum, displayFormatedSum);
	
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement ele;
	//WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
	//longPressAction(ele);
	
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
	private WebElement tac;
	//String tac = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
	//Assert.assertEquals(tac, "Terms Of Conditions");
	
	@AndroidFindBy(id = "android:id/button1")
	private WebElement okButton;
	//driver.findElement(By.id("android:id/button1")).click();
	
	@AndroidFindBy(xpath = "//android.widget.CheckBox[@bounds='[56,1919][1363,2031]']")
	private WebElement checkOut;
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;
	
//	driver.findElement(By.xpath("//android.widget.CheckBox[@bounds='[56,1919][1363,2031]']")).click();
//	driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
//	Thread.sleep(6000);
//	
	
	public void waitCartContents()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(cartText, "text", "Cart"));

	}
	
	public void getProductAndConfirmPrices()
	{
		int count = productPrices.size();

		double totalSum = 0;
		for (int i = 0; i < count; i++) {
			String amountString = productPrices.get(i).getText();
			Double price = getFormatedAmount(amountString);
			totalSum = totalSum + price; // 160.97 + 120 = 280.97

		}
	
		String displayedSum = displayedSumEle.getText();
		Double displayFormatedSum = getFormatedAmount(displayedSum);
		Assert.assertEquals(totalSum, displayFormatedSum);
	}
	
	
	public void longPress()
	{
	longPressAction(ele);
	}
	
	public void confirmTermsAndConditions() {
		 String tacText = tac.getText();
		Assert.assertEquals(tacText, "Terms Of Conditions");
		okButton.click();
		
	}
	
	public void checkOut() throws InterruptedException
	{
		checkOut.click();
		proceedButton.click();
		Thread.sleep(6000);
	}
	
	
}
