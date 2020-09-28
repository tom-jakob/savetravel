package Corona;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CountryCoronaInner {
	
	 Info info;
	 Double total_cases;
     Double total_recovered;
     int total_unresolved;
     String total_deaths;
     String total_new_cases_today;
     String total_new_deaths_today;
     int total_active_cases;
     long total_serious_cases;
     int total_danger_rank;
     

}
