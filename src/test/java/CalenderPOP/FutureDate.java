package CalenderPOP;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class FutureDate {
	@Test
	public void futureDate() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		Actions act=new Actions(driver);
		act.moveByOffset(10, 10).click().perform();
		driver.findElement(By.xpath("//span[text()='DEPARTURE']")).click();
		int count=1;
		while(count<12) {
			try {
				driver.findElement(By.xpath("//div[@aria-label='Apr Oct 08 2021']//p[text()='8']")).click();
				break;
			}
			catch (Exception e) {
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
				count++;
			}
		}
		if(count==12) {
			System.out.println("Invalid date");
		}
	}
}
