package com.tbond.eatking.view;

import greendroid.app.GDActivity;
import greendroid.widget.ActionBar;
import greendroid.widget.ActionBarItem;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;
import com.tbond.eatking.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SearchActivity extends GDActivity {

    ActionBar actionBar;
    SearchText searchText;
    
    public SearchActivity(){
    	super(ActionBar.Type.Empty);
    }
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setActionBarContentView(R.layout.activity_main);
        
        getActionBar().setFirstDrawable(this, R.drawable.gd_action_bar_back);
        getActionBar().getFirstButton().setOnClickListener(new BackBarListener());;
        searchText = new SearchText(this, null);
        getActionBar().addView(searchText);
        addActionBarItem(Type.Search, R.id.action_bar_search);
        
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
}
