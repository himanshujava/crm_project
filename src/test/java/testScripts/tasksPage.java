package testScripts;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class tasksPage {

	
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
	public void createTasksPage() throws Exception {
		
		driver.switchTo().frame("mainpanel");
	    WebElement taskElement = driver.findElement(By.xpath("//a[text()='Tasks']"));
	    
	    Actions action= new Actions(driver);
	    action.moveToElement(taskElement).build().perform();
	    
	    String newTasks = driver.findElement(By.xpath("//a[text()='New Task']")).getText();
	    
		Assert.assertEquals("New Task", newTasks);
		System.out.println(newTasks);
		
		driver.findElement(By.xpath("//a[text()='New Task']")).click();
		
		String taskno =driver.findElement(By.xpath("//input[@type='text']")).getText();
		System.out.println(taskno);
	    
		driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Task");
		driver.findElement(By.xpath("//*[@id=\"taskForm\"]/table/tbody/tr[2]/td[1]/table/tbody/tr[9]/td[2]/input[4]")).click();
		
		Set<String> handler =driver.getWindowHandles();
		Iterator<String>  it =handler.iterator();
		String parentWindowId =it.next();
		System.out.println("parent window id:"+parentWindowId);
		
		String childWindowId = it.next();
		System.out.println("child window id:"+childWindowId);
		
        WebElement date= driver.findElement(By.xpath("//input[@name='deadline']"));
		
		String dateValue= "30-04-2020";
		
		selectDateByJS(driver, date, dateValue);
		
		driver.switchTo().window(childWindowId);
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("deal");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String valuetext =driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td/table/tbody/tr[2]/td/a")).getText();
		System.out.println(valuetext);
		driver.close();
		
		//driver.switchTo().window(parentWindowId);
		//driver.findElement(By.xpath("//*[@id=\"taskForm\"]/table/tbody/tr[1]/td/input")).click();
		
	}
	
	
public static void selectDateByJS(WebDriver driver, WebElement element, String dateValue) {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].setAttribute('value','"+dateValue+"');", element);
		
		
	}
	
	
	
	
	

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
