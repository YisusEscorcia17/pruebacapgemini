package com.screenshot.example;

import static org.junit.Assert.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.text.SimpleAttributeSet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

public class ScreenShotTest {

	WebDriver driver;
	
	
	@Before
	public void setUp() throws Exception { /*dirrecion donde propert esta en esta direccion*/
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat ("dd-MM-yy");
		Date date = new Date ();
		return dateFormat.format(date);
	}
	
	@Rule
	public TestRule Watcher = new TestWatcher() {
		@Override
		protected void failed(Throwable Throwable, Description description) {
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshotFile, new File("error_"+description.getMethodName()+getDate()+".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		protected void finished(Description description) {
			 driver.quit();
		}
	};
	@Test
	public void googleSearchTest() {
		driver.get("https://www.google.com");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.clear();
		searchBox.sendKeys("lucho prueba");
		searchBox.submit();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("esto ocacionara un error", driver.getTitle());
		
	}

}
