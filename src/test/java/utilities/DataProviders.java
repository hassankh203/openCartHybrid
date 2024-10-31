package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders {

	// DataProvider 1 ---> this is to test the dataProvider below
	@Test(dataProvider = "loginData")
	void tats(String aa, String bb, String cc) throws IOException {
//		System.out.println( aa );
//		System.out.println( bb );
//		System.out.println( cc );
		ExcelUtils xlutil = new ExcelUtils("./testData/Opencart_LoginData.xlsx");
		
		System.out.println(xlutil.getCellData("sheet1", 0, 2));
	}

	@DataProvider(name = "loginData")
	public String[][] getData() throws IOException {
		// String path=".\\testData\\Opencart_LoginData.xlsx";//taking xl file from
		// testData
		String path = "./testData/Opencart_LoginData.xlsx";

		ExcelUtils xlutil = new ExcelUtils(path);// creating an object for XLUtility

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);

		String logindata[][] = new String[totalrows][totalcols];// created for two dimension array which can store the
																// data user and password
		System.out.println(totalrows);// it return the index not total rows : if total is 6 index would be 5
		System.out.println(totalcols);

		for (int i = 1; i <= totalrows; i++) // 1 //read the data from xl storing in two deminsional array
		{
			for (int j = 0; j < totalcols; j++) // 0 i is rows j is col
			{
				logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // 1,0
			}
		}
		return logindata;// returning two dimension array

	}

	// DataProvider 2

	// DataProvider 3

	// DataProvider 4
}
