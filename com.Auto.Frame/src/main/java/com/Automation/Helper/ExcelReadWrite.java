package com.Automation.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Automation.Util.TestBase;

public class ExcelReadWrite {

	public static File src;
	public static FileInputStream fis;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static String ExcelFilePath;
	public static int SheetNum;
	public static WebDriver driver;

	/*
	 * static String filepath =
	 * "D:\\My Workspace\\InspireBetaTesting\\com.Inspire_Automation\\src\\main\\resources\\ExcelFile\\Mapping.xlsx"
	 * ; static String Sheet = "Maping_Output";
	 */
	public ExcelReadWrite(String ExcelFilePath) {

		setExcelPath(ExcelFilePath);

	}

	public static void setExcelPath(String ExcelFilePath) {
		// File src=new File("./testdata/test.xlsx");
		try {
			src = new File(ExcelFilePath);
			fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			System.out.println("Excel workbook Loaded Successfully");
			// load the workbook
		} catch (Exception e) {
			System.out.println("Excel loading Issue:: " + e.getMessage());
		}
	}

	public static String getcelldata(int SheetNum, int row, int column) {
		sheet = wb.getSheetAt(SheetNum);
		System.out.println("Excel Sheet Loaded Successfully : " + sheet);
		String data = sheet.getRow(row).getCell(column).getStringCellValue();
		System.out.println(data);
		return data;
	}

	public static XSSFCell setcelldata(int SheetNum, int getRow, int setCell, String value) throws Exception {
		sheet = wb.getSheetAt(SheetNum);
		XSSFCell data = sheet.getRow(getRow).createCell(setCell);
		data.setCellValue(value);
		System.out.println(data);
		FileOutputStream output = new FileOutputStream(src);
		wb.write(output);
		return data;
	}

	public static void getAllExceldata(int SheetNum) {

		sheet = wb.getSheetAt(SheetNum);

		for (int r = 0; r < sheet.getPhysicalNumberOfRows(); r++) {
			Row row = sheet.getRow(r);
			for (int c = 0; c < sheet.getRow(0).getPhysicalNumberOfCells(); c++) {
				// do stuff to each cell here...

				String cellval = row.getCell(c).toString();
				System.out.print("  " + cellval);
			}
			System.out.println("");
		}
	}

	public static void CompareWebEle2(int SheetNum, String BeforeXpath, String AfterXpath1, String AfterXpath2,	String AfterXpath3) {
		
		System.out.println("SheetNum: " + SheetNum);
		System.out.println("BeforeXpath: "+BeforeXpath);
		System.out.println( "AfterXpath1: "+AfterXpath1 );
		System.out.println("AfterXpath2: "+ AfterXpath2 );
		System.out.println( " AfterXpath3: "+AfterXpath3 );

		
		List<WebElement> WebRowCount = driver.findElements(By.xpath(BeforeXpath));

		System.out.println("WebColumnSize:" + WebRowCount.size());

		for (int j = 0; j < WebRowCount.size(); j++) {
			String cellcount = BeforeXpath + j + AfterXpath1;
			System.out.println("cellcount::" + cellcount);

			sheet = wb.getSheetAt(SheetNum);
			for (int r = 1; r < sheet.getPhysicalNumberOfRows(); r++) {
				Row row = sheet.getRow(r);
				for (int c = 0; c < sheet.getRow(0).getPhysicalNumberOfCells(); c++) {
					String GetCellxpath = BeforeXpath + r + AfterXpath1 + AfterXpath2 + c + AfterXpath3;
					System.out.println("CellXpath:: " + GetCellxpath);

				WebElement xpath = driver.findElement(By.xpath(GetCellxpath));

					System.out.println("XPATH Value:: " + xpath.getText());

					System.out.println("Cell itrate::" + sheet.getRow(0).getPhysicalNumberOfCells());
					String cellval = row.getCell(c).toString().trim();
					if (cellval.contains(" ")) {
						System.out.println("Cell value Balnk");
						break;
					} else {
						System.out.println("Cell Value::" + cellval);
						// System.out.print("WebElement ::"+ xpath.getText());
						String text1 = xpath.getText().replace("expand_less", " ").trim();
						String[] ComponantName = text1.split(" ");
						String text = "";
						a: for (String a : ComponantName) {
							if (a.contains("(")) {
								break a;
							} else {
								text = text + a.trim() + " ";
								// System.out.println("a:: " + a);
							}

						}
						text = text.trim();
						// System.out.println("Text:" + text);

						System.out.println("WebElement::" + text);
						System.out.println(cellval.contentEquals((text)));

						if (cellval.contains(text)) {
							// xpath.click();
							System.out.println(
									"Excel Data : " + cellval + " WebElement: " + xpath.getText() + "  => Matched");
						} else {
							System.out.print("WebEle :: " + text);
							System.out.println("ExcelData :: " + cellval);
							System.out.println("XXXXXXXX Excel data and Webelement Not Matched XXXXXXXXXXX");
							break;

						}
					}
					System.out.println("");
				}
			}
		}

	}

	public static void CompareWebEle(int SheetNum, WebElement xpath) {

		sheet = wb.getSheetAt(SheetNum);

		for (int r = 1; r < sheet.getPhysicalNumberOfRows(); r++) {

			Row row = sheet.getRow(r);

			for (int c = 0; c < sheet.getRow(0).getPhysicalNumberOfCells(); c++) {

				System.out.println("Cell itrate::" + sheet.getRow(0).getPhysicalNumberOfCells());

				String cellval = row.getCell(c).toString().trim();
				if (cellval.contains(" ")) {
					System.out.println("Cell value Balnk");
					break;
				} else {

					System.out.println("Cell Value::" + cellval);

					// System.out.print("WebElement ::"+ xpath.getText());

					String text1 = xpath.getText().replace("expand_less", " ").trim();
					String[] ComponantName = text1.split(" ");
					String text = "";
					a: for (String a : ComponantName) {
						if (a.contains("(")) {
							break a;
						} else {
							text = text + a.trim() + " ";
							// System.out.println("a:: " + a);
						}

					}
					text = text.trim();
					// System.out.println("Text:" + text);

					System.out.println("11WebElement::" + text);
					System.out.println(cellval.contentEquals((text)));

					if (cellval.contains(text)) {
						// xpath.click();
						System.out.println(
								"Excel Data : " + cellval + " WebElement: " + xpath.getText() + "  => Matched");
					} else {
						System.out.print("WebEle :: " + text);
						System.out.println("ExcelData :: " + cellval);
						System.out.println("XXXXXXXX Excel data and Webelement Not Matched XXXXXXXXXXX");
						break;

					}
				}
				System.out.println("");
			}
		}

	}

	public static boolean isElementfound(int SheetNum, WebElement xpath) {

		sheet = wb.getSheetAt(SheetNum);
		boolean Result = false;
		for (int r = 0; r < sheet.getPhysicalNumberOfRows(); r++) {
			Row row = sheet.getRow(r);
			for (int c = 0; c < sheet.getRow(0).getPhysicalNumberOfCells(); c++) {
				String cellval = row.getCell(c).toString();
				if (cellval.contentEquals(xpath.getText()))
					;
				{
					Result = true;
					System.out.println("Excel data matched");
					try {
						setcelldata(SheetNum, r, 5, "Pass");
					} catch (Exception e) {

						System.out.println(e.getMessage());
					}
				}
			}
			System.out.println("");
		}

		return Result;
	}

	public static void main(String[] args) throws Exception {
		// new ExcelReadWrite(filepath);
		// getcelldata(0, 2); setcelldata(0, 1,"Prasad");
		// ExcelReadWrite.getcelldata(0, 1, 1);
		// ExcelReadWrite.getAllExceldata(0);
	}

}
