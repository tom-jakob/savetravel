package Corona;

import java.util.ArrayList;


public class CountryCoronaOuter {

	ArrayList<CountryCoronaInner> countrydata;
	
	public CountryCoronaOuter () {
		
	}
	
	
	
	public CountryCoronaOuter(ArrayList<CountryCoronaInner> countrydata) {
		super();
		this.countrydata = countrydata;
	}



	public ArrayList<CountryCoronaInner> getCountrydata() {
		return countrydata;
	}



	public void setCountrydata(ArrayList<CountryCoronaInner> countrydata) {
		this.countrydata = countrydata;
	}




	
	
	
}
