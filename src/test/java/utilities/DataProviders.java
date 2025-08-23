package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	@DataProvider(name="LoginData")
	public Object [][] getData() throws IOException
	{
		String path=".//testData//LoginData_DDT.xlsx";
		
		ExcelUtility excel=new ExcelUtility(path);
		
		int rows=excel.getRowCount("Sheet1");
		int cells=excel.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[rows][cells]; //String 2D array creates same rows & columns as in excel data
		
		for(int r=1;r<=rows;r++) //since 1st row starts header(0th index) we put starting index as 1 here.
		{
			for(int c=0;c<=cells-1;c++)
			{
				logindata[r-1][c]=excel.getCellData("Sheet1", r, c);
			}
		}
		return logindata;
	}

}
