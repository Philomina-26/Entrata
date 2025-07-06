package utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Exceldata {
	
	public static Object[][] getTestDataFromExcel(String sheetName)
	{
		
		String filename = "./Excel/"+Constants.ExcelFileName+".xlsx";	
		File excelfile = new File(filename);
		XSSFWorkbook workbook = null;
		
		try {
			FileInputStream fis = new FileInputStream(excelfile);
			workbook = new XSSFWorkbook(fis);
			
		} 
		catch (Throwable e) {
		
			e.printStackTrace();
		}
			
		 
		 
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();	
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0;i<rows;i++)
		{
		XSSFRow row = sheet.getRow(i+1);
		
		for(int j=0;j<cols;j++) {
			XSSFCell cell = row.getCell(j);
			
			CellType cellType = cell.getCellType();
			switch(cellType)
			{
			case STRING:
				data[i][j] = cell.getStringCellValue();
				break;
			case NUMERIC:
				data[i][j] = Integer.toString((int)cell.getNumericCellValue());
				break;
			case BOOLEAN:
				data[i][j] = cell.getBooleanCellValue();
				break;
			default:
				break;
			}
		}
		}
		
		return data;
	}

}
