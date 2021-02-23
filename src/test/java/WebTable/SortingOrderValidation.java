package WebTable;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SortingOrderValidation {
	@Test
	public void checkingSortingOrder() throws InterruptedException {
		// Launching Browser
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				ArrayList<String> al1=new ArrayList<String>();
				ArrayList<String> al2=new ArrayList<String>();

				// Login to application
				driver.get("http://localhost:8888/");
				driver.findElement(By.name("user_name")).sendKeys("admin");
				driver.findElement(By.name("user_password")).sendKeys("admin");
				driver.findElement(By.id("submitButton")).click();

				// Navigating to Organizations module
				driver.findElement(By.xpath("//a[text()='Organizations']")).click();

				// Fetching all organizations name available
				List<WebElement> orgNames =driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a[@title='Organizations']"));
				
				for(WebElement wb:orgNames) {
					String text = wb.getText();
					al1.add(text);
				}
				
				
				Collections.sort(al1);
				for(String s:al1) {
					System.out.println(s);
				}
				System.out.println("=============================");
				System.out.println("=============================");
				driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a[text()='Organization Name']")).click();
				Thread.sleep(3000);
				List<WebElement> orgNames1 =driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a[@title='Organizations']"));
				for(WebElement wb:orgNames1) {
					String text = wb.getText();
					al2.add(text);
				}
				for(String s:al2) {
					System.out.println(s);
				}
				assertEquals(al2, al1);
	}
}
