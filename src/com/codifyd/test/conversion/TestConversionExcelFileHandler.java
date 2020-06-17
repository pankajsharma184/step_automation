package com.codifyd.test.conversion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.codifyd.automation.stepconversion.attributelink.AttributeLinkExcelFileHandler;
import com.codifyd.automation.stepconversion.attributeschema.AttributeExcelFileHandler;
import com.codifyd.automation.stepconversion.context.ContextExcelFileHandler;
import com.codifyd.automation.stepconversion.context.DimensionExcelFileHandler;
import com.codifyd.automation.stepconversion.lovschema.LovExcelFileHandler;
import com.codifyd.automation.stepconversion.taxonomy.ClassificationTaxonomyExcelFileHandler;
import com.codifyd.automation.stepconversion.taxonomy.ProductTaxonomyExcelFileHandler;
import com.codifyd.automation.stepconversion.uom.UomExcelFileHandler;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.automation.util.AutomationConstants;
import com.codifyd.test.util.UserInputUtil;

public class TestConversionExcelFileHandler {
	public static void main(String[] args) {

		UserInputFileUtilDO defaultObject = getDefaultUserInputDO();

		Map<Integer, String> options = new HashMap<>();
		options.put(1, "AttributeSchema");
		options.put(2, "AttributeLinkSchema");
		options.put(3, "LOV");
		options.put(4, "UOM");
		options.put(5, "Product Taxonomy");
		options.put(6, "Classification Taxonomy");
		options.put(7, "Dimension");
		options.put(8, "Context");

		try {
			Integer choice = UserInputUtil.getUserSelectedOptionFromMap(options);
			switch (choice) {
			case 1:
				AttributeExcelFileHandler attributeExcelFileHandler = new AttributeExcelFileHandler();
				attributeExcelFileHandler
						.handleFile(UserInputUtil.getUserInput(AutomationConstants.ATTRIBUTE, defaultObject));
				break;

			case 2:
				AttributeLinkExcelFileHandler attributeLinkExcelFileHandler = new AttributeLinkExcelFileHandler();
				attributeLinkExcelFileHandler
						.handleFile(UserInputUtil.getUserInput(AutomationConstants.ATTRIBUTELINK, defaultObject));
				break;

			case 3:
				LovExcelFileHandler lovExcelFileHandler = new LovExcelFileHandler();
				lovExcelFileHandler.handleFile(UserInputUtil.getUserInput(AutomationConstants.LOV, defaultObject));
				break;

			case 4:
				UomExcelFileHandler uomExcelFIleHandler = new UomExcelFileHandler();
				uomExcelFIleHandler.handleFile(UserInputUtil.getUserInput(AutomationConstants.UOM, defaultObject));
				break;

			case 5:
				ProductTaxonomyExcelFileHandler productExcelFileHandler = new ProductTaxonomyExcelFileHandler();
				productExcelFileHandler
						.handleFile(UserInputUtil.getUserInput(AutomationConstants.PRODUCTTAXONOMY, defaultObject));
				break;

			case 6:
				ClassificationTaxonomyExcelFileHandler classificationExcelFileHandler = new ClassificationTaxonomyExcelFileHandler();
				classificationExcelFileHandler.handleFile(
						UserInputUtil.getUserInput(AutomationConstants.CLASSIFICATIONTAXONOMY, defaultObject));
				break;

			case 7:
				DimensionExcelFileHandler dimensionExcelFileHandler = new DimensionExcelFileHandler();
				dimensionExcelFileHandler
						.handleFile(UserInputUtil.getUserInput(AutomationConstants.DIMENSION, defaultObject));
				break;

			case 8:
				ContextExcelFileHandler contextExcelFileHandler = new ContextExcelFileHandler();
				contextExcelFileHandler
						.handleFile(UserInputUtil.getUserInput(AutomationConstants.CONTEXT, defaultObject));
				break;

			default:
				break;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static UserInputFileUtilDO getDefaultUserInputDO() {
		UserInputFileUtilDO defaultObject = new UserInputFileUtilDO();
		String inputFilePath = Paths.get(System.getProperty("user.home"), "Downloads", "conv1", "Context.xlsx")
				.toString();

		File tmpFile = new File(inputFilePath);
		String ouputDir = tmpFile.getParentFile().getAbsolutePath();

		String fileName = tmpFile.getName();
		int pos = fileName.lastIndexOf(".");
		if (pos > 0 && pos < (fileName.length() - 1)) {
			// If '.' is not the first or last character.
			fileName = fileName.substring(0, pos);
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String outputFilename = "Converted_" + fileName + "_" + df.format(new Date()) + ".xml";

		defaultObject.setInputPath(inputFilePath);
		defaultObject.setOutputPath(ouputDir);
		defaultObject.setFilename(outputFilename);

		return defaultObject;
	}

}
