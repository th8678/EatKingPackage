package com.tbond.eatking.model;

public class Pet {
	private int petid;//编号
	private String petname;//名称
	private String pettype;//类型
	public String getPetname(){
		return petname;
	}
	public String getPettype(){
		return pettype;
	}
	public int getPetid(){
		return petid;
	}
	public Pet(int id,String name, String type){
		petid = id;
		petname = name;
		pettype = type;
	}
	public void setPetname(String name){
		petname = name;
	}
	public void setPettype(String type){
		pettype = type;
	}
	public void setPetid(String id){
		int id1 = Integer.valueOf(id);
		petid = id1;
	}
	public Pet(){
		
	}
	public String print(){
		return "宠物名"+petname+"宠物品种"+pettype+"宠物id"+petid;
	}
}
