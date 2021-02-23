package WebTable;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import GenericLib.RandomData;

public class CreateOrgWithRandomData {
	
	@Test
	public void createOrg() {
		
		RandomData r=new RandomData();
		// Launching Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String orgName="TestYantra"+r;
		String website="zubair_shariff"+r;
		String expTitle="Accounts&action";
		System.out.println(orgName+"====="+expTitle);
		// Login to application
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		// Navigating to Organizations module
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		
		//Click on + button and create organisation
		driver.findElement(By.xpath("//img[contains(@alt,'Create Organization')]")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@name='website']")).sendKeys(website);
		WebElement addMember = driver.findElement(By.xpath("//img[@title='Select']"));
		addMember.click();
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for(String windows:allWindows) {
		
		if(!mainWindow.equals(allWindows)) {
			driver.switchTo().window(windows);
		}
		}
		String org="E-samplevtiger";
		driver.findElement(By.id("search_txt")).sendKeys(org);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//table/tbody/tr/td/a[contains(text(),'"+org+"')]")).click();
		driver.switchTo().alert().accept();
		driver.switchTo().window(mainWindow);
		WebElement ind = driver.findElement(By.name("industry"));
		ind.click();
		Select s=new Select(ind);
		s.selectByValue("Education");
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		String actSuccess = driver.findElement(By.xpath("//span[contains(text(),'"+orgName+" -  Organization Information')]")).getText();
		//assert.actSuccess.contains(orgName);
	}
}
