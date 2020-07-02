package com.Automation.Util;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDateReader {

	public static ArrayList<Object> list;

	public static void Read_All_Excel(String filePath, String fileName, String sheetName, int startingRow,
			int Totalcolumn, int TotalRowCount) throws IOException {

		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook ExcelWorkbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			ExcelWorkbook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			ExcelWorkbook = new HSSFWorkbook(inputStream);
		}

		Sheet ExcelSheet = ExcelWorkbook.getSheet(sheetName);
		for (int i = startingRow; i <= TotalRowCount; i++) {
			Row row = ExcelSheet.getRow(i);

			for (int j = 0; j <= Totalcolumn; j++) {

				Cell cell = row.getCell(j);
				switch (cell.getCellType()) {
				case STRING:
					System.out.print(row.getCell(j).getStringCellValue() + "|| ");

					break;
				case NUMERIC:
					System.out.print((int) row.getCell(j).getNumericCellValue() + "|| ");

					break;
				default:
					break;
				}
			}

			System.out.println();
		}

	}

	public static void CompareExcelData(String filePath, String fileName, String sheetName, int startingRow,
			int Totalcolumn, int TotalRowCount) throws IOException {
		int value = 0;
		String var = null;
		String[] comp = { "Anchor", "", "", "UG", "Ea", "5", "0", "5" };
		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook ExcelWorkbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			ExcelWorkbook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			ExcelWorkbook = new HSSFWorkbook(inputStream);
		}

		Sheet ExcelSheet = ExcelWorkbook.getSheet(sheetName);

		/* Row */ for (int r = startingRow; r <= TotalRowCount; r++) {
			Row row = ExcelSheet.getRow(r);
			System.out.println("***********************************************");
			System.out.println("Row#::" + r);

			/* Column */ for (int c = 0; c <= Totalcolumn; c++) {
				System.out.print("| ");
				value = 0;
				/*
				 * data*{ "Anchor","","", "UG", "Ea", "5", "0", "5" };/
				 */
				a: for (int i = 0; i < comp.length; i++) {

					if (value == 1) {
						break a;
					}

					String compwith = comp[i].trim();

					// System.out.println("Data provided by ME:: " + compwith);

					Cell cell = row.getCell(c);

					switch (cell.getCellType()) {

					case STRING:
						// System.out.print(row.getCell(c).getStringCellValue() + "|| ");
						var = row.getCell(c).getStringCellValue().trim();

						if (var.contentEquals(compwith)) {
							System.out.print(row.getCell(c).getStringCellValue());
							// System.out.print("Excel data : " + var);
							value = 1;

							// System.out.println("");
						}
						break;

					case NUMERIC:

						// System.out.print((int) row.getCell(c).getNumericCellValue() + "|| ");
						int var1 = (int) row.getCell(c).getNumericCellValue();
						var = String.valueOf(var1).trim();

						if (var.contentEquals(compwith)) {
							System.out.print((int) row.getCell(c).getNumericCellValue());
							// System.out.println("Excel data : " + var);
							value = 1;
							// System.out.println("");
						}
						break;

					default:
						break;

					}

				}

			}
			System.out.println("");
		}
	}

	public static void Excelcolumn(String filePath, String fileName, String sheetName, int startingRow, int Totalcolumn,int TotalRowCount, String Colval) throws IOException {

		String var = null;

		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook ExcelWorkbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			ExcelWorkbook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			ExcelWorkbook = new HSSFWorkbook(inputStream);
		}

		Sheet ExcelSheet = ExcelWorkbook.getSheet(sheetName);
                     a:
		/* Row */ for (int r = startingRow; r <= TotalRowCount; r++) {

			Row row = ExcelSheet.getRow(r);
			System.out.println("***********************************************");
			System.out.println("Row#::" + r);

			/* Column */ for (int c = 0; c <= Totalcolumn; c++) {

				Cell cell = row.getCell(c);

				switch (cell.getCellType()) {

				case STRING:
					// System.out.print(row.getCell(c).getStringCellValue() + "|| ");
					var = row.getCell(c).getStringCellValue().trim();

					if (var.contentEquals(Colval)) {
						System.out.print(row.getCell(c).getStringCellValue());
						// System.out.print("Excel data : " + var);

						// System.out.println("");
					} else if (Colval.contentEquals("")){
						//System.out.println("");
						System.out.println(var + ": Blank data");
						break;
					}else {
						System.out.println(var + " XXXX No data XXXX");
					}
					
					break a;
				case NUMERIC:

					// System.out.print((int) row.getCell(c).getNumericCellValue() + "|| ");
					int var1 = (int) row.getCell(c).getNumericCellValue();
					var = String.valueOf(var1).trim();

					if (var.contentEquals(Colval)) {
						System.out.print((int) row.getCell(c).getNumericCellValue());
						// System.out.println("Excel data : " + var);

						// System.out.println("");
					}

					else {
						//System.out.println("");
						System.out.println(var + " XXXX Not Matched XXXX");

					}
					break a;

				default:
					break;

				}

			}

		}
		System.out.println("");
	}

	
	
	public static void SetExcelcolumn(String filePath, String fileName, String sheetName, int startingRow, int Totalcolumn,int TotalRowCount, String Colval) throws IOException {
		{
			
		
		}
		}
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\ExcelFile";
		// ExcelDateReader.Read_All_Excel(filePath, "10th and O
		// Street_clean_rvt_(QuantityCompare)_01.xlsx","Demo_Project_01_10",11,8,50);

		ExcelDateReader.CompareExcelData(filePath, "10th and O Street_clean_rvt_(QuantityCompare)_01.xlsx",
				"Demo_Project_01_10", 11, 8, 11);

	}

}
