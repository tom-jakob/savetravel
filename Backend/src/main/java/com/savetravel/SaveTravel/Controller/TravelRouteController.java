package com.savetravel.SaveTravel.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.savetravel.SaveTravel.AllInfoObject.AllInfoObject;

@RestController
@RequestMapping("/gettravelwarnings")
public class TravelRouteController {

	@Autowired
	TravelRouteRepository travelRouteRepository;

	@RequestMapping(value = "/savetravelroute",
			method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<List<AllInfoObject>> saveTravelRoute(@RequestBody List<AllInfoObject> travelRoute) {

		travelRouteRepository.saveAll(travelRoute);
		return new ResponseEntity<List<AllInfoObject>>(HttpStatus.OK);
	}

	@GetMapping("/gettravelroute")
	public ResponseEntity<List<AllInfoObject>> getTravelRoute() {

		Integer id = 444;
		List<AllInfoObject> travelRoute = travelRouteRepository.findAll();

		List<AllInfoObject> usersTravelRoute = new ArrayList<AllInfoObject>();

		for (AllInfoObject allInfoObject : travelRoute) {

			System.out.println(id);

			if (allInfoObject.getUserId().equals(id)) {
				usersTravelRoute.add(allInfoObject);
			}
		}

		return new ResponseEntity<List<AllInfoObject>>(usersTravelRoute, HttpStatus.OK);

	}

}
