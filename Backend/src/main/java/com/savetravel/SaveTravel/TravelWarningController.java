package com.savetravel.SaveTravel;

import java.text.DecimalFormat;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.savetravel.SaveTravel.AllInfoObject.AllInfoObject;
import com.savetravel.SaveTravel.TravelWarning.TravelWarningObject;
import com.savetravel.SaveTravel.AllBasicCountryData.AllBasicCountryData;
import Corona.CoronaObject;
import Corona.CountryCoronaInner;
import com.savetravel.SaveTravel.TravelWarning.CountryCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/gettravelwarnings")
public class TravelWarningController {

    AllInfoObject allInfoObject = new AllInfoObject();
    CoronaObject coronaObject = new CoronaObject();
    Double coronaActiveCases = new Double(0);
    boolean cocoExistsInCorona = false;

    @Autowired
    ApiService apiService;
    
    
    
    
    @GetMapping("/onetw/")
    public ResponseEntity<AllInfoObject> getAllInfos(@RequestParam(name = "coco", required = true) String coco)
            throws JsonMappingException, JsonProcessingException {

        coco = coco.toUpperCase();

        TravelWarningObject travelWarningObject = apiService.getTravelWarnings(coco);
        
        for (Map.Entry<String, CountryCode> entry : travelWarningObject.getData().entrySet()) {
        	
        	if (entry.getKey().equals(coco)) {
        		allInfoObject.setCountryName(entry.getValue().getName());
        		allInfoObject.setCountryCode(entry.getValue().getIso_alpha_2());
        		allInfoObject.setLastUpdateTW(entry.getValue().getAdvisory().getUpdated());
        		allInfoObject.setTravelWarningScore(entry.getValue().getAdvisory().getScore());
        	}
        }
        
   
        

        try {

            coronaObject = apiService.getCoronaByCountry(coco);

            for (CountryCoronaInner countryCoronaInner : coronaObject.getCountrydata()) {

                coronaActiveCases = (countryCoronaInner.getTotal_cases() - countryCoronaInner.getTotal_recovered());
                allInfoObject.setCoronaActiveCases(coronaActiveCases);

                cocoExistsInCorona = true;
            }

        } catch (Exception e) {
            coronaActiveCases =  null;

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