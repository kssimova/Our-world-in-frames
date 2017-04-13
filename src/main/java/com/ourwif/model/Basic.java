package com.ourwif.model;

public class Basic {
	
	private int Status;
	private String url;
	private Long id;
	
	
	public Basic(int status, String url, Long id) {
		super();
		Status = status;
		this.url = url;
		this.id = id;
	}


	public int getStatus() {
		return Status;
	}


	public String getUrl() {
		return url;
	}


	public Long getId() {
		return id;
	}


	public void setStatus(int status) {
		Status = status;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
