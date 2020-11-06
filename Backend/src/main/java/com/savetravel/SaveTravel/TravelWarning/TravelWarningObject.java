package com.savetravel.SaveTravel.TravelWarning;

import java.util.HashMap;


public class TravelWarningObject {

	private Api_status api_status;
	private HashMap<String, CountryCode> data;

public TravelWarningObject () {
	
}

public HashMap<String, CountryCode> getData() {
	return data;
}

public void setData(HashMap<String, CountryCode> data) {
	this.data = data;
}


public Api_status getApi_status() {
	return api_status;
}

public void setApi_status(Api_status api_status) {
	this.api_status = api_status;
}

}

