package pcage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetWriter {

	private FileOutputStream fileOut;
	private XSSFWorkbook workbook = new XSSFWorkbook();
	private XSSFSheet sheet;
	private XSSFRow row;
	private int rowNum = 0;
	private int colNum = 0;
	
	public SheetWriter(String filePath) throws FileNotFoundException {
		fileOut = new FileOutputStream(filePath);
	}
	
	public void addSheet(String name) {
		sheet = workbook.createSheet(name);
		rowNum = 0;
	}
	
	public void addRow() {
		row = sheet.createRow(rowNum);
		rowNum++;
		colNum = 0;
	}
	
	public void addCell(String contents) {
		XSSFCell cell = row.createCell(colNum);
		cell.setCellValue(contents);
		colNum++;
	}
	
	public void save() throws IOException {
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	
	public static void main(String[] args) {
		try {
			SheetWriter sw = new SheetWriter("C:\\Users\\Sunshine\\Desktop\\test.xlsx");
			for(int i=0;i<5;i++) {
				sw.addSheet("Sheet "+i);
				for(int j=0;j<5;j++) {
					sw.addRow();
					for(int k=0;k<5;k++) {
						sw.addCell("Cell "+k);
					}
				}
			}
			sw.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
