package com.codifyd.automation.stepconversion.attributelink;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.Utility;
import com.codifyd.stepxsd.ClassificationType;
import com.codifyd.stepxsd.ProductType;

public class AttributeLinkHandlerUtil {

	public static List<ProductType> getSubProducts(ProductType product) {
		List<Object> objectList = product.getProductOrSequenceProductOrSuppressedProductCrossReference();
		List<ProductType> list = new ArrayList<ProductType>();
		if (!objectList.isEmpty()) {
			for (Object object : objectList) {
				if (object instanceof ProductType) {
					list.add((ProductType) object);
					list.addAll(getSubProducts((ProductType) object));
				}
			}
		}
		return list;
	}

	public static List<ClassificationType> getSubClassification(ClassificationType classification) {
		List<Object> objectList = classification.getNameOrAttributeLinkOrSequenceProduct();
		List<ClassificationType> list = new ArrayList<ClassificationType>();
		if (!objectList.isEmpty()) {
			for (Object object : objectList) {
				if (object instanceof ClassificationType) {
					list.add((ClassificationType) object);
					list.addAll(getSubClassification((ClassificationType) object));
				}
			}
		}
		return list;
	}

	public static void writeToWorkbook(XSSFWorkbook workbook, XSSFSheet spreadSheet,
			Map<Integer, List<String>> attributeLinkMap, Map<Integer, String> propertyMap) {
		// Iterating over the array, create rows and columns
		XSSFRow row = null;
		XSSFCellStyle cellStyle = null;
		Set<Integer> keyid = attributeLinkMap.keySet();
		int rowid = 0;
		for (Integer key : keyid) {
			row = spreadSheet.createRow(rowid++);
			List<String> objArr = attributeLinkMap.get(key);
			int cellid = 0;
			for (String obj : objArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue(obj);
				if (rowid == 1 && cell.getColumnIndex() < propertyMap.size()) {
					cell.setCellStyle(Utility.getHeaderStyle(workbook, cellStyle));
				} else if (rowid == 1 && cell.getColumnIndex() >= propertyMap.size()) {
					cell.setCellStyle(Utility.getMetaDataHeaderStyle(workbook, cellStyle));
				}

			}
		}
	}

	public static void addMetaDataValues(Map<Integer, List<String>> attributeLinkMap,
			Map<String, Map<String, String>> metadataMap, List<String> headerList) {
		for (Integer rowNum : attributeLinkMap.keySet()) {
			if (rowNum > 0) {
				String attributeId = attributeLinkMap.get(rowNum).get(0) + attributeLinkMap.get(rowNum).get(4);
				if (metadataMap.containsKey(attributeId)) {
					Map<String, String> metadataValues = metadataMap.get(attributeId);
					List<String> attributeData = attributeLinkMap.get(rowNum);
					for (Entry<String, String> itr : metadataValues.entrySet()) {
						String metaAttrID = itr.getKey();
						String metaAttrValue = itr.getValue();
						int index = headerList.indexOf(metaAttrID);
						attributeData.add(index, metaAttrValue);
					}
				}
			}
		}
	}
}
