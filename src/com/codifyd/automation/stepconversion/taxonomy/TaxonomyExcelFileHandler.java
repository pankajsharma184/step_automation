package com.codifyd.automation.stepconversion.taxonomy;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.ConfigHandler;
import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.ProductType;
import com.codifyd.stepxsd.ProductsType;
import com.codifyd.stepxsd.STEPProductInformation;

public class TaxonomyExcelFileHandler implements FileConversionHandler {

	public static void main(String[] args) {
		try {
			UserInputFileUtilDO userInput = new UserInputFileUtilDO();
			new TaxonomyExcelFileHandler().handleFile(userInput);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleFile(UserInputFileUtilDO userInput) throws Exception {
		try {

			ConfigHandler configFile = new ConfigHandler();
			configFile.load(new FileInputStream("C:\\Users\\Atripathi\\Downloads\\SlackDownloads\\generic.txt"));
			List<String> headerList = new ArrayList<String>();
			for (String key : configFile.keySet())
				headerList.add(key);
			File inputFile = new File("C:\\Users\\Atripathi\\Downloads\\SlackDownloads\\TaxonomyTemplate.xlsx");

			Map<Integer, List<String>> excelVal = new TreeMap<Integer, List<String>>();

			ExcelDO excelValues = new ExcelDO();

			readExcel(inputFile, excelValues, configFile);

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(HandlerConstants.CONTEXT1);
			stepProductInformation.setWorkspaceID(HandlerConstants.MAIN);

			ProductsType productsType = objectFactory.createProductsType();
			List<ProductType> level1ProductList = productsType.getProduct();
			Map<String, TaxonomyExcelInfo> level1Map = excelValues.structureMapList.get(0);
			for (Entry<String, TaxonomyExcelInfo> eachLevel1 : level1Map.entrySet()) {

				int levelnum = 0;
				TaxonomyExcelInfo infoObjectEachLevel1 = eachLevel1.getValue();
				ProductType level1 = getProductTypeFromFactory(objectFactory, infoObjectEachLevel1, excelValues,
						configFile, levelnum + 1);

				level1ProductList.add(level1);
			}

			stepProductInformation.setProducts(productsType);

			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			File file = new File(
					"C:\\Users\\Atripathi\\Downloads\\SlackDownloads\\Test" + df.format(new Date()) + ".xml");

			jaxbMarshaller.marshal((stepProductInformation), file);
//			jaxbMarshaller.marshal(stepProductInformation, System.out);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private void readExcel(File inputFile, ExcelDO excelValues, ConfigHandler configFile) throws Exception {
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

						String cellValue = df.formatCellValue(cell);
						if (isNullOrBlank(cellValue)) {
							continue;
						}
						int cellnum = cell.getColumnIndex();
						if (cellnum < structInfo.size()) {
							switch (cellnum) {
							case 0:
								structInfo.get(cellnum).setName(cellValue);
								structInfo.get(cellnum).setObjectType(configFile.get("Level" + cellnum + 1));
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
						} else {
							switch (cellnum) {
							default:
								/*
								 * if (key.equals(cell.getColumnIndex())) {
								 * currentStructure.setUniqueKeyID(properties.getProperty("KeyID"));
								 * currentStructure.setUniqueKeyValue(cellValue); } else if
								 * (metaData.containsKey(cell.getColumnIndex())) {
								 * currentStructure.getAttributeValues().put(metaData.get(cell.getColumnIndex())
								 * , cellValue); }
								 */
								break;
							}
						}

					}

					// get current structure
					for (int x = structInfo.size() - 1; x >= 0; x--) {
						if (x > 0 && !isNullOrBlank(structInfo.get(x).getName())) {
							addStructureToMap(currentStructure, structureMap.get(x), parentStructure,
									structureMap.get(x - 1));
							break;
						} else if (x == 0 && !isNullOrBlank(structInfo.get(x).getName())) {
							addStructureToMap(currentStructure, structureMap.get(x), null, null);
							break;
						}
					}
				}
			}
			excelValues.setValues(structureMap);
			fs.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

	}

	public static boolean isNullOrBlank(String param) {
		if (param == null || param.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	private static void addStructureToMap(TaxonomyExcelInfo currentStructure,
			Map<String, TaxonomyExcelInfo> currentStructureMap, TaxonomyExcelInfo parentStructure,
			Map<String, TaxonomyExcelInfo> parentStructureMap) {
		if (!currentStructureMap.containsKey(currentStructure.getStructurePath())) {
			currentStructureMap.put(currentStructure.getStructurePath(), currentStructure);
			if (parentStructure != null && parentStructureMap.get(parentStructure.getStructurePath()) != null) {
				parentStructureMap.get(parentStructure.getStructurePath()).getChildren()
						.add(currentStructure.getStructurePath());
			}
			if (parentStructureMap != null && parentStructureMap.get(parentStructure.getStructurePath()) == null) {
				System.out.println(
						parentStructure.getStructurePath() + "for current value" + currentStructure.getStructurePath());
			}

		}

	}

	private static ProductType getProductTypeFromFactory(ObjectFactory objectFactory,
			TaxonomyExcelInfo infoObjectEachLevel1, ExcelDO excelValues, ConfigHandler configFile, int levelnum) {

		ProductType product = objectFactory.createProductType();
		NameType name = objectFactory.createNameType();
		name.setContent(infoObjectEachLevel1.getName());

		product.getName().add(name);

		product.setUserTypeID(configFile.get("Level" + levelnum));

		List<ProductType> subProductList = new ArrayList<>();
		for (String eachLevel : infoObjectEachLevel1.getChildren()) {
			TaxonomyExcelInfo infoObjectEachlevel = excelValues.structureMapList.get(levelnum).get(eachLevel);
			ProductType subProduct = getProductTypeFromFactory(objectFactory, infoObjectEachlevel, excelValues,
					configFile, levelnum + 1);
			subProductList.add(subProduct);
		}
		product.getProductOrSequenceProductOrSuppressedProductCrossReference().addAll(subProductList);
		return product;
	}
}
