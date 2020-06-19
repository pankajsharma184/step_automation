package com.codifyd.automation.bgp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONHandler {

	public static void writeJSONtoExcel(List<JSONObject> list, String outputFilePath) throws Exception, ParseException {
		// parse error rows to excel data
		int i = 0;
		TreeMap<Integer, ArrayList<String>> map = new TreeMap<Integer, ArrayList<String>>();
		List<String> errors = new ArrayList<>();
		for (JSONObject obj : list) {
			ArrayList<String> set = new ArrayList<String>();
			String entryText = (String) obj.get("entryText");
			if (entryText.contains("step://attribute")) {
				String attributeID = getAttributeID(entryText);
				String productID = getProductID(entryText);
				String errorValue = getErrorValue(entryText, attributeID, productID);

				set.add(attributeID);
				set.add(productID);
				set.add(errorValue);
				map.put(i, set);

				i++;
			} else {
				errors.add(entryText);
			}
		}
		// write to excel
		writeToExcel(map, outputFilePath);
		writeErrorToExcel(errors, outputFilePath);
		System.out.println("File Generated in path : " + outputFilePath);

	}

	public static String getAttributeID(String entryText) {
		int beginIndex = entryText.indexOf("step://attribute?id=") + "step://attribute?id=".length();
		int endIndex = entryText.indexOf('\"', beginIndex);
		String attribute = entryText.substring(beginIndex, endIndex);
		return attribute;
	}

	public static String getProductID(String entryText) {
		int beginIndex = entryText.indexOf("step://product?id=") + "step://product?id=".length();
		int endIndex = entryText.indexOf('\"', beginIndex);
		String product = entryText.substring(beginIndex, endIndex);
		return product;
	}

	public static String getErrorValue(String entryText, String attributeID, String productID) {
		int beginIndex = entryText.indexOf("isn\'t valid (") + "isn\'t valid (".length();
		int endIndex = entryText.indexOf(")", beginIndex);
		String errorValue = entryText.substring(beginIndex, endIndex);

		return errorValue;
	}

	private static void writeToExcel(TreeMap<Integer, ArrayList<String>> map, String outputFilePath) throws Exception {
		try {
			XSSFWorkbook wb = new XSSFWorkbook();
			// Create a blank sheet
			XSSFSheet sheet = wb.createSheet("ErrorInformation");
			// Create row object
			XSSFRow row;
			// Create cell object
			XSSFCell cell;
			// Excel cell color
			XSSFCellStyle cellStyle;

			Set<String> headerSet = new HashSet<String>();
			for (Integer i : map.keySet()) {
				headerSet.add(map.get(i).get(0));
			}

			ArrayList<String> headerList = new ArrayList<String>();
			headerList.add("ProductID");
			headerList.addAll(headerSet);

			LinkedHashMap<String, ArrayList<String>> excelData = new LinkedHashMap<String, ArrayList<String>>();
			excelData.put("ProductID", headerList);
			for (Integer i : map.keySet()) {
				ArrayList<String> list = new ArrayList<String>();
				for (int x = 0; x < headerList.size(); x++) {
					list.add("");
				}
				String key = map.get(i).get(1);
				String value = map.get(i).get(2);
				list.set(0, key);
				list.set(headerList.indexOf(map.get(i).get(0)), value);
				if (excelData.containsKey(key)) {
					excelData.get(key).set(headerList.indexOf(map.get(i).get(0)), value);
				} else {
					excelData.put(key, list);
				}
			}

			Set<String> keyid = excelData.keySet();
			int rowid = 0;
			for (String key : keyid) {
				row = sheet.createRow(rowid++);
				List<String> dataList = excelData.get(key);
				int cellid = 0;
				for (String obj : dataList) {
					cell = row.createCell(cellid++);
					cell.setCellValue(obj);
					if (rowid == 1) {
						cellStyle = wb.createCellStyle();
						cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(50, 120, 180)));
						cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						cell.setCellStyle(cellStyle);
					}
				}

			}

			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(outputFilePath);
			wb.write(out);
			out.close();
			wb.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	private static void writeErrorToExcel(List<String> errors, String outputFilePath) throws Exception {
		try {
			FileInputStream inputStream = new FileInputStream(new File(outputFilePath));
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			// Create a blank sheet
			XSSFSheet sheet = wb.createSheet("Other Errors");
			// Create row object
			XSSFRow row;
			// Create cell object
			XSSFCell cell;
			// Excel cell color
			XSSFCellStyle cellStyle;

			// add header
			row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue("Errors");
			cellStyle = wb.createCellStyle();
			cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(50, 120, 180)));
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell.setCellStyle(cellStyle);

			// add data
			int rowid = 1;
			for (String eachErrorText : errors) {
				row = sheet.createRow(rowid);
				cell = row.createCell(0);
				cell.setCellValue(eachErrorText);
				rowid++;
			}
			inputStream.close();
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(outputFilePath);
			wb.write(out);
			out.close();
			wb.close();
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}

	}

	public static List<JSONObject> getErrorList(Set<String> urlList, Authenticator authenticator)
			throws IOException, ParseException {

		BufferedReader br = null;

		JSONParser jsonParser = new JSONParser();
		List<JSONObject> list = new ArrayList<JSONObject>();

		Authenticator.setDefault(authenticator);

		for (String urlString : urlList) {
			URL url = new URL(urlString);
			HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
			http.setAllowUserInteraction(true);
			http.setRequestMethod("GET");
			http.connect();
			if (http.getResponseCode() != 200) {

				throw new RuntimeException(
						"Failed : HTTP Error code : " + http.getResponseCode() + "with url -" + urlString);
			}
			InputStreamReader in = new InputStreamReader(http.getInputStream());
			br = new BufferedReader(in);

			String str;
			while ((str = br.readLine()) != null) {
				JSONArray json = (JSONArray) jsonParser.parse(str);
				for (Object eachjsonObject : json) {
					JSONObject jsonObject = (JSONObject) eachjsonObject;
					String entryType = (String) jsonObject.get("entryType");
					if (entryType.equals("error")) {
						list.add(jsonObject);
					}
				}
			}

			http.disconnect();
		}

		if (br != null) {
			br.close();
		}
		return list;

	}

}
