package com.savetravel.SaveTravel.AllBasicCountryData;

import java.net.URL;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AllBasicCountryData {

	String name;
	String alpha2Code;
	String alpha3Code;
	String capital;
	ArrayList<AltSpellings> altSpellings;
	String region;
	String subregion;
	Double population;
	Double[] latlng;
	String demonym;
    Double area;
    Double gini;
   String[] timezones;
    String[] borders;
    String nativeName;
    Integer numericCode;
    ArrayList<Currencies> currencies;
    ArrayList<Languages> languages;
    URL flag;
    String cioc;

}
