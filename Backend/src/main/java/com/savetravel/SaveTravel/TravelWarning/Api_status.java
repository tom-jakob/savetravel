package com.savetravel.SaveTravel.TravelWarning;




public class Api_status {

	private Request request;
	private Reply reply;
	
	
	public Api_status() {
		
	}
	
	public Api_status(com.savetravel.SaveTravel.TravelWarning.Request request,
			com.savetravel.SaveTravel.TravelWarning.Reply reply) {
		super();
		this.request = request;
		this.reply = reply;
	}


	public Request getRequest() {
		return request;
	}


	public void setRequest(Request request) {
		this.request = request;
	}


	public Reply getReply() {
		return reply;
	}


	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
	
	
	
}
