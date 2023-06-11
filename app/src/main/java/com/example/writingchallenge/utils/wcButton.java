package com.example.writingchallenge.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class wcButton extends AppCompatEditText {
    public wcButton(Context context) {
        super(context);
        init();
    }

    public wcButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public wcButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "IBMPlexSans-Bold.ttf");
        setTypeface(typeface);
    }
}
