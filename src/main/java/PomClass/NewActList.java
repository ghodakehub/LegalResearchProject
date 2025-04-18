package PomClass;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import UtilityClass.Library;
import generic.BaseLib;

public class NewActList  {

	WebDriver driver;
	ExtentTest test;
	
	@FindBy(xpath = "/html/body/div[3]/main/a[2]/button")
	private WebElement ClickActlistbtn;
	
	@FindBy(xpath = "//*[@id=\"court-tabs\"]/li[3]/a")
	private WebElement ClickonNewActList;
	
	@FindBy(xpath = "//*[@id=\"centralActType\"]")
	private WebElement Selecttype;
	
	 public NewActList (WebDriver driver) {
	        this.driver = driver;
	        
	        PageFactory.initElements(driver, this);
	    }    
	
	
		public void Actlist(WebDriver driver) throws InterruptedException
		{
			 Library.click(driver, ClickActlistbtn, "Click on actlist button");
			 Library.threadSleep(3000);
			 //Library.click(driver, ClickonNewActList, "Click on NewActlist Tab");
		}
		
		public void verifyNewActlsit(WebDriver driver) {
			
			
	            WebElement newActsListButton = driver.findElement(By.linkText("NEW ACTS LIST"));
	            newActsListButton.click();

	            // Wait for the New Acts List page to load
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	            
	             WebElement actlink = driver.findElement(By.xpath("//a[text()='THE BHARATIYA NYAYA SANHITA, 2023']"));
	             Library.threadSleep(2000);
	             
	             if (actlink.isDisplayed()) {
	                 System.out.println("NewActList page open successfully ,Test passed!");
	             } else {
	                 System.out.println("First Link not display error coming showing blank page , Test failed!");
	                 Assert.fail("NewActList It show erro while open it");
	             }
	        
	   		 
			}
		}
	             
	          
	    
	
