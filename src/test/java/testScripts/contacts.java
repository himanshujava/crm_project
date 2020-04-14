package testScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class contacts {

	WebElement element;
	WebDriver driver;
	

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "E:\\ALL Jar files\\chrome driver\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("https://www.crmpro.com/");
		
		driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		driver.findElement(By.name("username")).sendKeys("batchautomation");
		driver.findElement(By.name("password")).sendKeys("Test@12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
	}
	
	
	@Test
	public void addContacts(){
		 
		driver.switchTo().frame("mainpanel");
		WebElement contact = driver.findElement(By.xpath("//a[text()='Contacts']"));
		
		Actions action = new Actions(driver);
		action.moveToElement(contact).build().perform();
		String newContact = driver.findElement(By.xpath("//a[text()='New Contact']")).getText();
		driver.findElement(By.xpath("//a[text()='New Contact']")).click();
		
		Assert.assertEquals("New Contact", newContact);
		System.out.println(newContact);
		
		//driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("abc");
		driver.findElement(By.xpath("//input[@name='surname']")).sendKeys("def");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		
	}
	
	
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	
	
	
	
	
	
	
	
}
