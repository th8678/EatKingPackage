package com.tbond.eatking.view;

import com.tbond.eatking.R;

import android.R.anim;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import greendroid.app.GDTabActivity;

public class CorrectActivity extends GDTabActivity {
	private static final String TAB1 = "tab_one";
    private static final String TAB2 = "tab_two";
    private static final String TAB3 = "tab_three";

    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setTitle("Screen 2");
        getGDActionBar().setFirstDrawable(this, R.drawable.gd_action_bar_back);
        getGDActionBar().getFirstButton().setOnClickListener(new BackBarListener());

        addTab(TAB1, getResources().getString(R.string.shop_info_edit), Color.BLACK, "Content of tab #1");
        addTab(TAB2, getResources().getString(R.string.shop_status_edit), Color.rgb(20, 0, 20), "Content of tab #2");
        addTab(TAB3, getResources().getString(R.string.shop_duplicate), Color.rgb(40, 0, 40), "Content of tab #3");
       
        for(int i = 0;i < getTabHost().getTabWidget().getChildCount();i++)
		{
			// 设置为选择的tag颜色
			getTabHost().getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#8A4117"));
		}
        // 设置当前tag颜色
		getTabHost().getTabWidget().getChildAt(getTabHost().getCurrentTab()).setBackgroundColor(Color.parseColor("#C35817"));
        
		// 设置tag点击事件
        getTabHost().setOnTabChangedListener(new OnTabChangeListener(){
        	@Override
        	public void onTabChanged(String tabId) {
        		for(int i = 0;i < getTabHost().getTabWidget().getChildCount();i++)
        		{
        			
        			getTabHost().getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#8A4117"));
        		}

        		getTabHost().getTabWidget().getChildAt(getTabHost().getCurrentTab()).setBackgroundColor(Color.parseColor("#C35817"));
    		}
    	});
    }
    
    private void addTab(String tag, CharSequence label, int color, String text) {
        final Intent intent = new Intent(this, FakeActivity.class);
        intent.putExtra(FakeActivity.EXTRA_COLOR, color);
        intent.putExtra(FakeActivity.EXTRA_TEXT, text);
        addTab(tag, label, intent);
    }

    public static class FakeActivity extends Activity {

        public static final String EXTRA_COLOR = "com.cyrilmottier.android.gdcatalog.TabbedActionBarActivity$FakeActivity.extraColor";
        public static final String EXTRA_TEXT = "com.cyrilmottier.android.gdcatalog.TabbedActionBarActivity$FakeActivity.extraText";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            final Intent intent = getIntent();

            if (intent != null) {
                setContentView(R.layout.shop_info_changed);

                EditText textView = (EditText) findViewById(R.id.shop_name);
                textView.setText(intent.getStringExtra(EXTRA_TEXT));
                textView.setBackgroundColor(Color.WHITE);
                textView.setMaxLines(1);
            }
        }
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
