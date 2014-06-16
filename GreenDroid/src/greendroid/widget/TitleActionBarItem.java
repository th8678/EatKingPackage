package greendroid.widget;

import com.cyrilmottier.android.greendroid.R;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class TitleActionBarItem extends ActionBarItem{

	private String title;
	
	public TitleActionBarItem() {
		title = "";
    }
	
	public TitleActionBarItem(String t) {
		title = t;
    }
	
	public void setTitle(String t){
		TextView titleView = (TextView) mItemView.findViewById(R.id.gd_action_bar_item_title);
        titleView.setText(t);
	}
	
 	@Override
    protected View createItemView() {
        return LayoutInflater.from(mContext).inflate(R.layout.gd_action_bar_item_title, mActionBar, false);
    }

    @SuppressLint("NewApi")
	@Override
    protected void prepareItemView() {
        super.prepareItemView();
        TextView titleView = (TextView) mItemView.findViewById(R.id.gd_action_bar_item_title);
        titleView.setText(title);
        titleView.setContentDescription(mContentDescription);
    }

    @SuppressLint("NewApi")
	@Override
    protected void onContentDescriptionChanged() {
        super.onContentDescriptionChanged();
        mItemView.findViewById(R.id.gd_action_bar_title).setContentDescription(mContentDescription);
    }
    
    
    @Override
    protected void onDrawableChanged() {
        super.onDrawableChanged();
        TextView titleView = (TextView) mItemView.findViewById(R.id.gd_action_bar_item_title);
        titleView.setText(title);
    }
}
