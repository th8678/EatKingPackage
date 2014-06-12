package com.tbond.eatking.view;

import com.cyrilmottier.android.greendroid.R;

import greendroid.widget.AsyncImageView;
import greendroid.widget.item.Item;
import greendroid.widget.item.ThumbnailItem;
import greendroid.widget.itemview.ItemView;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchListItemView extends RelativeLayout implements ItemView{

	private TextView mTextView;
    private TextView mSubtitleView;
    private AsyncImageView mThumbnailView;

    public SearchListItemView(Context context) {
        this(context, null);
    }

    public SearchListItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchListItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void prepareItemView() {
        mTextView = (TextView) findViewById(R.id.gd_text);
        mSubtitleView = (TextView) findViewById(R.id.gd_subtitle);
        mThumbnailView = (AsyncImageView) findViewById(R.id.gd_thumbnail);
    }

    public void setObject(Item object) {
        final SearchListItem item = (SearchListItem) object;
        mTextView.setText(item.text);
        mSubtitleView.setText(item.subtitle);
        mThumbnailView.setDefaultImageResource(item.drawableId);
        mThumbnailView.setUrl(item.drawableURL);
    }
}
