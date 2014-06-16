package com.tbond.eatking.model;
import java.util.List;

import android.view.View;

import com.tencent.tencentmap.mapsdk.map.GeoPoint;
import com.tencent.tencentmap.mapsdk.map.MapActivity;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.MapView.LayoutParams;
import com.tencent.tencentmap.mapsdk.map.PoiOverlay;
import com.tencent.tencentmap.mapsdk.map.PoiOverlay.CustomInfoWindowAdapter;
import com.tencent.tencentmap.mapsdk.search.PoiItem;
import com.tencent.tencentmap.mapsdk.search.PoiResults;
import com.tencent.tencentmap.mapsdk.search.PoiSearch;

public class MapService {
	
	PoiOverlay myPoiOverlay=null;
	
	private String longtitude,latitude;
	public String getLongtitude(){return longtitude;}
	public String getLatitude(){return latitude;}
	
	class CustomInfoWindow implements CustomInfoWindowAdapter{
//定义信息显示框样式，还没搞懂怎么写
		@Override
		public View getInfoWindowView(PoiItem arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public LayoutParams getInfowindowLayoutOutParameter(GeoPoint arg0,
				int arg1, int arg2) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	}
	
	public int showShops(PoiResults shops, MapView mapView){
		try{
		List<PoiItem> listPois = shops.getCurrentPagePoiItems();
		if(listPois==null)
		{
			return 1;
		}
		
		if(myPoiOverlay==null)
		{
			myPoiOverlay = new PoiOverlay(null);
			mapView.addOverlay(myPoiOverlay);
		}
		myPoiOverlay.setPoiItems(listPois);
		myPoiOverlay.setCustomInfoWindowAdapter(new CustomInfoWindow());
		myPoiOverlay.showInfoWindow(0);
		return 0;
		}
		catch(Exception e){
			return 2;
			
		}
	}
	
}
