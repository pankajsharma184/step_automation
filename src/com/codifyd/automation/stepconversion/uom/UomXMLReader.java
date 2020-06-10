package com.codifyd.automation.stepconversion.uom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.codifyd.stepxsd.MultiValueType;
import com.codifyd.stepxsd.UnitConversionType;
import com.codifyd.stepxsd.UnitFamilyType;
import com.codifyd.stepxsd.UnitType;
import com.codifyd.stepxsd.ValueType;

public class UomXMLReader {
	public static Integer familyHandler(Object[] args) throws Exception {
		int i = (int) args[0];
		try {
			List<Object> unitListType = (List<Object>) args[1];
			ArrayList<String> headerList1 = (ArrayList<String>) args[2];
			ArrayList<String> headerList2 = (ArrayList<String>) args[3];
			TreeMap<Integer, List<String>> uomMap = (TreeMap<Integer, List<String>>) args[4];
			TreeMap<String, Map<String, String>> metadataMap = (TreeMap<String, Map<String, String>>) args[5];
			String delim = (String) args[6];

			for (Object family : unitListType) {
				if (family instanceof UnitFamilyType) {
					String unitGroupId = ((UnitFamilyType) family).getID();
					String unitGroupName = !((UnitFamilyType) family).getName().isEmpty()
							? ((UnitFamilyType) family).getName().get(0).getContent()
							: "";

					List<?> unitList = ((UnitFamilyType) family).getUnit();
					Object[] args2 = new Object[] { i, (List<Object>) unitList, headerList1, headerList2, uomMap,
							metadataMap, delim, unitGroupId, unitGroupName };
					i = unitHandler(args2);
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return i;
	}

	public static Integer unitHandler(Object[] args) throws Exception {
		int i = (int) args[0];
		try {
			List<Object> unitListType = (List<Object>) args[1];
			ArrayList<String> headerList1 = (ArrayList<String>) args[2];
			ArrayList<String> headerList2 = (ArrayList<String>) args[3];
			TreeMap<Integer, List<String>> uomMap = (TreeMap<Integer, List<String>>) args[4];
			TreeMap<String, Map<String, String>> metadataMap = (TreeMap<String, Map<String, String>>) args[5];
			String delim = (String) args[6];
			String unitGroupID = (String) args[7];
			String unitGroupName = (String) args[8];

			for (Object unit : unitListType) {
				if (unit instanceof UnitType) {
					i++;
					List<String> data = new ArrayList<String>();
					for (int index = data.size(); index <= headerList1.size(); index++) {
						data.add("");
					}
					
					Map<String, String> map = new HashMap<String, String>();

					String unitId = ((UnitType) unit).getID();
					String unitName = !((UnitType) unit).getName().isEmpty()
							? ((UnitType) unit).getName().get(0).getContent()
							: "";
					String reference = String.valueOf(((UnitType) unit).isReferenced());

					String conversionFactor = "";
					String conversionoffset = "";
					String conversionBaseUnitId = "";
					if (null != ((UnitType) unit).getUnitConversion()) {
						UnitConversionType conversion = ((UnitType) unit).getUnitConversion();
						conversionFactor = conversion.getFactor();
						conversionoffset = conversion.getOffset();
						conversionBaseUnitId = conversion.getBaseUnitID();
					}

					if (null != ((UnitType) unit).getMetaData()
							&& !((UnitType) unit).getMetaData().getValueOrMultiValueOrValueGroup().isEmpty()) {
						for (Object metaDataValue : ((UnitType) unit).getMetaData()
								.getValueOrMultiValueOrValueGroup()) {
							if (metaDataValue instanceof ValueType) {
								String id = ((ValueType) metaDataValue).getAttributeID();
								String value = ((ValueType) metaDataValue).getContent();
								map.put(id, value);
							} else if (metaDataValue instanceof MultiValueType) {
								String id = "";
								StringBuffer valueBuffer = new StringBuffer();
								for (Object metaDataValue2 : ((MultiValueType) metaDataValue).getValueOrValueGroup()) {
									id = ((ValueType) metaDataValue2).getAttributeID();
									valueBuffer.append(((ValueType) metaDataValue2).getContent());
									valueBuffer.append(delim);
								}
								String value = valueBuffer.toString();
								if (!id.equals(""))
									map.put(id, value);
							}

						}
						metadataMap.put(unitId + unitGroupID, map);
					}

					data.set(headerList2.indexOf("Unit_ID"), unitId);
					data.set(headerList2.indexOf("Unit_Name"), unitName);
					data.set(headerList2.indexOf("Unit_Group_ID"), unitGroupID);
					data.set(headerList2.indexOf("Unit_Group_Name"), unitGroupName);
					data.set(headerList2.indexOf("Referenced"), reference);
					data.set(headerList2.indexOf("Unit_Conversion_BaseUNIT_ID"), conversionBaseUnitId);
					data.set(headerList2.indexOf("Unit_Conversion_Factor"), conversionFactor);
					data.set(headerList2.indexOf("Unit_Conversion_Offset"), conversionoffset);

					uomMap.put(i, data);

				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return i;
	}

	public static void getMetaDataValue(Map<String, String> metadataValues, List<String> uomData,
			List<String> headerList) {
		if (!metadataValues.isEmpty())
			for (Map.Entry<String, String> itr : metadataValues.entrySet()) {
				String metaAttrID = itr.getKey();
				String metaAttrValue = itr.getValue();
				int index = headerList.indexOf(metaAttrID);
				uomData.add(index, metaAttrValue);
			}
	}
}
