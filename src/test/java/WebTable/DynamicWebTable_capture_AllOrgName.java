package WebTable;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DynamicWebTable_capture_AllOrgName {
	@Test
	public void captureOrganisationNames() {
		
		//Launching Browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Login to application
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		
		//Navigating to Organizations module
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		
		//Fetching all organizations name available
		List<WebElement> orgNames = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a[@title='Organizations']"));
		
		for(WebElement orgName:orgNames) {
			String name = orgName.getText();
			System.out.println(name);
		}
		
		
	}
}
