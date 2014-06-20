package com.tbond.eatking.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbond.eatking.R;
import com.tbond.eatking.model.ErrorMessage;
import com.tbond.eatking.model.UserComment;
import com.tbond.eatking.net.Api;
import com.tbond.eatking.net.JsonAnalysis;
import com.tencent.tencentmap.mapsdk.map.MapView;

import greendroid.widget.ActionBar;
import greendroid.widget.SegmentedHost;
import android.R.integer;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ShowReviewActivity extends Activity {

	ImageButton backBtn,addBtn;
	ListView listView;
	String shopId,currentUserId;
	private List<Map<String, Object>> datas;
	ShopReviewAdapter adapter;
	int buttonPos = -1;
	
    public ShowReviewActivity(){
    	super();
    }
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_review);
        try{
        shopId = savedInstanceState.getString("shop_id");
        currentUserId = savedInstanceState.getString("current_user_id");
        }catch(Exception e){
        	Log.i("no_bundle_data", "shopId or currentUserId missing");
        }
        //记着改啊！！！！！
        shopId = "1";
        JsonAnalysis.getInstance().searchShopEvaluationById(ShowReviewActivity.this, shopId);
        
    }
    
    public void setComment(List<UserComment> reviews){
    	datas = getListData(reviews);
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View btn) {
				// TODO Auto-generated method stub
				 //Intent intent=new Intent();   
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
				//intent.setClass(ShowReviewActivity.this, ShopReviewNewActivity.class);//remember to change the name
	            startActivityForResult(intent, 0);
	            //need code here
			}
        	
        });
        
        listView = (ListView)findViewById(R.id.listView);
        adapter = new ShopReviewAdapter(ShowReviewActivity.this);
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				buttonPos = position;
			}
		});
    }
	
    private List<Map<String, Object>> getListData(List<UserComment> reviews){
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	//JsonAnalysis.getInstance().searchShopEvaluationById(ShowReviewActivity.this, shopId);
    	//Review[] reviews = ja.getShopReviewList();

    	Map<String, Object> map;
    	
    	for(int i = 0;i < reviews.size();++i){
    		//deal with data show
    		map = new HashMap<String,Object>();
    		map.put("user_id", reviews.get(i).GetUserId());
    		map.put("time", reviews.get(i).GetTime());
    		map.put("comment", reviews.get(i).GetComment());
    		map.put("mark_taste", reviews.get(i).GetTastGrade());
    		map.put("mark_environment", reviews.get(i).GetEnvironmentGrade());
    		map.put("mark_service", reviews.get(i).GetServiceGrade());
    		map.put("number_good", reviews.get(i).getCount());
    		map.put("evaluated", reviews.get(i).getIsEvaluate());
    		map.put("review_id",reviews.get(i).GetShopEvaluationId());
    		map.put("per_price", reviews.get(i).GetPricePerPerson());
    		list.add(map);

    	}
    	
    	return list;
    }
    
    public void setMessage(ErrorMessage errMsg){
    	Toast.makeText(getApplicationContext(), errMsg.getResult(),
			     Toast.LENGTH_SHORT).show();
    }
    
    public void setMessage(String str){
    	if(str == "successed"){
    		Intent intent = new Intent();
    		intent.setClass(this, ShowReviewActivity.class);
    		this.finish();
    		startActivity(intent);
    	}
//    	if(isSuccessed){
//    	Boolean flag = false;
//    	int i;
//    	for(i = 0;i < datas.size();++i)
//    		if(datas.get(i).get("review_id") == reviewId){
//    			flag = true;
//    			break;
//    		}
//    		if(flag){
//    			Map<String, Object> item = datas.get(i);
//    			item.put("number_good", goodNumber);
//    			datas.set(i, item);
//    			adapter.notifyDataSetChanged();
//    		}
//    	}
    }
	
	// ListView 中某项被选中后的逻辑

	
	public final class ViewHolder{
		
		public TextView userId,time,comment,markTaste,markEnvironment,markService,goodNumber,perPrice;
		public ImageButton goodBtn;
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			
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
				holder.goodBtn = (ImageButton)convertView.findViewById(R.id.goodBtn);
				holder.perPrice = (TextView)convertView.findViewById(R.id.perPrice);
				convertView.setTag(holder);
				
			}else {
				
				holder = (ViewHolder)convertView.getTag();
			}
			
			holder.userId.setText((String)datas.get(position).get("user_id"));
			holder.time.setText((String)datas.get(position).get("time"));
			holder.comment.setText((String)datas.get(position).get("comment"));
			holder.markTaste.setText("口味："+(String)datas.get(position).get("mark_taste")+"  ");
			holder.markEnvironment.setText("环境："+(String)datas.get(position).get("mark_environment")+"  ");
			holder.markService.setText("服务："+(String)datas.get(position).get("mark_service")+"  ");
			holder.goodNumber.setText((String)datas.get(position).get("number_good"));
			holder.perPrice.setText("人均："+(String)datas.get(position).get("per_price")+"  ");
			//if((String)datas.get(position).get("evaluated") == "0"){
			if(true){
			holder.goodBtn.setActivated(true);
			holder.goodBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					JsonAnalysis.getInstance().addShopEvaluationEvaluation(ShowReviewActivity.this, (String)datas.get(position).get("review_id"));
				}
			});
			}
			else{
				holder.goodBtn.setEnabled(false);
				holder.goodBtn.setAlpha(0.5f);
			}
			
			return convertView;
		}
		
	}
    
}
