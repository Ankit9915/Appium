package ankitdemoMobile.Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public AndroidDriver driver;
	public 	AppiumDriverLocalService service;

	@Test
	@BeforeClass
	public void configureAppium() throws MalformedURLException {
		//code to start  Appium server
		 service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\HP\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		        service.start();
		//Android driver
		//appiumCode->appiumServer->mobileDevice
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("AnkitEmulator1");
		options.setChromedriverExecutable("C:\\Users\\HP\\Desktop\\chromedriver.exe"); //version issue
	//	options.setApp("C:\\Users\\HP\\eclipse-workspace\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		options.setApp("C:\\Users\\HP\\eclipse-workspace\\Appium\\src\\test\\java\\resources\\General-Store.apk");
		 driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void longPressAction(WebElement longPressElement) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement)longPressElement).getId(),
				"duration",2000));
	}
	public void scrollToEndAction() {
		boolean canScrollMore;
		do {
		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			));
		}while(canScrollMore);
	}
	public void swipeAction(WebElement firstImage , String direction ) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId",((RemoteWebElement)firstImage).getId(),
			    "direction", direction,
			    "percent", 0.75
			));
	}
	public Double getFormattedAmount(String amount) {
		Double price =Double.parseDouble(amount.substring(1));
		return price;
	}
	@Test
	@AfterClass
	public void tearDown() {
		
	//	driver.quit();;
		service.stop();
	}
}
