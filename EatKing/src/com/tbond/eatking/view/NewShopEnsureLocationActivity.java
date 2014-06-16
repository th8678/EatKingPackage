package com.tbond.eatking.view;

import java.util.ArrayList;
import java.util.List;

import com.tbond.eatking.R;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApi;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApiListener;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApiResult;
import com.tencent.tencentmap.mapsdk.map.GeoPoint;
import com.tencent.tencentmap.mapsdk.map.ItemizedOverlay;
import com.tencent.tencentmap.mapsdk.map.MapController;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.Overlay;
import com.tencent.tencentmap.mapsdk.map.OverlayItem;
import com.tencent.tencentmap.mapsdk.map.PoiOverlay;
import com.tencent.tencentmap.mapsdk.map.Projection;
import com.tencent.tencentmap.mapsdk.search.PoiResults;
import com.tencent.tencentmap.mapsdk.search.PoiSearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class NewShopEnsureLocationActivity extends Activity {

	EditText shopName,shopAddress;
	MapView mapView;
	ImageButton ensureBtn,backBtn;
	String location;
    ImageView btnLocate;
    PoiSearch poiSearch;
    PoiOverlay myPoiOverlay;
    LocListener mListener;
    GraphicOverlay graphicOverlay;
    MapOverlay mapOverlay=null;
    View viewTip=null;
    
    int iTipTranslateX=0;
	int iTipTranslateY=0;
	
	OnTapListener onTapListener=new OnTapListener(){

		@Override
		public void onTap(OverlayItem itemTap) {
			// TODO Auto-generated method stub
			if(viewTip==null||itemTap==null)
			{
				return;
			}
			MapView.LayoutParams layParaOntap=new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT,MapView.LayoutParams.WRAP_CONTENT,itemTap.getPoint(),iTipTranslateX,-iTipTranslateY,MapView.LayoutParams.BOTTOM_CENTER);
			if(mapView.indexOfChild(viewTip)==-1)
			{
				mapView.addView(viewTip,layParaOntap);
			}
			else
			{
				mapView.updateViewLayout(viewTip,layParaOntap);
			}
		}

		@Override
		public void onEmptyTap(GeoPoint pt) {
			// TODO Auto-generated method stub
			if(mapView.indexOfChild(viewTip)>=0)
			{
				mapView.removeView(viewTip);
			}
		}};
	
	public NewShopEnsureLocationActivity(){
    	super();
    }
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_shop_ensure_location);
        
        shopName = (EditText)findViewById(R.id.newShopName);
        shopAddress = (EditText)findViewById(R.id.newShopAddress);
        mapView = (MapView)findViewById(R.id.mapview);
        ensureBtn = (ImageButton)findViewById(R.id.ensureBtn);
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        
        shopName.setText(savedInstanceState.getString("shop_name"));
        shopAddress.setText("正在获取地址");
        
        
        
        backBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	            NewShopEnsureLocationActivity.this.finish();   
			}});
        
        ensureBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(location == null || location == ""){
					//弹出通知，请确认位置\请检查网络
				}
				else{
					Api api = new Api();
					Api.addNewShop(shopName.getText().toString(),location);
				}
			}});
        
        prepareMapView();
        
    }
	
	private void prepareMapView(){
		mapView.setBuiltInZoomControls(false);
		addButtonLocate();
    	locate(TencentMapLBSApi.GEO_TYPE_WGS84,TencentMapLBSApi.LEVEL_ADMIN_AREA,1);
	}
	
	void addButtonLocate(){
		btnLocate=new ImageView(this);
		btnLocate.setImageResource(R.drawable.action_bar_background);
		btnLocate.setAlpha(0.5f);
		
		//layout parameters of locate button
		MapView.LayoutParams btnLocateParams = new MapView.LayoutParams(100,100,
				100,100,MapView.LayoutParams.CENTER);
		btnLocate.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				locate(TencentMapLBSApi.GEO_TYPE_WGS84,TencentMapLBSApi.LEVEL_ADMIN_AREA,1);
			}});
		btnLocate.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getActionIndex() == MotionEvent.ACTION_DOWN){
					locate(TencentMapLBSApi.GEO_TYPE_WGS84,TencentMapLBSApi.LEVEL_ADMIN_AREA,1);
				v.setAlpha(0.8f);}
				else {v.setAlpha(0.5f);}
				return false;
			}});
		mapView.addView(btnLocate,btnLocateParams);
    }
	
	void locate(int geoType,int geoLevel,int mode){
    	mListener = new LocListener(geoType,
	            geoLevel,  
	            TencentMapLBSApi.DELAY_NORMAL,mode); 
		TencentMapLBSApi.getInstance().setGPSUpdateInterval(1000);
		mListener.setArea(20000);
		TencentMapLBSApi.getInstance().requestLocationUpdate(getApplicationContext(), mListener);
    }
    
    private class LocListener extends TencentMapLBSApiListener{

    	int area = -1;
    	public LocListener(int reqGeoType, int reqLevel,
				int reqDelay,int m) {
			super(reqGeoType, reqLevel, reqDelay);
		}
    	
    	public void setArea(int a){
    		area = a;
    	}
    	
    	@Override
		public void onLocationUpdate(TencentMapLBSApiResult locRes) {
    		MapController mapController = mapView.getController();
    		mapController.setCenter(new GeoPoint((int)(locRes.Latitude * 1E6),(int)(locRes.Longitude * 1E6)));
    		mapController.setZoom(15);
    		
    		Drawable marker = getResources().getDrawable(R.drawable.markpoint);  //得到需要标在地图上的资源
    		
    		iTipTranslateY=marker.getIntrinsicHeight();
    		
    		marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker
    				.getIntrinsicHeight());   //为maker定义位置和边界
    		
    		mapOverlay=new MapOverlay(marker, NewShopEnsureLocationActivity.this,locRes.Latitude,locRes.Longitude,2000);
    		
    		graphicOverlay=new GraphicOverlay();
    		graphicOverlay.setParams(new GeoPoint((int)(39.911766*1e6),(int)(116.305456*1e6)), area);
    		mapView.addOverlay(graphicOverlay);	
    		mapOverlay.setOnTapListener(onTapListener);
    		mapView.addOverlay(mapOverlay);
			stopLocate();
    		
		}
    	
    	public void stopLocate(){
    		TencentMapLBSApi.getInstance().removeLocationUpdate();
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
			//弹出一个通知好了
			//mTextState.setText(strState);
		}
    	
    }
    
    class GraphicOverlay extends Overlay
    {
    	GeoPoint heart;float r;
    	
    	public void setParams(GeoPoint h,float r2){
    		this.heart = h;
    		this.r = r2;
    	}
    	public void draw(Canvas canvas, MapView mapView) 
    	{
    		drawCircle(canvas,mapView);
    	}
    	
    	private void drawCircle(Canvas canvas, MapView mapView)
    	{
    		Point ptHeart=mapView.getProjection().toPixels(heart, null);
    		float r2 = mapView.getProjection().metersToEquatorPixels(r);
    		
    		Paint paintCircle=new Paint();
    		paintCircle.setStyle(Style.FILL);
    		paintCircle.setStrokeWidth(10);
    		paintCircle.setColor(Color.RED);
    		paintCircle.setAntiAlias(true);
    		paintCircle.setStrokeCap(Cap.ROUND);
    		paintCircle.setStrokeJoin(Join.ROUND);
    		paintCircle.setAlpha(100);
    		
    		canvas.drawCircle(ptHeart.x, ptHeart.y, r2, paintCircle);
    	}
    }
	
	class MyOverlayItem extends OverlayItem{

		GeoPoint heart;
		float r;
		
		public MyOverlayItem(GeoPoint point, String title, String snippet) {
			super(point, title, snippet);
			// TODO Auto-generated constructor stub
		}
		
		public void setParams(GeoPoint h,float r2){
			heart = h;
			r = r2;
		}

		public void setPoint(GeoPoint gPt){
			if(heart != null){
				Location locationA = new Location("ptA");
				Location locationB = new Location("ptB");
				locationA.setLatitude(heart.getLatitudeE6()/1E6);
				locationA.setLongitude(heart.getLongitudeE6()/1E6);
				locationB.setLatitude(gPt.getLatitudeE6()/1E6);
				locationB.setLongitude(gPt.getLongitudeE6()/1E6);
				float tempF = locationA.distanceTo(locationB);
				if( locationA.distanceTo(locationB) > r){
					gPt = new GeoPoint((int)(heart.getLatitudeE6() + (r/tempF)*(gPt.getLatitudeE6()-heart.getLatitudeE6())),
							(int)(heart.getLongitudeE6() + (r/tempF)*(gPt.getLongitudeE6()-heart.getLongitudeE6())));}
			}
			super.setPoint(gPt);
		}
		
	}
	
	class MapOverlay extends ItemizedOverlay<OverlayItem> {
		private List<OverlayItem> itemList = new ArrayList<OverlayItem>();
//		private Context mContext;
		private OnTapListener tapListener=null;

		private double mLat1; // point1纬度
		private double mLon1; // point1经度
		
		MyOverlayItem oi1;

		public void setLocation(double lat,double lon){
			mLat1 = lat;
			mLon1 = lon;
		}
		
		public MapOverlay(Drawable marker, Context context,double lat,double lon,float area) {
			super(boundCenterBottom(marker));
			setLocation(lat,lon);
			// 用给定的经纬度构造GeoPoint，单位是微度 (度 * 1E6)
			GeoPoint p1 = new GeoPoint((int) (mLat1 * 1E6), (int) (mLon1 * 1E6));

			// 构造OverlayItem的三个参数依次为：item的位置，标题文本，文字片段
			oi1 = new MyOverlayItem(p1, "1", "已选中第一个点");
			oi1.setParams(p1, area);
			itemList.add(oi1);
			populate();  //createItem(int)方法构造item。一旦有了数据，在调用其它方法前，首先调用这个方法
		}

		@Override
		public void draw(Canvas canvas, MapView mapView) {

			// Projection接口用于屏幕像素点坐标系统和地球表面经纬度点坐标系统之间的变换
			Projection projection = mapView.getProjection(); 
			for (int index = size() - 1; index >= 0; index--) { // 遍历GeoList
				OverlayItem overLayItem = getItem(index); // 得到给定索引的item

				String title = overLayItem.getTitle();
				// 把经纬度变换到相对于MapView左上角的屏幕像素坐标
				Point point = projection.toPixels(overLayItem.getPoint(), null); 

				Paint paintCircle = new Paint();
				paintCircle.setColor(Color.RED);
				canvas.drawCircle(point.x, point.y, 5, paintCircle); // 画圆


			}

			super.draw(canvas, mapView);
		}

		@Override
		protected OverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			return itemList.get(i);
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return itemList.size();
		}

		@Override
		protected boolean onTap(int i) {
			OverlayItem itemSelect=itemList.get(i);
			setFocus(itemSelect);
			if(tapListener!=null)
			{
				tapListener.onTap(itemSelect);
			}
			return true;
		}
		
		@Override
		public void onEmptyTap(GeoPoint pt) {
			// TODO Auto-generated method stub
			super.onEmptyTap(pt);
			
			if(tapListener!=null)
			{
				tapListener.onEmptyTap(pt);
			}
		}

		public void setOnTapListener(OnTapListener listnerTap)
		{
			tapListener=listnerTap;
		}
	}
	
	interface OnTapListener
	{
		void onTap(OverlayItem itemTap);
		void onEmptyTap(GeoPoint pt);
	}
}
