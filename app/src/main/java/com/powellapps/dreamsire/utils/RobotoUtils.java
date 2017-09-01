package com.powellapps.dreamsire.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by raphaelramos on 01/09/17.
 */

public class RobotoUtils extends android.support.v7.widget.AppCompatTextView {

    Typeface normalTypeface = Typeface.createFromAsset(getContext().getAssets(), ConstantsUtils.FONT_ROBOTO);

    public RobotoUtils(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RobotoUtils(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoUtils(Context context) {
        super(context);
        init();
    }

    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(normalTypeface/*, -1*/);
    }

    public void init() {

        setTypeface(normalTypeface,1);
    }
}
