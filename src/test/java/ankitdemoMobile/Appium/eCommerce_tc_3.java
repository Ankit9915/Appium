package ankitdemoMobile.Appium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.functions.ExpectedCondition;

public class eCommerce_tc_3  extends BaseTest{

	@Test
	public void fillFormTest() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ankit Singh");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Belgium']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(2000);
		// id is same hence wait is required
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	int count =	productPrices.size();
	double sum=0;
	for(int i=0;i<count;i++) {
		String amountString = productPrices.get(i).getText();
		//$160.07
		Double price =getFormattedAmount(amountString);
		sum+=price;
	}
	String totalPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	double  result = getFormattedAmount(totalPrice);
	Assert.assertEquals(sum, result);
	WebElement longPress = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
	longPressAction(longPress);
	driver.findElement(By.id("android:id/button1")).click();
	driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
	driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	}
}
