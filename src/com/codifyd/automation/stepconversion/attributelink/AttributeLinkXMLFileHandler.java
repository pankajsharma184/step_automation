package com.codifyd.automation.stepconversion.attributelink;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.AttributeLinkType;
import com.codifyd.stepxsd.ClassificationType;
import com.codifyd.stepxsd.MultiValueType;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ProductType;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.ValueType;

public class AttributeLinkXMLFileHandler implements FileConversionHandler {

	public void handleFile(UserInputFileUtilDO userInputFileUtilDO) throws Exception {
		try {
			// parse the input for errors
			InputValidator.validateXMLToExcel(userInputFileUtilDO);

			File inputFile = new File(userInputFileUtilDO.getInputPath());
			File outputFile = new File(Paths
					.get(new File(userInputFileUtilDO.getOutputPath()).getPath(), userInputFileUtilDO.getFilename())
					.toUri());
			Properties properties = userInputFileUtilDO.getPropertiesFile();

			String delimiter = userInputFileUtilDO.getDelimiters();

			JAXBContext jaxbContext = JAXBContext.newInstance(STEPProductInformation.class);
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
			STEPProductInformation objectFactory = (STEPProductInformation) jaxbUnMarshaller.unmarshal(inputFile);

			writeExcel(objectFactory, outputFile, properties, delimiter);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private static void writeExcel(STEPProductInformation objectFactory, File outputFile, Properties properties,
			String delimiter) throws Exception {

		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet productSpreadsheet = workbook.createSheet("ProductAttributeLinkInfo");
		XSSFSheet classificationSpreadsheet = workbook.createSheet("ClassificationAttributeLinkInfo");

		// Get all products
		List<ProductType> productTypeList = new ArrayList<ProductType>();
		productTypeList.addAll(objectFactory.getProducts().getProduct());
		for (ProductType product : objectFactory.getProducts().getProduct()) {
			productTypeList.addAll(AttributeLinkHandlerUtil.getSubProducts(product));
		}

		// Get all products
		List<ClassificationType> classificationTypeList = new ArrayList<ClassificationType>();
		classificationTypeList.addAll(objectFactory.getClassifications().getClassification());
		for (ClassificationType classification : objectFactory.getClassifications().getClassification()) {
			classificationTypeList.addAll(AttributeLinkHandlerUtil.getSubClassification(classification));
		}

		// Merge Product List And Classification List
		List<Object> objectList = new ArrayList<Object>();
		objectList.addAll(productTypeList);
		objectList.addAll(classificationTypeList);

		// Create Header List From Properties File
		Map<Integer, String> propertyMap = new TreeMap<Integer, String>();
		for (String key : properties.stringPropertyNames()) {
			propertyMap.put(Integer.parseInt(key), properties.getProperty(key));
		}

		ArrayList<String> headerList = new ArrayList<String>();
		for (Entry<Integer, String> ent : propertyMap.entrySet()) {
			headerList.add(ent.getValue().toString());
		}

		// Metadata Header List
		HashSet<String> prodMetaHeader = new HashSet<String>();
		HashSet<String> classMetaHeader = new HashSet<String>();
		for (Object object : objectList) {
			if (object instanceof ProductType) {
				ProductType product = (ProductType) object;
				for (AttributeLinkType link : product.getAttributeLink())
					if (null != link.getMetaData())
						for (Object value : link.getMetaData().getValueOrMultiValueOrValueGroup()) {
							if (value instanceof ValueType)
								prodMetaHeader.add(((ValueType) value).getAttributeID());
							else if (value instanceof MultiValueType)
								prodMetaHeader.add(((ValueType) ((MultiValueType) value).getValueOrValueGroup().get(0))
										.getAttributeID());
						}
			} else if (object instanceof ClassificationType) {
				ClassificationType classification = (ClassificationType) object;
				for (Object link : classification.getNameOrAttributeLinkOrSequenceProduct())
					if (link instanceof AttributeLinkType)
						if (null != ((AttributeLinkType) link).getMetaData())
							for (Object value : ((AttributeLinkType) link).getMetaData()
									.getValueOrMultiValueOrValueGroup()) {
								if (value instanceof ValueType)
									classMetaHeader.add(((ValueType) value).getAttributeID());
								else if (value instanceof MultiValueType)
									classMetaHeader
											.add(((ValueType) ((MultiValueType) value).getValueOrValueGroup().get(0))
													.getAttributeID());
							}

			}
		}
		// Add metadata header list into headerList
		List<String> prodHeaderList = new ArrayList<String>();
		List<String> classHeaderList = new ArrayList<String>();
		prodHeaderList.addAll(headerList);
		prodHeaderList.addAll(prodMetaHeader);
		classHeaderList.addAll(headerList);
		classHeaderList.addAll(classMetaHeader);

		// Create metadata map
		TreeMap<String, Map<String, String>> metadataMap = new TreeMap<String, Map<String, String>>();
		int i = 0, j = 0;

		// Create attributeLinkMap Store attributeLink Info
		Map<Integer, List<String>> productAttributeLinkMap = new TreeMap<Integer, List<String>>();
		productAttributeLinkMap.put(i, headerList);
		Map<Integer, List<String>> classificationAttributeLinkMap = new TreeMap<Integer, List<String>>();
		classificationAttributeLinkMap.put(j, headerList);

		for (Object object2 : objectList) {

			if (object2 instanceof ProductType) {
				ProductType product = (ProductType) object2;
				String productId = product.getID();
				String productName = product.getName().get(0).getContent();
				String objectType = product.getUserTypeID();
				String parentID = null != product.getParentID() ? product.getParentID() : "";

				if (null != product.getAttributeLink()) {

					List<AttributeLinkType> listAttLink = product.getAttributeLink();
					for (AttributeLinkType link : listAttLink) {
						i++;
						List<String> data = new ArrayList<String>();

						data.add(productId);
						data.add(productName);
						data.add(objectType);
						data.add(parentID);

						AttributeLinkHandlerUtil.getAttributeLinkInfo(data, link, metadataMap, productId, delimiter);

						for (int index = data.size(); index <= prodHeaderList.size(); index++) {
							data.add("");
						}

						productAttributeLinkMap.put(i, data);
					}
				}
			} else if (object2 instanceof ClassificationType) {

				ClassificationType classification = (ClassificationType) object2;
				String classificationId = classification.getID();
				String classificationName = ((NameType) classification.getNameOrAttributeLinkOrSequenceProduct().get(0))
						.getContent();
				String objectType = classification.getUserTypeID();
				String parentID = null != classification.getParentID() ? classification.getParentID() : "";

				if (null != classification.getNameOrAttributeLinkOrSequenceProduct()) {
					List<Object> classObjectList = classification.getNameOrAttributeLinkOrSequenceProduct();
					for (Object link : classObjectList) {
						if (link instanceof AttributeLinkType) {
							j++;
							List<String> data = new ArrayList<String>();

							data.add(classificationId);
							data.add(classificationName);
							data.add(objectType);
							data.add(parentID);

							AttributeLinkHandlerUtil.getAttributeLinkInfo(data, (AttributeLinkType) link, metadataMap,
									classificationId, delimiter);

							for (int index = data.size(); index <= classHeaderList.size(); index++) {
								data.add("");
							}

							classificationAttributeLinkMap.put(j, data);
						}
					}
				}
			}
		}

		// Adding metadata value in attributelink info map
		AttributeLinkHandlerUtil.addMetaDataValues(productAttributeLinkMap, metadataMap, prodHeaderList);
		AttributeLinkHandlerUtil.addMetaDataValues(classificationAttributeLinkMap, metadataMap, classHeaderList);

		AttributeLinkHandlerUtil.writeToWorkbook(workbook, productSpreadsheet, productAttributeLinkMap,
				propertyMap);
		AttributeLinkHandlerUtil.writeToWorkbook(workbook, classificationSpreadsheet,
				classificationAttributeLinkMap, propertyMap);

		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(outputFile);
		workbook.write(out);
		out.close();
		workbook.close();
		System.out.println("File Generated in path : " + outputFile);

	}
}
