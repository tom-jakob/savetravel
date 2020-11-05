package com.savetravel.SaveTravel.AllInfoObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface AllInfoFunctions {

	
	public AllInfoObject setDataFomAllCountryAPI(String coco, AllInfoObject allInfoObject) throws JsonMappingException, JsonProcessingException;
	public AllInfoObject setDataFromCoronaAPI(String coco);
	
	
}
