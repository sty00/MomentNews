package com.alextam.momentnews.widget;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by AlexTam on 2015/8/4.
 */
public class ClickTextView extends TextView implements View.OnTouchListener{



    public ClickTextView(Context context) {
        this(context, null);
    }

    public ClickTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClickTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initOnTouch();
    }

    private void initOnTouch()
    {
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        Layout layout = ((TextView) v).getLayout();
        int x = (int)event.getX();
        int y = (int)event.getY();
        if (layout!=null){
            int line = layout.getLineForVertical(y);
            int characterOffset = layout.getOffsetForHorizontal(line, x);
            Log.i("index", "" + characterOffset);
        }
        return true;
    }

}
