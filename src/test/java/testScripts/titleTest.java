package testScripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class titleTest {
	
	WebDriver driver;
	WebElement element;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "E:\\ALL Jar files\\chrome driver\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("https://www.crmpro.com/");
		
		List<WebElement> link =driver.findElements(By.xpath("//a"));
		 int linkCount= link.size();
		 System.out.println(linkCount);
		
		driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		driver.findElement(By.name("username")).sendKeys("batchautomation");
		driver.findElement(By.name("password")).sendKeys("Test@12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
	}
	@Test(priority=0)
	public void titletest()  {
		String title=driver.getTitle();
		
		Assert.assertEquals("CRMPRO",title);
		System.out.println(title);
		
	}
	
	@Test(priority=1)
	public void links() {
		driver.switchTo().frame("mainpanel");
		
		List<WebElement> link =driver.findElements(By.xpath("//a"));
		 int linkCount= link.size();
		 System.out.println(linkCount);
	 
		for(int i=0; i<link.size();i++) {
		String Links = link.get(i).getText();
		System.out.println(Links);

		}
	
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
