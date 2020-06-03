package com.codifyd.automation.stepconversion.util;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility {

	// MetaData Header Cell Styling
	public static XSSFCellStyle getMetaDataHeaderStyle(XSSFWorkbook workbook, XSSFCellStyle cellStyle) {
		cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(155, 195, 230)));
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return cellStyle;
	}

	// Common/Fixed Header Cell Styling
	public static XSSFCellStyle getHeaderStyle(XSSFWorkbook workbook, XSSFCellStyle cellStyle) {
		cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(50, 120, 180)));
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return cellStyle;
	}
}
