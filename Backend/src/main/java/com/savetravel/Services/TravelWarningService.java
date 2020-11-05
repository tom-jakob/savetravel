package com.savetravel.Services;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.savetravel.SaveTravel.AllInfoObject.AllInfoObject;
import com.savetravel.SaveTravel.TravelWarning.CountryCode;
import com.savetravel.SaveTravel.TravelWarning.TravelWarningObject;

@Service
public class TravelWarningService {

	public TravelWarningObject getTravelWarnings(String coco) throws JsonMappingException, JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();
		TravelWarningObject newTravelWarning = new TravelWarningObject();
		ResponseEntity<TravelWarningObject> responseEntity = restTemplate
				.getForEntity("https://www.travel-Advisory.info/api?countrycode=" + coco, TravelWarningObject.class);
		newTravelWarning = responseEntity.getBody();
		return newTravelWarning;

	}

	public AllInfoObject setDataForTWO(String coco) throws JsonMappingException, JsonProcessingException {

		AllInfoObject allInfoObject = new AllInfoObject();
		TravelWarningObject travelWarningObject = getTravelWarnings(coco);

		// using for-each loop for iteration over Map.entrySet()
		for (Map.Entry<String, CountryCode> entry : travelWarningObject.getData().entrySet()) {
			if (entry.getKey().equals(coco)) {
				allInfoObject.setCountryName(entry.getValue().getName());
				allInfoObject.setTravelWarningScore(entry.getValue().getAdvisory().getScore());
				allInfoObject.setLastUpdateTW(entry.getValue().getAdvisory().getUpdated());

			}
		}
		return allInfoObject;
	}

}
