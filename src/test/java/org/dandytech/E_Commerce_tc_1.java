package org.dandytech;

import org.dandytech.TestUtils.AndroidBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class E_Commerce_tc_1 extends AndroidBaseTest {
	 
//	@BeforeMethod
//	public void preSetup()
//	{
//		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
//		 ((StartsActivity) driver).startActivity(activity);
//	}
//	
//	
	
	@Test
	public void FillForm_ErrorValidation() throws InterruptedException {
		
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Mary Kate");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Aruba\"))"));
		WebElement country = driver.findElement(By.xpath("//android.widget.TextView[@text='Aruba']"));
		System.out.println(country.getText());
		country.click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		// Handling toast error message
		String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("text");
		AssertJUnit.assertEquals(toastMessage, "Please enter your name");
		
		Thread.sleep(3000);
	
	}
	
	
	@Test
	public void FillForm_PositiveFlow() throws InterruptedException {
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Mary Kate");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Aruba\"))"));
		WebElement country = driver.findElement(By.xpath("//android.widget.TextView[@text='Aruba']"));
		System.out.println(country.getText());
		country.click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		// Handling error message to be true
		AssertJUnit.assertTrue(driver.findElements(By.xpath("//android.widget.Toast[1]")).size()<1);
		
		Thread.sleep(1000);
	
	}

}
