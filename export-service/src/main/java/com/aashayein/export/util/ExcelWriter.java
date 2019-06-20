/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.util
 * @FileName: ExcelWriter.java
 * @Author: Avishek Das
 * @CreatedDate: 19-06-2019
 * @Modified_By avishek.das @Last_On 19-Jun-2019 11:17:23 PM
 */

package com.aashayein.export.util;

import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aashayein.export.dto.ExcelDetails;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExcelWriter {

	@Autowired
	private BuildSpreadSheet buildSpreadSheet;

	public XSSFWorkbook buildWorkbook(ExcelDetails excelDetails) {

		Map<String, String> header = excelDetails.getHeaderStyle();
		Map<String, String> recordNotFound = excelDetails.getRecordNotFoundStyle();
		Map<String, String> content = excelDetails.getContentStyle();
		Map<Integer, Map<String, String>> specificCellStyle = excelDetails.getSpecificCellStyle();

		// Create a Workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		/*
		 * CreationHelper helps us create instances of various things like DataFormat,
		 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
		 */
		CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		XSSFSheet spreadsheet = workbook.createSheet(excelDetails.getSheetName());

		// create style for header cells
		CellStyle headerStyle = workbook.createCellStyle();
		Font font = workbook.createFont();

		font.setFontName(getValue(header, "font", "Arial"));
		font.setBold(Boolean.parseBoolean(getValue(header, "bold", "false")));
		font.setColor(IndexedColors.valueOf(getValue(header, "color", "BLACK").toUpperCase()).getIndex());
		headerStyle.setAlignment(HorizontalAlignment.valueOf(getValue(header, "alignment", "CENTER").toUpperCase()));
		headerStyle.setVerticalAlignment(
				VerticalAlignment.valueOf(getValue(header, "verticalAlignment", "CENTER").toUpperCase()));
		headerStyle.setBorderBottom(BorderStyle.valueOf(getValue(header, "bottomBorder", "THIN").toUpperCase()));
		headerStyle.setBottomBorderColor(
				IndexedColors.valueOf(getValue(header, "bottomBorderColor", "GREEN").toUpperCase()).getIndex());
		headerStyle.setFont(font);

		// create style for record not found cells
		CellStyle recordNotFoundStyle = workbook.createCellStyle();
		font = workbook.createFont();

		font.setFontName(getValue(recordNotFound, "font", "Arial"));
		font.setBold(Boolean.parseBoolean(getValue(recordNotFound, "bold", "false")));
		font.setColor(IndexedColors.valueOf(getValue(recordNotFound, "color", "RED").toUpperCase()).getIndex());
		recordNotFoundStyle.setAlignment(
				HorizontalAlignment.valueOf(getValue(recordNotFound, "alignment", "CENTER").toUpperCase()));
		recordNotFoundStyle.setVerticalAlignment(
				VerticalAlignment.valueOf(getValue(recordNotFound, "verticalAlignment", "CENTER").toUpperCase()));
		recordNotFoundStyle.setFont(font);

		// create style for content cells
		CellStyle contentStyle = workbook.createCellStyle();
		font = workbook.createFont();

		font.setFontName(getValue(content, "font", "Arial"));
		font.setColor(IndexedColors.valueOf(getValue(content, "color", "BLACK").toUpperCase()).getIndex());
		contentStyle.setAlignment(HorizontalAlignment.valueOf(getValue(content, "alignment", "CENTER").toUpperCase()));
		contentStyle.setVerticalAlignment(
				VerticalAlignment.valueOf(getValue(content, "verticalAlignment", "CENTER").toUpperCase()));
		contentStyle.setFont(font);

		// create a header row
		Row headerRow = spreadsheet.createRow(0);

		// Create header cells
		for (int i = 0; i < excelDetails.getColumns().length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(excelDetails.getColumns()[i]);
			cell.setCellStyle(headerStyle);
		}

		if (excelDetails.getData().isEmpty()) {
			Row empty = spreadsheet.createRow(1);
			empty.createCell(2).setCellValue("No Employee Record Found");
			empty.getCell(2).setCellStyle(recordNotFoundStyle);

			// Resize all columns to fit the content size
			for (int i = 0; i < excelDetails.getColumns().length; i++) {
				spreadsheet.autoSizeColumn(i);
			}
		} else {

			XSSFSheet sheet = buildSpreadSheet.buildSheetForEmployees(spreadsheet, excelDetails.getData());

			// Applying content cell style
			for (int rowNo = 1; rowNo <= sheet.getLastRowNum(); rowNo++) {
				for (int colNo = 0; colNo < sheet.getRow(rowNo).getLastCellNum(); colNo++) {
					sheet.getRow(rowNo).getCell(colNo).setCellStyle(contentStyle);
				}
			}

			// create style for specific cell
			if (!specificCellStyle.isEmpty()) {
				specificCellStyle.forEach((columnNumber, style) -> {
					CellStyle cellStyle = workbook.createCellStyle();
					Font fontstyle = workbook.createFont();
					fontstyle.setFontName(getValue(style, "font", "Arial"));
					fontstyle.setColor(
							IndexedColors.valueOf(getValue(style, "color", "BLACK").toUpperCase()).getIndex());
					cellStyle.setAlignment(
							HorizontalAlignment.valueOf(getValue(style, "alignment", "CENTER").toUpperCase()));
					cellStyle.setVerticalAlignment(
							VerticalAlignment.valueOf(getValue(style, "verticalAlignment", "CENTER").toUpperCase()));
					cellStyle.setDataFormat(
							createHelper.createDataFormat().getFormat(getValue(style, "dataFormat", "dd/MM/yyyy")));
					cellStyle.setFont(fontstyle);

					// Applying specific cell style
					for (int rowNo = 1; rowNo <= sheet.getLastRowNum(); rowNo++) {
						for (int colNo = 0; colNo < sheet.getRow(rowNo).getLastCellNum(); colNo++) {
							if (colNo == columnNumber) {
								sheet.getRow(rowNo).getCell(colNo).setCellStyle(cellStyle);
								break;
							}
						}
					}
					sheet.getRow(1).getCell(columnNumber).setCellStyle(cellStyle);
				});
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < excelDetails.getColumns().length; i++) {
				sheet.autoSizeColumn(i);
			}
		}

		log.info("SpreedSheet written successfully");

		return workbook;
	}

	public String getValue(Map<String, String> style, String attributrName, String defaultValue) {
		return style.get(attributrName) != null ? style.get(attributrName) : defaultValue;
	}
}
