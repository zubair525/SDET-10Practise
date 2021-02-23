package CalenderPOP;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GenericLib.ExcelUtility1;

public class Demo {
	@Test(dataProvider = "getData")
	public void fetch(String src, String dest) {
		System.out.println(src+"==="+dest);
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
