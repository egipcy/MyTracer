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
import java.util.Queue;

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
        this.speed_samples = new LinkedList<Float>();

        this.p_white = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.p_white.setColor(0xFFFFFFFF);
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        for(int i=1; i<=6; i++)
            canvas.drawRect(new Rect(0,i*150,900,i*150+2), this.p_white);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        return true;
    }

    public void add_speed(float speed_sample)
    {
        this.speed_samples.offer(speed_sample);

        if(this.speed_samples.size() > 100)
            this.speed_samples.poll();
    }

    private Queue<Float> speed_samples;

    private Paint p_white;
}