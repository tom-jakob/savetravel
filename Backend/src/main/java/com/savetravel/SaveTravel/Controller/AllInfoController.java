package com.savetravel.SaveTravel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savetravel.SaveTravel.AllInfoObject.AllInfoObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/gettravelwarnings")
public class AllInfoController {

	@Autowired
	ApiService apiService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/onetw/")
	public ResponseEntity<AllInfoObject> getAllInfos(@RequestParam(name = "coco", required = true) String coco)
			throws JsonMappingException, JsonProcessingException {

		coco = coco.toUpperCase();
		AllInfoObject allInfoObject = new AllInfoObject();
		allInfoObject.setCountryCode(coco);

		// Set data from Travel Warning API

		allInfoObject = apiService.getTravelWarnings(coco, allInfoObject);

		// Set data from Basic Country API

		allInfoObject = apiService.getACDfromAPIService(coco, allInfoObject);
		
		// Set data from Corona API
		
		allInfoObject = apiService.setDataFromCoronaAPI(coco, allInfoObject);

		return new ResponseEntity<AllInfoObject>(allInfoObject, HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> testFunction() {
		
		String test = "test";
		
		return new ResponseEntity<String>(test, HttpStatus.OK);
		
	}
}