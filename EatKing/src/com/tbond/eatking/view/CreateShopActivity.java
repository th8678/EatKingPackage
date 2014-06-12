package com.tbond.eatking.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.tbond.eatking.R;

import greendroid.app.GDActivity;
import greendroid.widget.ActionBar;
import greendroid.widget.ActionBarItem;

public class CreateShopActivity extends GDActivity {
	ActionBar actionBar;
    ImageButton nextButton;
    
    public CreateShopActivity(){
    	super(ActionBar.Type.Normal);
    }
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setActionBarContentView(R.layout.activity_main);
        
        getActionBar().setFirstDrawable(this, R.drawable.gd_action_bar_back);
        getActionBar().getFirstButton().setOnClickListener(new BackBarListener());
        
        nextButton = new ImageButton(this);
        nextButton.setImageDrawable(getResources().getDrawable(R.drawable.gd_action_bar_add));
        nextButton.setLeft(10);
        getActionBar().addView(nextButton);
    }

    /**
     * 处理actionbar上的除了homelist的所有事件
     */
    @Override
    public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
    	
        int itemId = item.getItemId();
        if (itemId == R.id.action_bar_search) {
        	//Toast.makeText(SearchActivity.this, "action_bar_back clicked", Toast.LENGTH_SHORT).show();
        	finish();
        } else {
			return super.onHandleActionBarItemClick(item, position);
		}

        return true;
    }
    
    private class BackBarListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			finish();
		}
    };
}
