package org.dandytech.PageObjects.andriod;

import java.util.List;

import org.dandytech.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogue extends AndroidActions {

	AndroidDriver driver;

	public ProductCatalogue(AndroidDriver driver) // one time driver setting
	{
		super(driver); // super is used to call the parent constructor driver
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart; //List hence findelements
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;

	
	public void addItemToCartByIndex(int index)
	{
		addToCart.get(index).click();
	}
	
	//connect to next page to land by creating and returning its new object
	public CartPage goToCartPage() throws InterruptedException
	{
		cartButton.click();
		Thread.sleep(2000); //wait 2 sec
		
		return new CartPage(driver);
	}
	
	
}
