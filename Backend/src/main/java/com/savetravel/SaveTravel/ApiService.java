package com.savetravel.SaveTravel;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.savetravel.SaveTravel.AllBasicCountryData.AllBasicCountryData;
import com.savetravel.SaveTravel.AllInfoObject.AllInfoObject;
import com.savetravel.SaveTravel.TravelWarning.CountryCode;
import com.savetravel.SaveTravel.TravelWarning.TravelWarningObject;

import Corona.CoronaObject;
import Corona.CountryCoronaInner;

@Service
public class ApiService {

	AllInfoObject allInfoObject = new AllInfoObject();

	public TravelWarningObject getTravelWarnings(String coco) throws JsonMappingException, JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();
		TravelWarningObject newTravelWarning = new TravelWarningObject();
		ResponseEntity<TravelWarningObject> responseEntity = restTemplate
				.getForEntity("https://www.travel-Advisory.info/api?countrycode=" + coco, TravelWarningObject.class);
		newTravelWarning = responseEntity.getBody();
		return newTravelWarning;

	}

	public Optional<CoronaObject> getCoronaByCountry(String coco) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		RestTemplate restTemplate = new RestTemplate();
		Optional<CoronaObject> coronaCases;

		ResponseEntity<CoronaObject> responseEntity = restTemplate.exchange(
				"https://api.thevirustracker.com/free-api?countryTotal=" + coco.toUpperCase(), HttpMethod.GET, entity,
				CoronaObject.class);
		coronaCases = Optional.ofNullable(responseEntity.getBody());

		return coronaCases;

	}

	public AllBasicCountryData getACDfromAPIService(String coco) {

		RestTemplate restTemplate = new RestTemplate();

		AllBasicCountryData newAllBasicCountryData = new AllBasicCountryData();

		ResponseEntity<AllBasicCountryData> responseEntity = restTemplate
				.getForEntity("https://restcountries.eu/rest/v2/alpha/" + coco, AllBasicCountryData.class);
		newAllBasicCountryData = responseEntity.getBody();
		return newAllBasicCountryData;

	}

	public void setDataForTWO(String coco) throws JsonMappingException, JsonProcessingException {

		TravelWarningObject travelWarningObject = getTravelWarnings(coco);

		// using for-each loop for iteration over Map.entrySet()
		for (Map.Entry<String, CountryCode> entry : travelWarningObject.getData().entrySet()) {
			if (entry.getKey().equals(coco)) {
				allInfoObject.setCountryName(entry.getValue().getName());
				allInfoObject.setTravelWarningScore(entry.getValue().getAdvisory().getScore());
				allInfoObject.setLastUpdateTW(entry.getValue().getAdvisory().getUpdated());

			}
		}

	}

	public CoronaObject setDataFromCoronaAPI(String coco) {

		CoronaObject coronaObject = new CoronaObject();

		try {

			CoronaObject localCoronaObject = getCoronaByCountry(coco).get();
			Double coronaActiveCases;

			if (localCoronaObject != null) { 
				localCoronaObject.setCocoExistsInCorona(true);

				for (CountryCoronaInner countryCoronaInner : localCoronaObject.getCountrydata()) {
					coronaActiveCases = (countryCoronaInner.getTotal_cases() - countryCoronaInner.getTotal_recovered());
					allInfoObject.setCoronaActiveCases(coronaActiveCases);
				}
				coronaObject = localCoronaObject;
			}

		} catch (Exception e) {
		 	CoronaObject localCoronaObject = new CoronaObject();
			localCoronaObject.setCocoExistsInCorona(false);
			System.out.println("Country Code " + coco.toString() + " does not exist in Corona database!");
			coronaObject = localCoronaObject;
		}

		return coronaObject;

	}

	public void setDataFromBasicCountryAPI(String coco) {

		AllBasicCountryData allBasicCountryData = new AllBasicCountryData();
		allBasicCountryData = getACDfromAPIService(coco);
		Boolean cocoExistsInCorona = setDataFromCoronaAPI(coco).getCocoExistsInCorona();

		if (!cocoExistsInCorona) {

			allInfoObject.setCoronaByPopulation(null);
			allInfoObject.setCoronaActiveCases(null);

		} else {

			Double coronaActiveCases = allInfoObject.getCoronaActiveCases();
			Double coronaPopulationRatio = (coronaActiveCases / allBasicCountryData.getPopulation());

			DecimalFormat df = new DecimalFormat("0.000");
			String coronaPopulationRatioRounded = df.format(coronaPopulationRatio);

			allInfoObject.setCoronaByPopulation(coronaPopulationRatioRounded);

		}
	}

	public AllInfoObject returnIAllInfoObject() {
		return allInfoObject;
	}

}
