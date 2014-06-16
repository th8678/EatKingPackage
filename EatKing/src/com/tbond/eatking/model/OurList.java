package com.tbond.eatking.model;

public class OurList {
	private User pioneer[] = new User[3];
	private User chowhound[]= new User[3];
	private String myPioneerPoint = null;
	private String myChowhoundPoint = null;
	public void  setPioneer(User p1,User p2, User p3){
		this.pioneer[0] = p1;
		this.pioneer[1] = p2;
		this.pioneer[2] = p3;
	}
	public void  setChowhound(User p1,User p2, User p3){
		this.chowhound[0] = p1;
		this.chowhound[1] = p2;
		this.chowhound[2] = p3;
	}
	public void setMyPioneerPoint(String  myPioneerPoint){
		this.myPioneerPoint =  myPioneerPoint;
	}
	public void setMyChowhoundPoint(String myChowhoundPoint){
		this.myChowhoundPoint = myChowhoundPoint;
	}
	public String print(){
		return myPioneerPoint+myChowhoundPoint+pioneer[0].print()+pioneer[1].print()+pioneer[2].print()+chowhound[0].print()+chowhound[1].print()+chowhound[2].print();
	}
}
