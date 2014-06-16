package com.tbond.eatking.model;
import android.app.Application;
import android.widget.TextView;

//import com.tencent.tencentmap.lbssdk.MapTestActivity;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApi;  
import com.tencent.tencentmap.lbssdk.TencentMapLBSApiListener;  
import com.tencent.tencentmap.lbssdk.TencentMapLBSApiResult; 

public class TencentLBS {
	
	/*LocListener mListener;
	public String result,resState;
	public double latitude,longitude;
	MapTestActivity application;
	public void locate(String res,MapTestActivity app){
		application = app;
		mListener = new LocListener(TencentMapLBSApi.GEO_TYPE_WGS84,
	            TencentMapLBSApi.LEVEL_POI,  
	            TencentMapLBSApi.DELAY_NORMAL); 	
		TencentMapLBSApi.getInstance().setGPSUpdateInterval(1000);
	}
	
	public void locate(TextView tv,MapTestActivity app){
		application = app;
		mListener = new LocListener(TencentMapLBSApi.GEO_TYPE_WGS84,
	            TencentMapLBSApi.LEVEL_POI,  
	            TencentMapLBSApi.DELAY_NORMAL); 
		TencentMapLBSApi.getInstance().setGPSUpdateInterval(1000);
	}
	
	public void stopLocate(){
		TencentMapLBSApi.getInstance().removeLocationUpdate();
	}
	
	public void setApplication(MapTestActivity app){application = app;}
	
	public class LocListener extends TencentMapLBSApiListener {  
		
		TextView textView;
		
	    public LocListener(int reqGeoType, int reqLevel,  
	                int reqDelay) {  
	        super(TencentMapLBSApi.GEO_TYPE_WGS84, reqGeoType,reqLevel);  
	    }  
	    
	    public LocListener(int reqGeoType, int reqLevel,  
                int reqDelay,TextView tv) {  
        super(TencentMapLBSApi.GEO_TYPE_WGS84, reqGeoType,reqLevel);  
        textView = tv;
    }  
	   
	    @Override  
	    public void onLocationUpdate(TencentMapLBSApiResult locRes) {  
	        String res = locRes.toString();  
	        
	        if(locRes.Address != null)
	    	TencentMapLBSApi.getInstance().requestLocationUpdate(application.getApplicationContext(), mListener);
	        result = res;
	        latitude = locRes.Latitude;
	        longitude = locRes.Longitude;
	        if(textView != null){
	        	textView.setText(res);
	        }
	        application.func(locRes.Latitude, locRes.Longitude);
	    }  
	   
	    @Override 
	    public void onStatusUpdate(int state) {  
	        String strState = null;  
	        switch (state) {  
	        case TencentMapLBSApi.STATE_GPS_DISABLED:  
	            strState = "Gps Disabled";  
	            break;  
	        case TencentMapLBSApi.STATE_GPS_ENABLED:  
	            strState = "Gps Enabled";  
	            break;  
	        case TencentMapLBSApi.STATE_WIFI_DISABLED:  
	            strState = "Wifi Disabled";  
	            break;  
	        case TencentMapLBSApi.STATE_WIFI_ENABLED:  
	            strState = "Wifi Enabled";  
	            break;  
	        }  
	        resState = strState;  
	    }  
	}*/
	
}


