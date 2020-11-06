package com.savetravel.SaveTravel.Corona;


public class Info {

	String ourid; 
    String title; 
    String code;
    String source;
    
	
    
    public Info () {
    	
    }



	public Info(String ourid, String title, String code, String source) {
		super();
		this.ourid = ourid;
		this.title = title;
		this.code = code;
		this.source = source;
	}



	public String getOurid() {
		return ourid;
	}



	public void setOurid(String ourid) {
		this.ourid = ourid;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getSource() {
		return source;
	}



	public void setSource(String source) {
		this.source = source;
	}
    
    
    
}
