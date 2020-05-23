package com.codifyd.automation.uom;

import java.util.List;
import java.util.Map;

import com.codifyd.stepxsd.MetaDataType;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.UnitConversionType;
import com.codifyd.stepxsd.UnitFamilyType;
import com.codifyd.stepxsd.UnitType;
import com.codifyd.stepxsd.ValueType;

public class UomXMLWriter {

	public static UnitFamilyType familyHandler(ObjectFactory objectFactory, List<UomExcelInfo> uomInfo, String familyID) {
		UnitFamilyType unitFamily = objectFactory.createUnitFamilyType();
		unitFamily.setID(familyID);
		NameType nameType = objectFactory.createNameType();
		nameType.setContent(uomInfo.get(0).getParentName());
		unitFamily.getName().add(nameType);
		List<UnitType> unitList = unitFamily.getUnit();
		for (UomExcelInfo unitInfo : uomInfo) {
			UnitType unit = unitHandler(objectFactory, unitInfo);
			unitList.add(unit);
		}
		return unitFamily;
	}

	public static UnitType unitHandler(ObjectFactory objectFactory, UomExcelInfo unitInfo) {
		UnitType unit = objectFactory.createUnitType();
		NameType nameType = objectFactory.createNameType();
		nameType.setContent(unitInfo.getName());

		unit.setID(unitInfo.getId());
		unit.getName().add(nameType);

		unit.setReferenced(Boolean.parseBoolean(unitInfo.getReferenced().toLowerCase()));

		UnitConversionType unitConverSion = objectFactory.createUnitConversionType();
		unitConverSion.setBaseUnitID(unitInfo.getBaseUnitId());
		unitConverSion.setFactor(unitInfo.getFactor());
		unitConverSion.setOffset(unitInfo.getOffset());
		unit.setUnitConversion(unitConverSion);

		if (null != unitInfo.getUomMetadata()) {
			Map<String, String> map = unitInfo.getUomMetadata();
			MetaDataType meta = objectFactory.createMetaDataType();
			List<Object> valueList = meta.getValueOrMultiValueOrValueGroup();
			for (String key2 : map.keySet()) {
				String str = map.get(key2);
				if (str.split(";|,|\\|").length <= 1) {
					ValueType value = objectFactory.createValueType();
					value.setAttributeID(key2);
					value.setContent(str);
					valueList.add(value);
				} else {
					List<Object> multiValueList = objectFactory.createMultiValueType().getValueOrValueGroup();
					for (String val : str.split(";|,|\\|")) {
						ValueType value = objectFactory.createValueType();
						value.setAttributeID(key2);
						value.setContent(val);
						multiValueList.add(value);
					}
					valueList.add(multiValueList);
				}
			}
			unit.setMetaData(meta);
		}

		return unit;
	}

}
