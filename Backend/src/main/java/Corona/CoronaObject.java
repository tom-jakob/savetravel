package Corona;

import java.util.ArrayList;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CoronaObject {

	ArrayList<CountryCoronaInner> countrydata;
	Boolean cocoExistsInCorona = false;
	
}
