package com.tbond.eatking.net;
// Static wrapper library around AsyncHttpClient

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

public class WebRestClient {
    private static final String BASE_URL = "http://10.2.64.8/Foodie/?m=Dao&a=";
//	private static final String BASE_URL = "http://jxgzl.sinaapp.com/index.php/Admin/API/";
    
    private static AsyncHttpClient client = new AsyncHttpClient();
    
    private static PersistentCookieStore myCookieStore;
    
    private static WebRestClient instance = new WebRestClient(); 
    private WebRestClient(){
    	
    }
    
    public static WebRestClient getInstance(){
    	return instance;
    }

    public static AsyncHttpClient getClient(){
    	return client;
    }
    
    public static PersistentCookieStore getCookieStore(){
    	return myCookieStore;
    }
    
    /**
     * ³Ö¾Ã»¯´æ´¢cookie
     * @param ctx
     */
    public static void init(Context ctx){
    	myCookieStore = new PersistentCookieStore(ctx);
    	client.setCookieStore(myCookieStore);
    }
    
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
    	Log.i("post params:", getAbsoluteUrl(url) + params.toString());
    	client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}