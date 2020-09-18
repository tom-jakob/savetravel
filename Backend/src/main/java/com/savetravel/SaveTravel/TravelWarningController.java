package com.savetravel.SaveTravel;

import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savetravel.SaveTravel.TravelWarning.TravelWarningObject;

import Corona.CountryCoronaOuter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gettravelwarnings")
public class TravelWarningController {

	@GetMapping("/alltw")
	public TravelWarningObject getAllTravelWarnings() throws JsonMappingException, JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();
		TravelWarningObject allTravelWarnings = new TravelWarningObject();

		ResponseEntity<TravelWarningObject> responseEntity = restTemplate
				.getForEntity("https://www.travel-advisory.info/api", TravelWarningObject.class);
		allTravelWarnings = responseEntity.getBody();

		return allTravelWarnings;

	}

	@GetMapping("/onetw/")
	public ResponseEntity<TravelWarningObject> getTWbyPath(@RequestParam(name = "coco", required = true) String coco)
			throws JsonMappingException, JsonProcessingException {

		System.out.println("das wird gerade in coco gespeichert! =" + coco);

		if (coco.isEmpty()) {
			return new ResponseEntity<TravelWarningObject>(HttpStatus.BAD_REQUEST);
		} else {

			RestTemplate restTemplate = new RestTemplate();
			TravelWarningObject newTravelWarning = new TravelWarningObject();
			ResponseEntity<TravelWarningObject> responseEntity = restTemplate.getForEntity(
					"https://www.travel-Advisory.info/api?countrycode=" + coco, TravelWarningObject.class);
			newTravelWarning = responseEntity.getBody();
			return new ResponseEntity<TravelWarningObject>(newTravelWarning, HttpStatus.OK);
		}
	}

	@GetMapping("/coronabycountry")
	public CountryCoronaOuter getCoronaByCountry(String coCoCo) {

		// CountryCode for testing
		coCoCo = "US";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		RestTemplate restTemplate = new RestTemplate();
		CountryCoronaOuter coronaCases = new CountryCoronaOuter();

		ResponseEntity<CountryCoronaOuter> responseEntity = restTemplate.exchange(
				"https://api.thevirustracker.com/free-api?countryTotal=" + coCoCo.toUpperCase(), HttpMethod.GET, entity,
				CountryCoronaOuter.class);
		coronaCases = responseEntity.getBody();

		return coronaCases;

	}

}
