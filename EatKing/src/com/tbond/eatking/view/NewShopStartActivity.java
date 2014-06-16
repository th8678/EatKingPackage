package com.tbond.eatking.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbond.eatking.R;
import com.tbond.eatking.net.Api;
import com.tbond.eatking.view.ShowReviewActivity.ShopReviewAdapter;
import com.tbond.eatking.view.ShowReviewActivity.ViewHolder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class NewShopStartActivity extends Activity {
	
	ImageButton backBtn,ensureBtn;
	EditText shopName;
	ListView shopList;
	private List<Map<String, Object>> datas;
	ShopReviewAdapter adapter;
	
	public NewShopStartActivity(){
    	super();
    }
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_shop_start);
        
        
        datas = getListData();
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        ensureBtn = (ImageButton)findViewById(R.id.ensureBtn);
        shopName = (EditText)findViewById(R.id.shopName);
        shopList = (ListView)findViewById(R.id.listShop);
        
        backBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent=new Intent();   
	            //intent.setClass(NewShopStartActivity.this, MainActivity.class);//remember to change the name
	            //startActivity(intent);   
	            NewShopStartActivity.this.finish();   
			}});
        
        ensureBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(shopName.getText() == null || shopName.getText().toString() == ""){
					//弹出通知，请输入名称
				}
				else{
					Intent intent=new Intent();   
		            intent.setClass(NewShopStartActivity.this, NewShopEnsureLocationActivity.class);//remember to change the name
		            intent.putExtra("shop_name", shopName.getText());
		            startActivity(intent);   
		            NewShopStartActivity.this.finish();  
				}
			}});
        adapter = new ShopReviewAdapter(NewShopStartActivity.this);
        shopList.setAdapter(adapter);
        
        }
	
    private List<Map<String, Object>> getListData(){
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JsonAnalysis ja = new JsonAnalysis();
    	Shop[] shops = ja.getSimilarShopList();

    	Map<String, Object> map;
    	for(int i = 0;i < shops.length;++i){
    		//deal with data show
    		map = new HashMap<String,Object>();
    		map.put("shop_name", shops[i].name);
    		map.put("distance", shops[i].distance);
    		list.add(map);

    	}
    	
    	return null;
    }
	
    public final class ViewHolder{
		
		public TextView shopName,distance;
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
				
				convertView = mInflater.inflate(R.layout.new_shop_ensure_list_item, null);
				
				holder.shopName = (TextView)convertView.findViewById(R.id.shopName);
				holder.distance = (TextView)convertView.findViewById(R.id.distance);
				convertView.setTag(holder);
				
			}else {
				
				holder = (ViewHolder)convertView.getTag();
			}
			
			holder.shopName.setText((String)datas.get(position).get("shop_name"));
			holder.distance.setText((String)datas.get(position).get("distance"));
			
			return convertView;
		}
		
	}

}
