package com.codifyd.automation.stepconversion.attributelink;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.SampleFileHelperUtil;

public class AttributeLinkSampleInfo {

	public File getSamplePropertiesFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSamplePropertiesFile(HandlerConstants.ATTRIBUTELINK);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "AttributeLinkConfigInputSample.properties")
				.toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);

		return outputFile;
	}

	public File getSampleInputExcelFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSampleExcelFile(HandlerConstants.ATTRIBUTELINK);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "AttributeLinkExcelSampleInput.xlsx").toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);

		return outputFile;
	}

	public File getSampleInputXMLFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSampleXMLFile(HandlerConstants.ATTRIBUTELINK);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "AttributeLinkXMLInputSample.xml").toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);

		return outputFile;
	}

}
