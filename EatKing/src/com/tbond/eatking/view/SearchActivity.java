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

        searchText = new SearchText(this, null);
        addActionBarItem(getGDActionBar().
        		newActionBarItem(NormalActionBarItem.class)
                .setDrawable(R.drawable.gd_action_bar_back)
                .setContentDescription(R.string.gd_back), R.id.action_bar_back);
        getGDActionBar().addView(searchText);

        addActionBarItem(Type.Search, R.id.action_bar_search);
        
    }

    /**
     * ���listbar�е����¼�
     */
    private OnQuickActionClickListener mActionListener = new OnQuickActionClickListener() {
        public void onQuickActionClicked(QuickActionWidget widget, int position) {
            Toast.makeText(SearchActivity.this, "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
        }
    };
    
    /**
     * ����actionbar�ϵĳ���homelist�������¼�
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
     * ����listbar�¼�
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
