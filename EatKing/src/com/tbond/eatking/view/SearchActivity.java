package com.tbond.eatking.view;

import java.util.ArrayList;
import java.util.List;

import greendroid.app.GDActivity;
import greendroid.app.GDListActivity;
import greendroid.widget.ActionBar;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ItemAdapter;
import greendroid.widget.NormalActionBarItem;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;
import greendroid.widget.item.DescriptionItem;
import greendroid.widget.item.Item;
import greendroid.widget.item.SeparatorItem;
import greendroid.widget.item.ThumbnailItem;

import com.tbond.eatking.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends GDListActivity {

    ActionBar actionBar;
    SearchText searchText;
    List<Item> items = new ArrayList<Item>();
    ListView mListView;
    
    public SearchActivity(){
    	super(ActionBar.Type.Empty);
    }
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /**
         *  重置默认的list layout，修改了GDListActivity的setActionBarContentView函数，
         *  添加了setListView
         */
        setActionBarContentView(R.layout.search_activity);
        setListView((ListView)findViewById(android.R.id.list));
        searchText = new SearchText(this, null);
        addActionBarItem(getActionBar().
        		newActionBarItem(NormalActionBarItem.class)
                .setDrawable(R.drawable.gd_action_bar_back)
                .setContentDescription(R.string.gd_back), R.id.action_bar_back);
        getActionBar().addView(searchText);
        addActionBarItem(Type.Search, R.id.action_bar_search);
        addShop();
    }
    
    public void addShop(){
    	items.add(new SeparatorItem(""));
        items.add(new SearchListItem("Multi-axis", "Looks like a tiny plane", R.drawable.class3));
        items.add(new DescriptionItem("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus consequat leo, et tincidunt justo tristique in."));
        
        final ItemAdapter adapter = new ItemAdapter(this, items);
        setListAdapter(adapter);
    }
    
    /**
     * 处理actionbar上的除了homelist的所有事件
     */
    @Override
    public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
    	
        int itemId = item.getItemId();
        if (itemId == R.id.action_bar_back) {
        	//Toast.makeText(SearchActivity.this, "action_bar_back clicked", Toast.LENGTH_SHORT).show();
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
}
