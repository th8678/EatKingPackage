package com.tbond.eatking.model;

public class User {
	private String point = null;
	private String userName = null;
	private String userAvatar = null;
	private String userIntro = null;
	private String userPwd = null;
	private String image = null;
	private String userId = null;
	private String myChowhoundPoint = null;
	private String mypioneerpoint = null;
	private String myContribute = null;
	private String pioneerRank = null;
	private String chowhoundRank = null;

	private String findShopScore = null;
	private String findDishScore = null;
	private String otherSignShopScore = null;
	private String shopHighEvaluateScore = null;
	private String dishHighEvaluateScore = null;
	
	private String ownSignShopScore = null;
	private String evaluateShopScore = null;
	private String evaluateShopCommentScore = null;
	private String evaluateCommentGainScore = null;
	private String evaluationDishScore = null;
	private String correctInfoPassScore = null;
	
	
		public User() {
	}
		
		public  void setFindShopScore(String findShopScore) {
			this.findShopScore = findShopScore;
		}
		public  void setFindDishScore(String findDishScore) {
			this.findDishScore = findDishScore;
		}
		public  void setOtherSignShopScore(String otherSignShopScore) {
			this.otherSignShopScore = otherSignShopScore;
		}
		public void setShopHighEvaluateScore(String shopHighEvaluateScore) {
			this.shopHighEvaluateScore = shopHighEvaluateScore;
		}
		public void setDishHighEvaluateScore(String dishHighEvaluateScore) {
			this.dishHighEvaluateScore = dishHighEvaluateScore;
		}
		
		public void setOwnSignShopScore(String ownSignShopScore) {
			this.ownSignShopScore = ownSignShopScore;
		}
		public void setEvaluateShopScore(String evaluateShopScore) {
			this.evaluateShopScore = evaluateShopScore;
		}
		public void setEvaluateShopCommentScore(String evaluateShopCommentScore) {
			this.evaluateShopCommentScore = evaluateShopCommentScore;
		}
		public void setEvaluateCommentGainScore(String evaluateCommentGainScore) {
			this.evaluateCommentGainScore = evaluateCommentGainScore;
		}
		public void setEvaluationDishScore(String evaluationDishScore) {
			this.evaluationDishScore = evaluationDishScore;
		}
		public void setCorrectInfoPassScore( String correctInfoPassScore) {
			this.correctInfoPassScore = correctInfoPassScore;
		}
		
		
		
		
	public void setMyChowhoundPoint(String myChowhoundPoint) {
		this.myChowhoundPoint = myChowhoundPoint;
	}
	public void setMypioneerpoint(String mypioneerpoint) {
		this.mypioneerpoint = mypioneerpoint;
	}
	public void setMyContribute(String myContribute) {
		this.myContribute = myContribute;
	}
	public void setPioneerRank(String pioneerRank) {
		this.pioneerRank = pioneerRank;
	}
	public void setChowhoundRank(String chowhoundRank) {
		this.chowhoundRank = chowhoundRank;
	}
	public void setPoint(String point) {
		this.point = point;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}
	public void setUserPwd(String userPwd){
		this.userPwd = userPwd;
	}
	public void setImage(String image){
		this.image  = image;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getPoint(){
		return this.point;
	}
	public String getUserName(){
		return this.userName;
	}
	public String getUserPwd(){
		return  this.userPwd;
	}
	public String getImage(){
		return this.image;
	}
	public String getUserIntro(){
		return this.userIntro;
	}
	public String getUserId(){
		return this.userId;
	}
	public String getUserAvatar(){
		return this.userAvatar;
	}
	public String getMyChowhoundPoint() {
		return this.myChowhoundPoint;
	}
	public String getMypioneerpoint() {
		return this.mypioneerpoint;
	}
	public String getMyContribute() {
		return this.myContribute;
	}
	public String getPioneerRank() {
		return this.pioneerRank;
	}
	public String getChowhoundRank() {
		return this.chowhoundRank;
	}
	public String getFindShopScore() {
		return this.findShopScore;
	}
	public String getFindDishScore() {
		return this.findDishScore;
	}
	public String getOtherSignShopScore() {
		return this.otherSignShopScore;
	}
	public String getShopHighEvaluateScore() {
		return this.shopHighEvaluateScore;
	}
	public String getDishHighEvaluateScore() {
		return this.dishHighEvaluateScore;
	}
	
	public String getOwnSignShopScore() {
		return this.ownSignShopScore;
	}
	public String getEvaluateShopScore() {
		return this.evaluateShopScore;
	}
	public String getEvaluateShopCommentScore() {
		return this.evaluateShopCommentScore;
	}
	public String getEvaluateCommentGainScore() {
		return this.evaluateCommentGainScore;
	}
	public String getEvaluationDishScore() {
		return this.evaluationDishScore;
	}
	public String getCorrectInfoPassScore() {
		return this.correctInfoPassScore;
	}
	
	public String print(){
		return point + userName+userAvatar+userIntro;
	}
}
