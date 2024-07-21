package org.dandytech.PageObjects.andriod;

import org.dandytech.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{

	AndroidDriver driver;

	public FormPage(AndroidDriver driver) // one time driver setting
	{
		super(driver); //super is used to call the parent constructor driver
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// 1. Create locators variables

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	// driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Mary
	// Kate");

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;
	// driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryField;
	//driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	//driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();


	// 2. Create locator action methods
	public void setName(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}

	public void setGender(String gender) {
		if (gender.contains("female"))
			femaleOption.click();
		else
			maleOption.click();

	}
	
	
	public void setCountrySelection(String countryName)
	{
		countryField.click();
		scrollText(countryName); //call scroll method
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
		System.out.println(driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).getText());
		
	}
	
	
	//if you are very sure of the next page to land, connect to it
	public  ProductCatalogue submitForm()
	{
		shopButton.click();
		//create a new object of the page to land
		return new ProductCatalogue(driver);
	}
	
	
	
}
