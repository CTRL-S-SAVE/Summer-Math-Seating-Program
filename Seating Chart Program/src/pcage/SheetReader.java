package pcage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetReader {

	@SuppressWarnings({ "deprecation" })
	public static void main(String[] args)  {
		File myFile = new File("File Path");
        FileInputStream fis = null;
		try {
			fis = new FileInputStream(myFile);
		} catch (FileNotFoundException e) {
			System.out.println("Problem!");
			e.printStackTrace();
		}

        XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook (fis);
		} catch (IOException e) {
			System.out.println("Problem!");
			e.printStackTrace();
		}
        
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        
        Iterator<Row> rowIterator = mySheet.iterator();
        
        while (rowIterator.hasNext()) {
        	Row row = rowIterator.next();
            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
            	Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                	System.out.print(cell.getStringCellValue() + "\t");
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                	System.out.print(cell.getNumericCellValue() + "\t");
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                	System.out.print(cell.getBooleanCellValue() + "\t");
                    break;
                }
            }
            System.out.println("");
        }

	}
}