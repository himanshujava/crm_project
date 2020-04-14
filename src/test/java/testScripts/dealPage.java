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

public class dealPage {

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
	public void createDeal() {
		
		driver.switchTo().frame("mainpanel");
		WebElement mouseOver= driver.findElement(By.xpath("//a[text()='Deals']"));
		 
		Actions action= new Actions(driver);
		action.moveToElement(mouseOver).build().perform();
		
		String newDeals=driver.findElement(By.xpath("//a[text()='New Deal']")).getText();
		
		Assert.assertEquals("New Deal", newDeals);
		System.out.println(newDeals);
		
		driver.findElement(By.xpath("//a[text()='New Deal']")).click();
		
		
		
		///create deal
		
		String text = driver.findElement(By.xpath("//input[@name='sequence']")).getText();
		System.out.println(text);
		driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Deal");
		driver.findElement(By.xpath("//input[@name='client_lookup']")).sendKeys("xYz");
		driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("20");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		System.out.println("new deal created");
	}
	
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
		
}
