package com.savetravel.SaveTravel.TravelWarning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Request {
	
	private String item;

	public Request (String item) {
		this.item = item;
	}
	
	
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
