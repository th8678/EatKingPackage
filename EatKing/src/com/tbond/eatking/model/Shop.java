package com.tbond.eatking.model;

public class Shop {
	private String shopId = null;
	private String shopName = null;
	private String userId = null;
	private String locationX = null;
	private String locationY = null;
	private String address = null;
	private String phoneNumber = null;
	private String businessTime = null;
	private String tips = null;
	private String state = null;
	private String time = null;
	private String isCollected = null;
	private String environmentGrade = null;
	private String tastGrade = null;
	private String serviceGrade = null;
	private String pricePerPerson = null;
	
	public Shop() {

	}
	public void setIsCollected(String isCollected) {
		this.isCollected = isCollected;

	}
	public void setShopId(String shopId) {
		this.shopId = shopId;

	}

	public void setShopName(String shopName) {
		this.shopName = shopName;

	}

	public void setUserId(String userId) {
		this.userId = userId;

	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;

	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;

	}

	public void setAddress(String address) {
		this.address = address;

	}

	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;

	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;

	}

	public void setTips(String tips) {
		this.tips = tips;

	}

	public void setState(String state) {
		this.state = state;
	}
	public void setTime(String time) {
		this.time = time;

	}

	public String getShopId() {
		return this.shopId;

	}

	public String getShopName() {
		return this.shopName;

	}

	public String getUserId() {
		return this.userId;

	}

	public String getLocationX() {
		return this.locationX;

	}

	public String getLocationY() {
		return this.locationY;

	}

	public String getAddress() {
		return this.address;

	}

	public String getphoneNumber() {
		return this.phoneNumber;

	}

	public String getBusinessTime() {
		return this.businessTime;

	}

	public String getTips() {
		return this.tips ;

	}

	public String getState() {
		return this.state;
	}
	public  String getTime() {
		return this.time;

	}
	public String getIsCollected() {
		return this.isCollected;

	}
	public String print() {
		return shopId + shopName + userId + locationX + locationY + address
				+ phoneNumber + businessTime + tips + state;
	}
	public void setServiceGrade(String serviceGrade) {
		this.serviceGrade = serviceGrade;

	}
	public void setEnvironmentGrade(String environmentGrade) {
		this.environmentGrade = environmentGrade;

	}
	public void setPricePerPerson(String pricePerPerson) {
		this.pricePerPerson = pricePerPerson;

	}
public void setTastGrade(String tastGrade) {
		this.tastGrade = tastGrade;

	}
public String GetEnvironmentGrade() {
		return this.environmentGrade;

	}
	
	public String GetServiceGrade() {
		return this.serviceGrade;

	}
	public String GetPricePerPerson() {
		return this.pricePerPerson;

	}
	public String GetTastGrade() {
		return this.tastGrade;

	}

}
