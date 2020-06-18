package com.codifyd.automation.stepconversion.taxonomy;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.ConfigHandler;

public class TaxonomyExcelReader {

	public static void readExcel(File inputFile, ExcelDO excelValues, ConfigHandler configFile,
			Map<String, Map<String, String>> attributeValues, List<String> excelError) throws Exception {
		try {
			InputStream fs = new FileInputStream(inputFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// column headers
			List<String> headers = new ArrayList<>();

			List<Map<String, TaxonomyExcelInfo>> structureMap = new ArrayList<Map<String, TaxonomyExcelInfo>>();

			/*
			 * // get meta data information Map<Integer, String> metaData = new HashMap<>();
			 * if (!isNullOrBlank(properties.getProperty("MetaData"))) {
			 * Arrays.asList(properties.getProperty("MetaData").split(",")).forEach(each ->
			 * { if (!isNullOrBlank(each)) { String[] data = each.split(":");
			 * metaData.put(Integer.parseInt(data[0]), data[1]); } }); }
			 */

			// Structure Maps
			for (String key : configFile.keySet()) {
				Map<String, TaxonomyExcelInfo> levelMap = new HashMap<>();
				structureMap.add(levelMap);
			}

			DataFormatter df = new DataFormatter();
			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				Row row = iterator.next();

				if (row.getRowNum() == 0) {
					// get column headers
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();
						headers.add(df.formatCellValue(cell));
					}
				} else {
					List<TaxonomyExcelInfo> structInfo = new ArrayList<TaxonomyExcelInfo>();
					for (String key : configFile.keySet()) {
						TaxonomyExcelInfo levelStructure = new TaxonomyExcelInfo();
						structInfo.add(levelStructure);
					}

					TaxonomyExcelInfo parentStructure = new TaxonomyExcelInfo();
					TaxonomyExcelInfo currentStructure = new TaxonomyExcelInfo();

					/*
					 * // get key position Integer key =
					 * Integer.parseInt(properties.getProperty("KeyPosition"));
					 */

					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();
						int cellnum = cell.getColumnIndex();

						String cellValue = df.formatCellValue(cell);
						if (isNullOrBlank(cellValue)) {
							continue;
						}
						if (cellnum < structInfo.size()) {
							if (isNullOrBlank(cellValue)) {
								String nextCellVal = df.formatCellValue(row.getCell(cell.getColumnIndex() + 1));
								if (isNullOrBlank(nextCellVal)) {
									continue;
								} else {
									excelError.add("Row - " + row.getRowNum());
									continue;
								}
							} else {
								if (excelError.contains(row.getRowNum() + "" + cellnum)) {
									continue;
								}
							}
							switch (cellnum) {
							case 0:
								structInfo.get(cellnum).setName(cellValue);
								structInfo.get(cellnum).setObjectType(configFile.get("Level" + cellnum + 1));
								structInfo.get(cellnum);
								structInfo.get(cellnum).setStructurePath(cellValue);
								currentStructure = structInfo.get(cellnum);
								break;
							default:
								structInfo.get(cellnum).setName(cellValue);
								structInfo.get(cellnum).setObjectType(configFile.get("Level" + cellnum + 1));
								structInfo.get(cellnum).setStructurePath(
										structInfo.get(cellnum - 1).getStructurePath() + "\\" + cellValue);
								parentStructure = structInfo.get(cellnum - 1);
								currentStructure = structInfo.get(cellnum);
								break;
							}
						} else if (cellnum == structInfo.size()) {
						} else if (cellnum == structInfo.size() + 1) {
						} else {
							Map<String, String> map = new HashMap<String, String>();
							map.put(headers.get(cell.getColumnIndex()), df.formatCellValue(cell));
							if (!attributeValues.containsKey(currentStructure.getStructurePath())) {
								attributeValues.put(currentStructure.getStructurePath(), map);
							} else {
								attributeValues.get(currentStructure.getStructurePath()).putAll(map);
							}
						}

					}

					// get current structure
					for (int x = structInfo.size() - 1; x >= 0; x--) {
						if (x > 0 && !isNullOrBlank(structInfo.get(x).getName())) {
							addStructureToMap(currentStructure, structureMap.get(x), parentStructure,
									structureMap.get(x - 1), excelError);
							break;
						} else if (x == 0 && !isNullOrBlank(structInfo.get(x).getName())) {
							addStructureToMap(currentStructure, structureMap.get(x), null, null, excelError);
							break;
						}
					}
				}
			}
			excelValues.setValues(structureMap);
			workbook.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	private static void addStructureToMap(TaxonomyExcelInfo currentStructure,
			Map<String, TaxonomyExcelInfo> currentStructureMap, TaxonomyExcelInfo parentStructure,
			Map<String, TaxonomyExcelInfo> parentStructureMap, List<String> excelError) {
		if (!currentStructureMap.containsKey(currentStructure.getStructurePath())) {
			currentStructureMap.put(currentStructure.getStructurePath(), currentStructure);
			if (parentStructure != null && parentStructureMap.get(parentStructure.getStructurePath()) != null) {
				parentStructureMap.get(parentStructure.getStructurePath()).getChildren()
						.add(currentStructure.getStructurePath());
			}
			if (parentStructureMap != null && parentStructureMap.get(parentStructure.getStructurePath()) == null) {
				System.out.println(
						parentStructure.getStructurePath() + "for current value" + currentStructure.getStructurePath());

				excelError.add("Parent Missing for" + currentStructure.getName() + " : "
						+ currentStructure.getStructurePath());
			}

		}

	}

	public static boolean isNullOrBlank(String param) {
		if (param == null || param.trim().equals("")) {
			return true;
		}
		return false;
	}
}
