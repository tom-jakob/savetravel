package com.savetravel.SaveTravel.TravelWarning;


public class CountryCode {

	private String iso_alpha_2;
	private String name;
	private String continent; 
	private Advisory advisory;
	
	public CountryCode() {
		
	}
	
	public CountryCode(String iso_alpha_2, String name, String continent,
			com.savetravel.SaveTravel.TravelWarning.Advisory advisory) {
		super();
		this.iso_alpha_2 = iso_alpha_2;
		this.name = name;
		this.continent = continent;
		this.advisory = advisory;
	}

	public String getIso_alpha_2() {
		return iso_alpha_2;
	}

	public void setIso_alpha_2(String iso_alpha_2) {
		this.iso_alpha_2 =  iso_alpha_2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Advisory getAdvisory() {
		return advisory;
	}

	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}
	
		
}
