package DataProviderAnnotation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GenericLib.ExcelUtility;

public class DataProviderTest {
	@Test(dataProvider = "getData")
	public void dataProvider(String src, String dest) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		Actions act=new Actions(driver);
		act.moveByOffset(10, 10).click().perform();
		
		//Enter Source
		driver.findElement(By.xpath("//span[text()='From']")).click();
		driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys(src);
		driver.findElement(By.xpath("//div[text()='"+src+"']")).click();
		
		//Enter Destination
		driver.findElement(By.xpath("//span[text()='To']")).click();
		driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys(dest);
		driver.findElement(By.xpath("//div[text()='"+dest+"']")).click();
		
		//Enter the date
		Date d=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("E MMM d YYYY");
		String format = sd.format(d);
		System.out.println(format);
		driver.findElement(By.xpath("//div[@aria-label='"+format+"']")).click();
		
		
		
		//Search Flight for the given source to destination
	
		driver.findElement(By.xpath("//a[text()='Search']")).click();
		
	}
	@DataProvider
	
	  public Object[][] getData() throws Throwable{
		
	  
	  ExcelUtility util=new ExcelUtility();
	  
	  int rowCount = util.getRowCount("MakeMyTrip");
		Object[][]obj=new Object[rowCount][2];

	  
	  for(int i=1;i<rowCount;i++) {
		  for(int j=0;j<2;j++) {
			  
		  
		  obj[i][j]=util.getExcelData("./data/testInput.xlsx", "MakeMyTrip", i, j);
		  obj[i][j]=util.getExcelData("./data/testInput.xlsx", "MakeMyTrip", i, j);
	  }
	  }
		/*
		 * obj[0][0]="BLR"; obj[0][1]="GOI";
		 * 
		 * obj[1][0]="BLR"; obj[1][1]="HYD";
		 * 
		 * obj[2][0]="GOI"; obj[2][1]="CNN";
		 */
	  
	  return obj; }
	 
}
