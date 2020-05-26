package com.codifyd.automation.attributelink;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.util.FileConversionHandler;
import com.codifyd.automation.util.HandlerConstants;
import com.codifyd.automation.util.InputValidator;
import com.codifyd.automation.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.AttributeLinkType;
import com.codifyd.stepxsd.MetaDataType;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.ProductType;
import com.codifyd.stepxsd.ProductsType;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.TrueFalseType;
import com.codifyd.stepxsd.ValueType;

public class AttributeLinkExcelFileHandler implements FileConversionHandler{

	public void handleFile(UserInputFileUtilDO userInput) throws Exception {

		// parse the input for errors
		InputValidator.validateExcelToXML(userInput);
		
		try {
			// Read the Excel and build the UOM Objects
			TreeMap<String, ArrayList<AttributeLinkExcelInfo>> excelinfo = new TreeMap<String, ArrayList<AttributeLinkExcelInfo>>();
			readExcel(new File(userInput.getInputPath()), excelinfo);

			// System.out.println(excelValues.size());

			File outputFile = new File(userInput.getOutputPath() + "\\" + userInput.getFilename());

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(HandlerConstants.CONTEXT1);
			stepProductInformation.setWorkspaceID(HandlerConstants.MAIN);

			ProductsType products = objectFactory.createProductsType();
			List<ProductType> productList = products.getProduct();

			Set<String> set = excelinfo.keySet();
			for (String key : set) {

				ProductType product = objectFactory.createProductType();

				NameType nameType = objectFactory.createNameType();

				product.setID(key);
				nameType.setContent(excelinfo.get(key).get(0).getProductName());
				product.getName().add(nameType);
				product.setParentID(excelinfo.get(key).get(0).getParentID());
				product.setUserTypeID(excelinfo.get(key).get(0).getObjectType());

				List<AttributeLinkType> attributeLinkList = product.getAttributeLink();

				ArrayList<AttributeLinkExcelInfo> list = new ArrayList<AttributeLinkExcelInfo>();
				list.addAll(excelinfo.get(key));
				for (AttributeLinkExcelInfo info : list) {
					AttributeLinkType attributeLink = objectFactory.createAttributeLinkType();

					attributeLink.setAttributeID(info.getAttributeLink());
					if (!info.getInherited().trim().equals("")) {
						attributeLink.setInherited(new BigInteger(info.getInherited()));
					}
					if (Boolean.parseBoolean(info.getMandatory().toLowerCase())) {
						attributeLink.setMandatory(TrueFalseType.TRUE);
					}

					if (null != info.getAttributeLinkMetadata()) {
						Map<String, String> map = info.getAttributeLinkMetadata();
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

					attributeLinkList.add(attributeLink);
				}
				productList.add(product);
			}

			stepProductInformation.setProducts(products);
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(stepProductInformation, outputFile);
			// jaxbMarshaller.marshal(stepProductInformation, System.out);

			System.out.println("File Generated in path : " + outputFile.getAbsolutePath());

		} catch (Exception e) {

		}
	}

	public static void readExcel(File inputFile, TreeMap<String, ArrayList<AttributeLinkExcelInfo>> excelinfo) {
		try {
			List<String> headerList = null;
			InputStream fs = new FileInputStream(inputFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				Row row = (Row) iterator.next();

				if (row.getRowNum() == 0) {
					headerList = new ArrayList<String>();
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = (Cell) iterator2.next();
						headerList.add(cell.getStringCellValue());
					}
				} else {
					AttributeLinkExcelInfo attributeLinkInfo = new AttributeLinkExcelInfo();
					DataFormatter df = new DataFormatter();
					String id = df.formatCellValue(row.getCell(0));
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = (Cell) iterator2.next();
						if (cell.getColumnIndex() == 0) {
							attributeLinkInfo.setProductID(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 1) {
							attributeLinkInfo.setProductName(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 2) {
							attributeLinkInfo.setObjectType(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 3) {
							attributeLinkInfo.setParentID(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 4) {
							attributeLinkInfo.setAttributeLink(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 5) {
							attributeLinkInfo.setMandatory(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 6) {
							attributeLinkInfo.setQualifierID(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 7) {
							attributeLinkInfo.setInherited(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 8) {
							attributeLinkInfo.setReferenced(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() > 8 && cell.getColumnIndex() < headerList.size()) {
							Map<String, String> map = attributeLinkInfo.getAttributeLinkMetadata();
							if (map == null) {
								map = attributeLinkInfo.createMetadatMap();
							}
							map.put(headerList.get(cell.getColumnIndex()), df.formatCellValue(cell));
							attributeLinkInfo.setAttributeLinkMetadata(map);
						}
					}
					if (!excelinfo.containsKey(id)) {
						ArrayList<AttributeLinkExcelInfo> list = new ArrayList<AttributeLinkExcelInfo>();
						list.add(attributeLinkInfo);
						excelinfo.put(id, list);
					} else {
						excelinfo.get(id).add(attributeLinkInfo);
					}
				}

			}
			workbook.close();
//			System.out.println(headerList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
