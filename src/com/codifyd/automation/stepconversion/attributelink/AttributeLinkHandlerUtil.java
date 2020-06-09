package com.codifyd.automation.stepconversion.attributelink;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.ExcelWorkbookUtility;
import com.codifyd.stepxsd.AttributeLinkType;
import com.codifyd.stepxsd.ClassificationType;
import com.codifyd.stepxsd.MetaDataType;
import com.codifyd.stepxsd.MultiValueType;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.ProductType;
import com.codifyd.stepxsd.TrueFalseType;
import com.codifyd.stepxsd.ValueType;

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

	public static void setProducts(ObjectFactory objectFactory,
			TreeMap<String, ArrayList<AttributeLinkExcelInfo>> excelinfo, List<ProductType> productList, String key) {
		ProductType product = objectFactory.createProductType();

		NameType nameType = objectFactory.createNameType();

		product.setID(key);
		nameType.setContent(excelinfo.get(key).get(0).getProduct_class_Name());
		product.getName().add(nameType);
		product.setParentID(excelinfo.get(key).get(0).getParentID());
		product.setUserTypeID(excelinfo.get(key).get(0).getObjectType());

		List<AttributeLinkType> attributeLinkList = product.getAttributeLink();

		ArrayList<AttributeLinkExcelInfo> list = new ArrayList<AttributeLinkExcelInfo>();
		list.addAll(excelinfo.get(key));
		for (AttributeLinkExcelInfo atLinkInfo : list) {
			setAttributeLinkInfo(objectFactory, atLinkInfo, attributeLinkList);
		}
		productList.add(product);
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

	public static void setClassifications(ObjectFactory objectFactory,
			TreeMap<String, ArrayList<AttributeLinkExcelInfo>> classExcelInfo,
			List<ClassificationType> classificationList, String key) {
		ClassificationType classification = objectFactory.createClassificationType();

		NameType nameType = objectFactory.createNameType();

		classification.setID(key);
		nameType.setContent(classExcelInfo.get(key).get(0).getProduct_class_Name());

		classification.setParentID(classExcelInfo.get(key).get(0).getParentID());
		classification.setUserTypeID(classExcelInfo.get(key).get(0).getObjectType());

		List<Object> attributeLinkList = classification.getNameOrAttributeLinkOrSequenceProduct();
		attributeLinkList.add(nameType);

		ArrayList<AttributeLinkExcelInfo> list = new ArrayList<AttributeLinkExcelInfo>();
		list.addAll(classExcelInfo.get(key));

		for (AttributeLinkExcelInfo atLinkInfo : list) {
			setAttributeLinkInfo(objectFactory, atLinkInfo, attributeLinkList);
		}
		classificationList.add(classification);

	}

	public static void writeToWorkbook(XSSFWorkbook workbook, XSSFSheet spreadSheet,
			Map<Integer, List<String>> attributeLinkMap, int sizeofProperties) {
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
				if (rowid == 1 && cell.getColumnIndex() < sizeofProperties) {
					cell.setCellStyle(ExcelWorkbookUtility.getHeaderStyle(workbook, cellStyle));
				} else if (rowid == 1 && cell.getColumnIndex() >= sizeofProperties) {
					cell.setCellStyle(ExcelWorkbookUtility.getMetaDataHeaderStyle(workbook, cellStyle));
				}

			}
		}
	}

	public static void getMetaDataValues(Map<Integer, List<String>> attributeLinkMap,
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

	public static void setMetaDataValues() {

	}

	// Handle AttributeLink Data From the XML
	public static void getAttributeLinkInfo(List<String> data, AttributeLinkType link,
			TreeMap<String, Map<String, String>> metadataMap, String prod_classId, String delim,
			List<String> headerList2) throws Exception {
		try {
			String attributeId = link.getAttributeID();
			String mandatory = null != link.getMandatory() ? link.getMandatory().toString()
					: TrueFalseType.FALSE.toString();
			String qualifierId = null != link.getQualifierID() ? link.getQualifierID() : TrueFalseType.FALSE.toString();
			String inherited = null != link.getInherited() ? link.getInherited().toString() : "";
			String referenced = link.isReferenced() ? TrueFalseType.TRUE.toString() : TrueFalseType.FALSE.toString();

			if (null != link.getMetaData()) {
				Map<String, String> map = new HashMap<String, String>();
				for (Object value : link.getMetaData().getValueOrMultiValueOrValueGroup()) {
					if (value instanceof ValueType) {
						String key = ((ValueType) value).getAttributeID();
						String val = ((ValueType) value).getContent();
						map.put(key, val);
					} else if (value instanceof MultiValueType) {
						String key = ((ValueType) ((MultiValueType) value).getValueOrValueGroup().get(0))
								.getAttributeID();
						StringBuffer sb = new StringBuffer("");

						for (Object value2 : ((MultiValueType) value).getValueOrValueGroup()) {
							if (value instanceof ValueType) {
								sb.append(((ValueType) value2).getContent());
								sb.append(delim);
							}
						}
						String val = sb.toString();
						map.put(key, val);
					}
				}
				metadataMap.put(prod_classId + attributeId, map);
			}

			data.set(headerList2.indexOf("Attribute_Link"), attributeId);
			data.set(headerList2.indexOf("Mandatory"), mandatory);
			data.set(headerList2.indexOf("Qualifier_ID"), qualifierId);
			data.set(headerList2.indexOf("Inherited"), inherited);
			data.set(headerList2.indexOf("Referenced"), referenced);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public static <E> void setAttributeLinkInfo(ObjectFactory objectFactory, AttributeLinkExcelInfo atLinkInfo,
			List<E> attributeLinkList) {

		AttributeLinkType attributeLink = objectFactory.createAttributeLinkType();

		attributeLink.setAttributeID(atLinkInfo.getAttributeLink());
		if (atLinkInfo.getInherited() != null && !atLinkInfo.getInherited().trim().equals("")) {
			attributeLink.setInherited(new BigInteger(atLinkInfo.getInherited()));
		}
		if (atLinkInfo.getMandatory() != null && Boolean.parseBoolean(atLinkInfo.getMandatory().toLowerCase())) {
			attributeLink.setMandatory(TrueFalseType.TRUE);
		}

		if (null != atLinkInfo.getAttributeLinkMetadata()) {
			Map<String, String> map = atLinkInfo.getAttributeLinkMetadata();
			MetaDataType meta = objectFactory.createMetaDataType();
			List<Object> valueList = meta.getValueOrMultiValueOrValueGroup();
			for (String key2 : map.keySet()) {
				if (!map.get(key2).equals("")) {
					ValueType value = objectFactory.createValueType();
					value.setAttributeID(key2);
					value.setContent(map.get(key2));
					valueList.add(value);
				}
			}
			attributeLink.setMetaData(meta);
		}

		attributeLinkList.add((E) attributeLink);
	}
}
