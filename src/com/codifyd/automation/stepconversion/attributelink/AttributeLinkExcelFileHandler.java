package com.codifyd.automation.stepconversion.attributelink;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;
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

import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.ClassificationType;
import com.codifyd.stepxsd.ClassificationsType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.ProductType;
import com.codifyd.stepxsd.ProductsType;
import com.codifyd.stepxsd.STEPProductInformation;

public class AttributeLinkExcelFileHandler implements FileConversionHandler {

	@Override
	public void handleFile(UserInputFileUtilDO userInput) throws Exception {
		try {
			// parse the input for errors
			InputValidator.validateExcelToXML(userInput);

			// Read the Excel and build the UOM Objects
			TreeMap<String, ArrayList<AttributeLinkExcelInfo>> prodExcelInfo = new TreeMap<String, ArrayList<AttributeLinkExcelInfo>>();
			TreeMap<String, ArrayList<AttributeLinkExcelInfo>> classExcelInfo = new TreeMap<String, ArrayList<AttributeLinkExcelInfo>>();
			readExcel(new File(userInput.getInputPath()), prodExcelInfo, classExcelInfo);

			URI outputUri = Paths.get(new File(userInput.getOutputPath()).getPath(), userInput.getFilename()).toUri();
			File outputFile = new File(outputUri);

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(HandlerConstants.CONTEXT1);
			stepProductInformation.setWorkspaceID(HandlerConstants.MAIN);

			ProductsType products = objectFactory.createProductsType();
			List<ProductType> productList = products.getProduct();

			if (!prodExcelInfo.isEmpty()) {
				Set<String> prodSet = prodExcelInfo.keySet();
				for (String key : prodSet) {
					AttributeLinkHandlerUtil.setProducts(objectFactory, prodExcelInfo, productList, key);
				}

				stepProductInformation.setProducts(products);
			}

			if (!classExcelInfo.isEmpty()) {
				ClassificationsType classificationsType = objectFactory.createClassificationsType();
				List<ClassificationType> classificationList = classificationsType.getClassification();

				Set<String> classSet = classExcelInfo.keySet();
				for (String key : classSet) {
					AttributeLinkHandlerUtil.setClassifications(objectFactory, classExcelInfo, classificationList, key);
				}

				stepProductInformation.setClassifications(classificationsType);
			}

			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(stepProductInformation, outputFile);

			System.out.println("File Generated in path : " + outputFile.getAbsolutePath());

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public static void readExcel(File inputFile, TreeMap<String, ArrayList<AttributeLinkExcelInfo>> prodExcelInfo,
			TreeMap<String, ArrayList<AttributeLinkExcelInfo>> classExcelInfo) {
		try {
			List<String> headerList = null;
			InputStream fs = new FileInputStream(inputFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get desired sheet from the workbook
			for (int sheetAt = 0; sheetAt < workbook.getNumberOfSheets(); sheetAt++) {

				XSSFSheet sheet = workbook.getSheetAt(sheetAt);

				for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
					Row row = iterator.next();

					if (row.getRowNum() == 0) {
						headerList = new ArrayList<String>();
						for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
							Cell cell = iterator2.next();
							headerList.add(cell.getStringCellValue());
						}
					} else {
						AttributeLinkExcelInfo attributeLinkInfo = new AttributeLinkExcelInfo();
						DataFormatter df = new DataFormatter();
						String id = df.formatCellValue(row.getCell(0));
						for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
							Cell cell = iterator2.next();
							if (cell.getColumnIndex() == 0) {
								attributeLinkInfo.setProduct_class_ID(df.formatCellValue(cell));
							} else if (cell.getColumnIndex() == 1) {
								attributeLinkInfo.setProduct_class_Name(df.formatCellValue(cell));
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
						if (sheetAt == 0) {
							if (!prodExcelInfo.containsKey(id)) {
								ArrayList<AttributeLinkExcelInfo> list = new ArrayList<AttributeLinkExcelInfo>();
								list.add(attributeLinkInfo);
								prodExcelInfo.put(id, list);
							} else {
								prodExcelInfo.get(id).add(attributeLinkInfo);
							}
						} else if (sheetAt == 1) {
							if (!classExcelInfo.containsKey(id)) {
								ArrayList<AttributeLinkExcelInfo> list = new ArrayList<AttributeLinkExcelInfo>();
								list.add(attributeLinkInfo);
								classExcelInfo.put(id, list);
							} else {
								classExcelInfo.get(id).add(attributeLinkInfo);
							}
						}

					}

				}
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
