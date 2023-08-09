package ankitdemoMobile.Appium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBrowserTest extends MobileBaseTest {

	@Test
	public void browserTest() {
		driver.get("https://dribbble.com/shots");
		driver.findElement(By.xpath("//button[@class=\"nav-v2-burger\"]")).click();
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)", "");
		String text = driver.findElement(By.cssSelector("a[href=\"/session/new\"]")).getText();
		Assert.assertEquals(text, "Log in");
	}
}
