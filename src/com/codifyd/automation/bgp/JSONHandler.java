package com.codifyd.automation.bgp;

import static com.codifyd.automation.bgp.Util.getAttributeID;
import static com.codifyd.automation.bgp.Util.getErrorValue;
import static com.codifyd.automation.bgp.Util.getProductID;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

	public static void jsonHandler(List<JSONObject> list, String outputFileName, String outputPath)
			throws IOException, ParseException {
//		System.out.println(list.size());
		String output = "";
		if (outputPath.endsWith("\\")) {
			output = outputPath + outputFileName;
		} else {
			output = outputPath + "\\" + outputFileName;
		}
		int i = 0;
		TreeMap<Integer, ArrayList<String>> map = new TreeMap<Integer, ArrayList<String>>();
		for (JSONObject obj : list) {
			ArrayList<String> set = new ArrayList<String>();
			String entryText = (String) obj.get("entryText");
			String attributeID = getAttributeID(entryText);
			String productID = getProductID(entryText);
			String errorValue = getErrorValue(entryText, attributeID, productID);

			set.add(attributeID);
			set.add(productID);
			set.add(errorValue);
			map.put(i, set);

			i++;
		}

		writeExcel(map, output);

	}

	@SuppressWarnings("deprecation")
	private static void writeExcel(TreeMap<Integer, ArrayList<String>> map, String output) {
		try {
			XSSFWorkbook wb = new XSSFWorkbook();
			// Create a blank sheet
			XSSFSheet sheet = wb.createSheet("ErrorInfo");
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
			FileOutputStream out = new FileOutputStream(output);
			wb.write(out);
			out.close();
			wb.close();
			System.out.println("File Generated in path : " + output);
			System.out.println("=======================Error File Generated=======================\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public static List<JSONObject> getErrorList(Set<String> urlList, String outputPath)
			throws IOException, ParseException {

		BufferedReader br = null;
		String out = outputPath + "\\ErrorList.txt";
		@SuppressWarnings("resource")
		BufferedWriter bw = new BufferedWriter(new FileWriter(out));

		JSONParser jsonParser = new JSONParser();
		List<JSONObject> list = new ArrayList<JSONObject>();

		Authenticator.setDefault(new MyAuthenticator());

		for (String urlString : urlList) {
			System.out.println(urlString);
			URL url = new URL(urlString);
//			System.out.println(urlText[0] + bgpID + urlText[1]);
			HttpsURLConnection http = (HttpsURLConnection) url.openConnection();

			http.setAllowUserInteraction(true);
			http.setRequestMethod("GET");
			http.connect();
			if (http.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + http.getResponseCode());
			}
			InputStreamReader in = new InputStreamReader(http.getInputStream());
			br = new BufferedReader(in);

			String str;
			while ((str = br.readLine()) != null) {
				bw.write(str);
				JSONArray json = (JSONArray) jsonParser.parse(str);
				json.forEach(err -> parseJsonObject((JSONObject) err, list));
			}
			bw.flush();
			http.disconnect();
		}

		br.close();
		bw.close();

		return list;

	}

	private static void parseJsonObject(JSONObject jsonObject, List<JSONObject> list) {
		String entryType = (String) jsonObject.get("entryType");
		if (entryType.equals("error")) {
			list.add(jsonObject);
		}
	}
}
