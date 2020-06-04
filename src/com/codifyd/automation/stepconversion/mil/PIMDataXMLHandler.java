package com.codifyd.automation.stepconversion.mil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.milxsd.MasterItem;
import com.codifyd.milxsd.MasterItemList;
import com.codifyd.milxsd.NameAndValueType;
import com.codifyd.milxsd.PartInfoType;
import com.codifyd.milxsd.Supplier;
import com.codifyd.stepxsd.DeleteProductType;
import com.codifyd.stepxsd.DeleteProductsType;
import com.codifyd.stepxsd.ProductType;
import com.codifyd.stepxsd.ProductsType;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.ValueType;
import com.codifyd.stepxsd.ValuesType;


//class to convert StepXML to MIL XML based on XSD based classes
public class PIMDataXMLHandler {
	
	//for type c, use isSkeleton as false
	//for type A, use isSkeleton as true
	public  void handleFile(UserInputFileUtilDO userInputFileUtilDO, boolean isSkeleton) {
		Properties properties = userInputFileUtilDO.getPropertiesFile();

		try {

			// Read the Step xml
			File inputStepXML = new File(userInputFileUtilDO.getInputPath());
			
			JAXBContext jaxbContext = JAXBContext.newInstance(STEPProductInformation.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			STEPProductInformation stepProductInfo = (STEPProductInformation) unmarshaller.unmarshal(inputStepXML);

			//write to MIL object
			com.codifyd.milxsd.ObjectFactory objectFactory = new com.codifyd.milxsd.ObjectFactory();
			MasterItemList masterItemList = objectFactory.createMasterItemList();

			masterItemList.getMasterItem().addAll(getMasterItemObjectsToAddFromStep(stepProductInfo.getProducts(),objectFactory,isSkeleton,properties));
			masterItemList.getMasterItem().addAll(getMasterItemObjectsToDeleteFromStep(stepProductInfo.getDeleteProducts(),objectFactory));

			File outputMilXML = new File(userInputFileUtilDO.getOutputPath()+"\\"+userInputFileUtilDO.getFilename());

			// output pretty printed
			JAXBContext miljaxbContext = JAXBContext.newInstance(com.codifyd.milxsd.ObjectFactory.class);
			Marshaller miljaxbMarshaller = miljaxbContext.createMarshaller();
			miljaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			miljaxbMarshaller.marshal(masterItemList, outputMilXML);
			
			
			

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		
	}




	private List<MasterItem> getMasterItemObjectsToAddFromStep(ProductsType products, com.codifyd.milxsd.ObjectFactory milObjectFactory,boolean isSkeleton,Properties properties) {
		List<MasterItem> masterItemList = new ArrayList<>();
		for(ProductType product: products.getProduct()){			
			MasterItem eachMasterItem = milObjectFactory.createMasterItem();
			eachMasterItem.setItemId(properties.getProperty("ItemIDPrefix")+product.getID());
			eachMasterItem.setAction("ADD");

			if(!isSkeleton){
				Supplier supplier = milObjectFactory.createSupplier();
				eachMasterItem.getSupplier().add(supplier);
				PartInfoType manufacturer =milObjectFactory.createPartInfoType();
				eachMasterItem.setManufacturer(manufacturer);			
				//property attribute ids
				List<String> propertyTagAttributes = Arrays.asList(properties.getProperty("PropertyAttributes").split(";"));

				for(Object productSubTag : product.getProductOrSequenceProductOrSuppressedProductCrossReference()){
					if(productSubTag instanceof ValuesType){
						for(Object valueSubTag: ((ValuesType)productSubTag).getValueOrMultiValueOrValueGroup()){
							if(valueSubTag instanceof ValueType){
								String attributeID = ((ValueType)valueSubTag).getAttributeID();
								//upc
								if(attributeID.equals(properties.getProperty("UPC"))){								
									eachMasterItem.setUPC(((ValueType)valueSubTag).getContent());								
								}
								//manufacturer part number
								if(attributeID.equals(properties.getProperty("ManufacturerPartNumber"))){								
									eachMasterItem.getManufacturer().setPartNumber(((ValueType)valueSubTag).getContent());								
								}
								//manufacturer name
								if(attributeID.equals(properties.getProperty("ManufacturerName"))){								
									eachMasterItem.getManufacturer().setName(((ValueType)valueSubTag).getContent());								
								}
								//supplier id
								if(attributeID.equals(properties.getProperty("SupplierID"))){															
									eachMasterItem.getSupplier().get(0).setId(((ValueType)valueSubTag).getContent());;															
								}
								//supplier name
								if(attributeID.equals(properties.getProperty("SupplierName"))){															
									eachMasterItem.getSupplier().get(0).setName(((ValueType)valueSubTag).getContent());															
								}
								//supplier number
								if(attributeID.equals(properties.getProperty("SupplierPartNumber"))){															
									eachMasterItem.getSupplier().get(0).setPartNumber(((ValueType)valueSubTag).getContent());															
								}
								//properties
								if(propertyTagAttributes.contains(attributeID)){
									NameAndValueType eachValue= milObjectFactory.createNameAndValueType();
									eachValue.setName(attributeID);
									eachValue.setValue(((ValueType)valueSubTag).getContent());
									eachMasterItem.getProperty().add(eachValue);
								}
							}
						}
					}
				}
			}

			masterItemList.add(eachMasterItem);
		}
		return masterItemList;
	}

	private List<MasterItem> getMasterItemObjectsToDeleteFromStep(DeleteProductsType deleteProductsType, com.codifyd.milxsd.ObjectFactory milObjectFactory) {
		List<MasterItem> masterItemList = new ArrayList<>();		
		if(deleteProductsType!=null){			
			for(DeleteProductType product: deleteProductsType.getDeleteProduct()){			
				MasterItem eachMasterItem = milObjectFactory.createMasterItem();
				eachMasterItem.setItemId(product.getID());
				eachMasterItem.setAction("DELETE");
				masterItemList.add(eachMasterItem);
			}
		}
		
		return masterItemList;
	}


	public static boolean isNullOrBlank(String param) {
		if (param == null || param.trim().length() == 0) {
			return true;
		}
		return false;
	}



}
