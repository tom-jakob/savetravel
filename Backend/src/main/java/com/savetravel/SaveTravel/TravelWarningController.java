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
    Double coronaActiveCases = new Double(0.0);
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
        		allInfoObject.setCountryCode(coco);
        		allInfoObject.setLastUpdateTW(entry.getValue().getAdvisory().getUpdated());
        		allInfoObject.setTravelWarningScore(entry.getValue().getAdvisory().getScore());
        	}
        }
        
   
        

        try {
        	
        	Optional<CoronaObject> coronaObject = apiService.getCoronaByCountry(coco);

            if (coronaObject.isPresent()) {

                for (CountryCoronaInner countryCoronaInner : coronaObject.get().getCountrydata()) {
                    coronaActiveCases = (countryCoronaInner.getTotal_cases() - countryCoronaInner.getTotal_recovered());
                    allInfoObject.setCoronaActiveCases(coronaActiveCases);
                    cocoExistsInCorona = true;
                }
            } 
                    
            } catch (Exception e) {
//          coronaActiveCases = 0.0;
            	cocoExistsInCorona = false;
            System.out.println("Country Code " + coco.toString() + " does not exist in Corona database!");

        }

        AllBasicCountryData allBasicCountryData = new AllBasicCountryData();
        allBasicCountryData = apiService.getACDfromAPIService(coco);

        System.out.println(cocoExistsInCorona + " = value of cocoExistsInCorona");
        
        if (!cocoExistsInCorona) {

        	 allInfoObject.setCoronaByPopulation(null);
             allInfoObject.setCoronaActiveCases(null);

        } else {

     
            Double coronaPopulationRatio = (coronaActiveCases / allBasicCountryData.getPopulation());

            DecimalFormat df = new DecimalFormat("0.000");
            String coronaPopulationRatioRounded = df.format(coronaPopulationRatio);

            allInfoObject.setCoronaByPopulation(coronaPopulationRatioRounded);

        }

        return new ResponseEntity<AllInfoObject>(allInfoObject, HttpStatus.OK);
    }
}