package com.tbond.eatking.view;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.tbond.eatking.R;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import greendroid.widget.item.SubtitleItem;
import greendroid.widget.itemview.ItemView;

public class SearchListItem extends SubtitleItem {

	/**
     * The resource ID for the Drawable.
     */
    public int drawableId;

    /**
     * An optional URL that may be used to retrieve an image
     */
    public String drawableURL;

    /**
     * @hide
     */
    public SearchListItem() {
    }

    /**
     * Create a new ThumbnailItem.
     * 
     * @param text The text to draw
     * @param subtitle The subtitle to use
     * @param drawableId The resource identifier to the Drawable
     */
    public SearchListItem(String text, String subtitle, int drawableId) {
        this(text, subtitle, drawableId, null);
    }

    /**
     * Create a new ThumbnailItem which will asynchronously load the image at
     * the given URL.
     * 
     * @param text The text to draw
     * @param subtitle The subtitle to use
     * @param drawableId The default image used when loading the image at the
     *            given <em>drawableURL</em>
     * @param drawableURL The URL pointing to the image to load.
     */
    public SearchListItem(String text, String subtitle, int drawableId, String drawableURL) {
        super(text, subtitle);
        this.drawableId = drawableId;
        this.drawableURL = drawableURL;
    }

    @Override
    public ItemView newView(Context context, ViewGroup parent) {
        return createCellFromXml(context, R.layout.search_list_item, parent);
    }

    @Override
    public void inflate(Resources r, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        super.inflate(r, parser, attrs);

        TypedArray a = r.obtainAttributes(attrs, R.styleable.ThumbnailItem);
        drawableId = a.getResourceId(R.styleable.ThumbnailItem_thumbnail, drawableId);
        drawableURL = a.getString(R.styleable.ThumbnailItem_thumbnailURL);
        a.recycle();
    }
	
}
