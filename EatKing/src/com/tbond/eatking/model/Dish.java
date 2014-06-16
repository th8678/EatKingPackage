package com.tbond.eatking.model;

public class Dish {
	private String dishId = null;
	private String dishName = null;
	private String dishPrice = null;
	private String dishImage = null;
	private String userName = null;

	public Dish() {

	}

	public void setDishId(String dishId) {
		this.dishId = dishId;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;

	}

	public void setDishPrice(String dishPrice) {
		this.dishPrice = dishPrice;
	}

	public void setDishImage(String dishImage) {
		this.dishImage = dishImage;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDishId(){
		return dishId;
	}
	public String getDishName(){
		return dishName;
	}
	public String getDishImage(){
		return dishImage;
	}
	public String getDishPrice(){
		return dishPrice;
	}
	public String getUserName(){
		return userName;
	}
	public String print(){
		return " dishId : " + dishId + " dishName : " + dishName + " dishPrice : " + dishPrice + " dishImage : " + dishImage + " userName : " + userName;
	}

}
