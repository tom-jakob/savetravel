package com.savetravel.SaveTravel;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.savetravel.SaveTravel.AllBasicCountryData.AllBasicCountryData;
import com.savetravel.SaveTravel.TravelWarning.TravelWarningObject;

import Corona.CoronaObject;

@Service
public class ApiService {

	public TravelWarningObject getTravelWarnings(String coco) throws JsonMappingException, JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();
		TravelWarningObject newTravelWarning = new TravelWarningObject();
		ResponseEntity<TravelWarningObject> responseEntity = restTemplate
				.getForEntity("https://www.travel-Advisory.info/api?countrycode=" + coco, TravelWarningObject.class);
		newTravelWarning = responseEntity.getBody();
		return newTravelWarning;

	}

//	public CoronaObject getCoronaByCountry(String coco) {
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		headers.add("user-agent",
//				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
//		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//
//		RestTemplate restTemplate = new RestTemplate();
//		CoronaObject coronaCases = new CoronaObject();
//
//		ResponseEntity<CoronaObject> responseEntity = restTemplate.exchange(
//				"https://api.thevirustracker.com/free-api?countryTotal=" + coco.toUpperCase(), HttpMethod.GET, entity,
//				CoronaObject.class);
//		coronaCases = responseEntity.getBody();
//
//		return coronaCases;
//
//	}
	
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

}
