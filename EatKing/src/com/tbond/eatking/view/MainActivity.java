package com.tbond.eatking.view;

import java.util.Date;
import java.util.List;

import greendroid.app.GDActivity;
import greendroid.widget.ActionBar;
import greendroid.widget.ActionBarItem;
import greendroid.widget.QuickAction;
import greendroid.widget.QuickActionBar;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.SegmentedAdapter;
import greendroid.widget.SegmentedHost;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;

import com.tbond.eatking.R;
import com.tbond.eatking.view.MapOverlay.OnTapListener;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApi;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApiListener;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApiResult;
import com.tencent.tencentmap.mapsdk.map.GeoPoint;
import com.tencent.tencentmap.mapsdk.map.ItemizedOverlay;
import com.tencent.tencentmap.mapsdk.map.MapController;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.MapView.LayoutParams;
import com.tencent.tencentmap.mapsdk.map.Overlay;
import com.tencent.tencentmap.mapsdk.map.OverlayItem;
import com.tencent.tencentmap.mapsdk.map.PoiOverlay;
import com.tencent.tencentmap.mapsdk.map.PoiOverlay.CustomInfoWindowAdapter;
import com.tbond.eatking.net.JsonAnalysis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tencentmap.mapsdk.search.PoiItem; 
import com.tencent.tencentmap.mapsdk.search.PoiResults;
import com.tencent.tencentmap.mapsdk.search.PoiSearch;

public class MainActivity extends GDActivity {

	private final Handler mHandler = new Handler();
    private PeopleSegmentedAdapter mAdapter;
    SegmentedHost segmentedHost;
    private QuickActionBar mBar;
    MapView mapView;
    ImageView btnLocate;
    PoiSearch poiSearch;
    PoiOverlay myPoiOverlay;
    LocListener mListener;
    UserOverlay userOverlay;
    
    public MainActivity(){
    	super(ActionBar.Type.Normal);
    }
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setActionBarContentView(R.layout.activity_main);

        segmentedHost = (SegmentedHost) findViewById(R.id.segmented_host);
        mapView = (MapView)findViewById(R.id.mapview);
        
        mAdapter = new PeopleSegmentedAdapter();
//        mHandler.postDelayed(new Runnable() {
//            public void run() {
//                mAdapter.mReverse = true;
//                mAdapter.notifyDataSetChanged();
//            }
//        }, 4000);
        getGDActionBar().setFirstDrawable(this, R.drawable.gd_action_bar_list);
        getGDActionBar().getFirstButton().setOnClickListener(new ListBarListener());;
        addActionBarItem(Type.Search, R.id.action_bar_search);
        segmentedHost.setAdapter(mAdapter);
        getGDActionBar().setBackgroundColor(getResources().getColor(R.color.color1));
        
        prepareQuickActionBar();
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
		TencentMapLBSApi.getInstance().requestLocationUpdate(getApplicationContext(), mListener);
    }
    
    public void showPoi(GeoPoint userLocation,PoiResults poiResult,PoiOverlay myPoiOverlay){
    	List<PoiItem> listPois=poiResult.getCurrentPagePoiItems();
		if(listPois==null)
		{
			return;
		}
		
		if(myPoiOverlay==null)
		{
			myPoiOverlay = new PoiOverlay(null);
			myPoiOverlay.setCustomInfoWindowAdapter(new PoiInfoWindow(userLocation,myPoiOverlay));
			mapView.addOverlay(myPoiOverlay);
		}
		myPoiOverlay.setPoiItems(listPois);
		mapView.invalidate();  //刷新地图     
    }
    
    public void showUser(GeoPoint geoPoint){
    	if(userOverlay != null)
    		mapView.removeOverlay(userOverlay);
    	userOverlay = new UserOverlay();
    	userOverlay.setParams(geoPoint);
		mapView.addOverlay(userOverlay);
    }
    
    /**
     * 准备actionbar中的listbar，在listListener中显示
     */
    private void prepareQuickActionBar() {
        mBar = new QuickActionBar(this);
        mBar.addQuickAction(new MyQuickAction(this, R.drawable.gd_action_bar_all_friends, R.string.personalInfo));
        mBar.addQuickAction(new MyQuickAction(this, R.drawable.gd_action_bar_star, R.string.mark));
        mBar.addQuickAction(new MyQuickAction(this, R.drawable.gd_action_bar_compose, R.string.setting));

        mBar.setOnQuickActionClickListener(mActionListener);
    }

    /**
     * 点击listbar中的项事件
     */
    private OnQuickActionClickListener mActionListener = new OnQuickActionClickListener() {
        public void onQuickActionClicked(QuickActionWidget widget, int position) {
            Toast.makeText(MainActivity.this, "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
        }
    };
    
    private class SegmentSwitcher implements OnSegmentChangeListener {
        public void onSegmentChange(int index, boolean clicked) {
            changeActivity(index);
        }
    }
    
    public void changeActivity(int index){
    	Log.i("index", "点了点了" + index);
    	segmentedHost.getSegmentedBar().setEnabled(false);
    	switch (index) {
		case 0:
			intent = new Intent();
            intent.setClass(MainActivity.this, CreateShopActivity.class);
            startActivity(intent);
            break;
		case 1:
			JsonAnalysis.getInstance().login(MainActivity.this, "tbond", "tbond");
		
			break;
		case 2:
			/**
	         * 跳转纠错页
	         */
	        Intent intent = new Intent();
	        intent.setClass(MainActivity.this, CorrectActivity.class);
	        startActivity(intent);
			break;
		case 3:
			break;

		default:
			break;
		}
    }
    
    /**
     * segmentbar四个小项的事件处理以及名称
     * @author Administrator
     *
     */
    private class PeopleSegmentedAdapter extends SegmentedAdapter {

        public boolean mReverse = false;

        @Override
        public View getView(int position, ViewGroup parent) {
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.text, parent, false);
            textView.setText(getSegmentTitle(position));
            
            return textView;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public String getSegmentTitle(int position) {

            switch (mReverse ? ((getCount() - 1) - position) : position) {
                case 0:
                    return getString(R.string.newOne);
                case 1:
                    return getString(R.string.recommand);
                case 2:
                    return getString(R.string.list);
                case 3:
                    return getString(R.string.more);
            }

            return null;
        }
    }
    
    /**
     * 处理actionbar上的除了homelist的所有事件
     */
    @Override
    public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
    	
        int itemId = item.getItemId();
        if (itemId == R.id.action_bar_search) {
        	Intent intent = new Intent();
            intent.setClass(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        } else {
			return super.onHandleActionBarItemClick(item, position);
		}

        return true;
    }
    
    /**
     * 处理listbar事件
     * @author Administrator
     *
     */
    private class ListBarListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			mBar.show(arg0);
		}
    };
    
    /**
     * 自己quickActionBar
     * @author Administrator
     *
     */
    private static class MyQuickAction extends QuickAction {
        /**
         * 设置图片颜色
         */
        private static final ColorFilter BLACK_CF = new LightingColorFilter(Color.BLACK, Color.BLACK);

        public MyQuickAction(Context ctx, int drawableId, int titleId) {
            super(ctx, buildDrawable(ctx, drawableId), titleId);
        }
        
        private static Drawable buildDrawable(Context ctx, int drawableId) {
            Drawable d = ctx.getResources().getDrawable(drawableId);
            d.setColorFilter(BLACK_CF);
            return d;
        }
        
    }
    private class LocListener extends TencentMapLBSApiListener{

    	int mode;//0:locate and deal with 
    	//1:locate and up
    	int area = -1;
    	public LocListener(int reqGeoType, int reqLevel,
				int reqDelay,int m) {
			super(reqGeoType, reqLevel, reqDelay);
			mode = m;
		}
    	
    	public void setArea(int a){
    		area = a;
    	}
    	
    	@Override
		public void onLocationUpdate(TencentMapLBSApiResult locRes) {
    		MapController mapController = mapView.getController();
    		mapController.setCenter(new GeoPoint((int)(locRes.Latitude * 1E6),(int)(locRes.Longitude * 1E6)));
    		mapController.setZoom(15);
    		
    		switch(mode){
    		case 0:
    			
    			break;
    		case 1://处理主界面，定位后更新附近poi
    			if(poiSearch==null)
				{
					poiSearch=new PoiSearch(MainActivity.this);
				}
    			PoiResults poiResult=null;
    			try {
//					poiResult=poiSearch.searchPoiInBound(strKeyWord, geoLeftButtom, geoRightTop);
					poiResult=poiSearch.searchPoiInCircle("美食", new GeoPoint((int)(locRes.Latitude * 1E6),(int)(locRes.Longitude * 1E6)), 1000);
//					poiResult=poiSearch.searchPoiInCity("美食", locRes.City);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
    			showPoi(new GeoPoint((int)(locRes.Latitude * 1E6),(int)(locRes.Longitude * 1E6)),poiResult, myPoiOverlay);
    			showUser(new GeoPoint((int)(locRes.Latitude * 1E6),(int)(locRes.Longitude * 1E6)));
				stopLocate();
    			break;
    		default:
    			break;
    		}
    		
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
    
    private class UserOverlay extends Overlay{
    	GeoPoint location;
    	
    	public void setParams(GeoPoint l){
    		location = l;
    	}
    	@Override
    	public void draw(Canvas canvas, MapView mapView) 
    	{
    		Point pt = mapView.getProjection().toPixels(location, null);
    		Bitmap bitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/mark_location.png"));
    		Matrix matrix = new Matrix();
    		matrix.postScale(2f, 2f);
    		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    		canvas.drawBitmap(bitmap,pt.x,pt.y,new Paint());
    		
    	}
    }

    private class PoiInfoWindow implements CustomInfoWindowAdapter{
    	
    	Context context = MainActivity.this;
    	GeoPoint userLocation;
    	PoiOverlay poiOverlay;
    	
    	public PoiInfoWindow(GeoPoint u,PoiOverlay p){
    		super();
    		userLocation = u;
    		poiOverlay = p;
    	}
    	
		@Override
		public View getInfoWindowView(PoiItem poiItem) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.poi_info_window, null);
			TextView text = (TextView) layout.findViewById(R.id.poiName);
			text.setText(poiItem.name);
			text = (TextView)layout.findViewById(R.id.distance);
			Location locationA = new Location("ptA");
			Location locationB = new Location("ptB");
			locationA.setLatitude(userLocation.getLatitudeE6()/1E6);
			locationA.setLongitude(userLocation.getLongitudeE6()/1E6);
			locationB.setLatitude(poiItem.point.getLatitudeE6()/1E6);
			locationB.setLongitude(poiItem.point.getLongitudeE6()/1E6);
			float tempF = locationA.distanceTo(locationB);
			text.setText("距离："+(int)tempF+" m");
			poiOverlay.closeInfoWindow();
			
			return layout;
		}

		@Override
		public LayoutParams getInfowindowLayoutOutParameter(GeoPoint geoPoint,
				int markerWidth, int markerHeight) {
			// TODO Auto-generated method stub
			LayoutParams layoutParams = new MapView.LayoutParams(markerWidth*10,markerHeight*10,geoPoint,LayoutParams.CENTER);
			return layoutParams;
		}
	}
    
	public void setOnTapListener(OnTapListener listnerTap)
	{
		tapListener=listnerTap;
	}
    
}
