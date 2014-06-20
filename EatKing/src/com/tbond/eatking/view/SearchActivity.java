package com.tbond.eatking.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import greendroid.app.GDActivity;
import greendroid.widget.ActionBar;
import greendroid.widget.ActionBarItem;
import greendroid.widget.NormalActionBarItem;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;
import com.tbond.eatking.R;
import com.tbond.eatking.model.ErrorMessage;
import com.tbond.eatking.model.Shop;
import com.tbond.eatking.model.UserComment;
import com.tbond.eatking.net.JsonAnalysis;
import com.tbond.eatking.view.ShowReviewActivity.ViewHolder;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApi;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApiListener;
import com.tencent.tencentmap.lbssdk.TencentMapLBSApiResult;
import com.tencent.tencentmap.mapsdk.map.GeoPoint;
import com.tencent.tencentmap.mapsdk.map.MapController;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.Overlay;
import com.tencent.tencentmap.mapsdk.map.PoiOverlay;
import com.tencent.tencentmap.mapsdk.map.MapView.LayoutParams;
import com.tencent.tencentmap.mapsdk.map.PoiOverlay.CustomInfoWindowAdapter;
import com.tencent.tencentmap.mapsdk.search.PoiItem;
import com.tencent.tencentmap.mapsdk.search.PoiResults;
import com.tencent.tencentmap.mapsdk.search.PoiSearch;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class SearchActivity extends GDActivity {
	SearchActivity searchActivity;

    ActionBar actionBar;
    SearchText searchText;
    MapView mapView;
    ListView listView;
    ImageView btnLocate;
    List<Map<String, Object>> list;
    LocListener mListener;
    PoiOverlay poToRemove,myPoiOverlay;
    UserOverlay userOverlay;
    Boolean locateSearchShopFlag = false;
    GeoPoint userLocation;
    TabHost tabHost;
    TabSpec tabMap,tabList;
    
    
    public SearchActivity(){
    	super(ActionBar.Type.Empty);
    	searchActivity = this;
    }
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setActionBarContentView(R.layout.activity_main);

        searchText = new SearchText(this, null);
        addActionBarItem(getGDActionBar().
        		newActionBarItem(NormalActionBarItem.class)
                .setDrawable(R.drawable.gd_action_bar_back)
                .setContentDescription(R.string.gd_back), R.id.back_bar);
        getGDActionBar().addView(searchText);

        addActionBarItem(Type.Search, R.id.action_bar_search);
        
        mapView = (MapView)findViewById(R.id.mapview);
        listView = (ListView)findViewById(R.id.listview);
        
        prepareTab();
        prepareMapView();
        prepareListView();
        
        
    }

    /**
     * 点击listbar中的项事件
     */
    private OnQuickActionClickListener mActionListener = new OnQuickActionClickListener() {
        public void onQuickActionClicked(QuickActionWidget widget, int position) {
            Toast.makeText(SearchActivity.this, "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
        }
    };
    
    /**
     * 处理actionbar上的除了homelist的所有事件
     */
    @Override
    public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
    	
        int itemId = item.getItemId();
        if (itemId == R.id.action_bar_search) {
        	String keyText = searchText.et_searchtext_search.getText().toString();
        	if(keyText == null || keyText.length() == 0){
        		Toast.makeText(SearchActivity.this, "请输入关键字", Toast.LENGTH_SHORT).show();
        	}
        	else{
        		//remember to change here!!!!!!!!!!!!!!!
        		//JsonAnalysis.getInstance().searchShopByKey(SearchActivity.this, keyText);
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		List<Shop> shops = new ArrayList<Shop>();
        		Shop ts = new Shop();
        		ts.setLocationX((39)+"");
        		ts.setLocationY((117)+"");
        		ts.setShopName("123");
        		shops.add(ts);
        		setShop(shops);
        	}
        } else if(itemId == R.id.back_bar){
			finish();
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
    private class BackBarListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			finish();
		}
    };
    
    private void prepareTab(){
    	tabHost = (TabHost)findViewById(android.R.id.tabhost);
    	tabHost.setup();
    	
    	tabHost.addTab(tabHost.newTabSpec("tabMap").setContent(R.id.tabMapView).setIndicator("地图"));
    	tabHost.addTab(tabHost.newTabSpec("tabList").setContent(R.id.tabListView).setIndicator("列表"));
    	tabHost.setCurrentTab(0);
    	
    }
    
    private void prepareMapView(){
    	addButtonLocate();
    	locate(TencentMapLBSApi.GEO_TYPE_WGS84,TencentMapLBSApi.LEVEL_ADMIN_AREA,0);
    }
    
    private void prepareListView(){
    	
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
				locate(TencentMapLBSApi.GEO_TYPE_WGS84,TencentMapLBSApi.LEVEL_ADMIN_AREA,0);
			}});
		btnLocate.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getActionIndex() == MotionEvent.ACTION_DOWN){
					locate(TencentMapLBSApi.GEO_TYPE_WGS84,TencentMapLBSApi.LEVEL_ADMIN_AREA,0);
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
    
    public void showPoi(GeoPoint userLocation,List<Shop> shops,PoiOverlay myPoiOverlay) {
    	List<PoiItem> listPois = new ArrayList<PoiItem>();
    	Shop ts;//temp shop
    	PoiItem tpi;//temp poi item
    	for(int i = 0;i < shops.size();++i){
    		ts = shops.get(i);
    		tpi = new PoiItem();
    		tpi.address = ts.getAddress();
    		tpi.name = ts.getShopName();
    		tpi.phone = ts.getphoneNumber();
    		tpi.point = new GeoPoint((int)(Integer.parseInt(ts.getLocationX()) * 1E6), (int)(Integer.parseInt(ts.getLocationY()) * 1E6));
    		tpi.uid = ts.getShopId();
    		listPois.add(tpi);
    	}
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
   
    
    private class PoiInfoWindow implements CustomInfoWindowAdapter{
    	
    	Context context = SearchActivity.this;
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

    public class ShopReviewAdapter extends BaseAdapter{

		private LayoutInflater mInflater;
		
		
		public ShopReviewAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder = null;
			if (convertView == null) {
				
				holder=new ViewHolder();  
				
				convertView = mInflater.inflate(R.layout.shop_review_list_item, null);
				
				holder.shopName = (TextView)convertView.findViewById(R.id.shopName);
				holder.markTaste = (TextView)convertView.findViewById(R.id.markTaste);
				holder.markEnvironment = (TextView)convertView.findViewById(R.id.markEnvironment);
				holder.markService = (TextView)convertView.findViewById(R.id.markService);
				convertView.setTag(holder);
				
			}else {
				
				holder = (ViewHolder)convertView.getTag();
			}
			
			holder.shopName.setText((String)list.get(position).get("user_id"));
			holder.markTaste.setText("口味："+(String)list.get(position).get("mark_taste")+"  ");
			holder.markEnvironment.setText("环境："+(String)list.get(position).get("mark_environment")+"  ");
			holder.markService.setText("服务："+(String)list.get(position).get("mark_service")+"  ");
			//holder.perPrice.setText("人均："+(String)list.get(position).get("per_price")+"  ");
			
			return convertView;
		}
		
	}

    private List<Map<String, Object>> getListData(List<Shop> shops){
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	//JsonAnalysis.getInstance().searchShopEvaluationById(ShowReviewActivity.this, shopId);
    	//Review[] reviews = ja.getShopReviewList();

    	Map<String, Object> map;
    	
    	for(int i = 0;i < shops.size();++i){
    		//deal with data show
    		map = new HashMap<String,Object>();
    		map.put("shop_name", shops.get(i).getShopName());
    		map.put("shop_id", shops.get(i).getShopId());
    		map.put("mark_taste", shops.get(i).GetTastGrade());
    		map.put("mark_environment", shops.get(i).GetEnvironmentGrade());
    		map.put("mark_service", shops.get(i).GetServiceGrade());
    		list.add(map);

    	}
    	
    	return list;
    }
    
	public final class ViewHolder{
		
		public TextView shopName,markTaste,markEnvironment,markService;
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
    		case 0://定位用户并显示位置
    			userLocation = new GeoPoint((int)(locRes.Latitude * 1E6),(int)(locRes.Longitude * 1E6));
    			showUser(userLocation);
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
    
    public void setMessage(ErrorMessage erMsg){
    	Toast.makeText(this, erMsg.getResult(), Toast.LENGTH_SHORT).show();
    }
    
    public void setShop(List<Shop> shops){
    	showPoi(userLocation, shops, myPoiOverlay);
    	if(userLocation != null)
    	showUser(userLocation);
    	
    	list = getListData(shops);
    	SimpleAdapter saAdapter = new SimpleAdapter(this, list, R.layout.search_page_list_item,
				new String[]{"shop_name","mark_taste","mark_environment","mark_service"},
				new int[]{R.id.searchListItemShopName,R.id.searchListItemMarkTaste,R.id.searchListItemMarkEnvironment,R.id.searchListItemMarkService});
		
		listView.setAdapter(saAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
	            intent.setClass(SearchActivity.this, MainActivity.class);//remember to change the name
	            intent.putExtra("shop_id", list.get(position).get("shop_id").toString());
	            startActivity(intent);   
			}});
		

    }
    
    public void showUser(GeoPoint geoPoint){
    	if(userOverlay != null)
    		mapView.removeOverlay(userOverlay);
    	userOverlay = new UserOverlay();
    	userOverlay.setParams(geoPoint);
		mapView.addOverlay(userOverlay);
    }
    
}
