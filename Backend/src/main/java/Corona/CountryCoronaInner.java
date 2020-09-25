package Corona;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryCoronaInner {
	
	private Info info;
	private Double total_cases;
	private Double total_recovered;
	private int total_unresolved;
	private String total_deaths;
	private String total_new_cases_today;
    private String total_new_deaths_today;
    private int total_active_cases;
    private long total_serious_cases;
    private int total_danger_rank;

         
     
}
