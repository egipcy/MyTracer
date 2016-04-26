package com.example.egipcy.mytracer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Iterator;
import java.util.LinkedList;

public class TraceView extends View
{
    public TraceView(Context c)
    {
        super(c);
        init();
    }

    public TraceView(Context c, AttributeSet as)
    {
        super(c, as);
        init();
    }

    public TraceView(Context c, AttributeSet as, int default_style)
    {
        super(c, as, default_style);
        init();
    }

    public void init()
    {
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);


    }

    public boolean onTouchEvent(MotionEvent event)
    {
        return true;
    }

    public void add_speed(float speed_sample)
    {
    }
}