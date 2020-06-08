package com.codifyd.automation.bgp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import com.codifyd.automation.stepconversion.util.AutomationConstants;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.SampleFileHelperUtil;

public class BGPReportSampleInfo {

	public File getSampleInputFile() throws IOException {
		InputStream inStream = SampleFileHelperUtil.getSampleInputFile(HandlerConstants.BGP);
		URI outputUri = Paths.get(System.getProperty("java.io.tmpdir"), "BGPReportInput.txt")
				.toUri();
		File outputFile = new File(outputUri);
		FileUtils.copyToFile(inStream, outputFile);

		return outputFile;
	}
}
