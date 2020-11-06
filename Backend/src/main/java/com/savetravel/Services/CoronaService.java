package com.savetravel.Services;

import java.util.Arrays;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.savetravel.SaveTravel.AllInfoObject.AllInfoObject;

import Corona.CoronaObject;
import Corona.CountryCoronaInner;


@Service
public class CoronaService {

	@Autowired
	TravelWarningService travelWarningService;
	
	
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
	
	@PostConstruct
	public AllInfoObject setDataFromCoronaAPI(String coco) throws JsonMappingException, JsonProcessingException {

		AllInfoObject allInfoObject = travelWarningService.setDataForTWO(coco);

		try {

			CoronaObject localCoronaObject = getCoronaByCountry(coco).get();
			Double coronaActiveCases;

			if (localCoronaObject != null) {
				allInfoObject.setCocoExistsInCorona(true);

				for (CountryCoronaInner countryCoronaInner : localCoronaObject.getCountrydata()) {
					coronaActiveCases = (countryCoronaInner.getTotal_cases() - countryCoronaInner.getTotal_recovered());
					allInfoObject.setCoronaActiveCases(coronaActiveCases);
				}

			}

		} catch (Exception e) {
			allInfoObject.setCocoExistsInCorona(false);
			System.out.println("Country Code " + coco.toString() + " does not exist in Corona database!");
		}

		return allInfoObject;

	}

	
	
}
