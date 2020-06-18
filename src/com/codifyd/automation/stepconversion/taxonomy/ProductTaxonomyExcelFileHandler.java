package com.codifyd.automation.stepconversion.taxonomy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.codifyd.automation.stepconversion.util.ConfigHandler;
import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.ProductType;
import com.codifyd.stepxsd.ProductsType;
import com.codifyd.stepxsd.STEPProductInformation;

import static com.codifyd.automation.stepconversion.taxonomy.TaxonomyExcelReader.readExcel;

public class ProductTaxonomyExcelFileHandler implements FileConversionHandler {

	@Override
	public void handleFile(UserInputFileUtilDO userInput) throws Exception {
		try {

			ConfigHandler configFile = userInput.getConfigFile();

			List<String> headerList = new ArrayList<String>();
			for (String key : configFile.keySet())
				headerList.add(key);

			File inputFile = new File(userInput.getInputPath());

			DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			File outputFile = new File(Paths.get(userInput.getOutputPath(), userInput.getFilename()).toString());

			List<String> excelError = new ArrayList<String>();
			Map<String, Map<String, String>> attributeValues = new TreeMap<String, Map<String, String>>();

			ExcelDO excelValues = new ExcelDO();

			readExcel(inputFile, excelValues, configFile, attributeValues, excelError);
			if (excelError.isEmpty()) {

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

				jaxbMarshaller.marshal((stepProductInformation), outputFile);
//			jaxbMarshaller.marshal(stepProductInformation, System.out);
			} else {
				File file = new File(
						Paths.get(userInput.getOutputPath(), "Errors", df.format(new Date()), ".txt").toString());
				FileWriter writer = new FileWriter(file);
				BufferedWriter buffer = new BufferedWriter(writer);
				for (String errors : excelError) {
					buffer.write(errors + "\n");
				}
				buffer.close();
				writer.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
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
