package com.savetravel.SaveTravel.TravelWarning;




public class Advisory {

	private double score;
	private int sources_active;
	private String message;
	private String updated;
	private String source;
	
	public Advisory() {
		
	}
	
	public Advisory(double score, int sources_active, String message, String updated, String source) {
		super();
		this.score = score;
		this.sources_active = sources_active;
		this.message = message;
		this.updated = updated;
		this.source = source;
	}
	
	
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getSources_active() {
		return sources_active;
	}
	public void setSources_active(int sources_active) {
		this.sources_active = sources_active;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
	
	
	
	
	
}
