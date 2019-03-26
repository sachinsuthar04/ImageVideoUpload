package com.phonedialer.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

public class FontStyle {

    private final String FONT_AVENIR_LIGHT;
    private final String FONT_AVENIR_MEDIUM;
    private final String FONT_AVENIRNEXT_BOLD;
    private final String FONT_AVENIRNEXT_SEMIBOLD;
    private final String FONT_AVENIRNEXT_HEAVY;
    private final String FONT_AVENIRNEXT_MEDIUM;
    private final String FONT_AVENIRNEXT_REGULAR;

    private Context context;

    public FontStyle(final Context context) {
        super();
        this.FONT_AVENIR_LIGHT = "AVENIR-LIGHT.TTF";
        this.FONT_AVENIR_MEDIUM = "AVENIR-MEDIUM.TTF";
        this.FONT_AVENIRNEXT_BOLD = "AVENIRNEXT-BOLD.TTF";
        this.FONT_AVENIRNEXT_SEMIBOLD = "AVENIRNEXT-DEMIBOLD.TTF";
        this.FONT_AVENIRNEXT_HEAVY = "AVENIRNEXT-HEAVY.TTF";
        this.FONT_AVENIRNEXT_MEDIUM = "AVENIRNEXT-MEDIUM.TTF";
        this.FONT_AVENIRNEXT_REGULAR = "AVENIRNEXT-REGULAR.TTF";
        this.context = context;
    }


    public void setFONT_AVENIR_LIGHT(final View view) {
        try {
            final Typeface fromAsset = Typeface.createFromAsset(this.context.getAssets(), FONT_AVENIR_LIGHT);
            final TextView textView = (TextView) view;
            textView.setTypeface(fromAsset);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setFONT_AVENIR_MEDIUM(final View view) {
        try {
            final Typeface fromAsset = Typeface.createFromAsset(this.context.getAssets(), FONT_AVENIR_MEDIUM);
            final TextView textView = (TextView) view;
            textView.setTypeface(fromAsset);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setFONT_AVENIRNEXT_BOLD(final View view) {
        try {
            final Typeface fromAsset = Typeface.createFromAsset(this.context.getAssets(), FONT_AVENIRNEXT_BOLD);
            final TextView textView = (TextView) view;
            textView.setTypeface(fromAsset);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void setFONT_AVENIRNEXT_SEMIBOLD(final View view) {
        try {
            final Typeface fromAsset = Typeface.createFromAsset(this.context.getAssets(), FONT_AVENIRNEXT_SEMIBOLD);
            final TextView textView = (TextView) view;
            textView.setTypeface(fromAsset);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setFONT_AVENIRNEXT_HEAVY(final View view) {
        try {
            final Typeface fromAsset = Typeface.createFromAsset(this.context.getAssets(), FONT_AVENIRNEXT_HEAVY);
            final TextView textView = (TextView) view;
            textView.setTypeface(fromAsset);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setFONT_AVENIRNEXT_REGULAR(final View view) {
        try {
            final Typeface fromAsset = Typeface.createFromAsset(this.context.getAssets(), FONT_AVENIRNEXT_REGULAR);
            final TextView textView = (TextView) view;
            textView.setTypeface(fromAsset);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void setFONT_AVENIRNEXT_MEDIUM(final View view) {
        try {
            final Typeface fromAsset = Typeface.createFromAsset(this.context.getAssets(), FONT_AVENIRNEXT_MEDIUM);
            final TextView textView = (TextView) view;
            textView.setTypeface(fromAsset);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}