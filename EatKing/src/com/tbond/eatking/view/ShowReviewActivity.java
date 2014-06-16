package com.tbond.eatking.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbond.eatking.R;
import com.tbond.eatking.net.Api;
import com.tencent.tencentmap.mapsdk.map.MapView;

import greendroid.widget.ActionBar;
import greendroid.widget.SegmentedHost;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ShowReviewActivity extends Activity {

	ImageButton backBtn,addBtn;
	ListView listView;
	String shopId,currentUserId;
	private List<Map<String, Object>> datas;
	ShopReviewAdapter adapter;
	
    public ShowReviewActivity(){
    	super();
    }
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_review);
        datas = getListData();
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View btn) {
				// TODO Auto-generated method stub
				 Intent intent=new Intent();   
		            //intent.setClass(ShowReviewActivity.this, ShopMainActivity.class);//remember to change the name
		            //startActivity(intent);   
		            ShowReviewActivity.this.finish();   
			}
		});
        addBtn = (ImageButton)findViewById(R.id.addReviewBtn);
        addBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(); 
				intent.setClass(ShowReviewActivity.this, ShopReviewNewActivity.class);//remember to change the name
	            startActivityForResult(intent, 0);
	            //need code here
			}
        	
        });
        shopId = savedInstanceState.getString("shop_id");
        currentUserId = savedInstanceState.getString("current_user_id");
        listView = (ListView)findViewById(R.id.listView);
        adapter = new ShopReviewAdapter(ShowReviewActivity.this);
        listView.setAdapter(adapter);
    }
	
    private List<Map<String, Object>> getListData(){
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JsonAnalysis ja = new JsonAnalysis();
    	Review[] reviews = ja.getShopReviewList();

    	Map<String, Object> map;
    	for(int i = 0;i < reviews.length;++i){
    		//deal with data show
    		map = new HashMap<String,Object>();
    		map.put("user_id", reviews[i].userId);
    		map.put("time", reviews[i].time);
    		map.put("comment", reviews[i].comment);
    		map.put("mark_taste", reviews[i].markTaste);
    		map.put("mark_environment", reviews[i].markEnvironment);
    		map.put("mark_service", reviews[i].markService);
    		map.put("number_good", reviews[i].goodNumber);
    		map.put("number_bad", reviews[i].baidNumber);
    		map.put("evaluated", reviews[i].evaluated);
    		map.put("review_id",reviews[i].reviewId);
    		list.add(map);

    	}
    	
    	return null;
    }
    
    public void onReviewReturned(Boolean isSuccessed,String reviewId,int goodNumber,int badNumber){
    	if(isSuccessed){
    	Boolean flag = false;
    	int i;
    	for(i = 0;i < datas.size();++i)
    		if(datas.get(i).get("review_id") == reviewId){
    			flag = true;
    			break;
    		}
    		if(flag){
    			Map<String, Object> item = datas.get(i);
    			item.put("number_good", goodNumber);
    			item.put("number_bad", badNumber);
    			datas.set(i, item);
    			adapter.notifyDataSetChanged();
    		}
    	}else{//弹出点赞失败
    		}
    }
	
	// ListView 中某项被选中后的逻辑

	
	public final class ViewHolder{
		
		public TextView userId,time,comment,markTaste,markEnvironment,markService,goodNumber,badNumber;
		public ImageButton goodBtn,badBtn;
	}
	
    
    public class ShopReviewAdapter extends BaseAdapter{

		private LayoutInflater mInflater;
		
		
		public ShopReviewAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return datas.size();
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
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder = null;
			if (convertView == null) {
				
				holder=new ViewHolder();  
				
				convertView = mInflater.inflate(R.layout.shop_review_list_item, null);
				
				holder.userId = (TextView)convertView.findViewById(R.id.userName);
				holder.time = (TextView)convertView.findViewById(R.id.time);
				holder.comment = (TextView)convertView.findViewById(R.id.comment);
				holder.markTaste = (TextView)convertView.findViewById(R.id.markTaste);
				holder.markEnvironment = (TextView)convertView.findViewById(R.id.markEnvironment);
				holder.markService = (TextView)convertView.findViewById(R.id.markService);
				holder.goodNumber = (TextView)convertView.findViewById(R.id.goodNumber);
				holder.badNumber = (TextView)convertView.findViewById(R.id.badNumber);
				holder.goodBtn = (ImageButton)convertView.findViewById(R.id.goodBtn);
				holder.badBtn = (ImageButton)convertView.findViewById(R.id.badBtn);
				convertView.setTag(holder);
				
			}else {
				
				holder = (ViewHolder)convertView.getTag();
			}
			
			holder.userId.setText((String)datas.get(position).get("user_id"));
			holder.time.setText((String)datas.get(position).get("time"));
			holder.comment.setText((String)datas.get(position).get("comment"));
			holder.markTaste.setText((String)datas.get(position).get("mark_taste"));
			holder.markEnvironment.setText((String)datas.get(position).get("mark_environment"));
			holder.markService.setText((String)datas.get(position).get("mark_service"));
			holder.goodNumber.setText((String)datas.get(position).get("number_good"));
			holder.badNumber.setText((String)datas.get(position).get("number_bad"));
			if(!(Boolean)datas.get(position).get("evaluated")){
			holder.goodBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Api api = new Api();
					api.goodReview((String)datas.get((int) listView.getSelectedItemId()).get("review_id"),currentUserId);				
				}
			});
			
			holder.badBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Api api = new Api();
					api.badReview((String)datas.get((int) listView.getSelectedItemId()).get("review_id"),currentUserId);				
				}
			});
			}
			else{
				holder.goodBtn.setActivated(false);
				holder.badBtn.setActivated(false);
			}
			
			return convertView;
		}
		
	}
    
}
