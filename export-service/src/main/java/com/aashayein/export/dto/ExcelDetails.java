/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.dto
 * @FileName: ExcelDetails.java
 * @Author: Avishek Das
 * @CreatedDate: 20-06-2019
 * @Modified_By avishek.das @Last_On 20-Jun-2019 12:03:48 AM
 */

package com.aashayein.export.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ExcelDetails {

	private String sheetName;

	private String[] columns;

	private Map<String, String> headerStyle;

	private List<Object> data;

	private Map<String, String> recordNotFoundStyle;

	private Map<String, String> contentStyle;

	private Map<Integer, Map<String, String>> specificCellStyle;
}
