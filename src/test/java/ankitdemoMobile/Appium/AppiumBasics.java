package ankitdemoMobile.Appium;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.DeviceRotation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.android.*;
import com.google.common.collect.ImmutableMap;

public class AppiumBasics extends BaseTest {

//	@Test
	public void WifiSettingNameTest() throws MalformedURLException {
		//xpath, id,accessibiltyId, className, androidUIAutomator
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\'3. Preference dependencies\']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle,"WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("ankit's Wifi");
		driver.findElement(By.id("android:id/button1")).click();
	}
	//@Test
	public void verifyLongPressTest() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Expandable Lists\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"1. Custom Adapter\"]")).click();
		WebElement longPressElement = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
		longPressAction(longPressElement);
		String title=driver.findElement(By.id("android:id/title")).getText();
		Assert.assertEquals(title, "Sample menu");
		Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());
	}
//	@Test
	public void scrollDemoTest() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		//by using UIAutomator ,where to scroll
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
		//by using appium gesture
		scrollToEndAction();
	}
//	@Test
	public void swipeTest() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Gallery\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='1. Photos']")).click();
		WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"),"true");
		//swipe code
		swipeAction(firstImage,"left");
		Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"),"false");
	}
	//@Test
	public void dragAndDropTest() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Drag and Drop\"]")).click();
		WebElement dragElement = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) dragElement).getId(),
			    "endX", 827,
			    "endY", 734
			));
		Assert.assertTrue(driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).isDisplayed());
		String resulText = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
		Assert.assertEquals(resulText, "Dropped!");
	}
//	@Test
	public void assignmentTest() {
		driver.findElement(AppiumBy.accessibilityId("App")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Alert Dialogs\"]")).click();
		driver.findElement(By.id("io.appium.android.apis:id/two_buttons2")).click();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(AppiumBy.accessibilityId("Single choice list")).click();
		driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Satellite']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
		driver.findElement(AppiumBy.accessibilityId("Text Entry dialog")).click();
		driver.findElement(By.id("io.appium.android.apis:id/username_edit")).sendKeys("ankit123");
		driver.findElement(By.id("io.appium.android.apis:id/password_edit")).sendKeys("Ankit786");
		driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
	}
	@SuppressWarnings("deprecation")
	@Test
	public void misscellaneousTest() {
		//AppPackage ,App Activity
		//adb shell dumpsys window | find "mCurrentFocus"-windows command
		Activity activity = new Activity(" io.appium.android.apis","io.appium.android.apis.preference.PreferenceDependencies");
		driver.startActivity(activity);
	//	driver.findElement(AppiumBy.accessibilityId("Preference")).click();
//		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\'3. Preference dependencies\']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		//landscape mode 
		DeviceRotation landscape = new DeviceRotation(0,0,90);
		driver.rotate(landscape);
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle,"WiFi settings");
		//copy
		//copy to clipboard - paste it to clipboard
		driver.setClipboardText("ankit's Wifi");
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.findElement(By.id("android:id/button1")).click();
		//clicking home,back,enter 
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		
		
	}
}
