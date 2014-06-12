package com.tbond.eatking.view;

import org.json.JSONObject;

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
import greendroid.widget.SegmentedBar.OnSegmentChangeListener;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.tbond.eatking.R;
import com.tbond.eatking.net.Api;
import com.tencent.tencentmap.mapsdk.map.MapView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends GDActivity {

    private PeopleSegmentedAdapter mAdapter;
    SegmentedHost segmentedHost;
    private QuickActionBar mBar;
    private Intent intent;
    
    public MainActivity(){
    	super(ActionBar.Type.Normal);
    }
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setActionBarContentView(R.layout.activity_main);

        segmentedHost = (SegmentedHost) findViewById(R.id.segmented_host);
        segmentedHost.getSegmentedBar().setOnSegmentChangeListener(new SegmentSwitcher());
        
        mAdapter = new PeopleSegmentedAdapter();
//        mHandler.postDelayed(new Runnable() {
//            public void run() {
//                mAdapter.mReverse = true;
//                mAdapter.notifyDataSetChanged();
//            }
//        }, 4000);
        getActionBar().setFirstDrawable(this, R.drawable.gd_action_bar_list);
        getActionBar().getFirstButton().setOnClickListener(new ListBarListener());;
        addActionBarItem(Type.Search, R.id.action_bar_search);
//        TitleActionBarItem titleActionBarItem = new TitleActionBarItem(getString(R.string.gd_mail));
//        addActionBarItem(titleActionBarItem, R.id.action_bar_title);
//        addActionBarItem(getActionBar()
//                .newActionBarItem(NormalActionBarItem.class)
//                .setDrawable(R.drawable.ic_title_export)
//                .setContentDescription(R.string.gd_export), R.id.action_bar_export);
//        addActionBarItem(Type.Locate, R.id.action_bar_locate);
        segmentedHost.setAdapter(mAdapter);
        prepareQuickActionBar();
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
			Api.getInstance().login("tbond", "tbond", new JsonHttpResponseHandler(){
				@Override
				public void onFailure(Throwable e, JSONObject errorResponse) {
//					dialog.dismiss();
//					LogUtil.i(TAG, "login request onFailure:" + errorResponse.toString());
//					super.onFailure(e, errorResponse);
					Log.i("eatking", "onFailure");
					Log.i("response", e.toString());
				}

				@Override
				public void onSuccess(JSONObject response) {
					Log.i("eatking", "onSuccess");
					//LogUtil.i(TAG, "login request onSuccess:" + response.toString());
					//dialog.dismiss();
					Log.i("response", response.toString());
				}
			});
		
			break;
		case 2:
			
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
        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public String getSegmentTitle(int position) {
            switch (position) {
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

		@Override
		public View getView(int position, ViewGroup parent) {
			// TODO Auto-generated method stub
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
}
