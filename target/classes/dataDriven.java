package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public ArrayList<String> dataDrivenExcel(String sheetName, String TCName) throws IOException
	{
	ArrayList<String> a = new ArrayList<String>();
	System.out.println(System.getProperty("user.dir"));
	FileInputStream fis = new FileInputStream("C:\\Users\\Lenovo\\Downloads\\dateDriven.xlsx");
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	int sheets = workbook.getNumberOfSheets();
	for(int i=0; i<sheets; i++)
	{
		if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
		{
			XSSFSheet sheet = workbook.getSheetAt(i);		
			
			Iterator <Row> r = sheet.iterator();
			Row firstRow = r.next();
			Iterator <Cell> c = firstRow.cellIterator();
			int k=0; 
			int column =0;
			
			while(c.hasNext()) {
				Cell value = c.next();
				value.getStringCellValue().equalsIgnoreCase("TestCases");
				column = k;
				}
			k++;
			
			while(r.hasNext())
			{
				Row r1 = r.next();
				if(r1.getCell(column).getStringCellValue().equalsIgnoreCase(TCName))
				{
					Iterator <Cell> cell = r1.cellIterator();
					while(cell.hasNext())
					{
						Cell cv = cell.next();
						if(cv.getCellTypeEnum()==CellType.STRING)
							a.add(cv.getStringCellValue());
						else
							a.add(NumberToTextConverter.toText(cv.getNumericCellValue()));
					}
				}
			}
		}
	}
	return a;
	}
	
}
