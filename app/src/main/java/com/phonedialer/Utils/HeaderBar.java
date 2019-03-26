package com.phonedialer.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phonedialer.R;

public class HeaderBar extends RelativeLayout {

    public TextView tvTitle;
    public ImageView ivLeft, ivRight;
    private Context context;
    private FontStyle fontStyle;

    public HeaderBar(Context context) {
        super(context);
        this.context = context;
        init();
        setFontStyle();
    }

    public HeaderBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            View view = LayoutInflater.from(context).inflate(R.layout.headerbar, this, true);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            ivLeft = (ImageView) findViewById(R.id.ivLeft);
            ivRight = (ImageView) findViewById(R.id.ivRight);
            tvTitle.setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setFontStyle() {

        try {
            fontStyle = new FontStyle(context);
            fontStyle.setFONT_AVENIRNEXT_BOLD(tvTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
