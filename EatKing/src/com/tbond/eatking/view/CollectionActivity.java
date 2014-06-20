package com.tbond.eatking.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbond.eatking.R;
import com.tbond.eatking.model.ErrorMessage;
import com.tbond.eatking.model.Shop;
import com.tbond.eatking.net.JsonAnalysis;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class CollectionActivity extends Activity {
	
	ImageButton backBtn;
	ListView listView;
	List<Map<String, Object>> list;
	int selectedPos = -1;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_page);
        
        backBtn = (ImageButton)findViewById(R.id.collectionBackBtn);
        backBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CollectionActivity.this.finish();
			}});
        
        JsonAnalysis.getInstance().getCollection(this);
	}
	
	public void setShop(List<Shop> shops){
		listView = (ListView)findViewById(R.id.collectionListView);
		list = getDatas(shops);
		SimpleAdapter saAdapter = new SimpleAdapter(this, list, R.layout.collection_page_list_item,
				new String[]{"shop_name"}, new int[]{R.id.collectionListItemShopName});
		
		listView.setAdapter(saAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
	            intent.setClass(CollectionActivity.this, MainActivity.class);//remember to change the name
	            intent.putExtra("shop_id", list.get(position).get("shop_id").toString());
	            startActivity(intent);   
			}});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			int pos;
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				pos = position;
				AlertDialog.Builder builder = new Builder(CollectionActivity.this);

				builder.setMessage("删除对该店铺的收藏？");
				builder.setTitle("删除收藏");
				
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					//这里File构造方法参数就是从你list读取的文件路径
						Map<String, Object> mapobj = list.get(pos);
					JsonAnalysis.getInstance().cancelCollectionShop(CollectionActivity.this, list.get(pos).get("shop_id").toString());
					}});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				
				builder.create().show();
				
				return false;
			}
		});
		
	}
	
	private List<Map<String, Object>> getDatas(List<Shop> shops){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		
    	for(int i = 0;i < shops.size();++i){
    		//deal with data show
    		map = new HashMap<String,Object>();
    		map.put("shop_id", shops.get(i).getShopId());
    		map.put("shop_name", shops.get(i).getShopName());
    		list.add(map);

    	}
		
		return list;
	}
	
	public void setMessage(String str){
		if(str == "successed"){
			Intent intent = new Intent();
            intent.setClass(this, CollectionActivity.class);
            this.finish();
            startActivity(intent);
		}
	}
	
	public void setMessage(ErrorMessage errmsg){
		Toast.makeText(getApplicationContext(), errmsg.getResult(),
			     Toast.LENGTH_SHORT).show();
	}
}
