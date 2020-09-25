package com.savetravel.SaveTravel;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savetravel.SaveTravel.AllInfoObject.AllInfoObject;
import com.savetravel.SaveTravel.TravelWarning.TravelWarningObject;

import AllBasicCountryData.AllBasicCountryData;
import Corona.CoronaObject;
import Corona.CountryCoronaInner;

import com.savetravel.SaveTravel.TravelWarning.CountryCode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gettravelwarnings")
public class TravelWarningController {

	AllInfoObject allInfoObject = new AllInfoObject();
	TravelWarningObject travelWarningObject = new TravelWarningObject();
	Double coronaActiveCases = new Double(0);
	boolean cocoExistsInCorona = false;

	@Autowired
	ApiService apiService;

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
	public ResponseEntity<AllInfoObject> getAllInfos(@RequestParam(name = "coco", required = true) String coco)
			throws JsonMappingException, JsonProcessingException {

		coco = coco.toUpperCase();

		travelWarningObject = apiService.getTWfromAPIService(coco);

		// using for-each loop for iteration over Map.entrySet()
		for (Map.Entry<String, CountryCode> entry : travelWarningObject.getData().entrySet()) {
			if (entry.getKey().equals(coco)) {
				allInfoObject.setCountryName(entry.getValue().getName());
				allInfoObject.setTravelWarningScore(entry.getValue().getAdvisory().getScore());
				allInfoObject.setLastUpdateTW(entry.getValue().getAdvisory().getUpdated());

			}
		}

		Optional<CoronaObject> coronaObject = apiService.getCoronaByCountry(coco);

		if (coronaObject.isPresent()) {

			for (CountryCoronaInner countryCoronaInner : coronaObject.get().getCountrydata()) {

				coronaActiveCases = (countryCoronaInner.getTotal_cases() - countryCoronaInner.getTotal_recovered());
				allInfoObject.setCoronaActiveCases(coronaActiveCases);

				cocoExistsInCorona = true;
			}
		}

		AllBasicCountryData allBasicCountryData = new AllBasicCountryData();
		allBasicCountryData = apiService.getACDfromAPIService(coco);

		if (cocoExistsInCorona) {

			Double coronaPopulationRatio = (coronaActiveCases / allBasicCountryData.getPopulation());

			DecimalFormat df = new DecimalFormat("0.000");
			String coronaPopulationRatioRounded = df.format(coronaPopulationRatio);

			allInfoObject.setCoronaByPopulation(coronaPopulationRatioRounded);

		} else {

			allInfoObject.setCoronaByPopulation(null);

		}

		return new ResponseEntity<AllInfoObject>(allInfoObject, HttpStatus.OK);
	}
}
