package com.savetravel.SaveTravel.TravelWarning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Reply {

	private String cache;
	private int code;
	private String status;
	private String note;
	private int count;
	
	
	public Reply(String cache, int code, String status, String note, int count) {
		super();
		this.cache = cache;
		this.code = code;
		this.status = status;
		this.note = note;
		this.count = count;
	}


	
	public String getCache() {
		return cache;
	}


	public void setCache(String cache) {
		this.cache = cache;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	
	
}
