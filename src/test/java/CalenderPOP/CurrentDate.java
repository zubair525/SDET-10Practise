package CalenderPOP;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CurrentDate {
	@Test
	public void currentDate() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		Actions act=new Actions(driver);
		act.moveByOffset(10, 10).click().perform();
		driver.findElement(By.xpath("//span[text()='DEPARTURE']")).click();
		Date d=new Date();
		String strDate = d.toString();
		System.out.println(strDate);
		String[] split = strDate.split(" ");
		String day = null,month = null,date = null,year = null;
		for(String data:split) {
			 day = split[0];
			 month=split[1];
			 date=split[2];
			 year=split[5];
		}
		String dateFormat=""+day+" "+month+" "+" "+date+" "+year+"";
		System.out.println(day+"---"+month+"---"+date+"---"+year);
		System.out.println(dateFormat);
		driver.findElement(By.xpath("//div[@aria-label='"+day+" "+month+" "+date+" "+year+"']")).click();
	}
}
