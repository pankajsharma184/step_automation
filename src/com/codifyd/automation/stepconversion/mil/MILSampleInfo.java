package com.codifyd.automation.stepconversion.mil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import com.codifyd.automation.stepconversion.util.AutomationConstants;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.SampleFileHelperUtil;

public class MILSampleInfo {
	
	public File getSampleConfigPropertiesFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSamplePropertiesFile(HandlerConstants.MIL);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "MILConfig.properties")
				.toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);

		return outputFile;
	}
	
	public File getSampleInputXMLFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSampleXMLFile(HandlerConstants.MIL);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "MilXMLInputSample.xml").toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);
		return outputFile;
	}

}
