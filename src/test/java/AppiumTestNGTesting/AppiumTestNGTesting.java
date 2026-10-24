package AppiumTestNGTesting;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumTestNGTesting {
	AppiumDriver driver;
	@BeforeClass
	public void beforeclass() throws MalformedURLException {
		UiAutomator2Options options=new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setPlatformVersion("14");
		options.setAutomationName("UiAutomator2");
		options.setUdid("RZCT40WK0VR");
		options.setAppPackage("com.swaglabsmobileapp");
		options.setAppActivity("com.swaglabsmobileapp.MainActivity");
		URL url=URI.create("http://127.0.0.1:4723").toURL();
		driver=new AndroidDriver(url,options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void invalidUserName() {
		WebElement username=driver.findElement(AppiumBy.accessibilityId("test-Username"));
		WebElement password=driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"));
		WebElement loginbutton=driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
		username.sendKeys("abc");
		password.sendKeys("secret_sauce");
		loginbutton.click();
		String errormsg=driver.findElement(AppiumBy.xpath("//android.widget.TextView[contains(@text,'Username and password do not match')]")).getText();
		String expectedmsg="Username and password do not match any user in this service.";
		Assert.assertEquals(expectedmsg, errormsg);
	}
	
	@Test
	public void invalidPassword() {
		WebElement username=driver.findElement(AppiumBy.accessibilityId("test-Username"));
		WebElement password=driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"));
		WebElement loginbutton=driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
		username.sendKeys("standard_user");
		password.sendKeys("xyz");
		loginbutton.click();
		String errormsg=driver.findElement(AppiumBy.xpath("//android.widget.TextView[contains(@text,'Username and password do not match')]")).getText();
		String expectedmsg="Username and password do not match any user in this service.";
		Assert.assertEquals(errormsg,expectedmsg);
	}
	
	@Test
	public void successfulLogin() {
		WebElement username=driver.findElement(AppiumBy.accessibilityId("test-Username"));
		WebElement password=driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"));
		WebElement loginbutton=driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
		username.sendKeys("standard_user");
		password.sendKeys("secret_sauce");
		loginbutton.click();
		WebElement element=driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")"));
		String result=element.getAttribute("text");
		Assert.assertEquals(result,"PRODUCTS");
	}	
	
	@AfterClass
	public void afterclass() {
		driver.quit();
	}
}
	