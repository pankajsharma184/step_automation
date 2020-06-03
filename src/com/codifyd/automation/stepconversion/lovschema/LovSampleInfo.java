package com.codifyd.automation.stepconversion.lovschema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import com.codifyd.automation.stepconversion.util.AutomationConstants;
import com.codifyd.automation.stepconversion.util.SampleFileHelperUtil;

public class LovSampleInfo {

	public File getSamplePropertiesFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSamplePropertiesFile(AutomationConstants.ATTRIBUTELINK);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "LovConfigInputSample.properties")
				.toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);

		return outputFile;
	}

	public File getSampleInputExcelFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSampleExcelFile(AutomationConstants.ATTRIBUTELINK);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "LovExcelSampleInput.xlsx").toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);

		return outputFile;
	}

	public File getSampleInputXMLFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSampleXMLFile(AutomationConstants.ATTRIBUTELINK);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "LovXMLInputSample.xml").toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);

		return outputFile;
	}

}
