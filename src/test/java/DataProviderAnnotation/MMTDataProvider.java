package DataProviderAnnotation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GenericLib.CurrentSystemDate;
import GenericLib.ExcelUtility1;

public class MMTDataProvider {
	
	@Test(dataProvider = "getData")
	
	public void dataProviderTest(String src, String dest) throws EncryptedDocumentException, IOException, InterruptedException {
		ExcelUtility1 util=new ExcelUtility1();
		
		System.out.println("Count before increment "+Sample.count);
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		
		//To come out of the focus from pop-up
		Actions act=new Actions(driver);
		act.moveByOffset(10, 10).click().perform();

		
		//Entering the Source
		driver.findElement(By.xpath("//span[text()='From']")).click();

		driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys(src);

		driver.findElement(By.xpath("//div[text()='"+src+"']")).click();

		//Enter the Destination
		driver.findElement(By.xpath("//span[text()='To']")).click();

		driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys(dest);

		driver.findElement(By.xpath("//div[text()='"+dest+"']")).click();

		
		//Select the Departure Date
		
		driver.findElement(By.xpath("//span[text()='DEPARTURE']")).click();
		driver.findElement(By.xpath("//div[@aria-label='Mon Mar 01 2021']")).click();

		
		//Click on Search to find the available fligts for the given source to destination
		driver.findElement(By.xpath("//a[text()='Search']")).click();
		

		
		//Checking the number of Fligts available
		List<WebElement> allFlights = driver.findElements(By.xpath("//span[@class='arln-logo logo1']"));
		WebDriverWait wait=new WebDriverWait(driver, 40);
		//wait.until(ExpectedConditions.visibilityOfAllElements(allFlights));
		Integer noOfFlights = allFlights.size();
		String flights = noOfFlights.toString();
		System.out.println(flights);
		MMTDataProvider mmt=new MMTDataProvider();
		
		util.setValueIntoExcel("./data/testData.xlsx", "bookTicket", Sample.count, 2, flights);
		Sample.count++;
		System.out.println("Count after increment "+Sample.count);
	}
	
	  
	 
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException{
		ExcelUtility1 util=new ExcelUtility1();
		int noOfRows = util.getNoOfRows("./data/testData.xlsx", "bookTicket");
		System.out.println(noOfRows);
		Object [][]obj=new Object[noOfRows][2];
		for(int i=1;i<noOfRows+1;i++) {
			
				obj[i-1][0]=util.fetchDataFromExcel("./data/testData.xlsx", "bookTicket", i, 0);
				obj[i-1][1]=util.fetchDataFromExcel("./data/testData.xlsx", "bookTicket", i, 1);			
		}
		
		return obj;
	}
}
