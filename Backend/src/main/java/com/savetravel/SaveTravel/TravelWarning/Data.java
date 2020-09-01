package com.savetravel.SaveTravel.TravelWarning;

import java.util.HashMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Data {


	private HashMap<String, CountryCode> data;
	

	public HashMap<String, CountryCode> getData() {
		return data;
	}
	
	public void setData(HashMap<String, CountryCode> data) {
		this.data = data;
	}
	
	
	
}
