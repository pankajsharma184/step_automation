package com.codifyd.automation.xmlextractor;

import java.io.File;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.codifyd.stepxsd.AttributeGroupListType;
import com.codifyd.stepxsd.AttributeListType;
import com.codifyd.stepxsd.BusinessLibrariesType;
import com.codifyd.stepxsd.BusinessRulesType;
import com.codifyd.stepxsd.IntegrationEndpointsType;
import com.codifyd.stepxsd.KeysType;
import com.codifyd.stepxsd.ListOfValuesGroupListType;
import com.codifyd.stepxsd.ListsOfValuesType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.UserGroupListType;
import com.codifyd.stepxsd.UserListType;

//class to convert StepXML to smaller classes based on ID for migration
public class StepDataXMLHandler {

	public void handleFile(XMLExtractorInputUtil xmlExtractorInputUtil) throws Exception {
		try {

			// Validate Input File
			String inputFilePath = xmlExtractorInputUtil.getInputFilePath();
			if (inputFilePath != null && !inputFilePath.trim().isEmpty()) {
				if (new File(inputFilePath).isFile()) {
					if (!inputFilePath.trim().endsWith(".xml")) {
						throw new Exception("Input File is not a XML file at path -  " + inputFilePath);
					}
				} else {
					throw new Exception("Input File Not Found at given path - " + inputFilePath);
				}
			} else {
				throw new Exception("Input File Path is Empty");
			}

			// Read the Step xml
			File inputStepXML = new File(inputFilePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(STEPProductInformation.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			STEPProductInformation inputStepProductInfo = (STEPProductInformation) unmarshaller.unmarshal(inputStepXML);

			// create the output step xml
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(inputStepProductInfo.getContextID());
			stepProductInformation.setWorkspaceID(inputStepProductInfo.getWorkspaceID());

			// for each tag , read string , get ids, write ids			
			List<String> BusinessRuleIDs = getIDsFromString(xmlExtractorInputUtil.getBusinessRuleIDs());
			if (!BusinessRuleIDs.isEmpty()) {
				writeBusinessRulesToOutput(inputStepProductInfo, BusinessRuleIDs, objectFactory,
						stepProductInformation);
			}
			List<String> BusinessLibraryIDs = getIDsFromString(xmlExtractorInputUtil.getBusinessLibraryIDs());
			if (!BusinessLibraryIDs.isEmpty()) {
				writeBusinessLibrariesToOutput(inputStepProductInfo, BusinessLibraryIDs, objectFactory,
						stepProductInformation);
			}
			List<String> AttributeIDs = getIDsFromString(xmlExtractorInputUtil.getAttributeIDs());
			if (!AttributeIDs.isEmpty()) {
				writeAttributesToOutput(inputStepProductInfo, AttributeIDs, objectFactory,
						stepProductInformation);
			}
			List<String> AttributeGroupIDs = getIDsFromString(xmlExtractorInputUtil.getAttributeGroupIDs());
			if (!AttributeGroupIDs.isEmpty()) {
				writeAttributeGroupsToOutput(inputStepProductInfo, AttributeGroupIDs, objectFactory,
						stepProductInformation);
			}
			List<String> LOVIDs = getIDsFromString(xmlExtractorInputUtil.getLOVIDs());
			if (!LOVIDs.isEmpty()) {
				writeLOVsToOutput(inputStepProductInfo, LOVIDs, objectFactory,
						stepProductInformation);
			}
			List<String> LOVGroupIDs = getIDsFromString(xmlExtractorInputUtil.getLOVGroupIDs());
			if (!LOVGroupIDs.isEmpty()) {
				writeLOVGroupsToOutput(inputStepProductInfo, LOVGroupIDs, objectFactory,
						stepProductInformation);
			}

			List<String> userIDs = getIDsFromString(xmlExtractorInputUtil.getUserIDs());
			if (!userIDs.isEmpty()) {
				writeUsersToOutput(inputStepProductInfo, userIDs, objectFactory,
						stepProductInformation);
			}
			
			List<String> userGroupIDs = getIDsFromString(xmlExtractorInputUtil.getUserGroupIDs());
			if (!userGroupIDs.isEmpty()) {
				writeUserGroupsToOutput(inputStepProductInfo, userGroupIDs, objectFactory,
						stepProductInformation);
			}
			
			List<String> keyIDs = getIDsFromString(xmlExtractorInputUtil.getKeyIDs());
			if (!keyIDs.isEmpty()) {
				writeKeysToOutput(inputStepProductInfo, keyIDs, objectFactory,
						stepProductInformation);
			}
			
			List<String> IntegrationEndpointIDs = getIDsFromString(xmlExtractorInputUtil.getIntegrationEndpointIDs());
			if (!IntegrationEndpointIDs.isEmpty()) {
				writeIntegrationEndpointToOutput(inputStepProductInfo, IntegrationEndpointIDs, objectFactory,
						stepProductInformation);
			}

			
			// write to outut stepxml
			File outputStepXML = new File(
					Paths.get(new File(getDefaultOutputDirectoryFromInput(inputFilePath)).getPath(),
							setDefaultXMLFilenameFromInput(inputFilePath)).toUri());

			// output pretty printed
			JAXBContext stepjaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Marshaller stepjaxbMarshaller = stepjaxbContext.createMarshaller();
			stepjaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			stepjaxbMarshaller.marshal(stepProductInformation, outputStepXML);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	private void writeIntegrationEndpointToOutput(STEPProductInformation inputStepProductInfo,
			List<String> IntegrationEndpointIDs, ObjectFactory objectFactory,
			STEPProductInformation stepProductInformation) {
		IntegrationEndpointsType integrationEndpointsType = objectFactory.createIntegrationEndpointsType();
		inputStepProductInfo.getIntegrationEndpoints().getOutBoundIntegrationEndpoint().forEach(eachEndpoint -> {
			if (IntegrationEndpointIDs.contains(eachEndpoint.getID())) {
				integrationEndpointsType.getOutBoundIntegrationEndpoint().add(eachEndpoint);
			}
		});
		inputStepProductInfo.getIntegrationEndpoints().getInBoundIntegrationEndpoint().forEach(eachEndpoint -> {
			if (IntegrationEndpointIDs.contains(eachEndpoint.getID())) {
				integrationEndpointsType.getInBoundIntegrationEndpoint().add(eachEndpoint);
			}
		});
		stepProductInformation.setIntegrationEndpoints(integrationEndpointsType);
	}

	private void writeAttributesToOutput(STEPProductInformation inputStepProductInfo, List<String> attributeIDs,
			ObjectFactory objectFactory, STEPProductInformation stepProductInformation) {
		AttributeListType attributeListType = objectFactory.createAttributeListType();
		inputStepProductInfo.getAttributeList().getAttribute().forEach(eachAttribute -> {
			if (attributeIDs.contains(eachAttribute.getID())) {
				attributeListType.getAttribute().add(eachAttribute);
			}
		});
		stepProductInformation.setAttributeList(attributeListType);
		
	}
	private void writeAttributeGroupsToOutput(STEPProductInformation inputStepProductInfo, List<String> attributeGroupIDs,
			ObjectFactory objectFactory, STEPProductInformation stepProductInformation) {
		AttributeGroupListType attributeGroupListType = objectFactory.createAttributeGroupListType();
		inputStepProductInfo.getAttributeGroupList().getAttributeGroup().forEach(eachAttributeGroup -> {
			if (attributeGroupIDs.contains(eachAttributeGroup.getID())) {
				attributeGroupListType.getAttributeGroup().add(eachAttributeGroup);
			}
		});
		stepProductInformation.setAttributeGroupList(attributeGroupListType);		
	}
	
	private void writeLOVsToOutput(STEPProductInformation inputStepProductInfo, List<String> LOVIDs,
			ObjectFactory objectFactory, STEPProductInformation stepProductInformation) {
		ListsOfValuesType listsOfValuesType = objectFactory.createListsOfValuesType();
		inputStepProductInfo.getListsOfValues().getListOfValue().forEach(eachLOV -> {
			if (LOVIDs.contains(eachLOV.getID())) {
				listsOfValuesType.getListOfValue().add(eachLOV);
			}
		});
		stepProductInformation.setListsOfValues(listsOfValuesType);
		
	}
	private void writeLOVGroupsToOutput(STEPProductInformation inputStepProductInfo, List<String> LOVGroupIDs,
			ObjectFactory objectFactory, STEPProductInformation stepProductInformation) {
		ListOfValuesGroupListType listOfValuesGroupListType = objectFactory.createListOfValuesGroupListType();
		inputStepProductInfo.getListOfValuesGroupList().getListOfValuesGroup().forEach(eachLOVGroup -> {
			if (LOVGroupIDs.contains(eachLOVGroup.getID())) {
				listOfValuesGroupListType.getListOfValuesGroup().add(eachLOVGroup);
			}
		});
		stepProductInformation.setListOfValuesGroupList(listOfValuesGroupListType);		
	}

	private void writeBusinessRulesToOutput(STEPProductInformation inputStepProductInfo, List<String> businessRuleIDs,
			ObjectFactory objectFactory, STEPProductInformation stepProductInformation) {
		BusinessRulesType businessRulesType = objectFactory.createBusinessRulesType();
		inputStepProductInfo.getBusinessRules().getBusinessRule().forEach(eachBusinessRule -> {
			if (businessRuleIDs.contains(eachBusinessRule.getID())) {
				businessRulesType.getBusinessRule().add(eachBusinessRule);
			}
		});
		stepProductInformation.setBusinessRules(businessRulesType);

	}

	private void writeBusinessLibrariesToOutput(STEPProductInformation inputStepProductInfo,
			List<String> businessLibraryIDs, ObjectFactory objectFactory,
			STEPProductInformation stepProductInformation) {
		BusinessLibrariesType businessLibrariesType = objectFactory.createBusinessLibrariesType();
		inputStepProductInfo.getBusinessLibraries().getBusinessRule().forEach(eachBusinessRule -> {
			if (businessLibraryIDs.contains(eachBusinessRule.getID())) {
				businessLibrariesType.getBusinessRule().add(eachBusinessRule);
			}
		});
		stepProductInformation.setBusinessLibraries(businessLibrariesType);

	}
	
	private void writeUserGroupsToOutput(STEPProductInformation inputStepProductInfo,
			List<String> userGroupIDs, ObjectFactory objectFactory,
			STEPProductInformation stepProductInformation) {
		UserGroupListType userGroupListType = objectFactory.createUserGroupListType();
		inputStepProductInfo.getUserGroupList().getUserGroup().forEach(eachUserGroup -> {
			if (userGroupIDs.contains(eachUserGroup.getID())){
				userGroupListType.getUserGroup().add(eachUserGroup);
			}
		});
		stepProductInformation.setUserGroupList(userGroupListType);
	}

	private void writeUsersToOutput(STEPProductInformation inputStepProductInfo,
			List<String> userIDs, ObjectFactory objectFactory,
			STEPProductInformation stepProductInformation) {
		UserListType userListType = objectFactory.createUserListType();
		inputStepProductInfo.getUserList().getUser().forEach(eachUser -> {
			if (userIDs.contains(eachUser.getID())){
				userListType.getUser().add(eachUser);
			}
		});
		stepProductInformation.setUserList(userListType);
	}
	
	private void writeKeysToOutput(STEPProductInformation inputStepProductInfo,
			List<String> KeyIDs, ObjectFactory objectFactory,
			STEPProductInformation stepProductInformation) {
		KeysType keysType = objectFactory.createKeysType();
		inputStepProductInfo.getKeys().getKey().forEach(eackKey -> {
			if (KeyIDs.contains(eackKey.getID())){
				keysType.getKey().add(eackKey);
			}
		});
		stepProductInformation.setKeys(keysType);
	}

	
	private List<String> getIDsFromString(String IDStrings) {
		if (isNullOrBlank(IDStrings)) {
			return new ArrayList<>();
		}
		return Arrays.asList(IDStrings.replaceAll("[,\\s]+$", ",").split(","));
	}

	public static String getDefaultOutputDirectoryFromInput(String inputFilePath) {
		File tmpFile = new File(inputFilePath);
		return tmpFile.getParentFile().getAbsolutePath();
	}

	public static String setDefaultXMLFilenameFromInput(String inputFilePath) {
		File tmpFile = new File(inputFilePath);
		String fileName = tmpFile.getName();
		int pos = fileName.lastIndexOf(".");
		if (pos > 0 && pos < (fileName.length() - 1)) { // If '.' is not the
														// first or last
														// character.
			fileName = fileName.substring(0, pos);
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		return "Extracted_" + fileName + "_" + df.format(new Date()) + ".xml";
	}

	public static boolean isNullOrBlank(String param) {
		if (param == null || param.trim().length() == 0) {
			return true;
		}
		return false;
	}

}
