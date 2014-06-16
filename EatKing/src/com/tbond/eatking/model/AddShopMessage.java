package com.tbond.eatking.model;

public class AddShopMessage {
	private String error = null;
	private String address = null;
	private String shopId = null;
	public AddShopMessage(){
		
	}
	public void setError(String error){
		this.error = error;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setShopId(String shopId){
		this.shopId  = shopId;
	}
	public String print(){
		return "error : " + error + " address : " + address + " shopId : " + shopId;
	}
}
