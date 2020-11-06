package com.savetravel.SaveTravel.Controller;

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

import com.savetravel.SaveTravel.Corona.CoronaObject;
import com.savetravel.SaveTravel.Corona.CountryCoronaInner;

@Service
public class ApiService {

	public AllInfoObject getTravelWarnings(String coco, AllInfoObject allInfoObject)
			throws JsonMappingException, JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();
		TravelWarningObject newTravelWarning = new TravelWarningObject();
		ResponseEntity<TravelWarningObject> responseEntity = restTemplate
				.getForEntity("https://www.travel-Advisory.info/api?countrycode=" + coco, TravelWarningObject.class);
		newTravelWarning = responseEntity.getBody();

		for (Map.Entry<String, CountryCode> entry : newTravelWarning.getData().entrySet()) {
			if (entry.getKey().equals(coco)) {
				allInfoObject.setCountryName(entry.getValue().getName());
				allInfoObject.setTravelWarningScore(entry.getValue().getAdvisory().getScore());
				allInfoObject.setLastUpdateTW(entry.getValue().getAdvisory().getUpdated());
			}

		}

		return allInfoObject;

	}

	public AllInfoObject getACDfromAPIService(String coco, AllInfoObject allInfoObject) {

		RestTemplate restTemplate = new RestTemplate();

		AllBasicCountryData newAllBasicCountryData = new AllBasicCountryData();

		ResponseEntity<AllBasicCountryData> responseEntity = restTemplate
				.getForEntity("https://restcountries.eu/rest/v2/alpha/" + coco, AllBasicCountryData.class);
		newAllBasicCountryData = responseEntity.getBody();

		// Set timezones
		allInfoObject.setTimezone(newAllBasicCountryData.getTimezones());

		// Set population
		allInfoObject.setPopulation(newAllBasicCountryData.getPopulation());

		return allInfoObject;

	}

	// Set data from Corona API

	public AllInfoObject setDataFromCoronaAPI(String coco, AllInfoObject allInfoObject) {

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			RestTemplate restTemplate = new RestTemplate();
			Optional<CoronaObject> coronaCases;

			ResponseEntity<CoronaObject> responseEntity = restTemplate.exchange(
					"https://api.thevirustracker.com/free-api?countryTotal=" + coco.toUpperCase(), HttpMethod.GET,
					entity, CoronaObject.class);
			coronaCases = Optional.ofNullable(responseEntity.getBody());

			allInfoObject.setCocoExistsInCorona(true);
			for (CountryCoronaInner countryCoronaInner : coronaCases.get().getCountrydata()) {
				Double coronaActiveCases = (countryCoronaInner.getTotal_cases()
						- countryCoronaInner.getTotal_recovered());
				allInfoObject.setCoronaActiveCases(coronaActiveCases);
			}

			Double coronaActiveCases = allInfoObject.getCoronaActiveCases();
			Double coronaPopulationRatio = (coronaActiveCases / allInfoObject.getPopulation());

			DecimalFormat df = new DecimalFormat("0.000");
			String coronaPopulationRatioRounded = df.format(coronaPopulationRatio);

			allInfoObject.setCoronaByPopulation(coronaPopulationRatioRounded);

		} catch (Exception e) {

			System.out.println("Country " + allInfoObject.getCountryName() + " does not exist in Corona API!");
			allInfoObject.setCocoExistsInCorona(false);
			allInfoObject.setCoronaActiveCases(null);
			allInfoObject.setCoronaByPopulation(null);
		}

		return allInfoObject;

	}
}