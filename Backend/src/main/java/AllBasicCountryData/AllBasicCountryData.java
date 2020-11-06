package AllBasicCountryData;

import java.net.URL;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AllBasicCountryData {

	String name;
	ArrayList<TopLevelDomain> topLevelDomain;
	
	String alpha2Code;
    String alpha3Code;
    
    ArrayList<CallingCodes> callingCodes;
    
    String capital;
    
    String[] altSpellings;
    
    String region;
    String subregion;
    Long population;
    
    String demonym;
    Long area;
    Double gini;
    
    ArrayList<TimeZone> timezones;
    
    String[] borders;
    
    String nativeName;
    String numericCode;
	
    ArrayList<Currencies> currencies;
    
    ArrayList<Language> languages;
    
    URL flag;
    
    String cioc;
    
  
}
