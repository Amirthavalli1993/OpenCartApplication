package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow ro;
	public static XSSFCell ce;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheet) throws IOException
	{
		fis=new FileInputStream(path);
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet(sheet);
		int noOfRows=sh.getLastRowNum();		
		fis.close();
		wb.close();
		return noOfRows;		
		
	}
	
	public int getCellCount(String sheet, int rownum) throws IOException
	{
		fis=new FileInputStream(path);
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet(sheet);
		int noOfCell=sh.getRow(rownum).getLastCellNum();
		fis.close();
		wb.close();
		return noOfCell;
	}
	
	public String getCellData(String sheet, int rownum, int colnum) throws IOException
	{
		fis=new FileInputStream(path);
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet(sheet);
		ro=sh.getRow(rownum);
		ce=ro.getCell(colnum);	
		
		DataFormatter formatter=new DataFormatter();
		String data;
		
		try
		{
			data=formatter.formatCellValue(ce);
		}
		catch(Exception e)
		{
			data="";
		}
		fis.close();
		wb.close();
		return data;
	}
	
	public void setCellData(String sheet, int rownum, int colnum, String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists()) //if file nit exists, then create new file
		{
			wb=new XSSFWorkbook();
			fos=new FileOutputStream(path);
			wb.write(fos);
		}
		
		fis=new FileInputStream(path);
		wb=new XSSFWorkbook(fis);
		
		if(wb.getSheetIndex(sheet)==-1) //If sheet not exists then create new sheet
				wb.createSheet(sheet);
		sh=wb.getSheet(sheet);
		
		if(sh.getRow(rownum)==null) //If row not exists then create new Roe
			sh.createRow(rownum);
		ro=sh.getRow(rownum);
		
		ce=ro.createCell(colnum);
		ce.setCellValue(data);
		fos=new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
		
	}
}
