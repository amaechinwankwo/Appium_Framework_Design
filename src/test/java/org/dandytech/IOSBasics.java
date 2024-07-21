package org.dandytech;

import org.dandytech.PageObjects.IOS.TextViewPage;
import org.dandytech.TestUtils.IOSBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSBasics extends IOSBaseTest {

	@Test
	public void IOSBasicsTest() throws InterruptedException {
	
		TextViewPage textViewPage = homePage.selectAlertView();
		textViewPage.fillTextLabel("Hello Guys, Happy Coding!!!");
		String actulMessage = textViewPage.getConfirmationText();
		Assert.assertTrue(actulMessage != null && actulMessage.contains("A message"),
				"The element does not contain the expected text.");

		textViewPage.submitForm();

		


	}
}
