package com.savetravel.SaveTravel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.savetravel.SaveTravel.AllInfoObject.AllInfoObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/gettravelwarnings")
public class TravelWarningController {


    @Autowired
    ApiService apiService;
    
    @GetMapping("/onetw/")
    public ResponseEntity<AllInfoObject> getAllInfos(@RequestParam(name = "coco", required = true) String coco)
            throws JsonMappingException, JsonProcessingException {

        coco = coco.toUpperCase();
        
        apiService.setDataForTWO(coco);
        apiService.setDataFromCoronaAPI(coco);
        apiService.setDataFromBasicCountryAPI(coco);
        AllInfoObject allInfoObject = apiService.returnIAllInfoObject();
        
        return new ResponseEntity<AllInfoObject>(allInfoObject, HttpStatus.OK);
    }
}