package com.codifyd.automation.attribute;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import com.codifyd.automation.util.SampleFileHelperUtil;

public class AttributeSampleInfo {

	public File getSamplePropertiesFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSamplePropertiesFile(SampleFileHelperUtil.ATTRIBUTE);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "AttributeConfigInputSample.properties")
				.toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);

		return outputFile;
	}

	public File getSampleInputExcelFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSampleExcelFile(SampleFileHelperUtil.ATTRIBUTE);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "AttributeExcelSampleInput.xlsx").toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);

		return outputFile;
	}

	public File getSampleInputXMLFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSampleXMLFile(SampleFileHelperUtil.ATTRIBUTE);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "AttributeXMLInputSample.xml").toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);

		return outputFile;
	}

}
