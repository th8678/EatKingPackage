package com.tbond.eatking.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tbond.eatking.*;

<<<<<<< HEAD
import com.tbond.eatking.model.TencentLBS;
=======
//import com.tbond.eatking.model.TencentLBS;
>>>>>>> be49ce460bd2b501dcf8b253768839c7fb216757
//import com.tbond.eatking.model.TencentLBS.LocListener;
//import com.tbond.eatking.view.MapOverlay.OnTapListener;
//import com.tbond.eatking.view.GraphicOverlay;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApi;
import com.tencent.tencentmap.mapsdk.map.GeoPoint;
import com.tencent.tencentmap.mapsdk.map.ItemizedOverlay;
import com.tencent.tencentmap.mapsdk.map.MapActivity;
import com.tencent.tencentmap.mapsdk.map.MapController;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.Overlay;
import com.tencent.tencentmap.mapsdk.map.OverlayItem;
import com.tencent.tencentmap.mapsdk.map.Projection;

public class MapTestActivity extends MapActivity {
	
	/*MapView mMapView;
	MapController mMapController;
	View viewTip=null;
	MapOverlay mapOverlay=null;
	TextView textView;
	Button btn;
	TencentLBS tlbs = new TencentLBS();
	TencentLBS.LocListener mListener;
	Boolean btnSwitch = false;
	MapTestActivity app = this;
	MyOverlayItem moi;
	GraphicOverlay graphicOverlay;
	
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
			if(mMapView.indexOfChild(viewTip)==-1)
			{
				mMapView.addView(viewTip,layParaOntap);
			}
			else
			{
				mMapView.updateViewLayout(viewTip,layParaOntap);
			}
		}

		@Override
		public void onEmptyTap(GeoPoint pt) {
			// TODO Auto-generated method stub
			if(mMapView.indexOfChild(viewTip)>=0)
			{
				mMapView.removeView(viewTip);
			}
		}};
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.map_test);
		
		LayoutInflater layoutInfla = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

		mMapView = (MapView) findViewById(R.id.itemizedoverlayview);
		mMapView.setBuiltInZoomControls(true); //设置启用内置的缩放控件
		textView = (TextView)findViewById(R.id.textView1);
		btn = (Button)findViewById(R.id.button1);
		mMapController = mMapView.getController();

		Drawable marker = getResources().getDrawable(R.drawable.markpoint);  //得到需要标在地图上的资源
		
		this.iTipTranslateY=marker.getIntrinsicHeight();
		
		marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker
				.getIntrinsicHeight());   //为maker定义位置和边界
		
		mapOverlay=new MapOverlay(marker, this);
		
		graphicOverlay=new GraphicOverlay();
		graphicOverlay.setParams(new GeoPoint((int)(39.911766*1e6),(int)(116.305456*1e6)), 20000);
		mMapView.addOverlay(graphicOverlay);	
		
		mapOverlay.setOnTapListener(onTapListener);
		mMapView.addOverlay(mapOverlay); //添加标注，可以通过mMapView.getOverlays().remove删除标注，删除后可以通过mapview.refreshMap()刷新地图   
		                                        //添加ItemizedOverlay实例到mMapView
		
		Bitmap bmpMarker=null;
		Resources res=MapTestActivity.this.getResources();
		bmpMarker=BitmapFactory.decodeResource(res, R.drawable.mark_location);
		
		SimulateLocationOverlay simuOvelay=new SimulateLocationOverlay(bmpMarker);
		mMapView.addOverlay(simuOvelay);
		
		GeoPoint geoSimulateLocation=new GeoPoint((int)(39.984297*1e6), (int)(116.307523*1e6));
		simuOvelay.setGeoCoords(geoSimulateLocation);
		simuOvelay.setAccuracy(5000);
		
		mMapView.invalidate();  //刷新地图      
		
		textView.setText("Press the Button locate.");
		btn.setOnClickListener(new OnClickListener() {
			@SuppressLint("NewApi")
			public void onClick(View arg0) {
				if(!btnSwitch){
					btnSwitch = true;
					btn.setText("stop");
					tlbs.setApplication(app);
					mListener = tlbs.new LocListener(TencentMapLBSApi.GEO_TYPE_WGS84,
				            TencentMapLBSApi.LEVEL_POI,  
				            TencentMapLBSApi.DELAY_NORMAL,textView); 	
					int req = TencentMapLBSApi.getInstance().requestLocationUpdate(
							MapTestActivity.this
									.getApplicationContext(), mListener);
					Log.e("REQLOC", "res: " + req);
					TencentMapLBSApi.getInstance().setGPSUpdateInterval(1000);
				}else{
					btnSwitch = false;
					btn.setText("locate");
					TencentMapLBSApi.getInstance().removeLocationUpdate();
					textView.setText("");
					
				}
			}
		});
	}
	
	public void func(double latitude,double longitude){
		mapOverlay.oi1.setParams(new GeoPoint((int)(latitude * 1E6),(int)(longitude * 1E6)), 2000);
		mapOverlay.oi1.setPoint(new GeoPoint((int)(latitude * 1E6),(int)(longitude * 1E6)));
		graphicOverlay.setParams(new GeoPoint((int)(latitude * 1E6),(int)(longitude * 1E6)), 20000);
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
//	private Context mContext;
	private OnTapListener tapListener=null;

	private double mLat1 = 39.911766; // point1纬度
	private double mLon1 = 116.305456; // point1经度

	private double mLat2 = 39.80233;
	private double mLon2 = 116.397741;

	private double mLat3 = 39.99233;
	private double mLon3 = 116.4646;
	
	MyOverlayItem oi1;

	public MapOverlay(Drawable marker, Context context) {
		super(boundCenterBottom(marker));
		// 用给定的经纬度构造GeoPoint，单位是微度 (度 * 1E6)
		GeoPoint p1 = new GeoPoint((int) (mLat1 * 1E6), (int) (mLon1 * 1E6));
		GeoPoint p2 = new GeoPoint((int) (mLat2 * 1E6), (int) (mLon2 * 1E6));
		GeoPoint p3 = new GeoPoint((int) (mLat3 * 1E6), (int) (mLon3 * 1E6));

		// 构造OverlayItem的三个参数依次为：item的位置，标题文本，文字片段
		oi1 = new MyOverlayItem(p1, "1", "已选中第一个点");
		oi1.setParams(p1, 2000);
		itemList.add(oi1);
		OverlayItem itemNntDrag=new OverlayItem(p2, "2", "该点无法拖拽");
		itemNntDrag.setDragable(false);
		itemList.add(itemNntDrag);
		itemList.add(new OverlayItem(p3, "3", "已选中第三个点"));		
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



	interface OnTapListener
	{
		void onTap(OverlayItem itemTap);
		void onEmptyTap(GeoPoint pt);
	}
	
	public void setOnTapListener(OnTapListener listnerTap)
	{
		tapListener=listnerTap;
	}
}

class SimulateLocationOverlay extends Overlay {
	
	GeoPoint geoPoint;
	GeoPoint heart;
	float r;
	Bitmap bmpMarker;
	float fAccuracy=0f;
	

	public void setParams(GeoPoint heart2,float r2){
		heart = heart2;
		r = r2;
	}
	
	public SimulateLocationOverlay(Bitmap mMarker) {
	    bmpMarker = mMarker;
	}
	
    private final double EARTH_RADIUS = 6378137.0;  
private double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
       double radLat1 = (lat_a * Math.PI / 180.0);
       double radLat2 = (lat_b * Math.PI / 180.0);
       double a = radLat1 - radLat2;
       double b = (lng_a - lng_b) * Math.PI / 180.0;
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
              + Math.cos(radLat1) * Math.cos(radLat2)
              * Math.pow(Math.sin(b / 2), 2)));
       s = s * EARTH_RADIUS;
       s = Math.round(s * 10000) / 10000;
       return s;
    }
	
	public void setGeoCoords(GeoPoint geoSimulateLoc)
	{
		if(geoPoint==null)
		{
			geoPoint=new GeoPoint(geoSimulateLoc.getLatitudeE6(),geoSimulateLoc.getLongitudeE6());
		}
		else if(gps2m(geoPoint.getLatitudeE6(),geoPoint.getLongitudeE6(),
				geoSimulateLoc.getLatitudeE6(),geoSimulateLoc.getLongitudeE6()) < r)
		{
			geoPoint.setLatitudeE6(geoSimulateLoc.getLatitudeE6());
			geoPoint.setLongitudeE6(geoSimulateLoc.getLongitudeE6());
		}
	}
	
	public void setAccuracy(float fAccur)
	{
		fAccuracy=fAccur;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView) {
		if(geoPoint==null)
		{
			return;
		}
		Projection mapProjection = mapView.getProjection();
		Paint paint = new Paint();
		Point ptMap = mapProjection.toPixels(geoPoint, null);
		paint.setColor(Color.BLUE);
		paint.setAlpha(8);
		paint.setAntiAlias(true);

		float fRadius=mapProjection.metersToEquatorPixels(fAccuracy);
		canvas.drawCircle(ptMap.x, ptMap.y, fRadius, paint);
		paint.setStyle(Style.STROKE);
		paint.setAlpha(200);
		canvas.drawCircle(ptMap.x, ptMap.y, fRadius, paint);

		if(bmpMarker!=null)
		{
			paint.setAlpha(255);
			canvas.drawBitmap(bmpMarker, ptMap.x - bmpMarker.getWidth() / 2, ptMap.y
					- bmpMarker.getHeight() / 2, paint);
		}
		
		super.draw(canvas, mapView);
	}*/
}
