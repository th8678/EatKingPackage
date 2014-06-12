package com.tbond.eatking.net;

import java.io.File;
import java.io.FileNotFoundException;

import android.os.Environment;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Api {
	private static Api instance = new Api();
	
	//api
    private static final String API_LOGIN = "login";
    private static final String API_LOGOUT = "logout";
    private static final String API_REGIS = "regis";
    
    public static final int LOGIN_SUCCESS = 0;
	public static final int LOGIN_FAILED = 1;
	
    private Api(){
		
	}
	
	public static Api getInstance(){
		return instance;
	}
	
	public void login(String userName, String userPwd, JsonHttpResponseHandler reponseHandler){
		RequestParams params = new RequestParams();
		params.put("userName", userName);
		params.put("userPwd", userPwd);
		WebRestClient.post(API_LOGIN, params, reponseHandler);
	}
	
	public void regis(String userName, String userPwd, JsonHttpResponseHandler reponseHandler){
		RequestParams params = new RequestParams();
		params.put("userName", userName);
		params.put("userPwd", userPwd);
		String pathString = Environment.getExternalStorageDirectory().getAbsolutePath() + "/infosearch_share.jpg";
		File im = new File(pathString);
		Log.i("image",String.valueOf(im.length()));
		try {
			params.put("image", im);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebRestClient.post(API_REGIS, params, reponseHandler);
	}
	
	public void logout(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_LOGOUT, null, reponseHandler);
		WebRestClient.getCookieStore().clear();
	}
	
	/**
	 * 通过中心位置获得radius半径内所有商店信息
	 * @param locX
	 * @param locY
	 * @param radius
	 * @param reponseHandler
	 */
	public void searchShopByLoc(double locX, double locY, double radius, JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 通过uid查询用户信息
	*/
	public void getPersonPage(JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 获得个人贡献
	 * @param reponseHandler
	 */
	public void getPersonContribute(JsonHttpResponseHandler reponseHandler){
		
	}

	/**
	 * 通过uid查询用户收藏店铺信息，包括离当前位置的距离
	 * @param reponseHandler
	 */
	public void getCollection(JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 收藏店铺
	 * @param shopId
	 * @param reponseHandler
	 */
	public void collectionShop(String shopId, JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 取消收藏店铺
	 * @param shopId
	 * @param reponseHandler
	 */
	public void cancelCollectionShop(String shopId, JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * id搜索商户评价信息
	 * @param shopId
	 * @param reponseHandler
	 */
	public void searchShopEvaluationById(String shopId, JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 创建商铺信息
	 * @param shopName
	 * @param locationX
	 * @param locationY
	 * @param address
	 * @param phoneNumber
	 * @param businessTime
	 * @param tips
	 * @param state
	 * @param reponseHandler
	 */
	public void addShop(String shopName, double locationX, double locationY, String address, String phoneNumber, 
			String businessTime, String tips, String state, JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 创建菜信息
	 * @param shopId
	 * @param dishName
	 * @param dishPrice
	 * @param reponseHandler
	 */
	public void addDish(String shopId, String dishName, String dishPrice, JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 通过信息搜索店铺
	 * @param key
	 * @param reponseHandler
	 */
	public void searchShopByKey(String key, JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 举报店铺信息
	 * @param shopId
	 * @param shopName
	 * @param locationX
	 * @param locationY
	 * @param phoneNumber
	 * @param state
	 * @param businessTime
	 * @param comment
	 * @param reponseHandler
	 */
	public void addShopReport(String shopId, String shopName, double locationX, double locationY, String phoneNumber, 
			String state, String businessTime, String comment, JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 举报菜信息
	 * @param dishId
	 * @param comment
	 * @param reponseHandler
	 */
	public void addDishReport(String dishId, String comment, JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 添加商铺评价
	 * @param shopId
	 * @param tasteGrade
	 * @param environmentGrade
	 * @param serviceGrade
	 * @param pricePerPerson
	 * @param comment
	 * @param reponseHandler
	 */
	public void addShopEvaluation(String shopId, String tasteGrade, String environmentGrade, String serviceGrade, String pricePerPerson,
			String comment, JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 对店铺评价点赞或者取消赞
	 * @param seId
	 * @param isPrase true:点赞   false：取消赞
	 * @param reponseHandler
	 */
	public void praseShopComment(String seId, boolean isPrase, JsonHttpResponseHandler reponseHandler){
		//addShopEvaluation deleteShopEvaluation
		
	}
	
	/**
	 * 对菜点赞或者取消赞
	 * @param dishId
	 * @param isPrase true:点赞   false：取消赞
	 * @param reponseHandler
	 */
	public void praseDish(String dishId, boolean isPrase, JsonHttpResponseHandler reponseHandler){
		//addDishEvaluation deleteDishEvaluation
	
	}
	
	/**
	 * 获得用户两项积分信息
	 * @param reponseHandler
	 */
	public void getPersonPoint(JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 查询我的评价
	 * @param reponseHandler
	 */
	public void getPersonComment(JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 获得两个排行榜名次榜，前10名
	 * @param reponseHandler
	 */
	public void getLevelList(JsonHttpResponseHandler reponseHandler){
		
	}
}
