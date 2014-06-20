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
	 * ��½����
	 * @param params
	 * @param reponseHandler
	 */
	public void login(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_LOGIN, params, reponseHandler);
	}
	
	/**
	 * ���û�ע��
	 * @param params
	 * @param reponseHandler
	 */
	public void regis(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_REGIS, params, reponseHandler);	
	}
	
	/**
	 * ע����¼
	 * @param reponseHandler
	 */
	public void logout(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_LOGOUT, null, reponseHandler);
		//WebRestClient.getCookieStore().clear();
	}
	
	/**
	 * �ղص���
	 * @param params
	 * @param reponseHandler
	 */
	public void collectionShop(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_COLLECTIONSHOP, params, reponseHandler);
	}
	
	/**
	 * ȡ�������ղ�
	 * @param params
	 * @param reponseHandler
	 */
	public void cancelCollectionShop(RequestParams params,JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_CANELCOLLECTIONSHOP, params, reponseHandler);
	}	
	
	/**
	 * �½�����
	 * @param params
	 * @param reponseHandler
	 */
	public void addShop(RequestParams params,JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_ADDSHOP, params, reponseHandler);
	}
	
	/**
	 * �½���Ʒ
	 * @param params
	 * @param reponseHandler
	 */
	public void addDish(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_ADDDISH, params, reponseHandler);
	}

	/**
	 * ��ѯ�ҵ�����
	 * @param reponseHandler
	 */
	public void getPersonComment(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETPERSONCOMMENT, null, reponseHandler);
	}
	
	/**
	 * ����������а����ΰ�ǰ10��
	 * @param reponseHandler
	 */
	public void getLevelList(JsonHttpResponseHandler reponseHandler){
		
	}
	
	/**
	 * ǩ��
	 * @param params
	 * @param reponseHandler
	 */
   public void signin(RequestParams params,JsonHttpResponseHandler reponseHandler){
    	WebRestClient.post(API_SIGNIN, params, reponseHandler);
	}
   
   /**
     * ȡ����Ʒ����
     * @param params
     * @param reponseHandler
     */
    public void deleteDishEvaluation(RequestParams params,JsonHttpResponseHandler reponseHandler){
    	WebRestClient.post(API_DELETEDISHEVALUATION, params, reponseHandler);
	}

/**
     * ��Ӳ�Ʒ����
     * @param params
     * @param reponseHandler
     */
    public void addDishEvaluation(RequestParams params,JsonHttpResponseHandler reponseHandler){
    	WebRestClient.post(API_ADDDISHEVALUATION, params, reponseHandler);
	}
    
    /**
     * ȡ���Ե������۵�����
     * @param params
     * @param reponseHandler
     */
    public void deleteShopEvaluationEvaluation(RequestParams params,JsonHttpResponseHandler reponseHandler){
    	WebRestClient.post(API_DELETESHOPEVALUATIONEVALUATION, params, reponseHandler);
	}
    
    
    /**
     * ��ӶԵ������۵�����
     * @param params
     * @param reponseHandler
     */
    public void addShopEvaluationEvaluation(RequestParams params,JsonHttpResponseHandler reponseHandler){
    	WebRestClient.post(API_ADDSHOPEVALUATIONEVALUATION, params, reponseHandler);
	}
    
	/**
	 * �ٱ�������Ϣ
	 * @param params
	 * @param reponseHandler
	 */
	public void addShopReport(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_ADDSHOPREPORT, params, reponseHandler);
	}
	
	/**
	 * �ٱ�����Ϣ
	 * @param params
	 * @param reponseHandler
	 */
	public void addDishReport(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_ADDDISHREPORT, params, reponseHandler);
	}
	/**
	 * ͨ����Ϣ��������
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
		 * id�����̻�������Ϣ
		 * @param params
		 * @param reponseHandler
		 */
	public void searchShopEvaluationById(RequestParams params, JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_SEARCHSHOPEVALUATIONBYID, params, reponseHandler);
	}
	/**
	 * �����������
	 * @param params
	 * @param reponseHandler
	 */
	public void addShopEvaluation(RequestParams params,JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_ADDSHOPEVALUATION, params, reponseHandler);
	}
	/**
	 * ��ȡ���з���ǰ��
	 * @param reponseHandler
	 */
	public void getAllPoint(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETALLPOINT, null, reponseHandler);
	}
	/**
	 * ͨ��uid��ѯ�û���Ϣ
	*/
	public void getPersonPage(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETPERSONPAGE, null, reponseHandler);
	}
	/**
	 * ��ø��˹���
	 * @param reponseHandler
	 */
	public void getPersonContribute(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETPERSONCONTRIBUTE, null, reponseHandler);
	}
		/**
	 * ͨ��uid��ѯ�û��ղص�����Ϣ�������뵱ǰλ�õľ���
	 * @param reponseHandler
	 */
	public void getCollection(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETCOLLECTION, null, reponseHandler);
	}
	/**
	 * ����û����������Ϣ
	 * @param reponseHandler
	 */
	public void getPersonPoint(JsonHttpResponseHandler reponseHandler){
		WebRestClient.post(API_GETPERSONPOINT, null, reponseHandler);
	}

	
//	
//	/**
//	 * �Ե������۵��޻���ȡ����
//	 * @param seId
//	 * @param isPrase true:����   false��ȡ����
//	 * @param reponseHandler
//	 */
//	public void praseShopComment(String seId, boolean isPrase, JsonHttpResponseHandler reponseHandler){
//		//addShopEvaluation deleteShopEvaluation
//		WebRestClient.post(API_ADDDISHREPORT, params, reponseHandler);
//	}
//	
//	/**
//	 * �Բ˵��޻���ȡ����
//	 * @param dishId
//	 * @param isPrase true:����   false��ȡ����
//	 * @param reponseHandler
//	 */
//	public void praseDish(String dishId, boolean isPrase, JsonHttpResponseHandler reponseHandler){
//		//addDishEvaluation deleteDishEvaluation
//		WebRestClient.post(API_ADDDISHREPORT, params, reponseHandler);
//	}
//	
//	/**
//	 * ����û����������Ϣ
//	 * @param reponseHandler
//	 */
//	public void getPersonPoint(JsonHttpResponseHandler reponseHandler){
//		WebRestClient.post(API_ADDDISHREPORT, params, reponseHandler);
//	}


//	/**
//	 * ͨ������λ�û��radius�뾶�������̵���Ϣ
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
