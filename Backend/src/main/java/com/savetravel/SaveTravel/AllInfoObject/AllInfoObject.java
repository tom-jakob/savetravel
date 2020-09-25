package com.savetravel.SaveTravel.AllInfoObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllInfoObject {

	String countryName;
	String countryCode;
	String lastUpdateTW;
	Double travelWarningScore;
	Double coronaScore;
	String coronaByPopulation;
	Double coronaActiveCases;
		
}
