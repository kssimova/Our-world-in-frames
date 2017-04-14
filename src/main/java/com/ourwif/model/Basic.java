package com.ourwif.model;

public class Basic {
	
	private boolean status;
	private String url;
	private Long id;
	
	
	public Basic(boolean loged, String url, Long id) {
		super();
		status = loged;
		this.url = url;
		this.id = id;
	}


	public boolean getStatus() {
		return status;
	}


	public String getUrl() {
		return url;
	}


	public Long getId() {
		return id;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
