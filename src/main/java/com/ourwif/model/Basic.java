package com.ourwif.model;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basic {
	
	private boolean status;
	private String url;
	private Long id;
	private String strId;
	public TreeMap<String, String> errors;
	
	public Basic(boolean loged, String url) {
		this();
		status = loged;
		this.url = url;
	}
	
	public Basic() {
		errors = new TreeMap<>();
	}
	
	public Map<String, String> getErrors() {
		return Collections.unmodifiableMap(errors);
	}

	public void addError(String errorPlace, String error){
		errors.put(errorPlace, error);
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


	public String getStrId() {
		return strId;
	}


	public void setStrId(String strId) {
		this.strId = strId;
	}	

}
