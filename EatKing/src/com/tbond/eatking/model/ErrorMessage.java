package com.tbond.eatking.model;

public class ErrorMessage {
	private String error = null;
	private String result = null;
	public ErrorMessage(){
		
	}
	public void setError(String error){
		this.error = error;
	}
	public void setResult(String result){
		this.result = result;
	}
	public String getResult(){
		return this.result;
	}
}
