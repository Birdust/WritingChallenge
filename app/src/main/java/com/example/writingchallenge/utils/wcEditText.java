package com.example.writingchallenge.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;


import androidx.appcompat.widget.AppCompatEditText;

public class wcEditText extends AppCompatEditText {

    public wcEditText(Context context) {
        super(context);
        init();
    }

    public wcEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public wcEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "IBMPlexSans-Bold.ttf");
        setTypeface(typeface);
    }
}
