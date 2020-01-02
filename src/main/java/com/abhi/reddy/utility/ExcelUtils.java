package com.abhi.reddy.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * The Class ExcelUtils.
 */
public class ExcelUtils {

	/** The File reader. */
	private InputStream FileReader=null;;
	
	/** The Excel workbook. */
	private Workbook ExcelWorkbook=null;;
	
	/** The Excel sheet. */
	Sheet ExcelSheet;
	
	/** The output row. */
	Row outputRow;
	
	/** The output cell. */
	Cell outputCell;
	
	/**
	 * Sets the null.
	 */
	private void setNull() {
		FileReader = null;
		ExcelWorkbook = null;
	}

	/**
	 * Instantiates a new excel utils.
	 */
	public ExcelUtils() {
		this.setNull();
	}

	// -------------------Get Excel File pointer-----------------------------

	/**
	 * Open excel workbook.
	 *
	 * @param WorkbookFileName the workbook file name
	 */
	public void openExcelWorkbook(String WorkbookFileName) {
		try {

			WorkbookFileName = WorkbookFileName.trim();
			if (WorkbookFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if (!(new File(WorkbookFileName)).exists()) {
				throw new Exception("File does not Exists");
			}

			FileReader = new FileInputStream(WorkbookFileName);
			ExcelWorkbook = WorkbookFactory.create(FileReader);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ----------------------Close Excel file Pointer---------------------

	/**
	 * Close.
	 */
	public void close() {
		try {
			ExcelWorkbook.close();
			FileReader.close();
			setNull();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// -----------------------Get Row count from sheet--------------------

	/**
	 * Gets the row count of sheet.
	 *
	 * @param ExcelSheetName the excel sheet name
	 * @return the row count of sheet
	 */
	public int getRowCountOfSheet(String ExcelSheetName) {
		try {
			ExcelSheetName = ExcelSheetName.trim();
			if (ExcelSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");

			}
			Sheet ExcelSheet;
			ExcelSheet = ExcelWorkbook.getSheet(ExcelSheetName);
			System.out.println("Sheet Name: " + ExcelSheetName);
			if (ExcelSheet == null) {
				throw new Exception("Sheet does not Exist");
			}

			return ExcelSheet.getLastRowNum();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	// ---------------------------Get Cell Count-----------------------------

	/**
	 * Gets the cell count.
	 *
	 * @param ExcelSheetName the excel sheet name
	 * @param inputRow the input row
	 * @return the cell count
	 */
	public int getCellCount(String ExcelSheetName, int inputRow) {
		try {
			ExcelSheetName = ExcelSheetName.trim();
			if (ExcelSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");

			}

			Sheet ExcelSheet;

			ExcelSheet = ExcelWorkbook.getSheet(ExcelSheetName);
			if (ExcelSheet == null) {
				throw new Exception("Sheet doesnot Exist");
			}

			if (inputRow < 1) {
				throw new Exception("ROw Index start from 1");
			}

			Row outputRow;

			outputRow = ExcelSheet.getRow(inputRow - 1);

			if (outputRow == null) {
				return 0;
			} else {
				return outputRow.getLastCellNum() + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	// ----------------------Get Data from Cell---------------------------

	/**
	 * Gets the cell C data.
	 *
	 * @param ExcelSheetName the excel sheet name
	 * @param inputRow the input row
	 * @param inputCell the input cell
	 * @return the cell C data
	 */
	public String getCellCData(String ExcelSheetName, int inputRow, int inputCell) { 															

		try {
			ExcelSheetName = ExcelSheetName.trim();
			if (ExcelSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");
			}
			ExcelSheet = ExcelWorkbook.getSheet(ExcelSheetName);

			if (ExcelSheet == null) {
				throw new Exception("Sheet does not Exist");
			}
			if (inputRow < 1 || inputCell < 1) {
				throw new Exception("Row & Cell Index start from 1");
			}
			outputRow = ExcelSheet.getRow(inputRow - 1);

			if (outputRow == null) {
				return "";
			}
			outputCell = outputRow.getCell(inputCell - 1);
			if (outputCell == null) {
				// fill the return string as per your requirement
				return "";
			} else {
				if(outputCell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC) {
					outputCell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
					}

					String CellData = outputCell.getStringCellValue();
					return CellData;

			}

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}
}

