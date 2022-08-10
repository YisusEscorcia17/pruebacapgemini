package PruebaRapidaCapgemini;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCapgemini {
	
	private WebDriver driver;
	By registerLinkLocator = By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a");
	By registerPageLocator = By.xpath("//img[@src=\"images/mast_register.gif\"]");
	By usernameLocator = By.id("email");
	By passwordLocator = By.name("password");
	By confirmPasswordLocator = By.cssSelector("input[name=\"confirmPassword\"]");
	By RegisterBtnLocator = By.name("submit");
	By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By loginLocator = By.name("submit");
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/test/newtours/");
	}
	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}

	@Test
	public void registerUser() throws InterruptedException {
		driver.findElement(registerLinkLocator).click();
		Thread.sleep(2000);
		if(driver.findElement(registerPageLocator).isDisplayed()) {
			driver.findElement(usernameLocator).sendKeys("luchopor");
			driver.findElement(passwordLocator).sendKeys("jesusE");
			driver.findElement(confirmPasswordLocator).sendKeys("jesusE");
			driver.findElement(RegisterBtnLocator).click();
			Thread.sleep(2000);
		}
		
		else {
			System.out.print("Registro no se encontro");	
		}
		
		List<WebElement> fonts = driver.findElements(By.tagName("font"));
		
		asserEquals("Note: Your user name is luchopor.", fonts.get(5).getText());
		
	}
	private void asserEquals(String string, String text) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void signIn() throws InterruptedException {
		if(driver.findElement(userLocator).isDisplayed()) {
			driver.findElement(userLocator).sendKeys("luchopor");
			driver.findElement(passLocator).sendKeys("jesusE");
			driver.findElement(loginLocator).click();
			Thread.sleep(2000);
			//asserEquals(driver.findElement(by)"
			//List<WebElement> b = driver.findElements(By.tagName("b"));
			//asserEquals("Thank you for Loggin.", b.get(5).getText());
		}
		
		else {
			System.out.print("Registro no se encontro");
		}
	}	
}
