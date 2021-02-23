package WebTable;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CheckWhetherExpOrgNameIsAvailableInTable {
	@Test
	public void checkOrgName() {
		// Launching Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Login to application
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		// Navigating to Organizations module
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();

		// Fetching all organizations name available
		List<WebElement> orgNames = driver
				.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a[@title='Organizations']"));
		String expName = "vtiger";
		boolean abc = false;
		for (WebElement orgName : orgNames) {
			String actName = orgName.getText();

			if (expName.equals(actName)) {
				orgName.click();
				abc = true;
				break;
			}
		}
		if (abc) {
			driver.findElement(By.xpath("//input[@name='Delete']")).click();
			driver.switchTo().alert().accept();
			driver.findElement(By.xpath("//a[text()='Organizations']")).click();
			

//				//Deleting the Organization
//				driver.findElement(By.name("Delete")).click();
//				
//				//Handle pop-up
//				driver.switchTo().alert().accept();
//				
//				//Navigate to Organizations module
//				driver.findElement(By.xpath("//a[text()='Organizations']")).click();
//				boolean flag=false;
//				for(WebElement wb:orgNames) {
//					String text = wb.getText();
//					System.out.println(text);
//					if(!expName.equals(text)) {
//						flag=true;
//					}
//				}
//				if(flag) {
//					System.out.println("Expected Organisation Name "+expName+" is no more available");
//				}
//				else {
//					System.out.println("Expected Organisation Name "+expName+" is still available");
//				}

	}
}
}
