package com.qa.hubspot.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.model.RowBlocksReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	String path;
	String sheetName;
	int index;
	FileInputStream fis;
	FileOutputStream fos;
	Workbook workbook;
	CellStyle style;

	Sheet sheet;
	Row row;
	Cell cell;

	public ExcelReader(String path, String sheetName) {
		this.path = path;
		this.sheetName = sheetName;
		try {
			fis = new FileInputStream(path);

			workbook = new XSSFWorkbook(fis);
			index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getRowCount() {
		int rowcount = 0;
		try {
			if (index < 0) {
				return 0;
			} else {
				rowcount = sheet.getLastRowNum() + 1;

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return rowcount;
	}

	public String getCellData(int rownumber, int cellnumber) {
		String cellvalue = null;

		try {
			if (index < 0) {
				return "";
			}
			if (rownumber <= 0) {
				return "";
			}

			row = sheet.getRow(rownumber);
			cell = row.getCell(cellnumber);

			if (row == null) {
				return "";
			}
			if (cell == null) {
				return "";
			}

			if (cell.getCellType().name().equals("STRING")) {
				cellvalue = cell.getStringCellValue();

			} else if (cell.getCellType().name().equals("Numeric") || cell.getCellType().name().equals("FORMULA")) {
				cellvalue = String.valueOf(cell.getNumericCellValue());
			} else {
				cell.getCellType();
				if (cell.getCellType()!= null) {
					return "";
				} else {
					cellvalue = String.valueOf(cell.getBooleanCellValue());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			cellvalue = "row" + rownumber + "col" + cellnumber + "does not exist";
		}

		return cellvalue;

	}

	public String getCellData(int rownumber, String cellvalue) {
		int cellnumber = 0;
		String cellvalues = null;
		try {
			if (index < 0) {
				return "";
			}

			if (rownumber <= 0) {
				return "";
			}

			row = sheet.getRow(0);
			int cellcount = row.getLastCellNum();

			for (int i = 0; i < cellcount; i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(cellvalue.trim())) {
					cellnumber = i;
				}
			}

			if (cellnumber < 0) {
				return "";
			}

			row = sheet.getRow(rownumber);
			cell = row.getCell(cellnumber);

			if (row == null) {
				return "";
			}

			if (cell == null) {
				return "";
			}

			if (cell.getCellType().name().equals("String")) {
				cellvalues = cell.getStringCellValue();
			}

			else if (cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA")) {
				cellvalues = String.valueOf(cell.getNumericCellValue());
			} else {
				cell.getCellType();
				if (CellType.BLANK != null) {
					return "";
				} else {
					cellvalues = String.valueOf(cell.getBooleanCellValue());
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
			cellvalues = "row" + rownumber + "cell" + cellvalue + "does not exist";
		}

		return cellvalues;

	}

	public boolean setCellData(int rownumber, String cellheader, String cellStatus)
			throws IOException, InterruptedException {
		try {
			int cellnumber = 0;
			if (index < 0) {
				return false;
			}

			addColumn(cellheader);

			row = sheet.getRow(0);
			int cellcount = row.getLastCellNum();

			for (int i = 0; i < cellcount; i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(cellheader)) {
					cellnumber = i;
				}
			}

			if (cellnumber < 0) {
				return false;
			}

			row = sheet.getRow(rownumber);

			if (row == null) {
				return false;
			}

			row.createCell(cellnumber).setCellValue(cellStatus);

			if (cell == null) {
				return false;
			}

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

	public boolean addColumn(String colname) throws IOException {
		try {
			if (index == -1)
				return false;

			style = workbook.createCellStyle();
			// style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			row = sheet.getRow(0);
			if (row == null) {
				row = sheet.createRow(0);
			}

			if (row.getLastCellNum() == -1) {
				cell = row.createCell(0);
			}

			else if (row.getLastCellNum() != 0) {
				int cellcount = row.getLastCellNum();
				
				for (int i = 0; i < cellcount; i++) {
					cell = row.getCell(i);
					if (cell.getStringCellValue().trim().equals(colname)) {
						return true;
					}
				}
				
				

					cell = row.createCell(row.getLastCellNum());
				
			} 

			cell.setCellValue(colname);
			cell.setCellStyle(style);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

		return true;

	}

	public boolean removeColumn(int colnumber) throws IOException {
		try {
			if (index < 0) {
				return false;
			}
			for (int i = 0; i <= getRowCount(); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					cell = row.getCell(colnumber);
				}
				if (cell != null) {
					row.removeCell(cell);
				}

			}

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.getStackTrace();
			return false;

		}

		return true;

	}

	public boolean addSheet(String sheetname) throws IOException {

		try {

			workbook.createSheet(sheetname);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;

	}

	public boolean removeSheet() {
		try {
			if (index < 0) {
				return false;
			}
			workbook.removeSheetAt(index);
			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

	public boolean addRow() {
		try {

			if (index < 0) {
				return false;
			}

			if (sheet.getRow(0) == null) {
				return false;
			}
			int sheetcount = sheet.getLastRowNum();
			System.out.println(sheetcount);
			row = sheet.getRow(sheetcount);
			int cellcount = row.getLastCellNum();
			cell = row.getCell(0);

			if (cell != null) {
				System.out.println(sheet.getLastRowNum());
				row = sheet.createRow(sheetcount + 1);
				fos = new FileOutputStream(path);
				workbook.write(fos);

			}

		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

	public void addRowData(HashMap map) throws IOException {

		int sheetcount = sheet.getLastRowNum() - 1;

		row = sheet.getRow(sheetcount);
		int cellcount = row.getLastCellNum();

		addRow();

		for (int i = 1; i <= map.size(); i++) {

			for (int j = 0; j < cellcount; j++) {
				if (row.getCell(j) == null) {
					cell = row.createCell(j);
					break;
				}
			}

			if (map.get(i) instanceof String) {
				cell.setCellValue((String) map.get(i));
				System.out.println(cell.getCellType());
			}

			if (map.get(i) instanceof Integer) {
				cell.setCellValue((Integer) map.get(i));
				System.out.println(cell.getCellType());
			}

			if (map.get(i) instanceof Boolean) {
				cell.setCellValue((Boolean) map.get(i));
				System.out.println(cell.getCellType());
			}

		}

		fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

	}

}