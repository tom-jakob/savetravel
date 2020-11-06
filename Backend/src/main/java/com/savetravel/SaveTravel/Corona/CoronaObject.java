package com.savetravel.SaveTravel.Corona;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoronaObject {

	ArrayList<CountryCoronaInner> countrydata;
	String stat;
	Boolean cocoExistsInCorona = false;

	
}
