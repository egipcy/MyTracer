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

        this.p_green = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.p_green.setColor(0xFF00FF00);

        this.p_red = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.p_red.setColor(0xFFFF0000);

        this.overall_time = 0;
        this.current_speed = 0.f;
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        for(int i=1; i<=6; i++)
            canvas.drawRect(new Rect(0,i*150,900,i*150+2), this.p_white);

        if(this.speed_samples.size() > 1)
        {
            Iterator it = this.speed_samples.iterator();

            float prec = (float) it.next();
            float suiv;
            for (int i = 1; it.hasNext(); i++)
            {
                suiv = (float) it.next();
                canvas.drawLine((i - 1) * 9, 900 - prec * 15, i * 9, 900 - suiv * 15, this.p_green);
                prec = suiv;
            }

            if(this.speed_samples.size() == 100)
            {
                int average_speed = Math.round(this.getAverage_speed());
                canvas.drawLine(0, 900 - average_speed * 15, 900, 900 - average_speed * 15, this.p_red);
            }
        }

        invalidate();
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

        this.overall_time++;
        this.current_speed = speed_sample;
    }

    public float getAverage_speed()
    {
        Iterator it = this.speed_samples.iterator();
        float x = 0.f;

        while(it.hasNext())
            x += (float)it.next();

        return x / this.speed_samples.size();
    }

    public float getCurrent_speed()
    {
        return this.current_speed;
    }

    public int getOverall_time()
    {
        return this.overall_time;
    }

    private Queue<Float> speed_samples;

    private Paint p_white;
    private Paint p_green;
    private Paint p_red;

    private int overall_time;
    private float current_speed;
}