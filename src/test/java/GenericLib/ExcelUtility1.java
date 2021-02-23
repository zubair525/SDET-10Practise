package GenericLib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This class contains the methods to deal with Excel
 * @author zubairahmed
 *
 */
public class ExcelUtility1 {
	
	
	
	/**
	 * This method is used to fetch the data from excelsheet
	 * @param FilePath
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return cellValue
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String fetchDataFromExcel(String FilePath, String sheetName, int rowNum, int cellNum ) throws EncryptedDocumentException, IOException {
		FileInputStream file=new FileInputStream(FilePath);
		Workbook wb = WorkbookFactory.create(file);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		//wb.close();
		return cell.getStringCellValue();		
	}
	
	/**
	 * This method is used to get the count of the rows that are used in Excel
	 * @param FilePath
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getNoOfRows(String FilePath, String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream file=new FileInputStream(FilePath);
		Workbook wb = WorkbookFactory.create(file);
		Sheet sh = wb.getSheet(sheetName);
		//wb.close();
		return sh.getLastRowNum();
	}
	
	/**
	 * This method is used to write the data into Excel
	 * @param filepath
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @param Data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void setValueIntoExcel(String filepath, String sheetName, int rowNum,int cellNum, String Data ) throws EncryptedDocumentException, IOException {
			  FileInputStream file=new FileInputStream(filepath); 
			  Workbook wb = WorkbookFactory.create(file);
			  Sheet sh = wb.getSheet(sheetName);
			  Row row =sh.getRow(rowNum);
			  Cell createCell = row.createCell(cellNum);
			  createCell.setCellValue(Data);
			  FileOutputStream fileO=new FileOutputStream(filepath); wb.write(fileO); wb.close(); 
			  }
}
