package org.dandytech;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.dandytech.PageObjects.andriod.CartPage;
import org.dandytech.PageObjects.andriod.ProductCatalogue;
import org.dandytech.TestUtils.AndroidBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;


public class E_Commerce_tc_4_Hybrid extends AndroidBaseTest {

	
	@Test(dataProvider="getData", groups={"Smoke"}) //invoke method to collect data
	public void FillForm(HashMap<String, String> input) throws InterruptedException {
		
		formPage.setName(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));		
		ProductCatalogue productCatalogue  = formPage.submitForm(); //link to new page
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();
		cartPage.waitCartContents();
		cartPage.getProductAndConfirmPrices();
		cartPage.longPress();
		cartPage.confirmTermsAndConditions();
		cartPage.checkOut();

		
		//Hybrid
		//get all the driver contexts, basically two
		
		Set<String> contexts = driver.getContextHandles();
		//enhanced loop
		for(String contextName :contexts)
		{
			System.out.println(contextName); //check & copy the WEBVIEW output to the the next line
		}
		//Switching context from Native to Hybrid(Webview) to perform action on web
		driver.context("WEBVIEW_com.androidsample.generalstore");
	
		driver.findElement(By.name("q")).sendKeys("Dandytech");//chrome test
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK)); //Appium KeyEvent
	
		//switch back to Native app to perform action on Native
		driver.context("NATIVE_APP");
		//continue native test
	
	}
	
	//to parameterize test with set of data, we need to start each test on Home screen using Activity class hence test runs based on number of data set
//	@BeforeMethod
//	public void preSetup()
//	{
//		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
//		driver.startActivity(activity);
//	}
//	
	
	//method to pass data to a test at run time from getJsonData
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//org//dandytech//testData//eCommerce");
		return new Object[][] {{data.get(0)}  };
	}
	
	//{"Daniel Amaechi", "male", "Aruba"}, {"Kate Amaechi", "female", "Aruba"}

}
