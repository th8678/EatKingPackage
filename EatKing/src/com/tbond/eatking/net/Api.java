package com.tbond.eatking.net;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Api {
	private static Api instance = new Api();
	
	//api
    private static final String API_LOGIN = "login";
    private static final String API_LOGOUT = "logout";
    private static final String API_REGIS = "regis";
    private static final String API_ADDSHOP = "addShop";
    private static final String API_ADDDISH = "addDish";
    private static final String API_SIGNIN = "signin";
    private static final String API_COLLECTIONSHOP = "collectionShop";
    private static final String API_CANELCOLLECTIONSHOP = "cancelCollectionShop";
    private static final String API_ADDSHOPEVALUATIONEVALUATION = "addShopEvaluationEvaluation";
    private static final String API_DELETESHOPEVALUATIONEVALUATION = "deleteShopEvaluationEvaluation";
    private static final String API_ADDDISHEVALUATION = "addDishEvaluation";
    private static final String API_DELETEDISHEVALUATION = "deleteDishEvaluation";
    private static final String API_ADDSHOPREPORT = "addShopReport";
    private static final String API_ADDDISHREPORT = "addDishReport";
    private static final String API_SEARCHSHOPBYKEY = "searchShopByKey";
    private static final String API_SEARCHSHOPBYID = "searchShopById";
    private static final String API_SEARCHSHOPEVALUATIONBYID = "searchShopEvaluationById";
    private static final String API_ADDSHOPEVALUATION = "addShopEvaluation";
    private static final String API_GETALLPOINT = "getAllPoint";
	private static final String API_GETPERSONPAGE = "getPersonPage";
	private static final String API_GETPERSONCONTRIBUTE = "getPersonContribute";
	private static final String API_GETPERSONCOMMENT = "getPersonComment";
	private static final String API_GETPERSONPOINT = "getPersonPoint";
	private static final String API_GETCOLLECTION = "getCollection";
    public static final int LOGIN_SUCCESS = 0;
	public static final int LOGIN_FAILED = 1;
	

	
    private Api(){
		
	}
	
	public static Api getInstance(){
		return instance;
	}

	/**
	 * 登陆操作
	 * @param params
	 * @param reponseHandler
	 */
	public void login(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_LOGIN, params, reponseHandler);
	}
	
	/**
	 * 新用户注册
	 * @param params
	 * @param reponseHandler
	 */
	public void regis(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_REGIS, params, reponseHandler);	
	}
	
	/**
	 * 注销登录
	 * @param reponseHandler
	 */
	public void logout(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_LOGOUT, null, reponseHandler);
		//WebRestClient.getCookieStore().clear();
	}
	
	/**
	 * 收藏店铺
	 * @param params
	 * @param reponseHandler
	 */
	public void collectionShop(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_COLLECTIONSHOP, params, reponseHandler);
	}
	
	/**
	 * 取消店铺收藏
	 * @param params
	 * @param reponseHandler
	 */
	public void cancelCollectionShop(RequestParams params,JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_CANELCOLLECTIONSHOP, params, reponseHandler);
	}	
	
	/**
	 * 新建店铺
	 * @param params
	 * @param reponseHandler
	 */
	public void addShop(RequestParams params,JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_ADDSHOP, params, reponseHandler);
	}
	
	/**
	 * 新建菜品
	 * @param params
	 * @param reponseHandler
	 */
	public void addDish(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_ADDDISH, params, reponseHandler);
	}

	/**
	 * 查询我的评价
	 * @param reponseHandler
	 */
	public void getPersonComment(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETPERSONCOMMENT, null, reponseHandler);
	}
	
	/**
	 * 获得两个排行榜名次榜，前10名
	 * @param reponseHandler
	 */
	public void getLevelList(JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * 签到
	 * @param params
	 * @param reponseHandler
	 */
   public void signin(RequestParams params,JsonHttpResponseHandler reponseHandler){
    	WebRestClient.post(API_SIGNIN, params, reponseHandler);
	}
   
   /**
     * 取消菜品评价
     * @param params
     * @param reponseHandler
     */
    public void deleteDishEvaluation(RequestParams params,JsonHttpResponseHandler reponseHandler){
    	WebRestClient.post(API_DELETEDISHEVALUATION, params, reponseHandler);
	}

/**
     * 添加菜品评价
     * @param params
     * @param reponseHandler
     */
    public void addDishEvaluation(RequestParams params,JsonHttpResponseHandler reponseHandler){
    	WebRestClient.post(API_ADDDISHEVALUATION, params, reponseHandler);
	}
    
    /**
     * 取消对店铺评价的评价
     * @param params
     * @param reponseHandler
     */
    public void deleteShopEvaluationEvaluation(RequestParams params,JsonHttpResponseHandler reponseHandler){
    	WebRestClient.post(API_DELETESHOPEVALUATIONEVALUATION, params, reponseHandler);
	}
    
    
    /**
     * 添加对店铺评价的评价
     * @param params
     * @param reponseHandler
     */
    public void addShopEvaluationEvaluation(RequestParams params,JsonHttpResponseHandler reponseHandler){
    	WebRestClient.post(API_ADDSHOPEVALUATIONEVALUATION, params, reponseHandler);
	}
    
	/**
	 * 举报店铺信息
	 * @param params
	 * @param reponseHandler
	 */
	public void addShopReport(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_ADDSHOPREPORT, params, reponseHandler);
	}
	
	/**
	 * 举报菜信息
	 * @param params
	 * @param reponseHandler
	 */
	public void addDishReport(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_ADDDISHREPORT, params, reponseHandler);
	}
	/**
	 * 通过信息搜索店铺
	 * @param params
	 * @param reponseHandler
	 */
	public void searchShopByKey(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_SEARCHSHOPBYKEY , params, reponseHandler);
	}
	
	 public void searchShopById(RequestParams params, JsonHttpResponseHandler reponseHandler){
		 WebRestClient.post(API_SEARCHSHOPBYID, params, reponseHandler);
	 }
	 
		/**
		 * id搜索商户评价信息
		 * @param params
		 * @param reponseHandler
		 */
	public void searchShopEvaluationById(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_SEARCHSHOPEVALUATIONBYID, params, reponseHandler);
	}
	/**
	 * 添加商铺评价
	 * @param params
	 * @param reponseHandler
	 */
	public void addShopEvaluation(RequestParams params,JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_ADDSHOPEVALUATION, params, reponseHandler);
	}
	/**
	 * 获取所有分数前三
	 * @param reponseHandler
	 */
	public void getAllPoint(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETALLPOINT, null, reponseHandler);
	}
	/**
	 * 通过uid查询用户信息
	*/
	public void getPersonPage(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETPERSONPAGE, null, reponseHandler);
	}
	/**
	 * 获得个人贡献
	 * @param reponseHandler
	 */
	public void getPersonContribute(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETPERSONCONTRIBUTE, null, reponseHandler);
	}
		/**
	 * 通过uid查询用户收藏店铺信息，包括离当前位置的距离
	 * @param reponseHandler
	 */
	public void getCollection(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETCOLLECTION, null, reponseHandler);
	}
	/**
	 * 获得用户两项积分信息
	 * @param reponseHandler
	 */
	public void getPersonPoint(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETPERSONPOINT, null, reponseHandler);
	}

	
//	
//	/**
//	 * 对店铺评价点赞或者取消赞
//	 * @param seId
//	 * @param isPrase true:点赞   false：取消赞
//	 * @param reponseHandler
//	 */
//	public void praseShopComment(String seId, boolean isPrase, JsonHttpResponseHandler reponseHandler){
//		//addShopEvaluation deleteShopEvaluation
//		WebRestClient.post(API_ADDDISHREPORT, params, reponseHandler);
//	}
//	
//	/**
//	 * 对菜点赞或者取消赞
//	 * @param dishId
//	 * @param isPrase true:点赞   false：取消赞
//	 * @param reponseHandler
//	 */
//	public void praseDish(String dishId, boolean isPrase, JsonHttpResponseHandler reponseHandler){
//		//addDishEvaluation deleteDishEvaluation
//		WebRestClient.post(API_ADDDISHREPORT, params, reponseHandler);
//	}
//	
//	/**
//	 * 获得用户两项积分信息
//	 * @param reponseHandler
//	 */
//	public void getPersonPoint(JsonHttpResponseHandler reponseHandler){
//		WebRestClient.post(API_ADDDISHREPORT, params, reponseHandler);
//	}


//	/**
//	 * 通过中心位置获得radius半径内所有商店信息
//	 * @param locX
//	 * @param locY
//	 * @param radius
//	 * @param reponseHandler
//	 */
//	public void searchShopByLoc(double locX, double locY, double radius, JsonHttpResponseHandler reponseHandler){
//		WebRestClient.post(API_ADDDISHREPORT, params, reponseHandler);
//	}
//	


}
