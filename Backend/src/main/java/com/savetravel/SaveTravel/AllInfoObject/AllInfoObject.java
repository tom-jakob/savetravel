package com.savetravel.SaveTravel.AllInfoObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllInfoObject {

    String countryName;
    String countryCode;
    String lastUpdateTW;
    Double travelWarningScore;
    Double coronaScore;
    String coronaByPopulation;
    Double coronaActiveCases;
        
}