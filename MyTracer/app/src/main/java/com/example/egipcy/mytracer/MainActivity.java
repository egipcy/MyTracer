package com.example.egipcy.mytracer;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener, LocationListener
{
    Button btn_tracking;

    LocationManager lm;
    TraceView traceView;
    TextView tv_current_speed;
    TextView tv_average_speed;
    TextView tv_overall_time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btn_tracking = (Button) findViewById(R.id.btn_tracking);
        this.btn_tracking.setOnClickListener(this);

        this.traceView = (TraceView)findViewById(R.id.traceView);

        this.tv_current_speed = (TextView) findViewById(R.id.tv_current_speed);
        this.tv_average_speed = (TextView) findViewById(R.id.tv_average_speed);
        this.tv_overall_time = (TextView) findViewById(R.id.tv_overall_time);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_tracking:
                if(this.btn_tracking.getText() == getString(R.string.active))
                {
                    this.btn_tracking.setText(getString(R.string.inactive));

                    this.lm.removeUpdates(this);
                }
                else
                {
                    this.btn_tracking.setText(getString(R.string.active));

                    this.lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
                    if (this.lm.isProviderEnabled(LocationManager.GPS_PROVIDER))
                        this.lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,
                                this);
                    else
                        this.lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0,
                                this);
                }
                break;
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location)
    {
        this.traceView.add_speed(location.getSpeed()*3.6f);

        this.tv_current_speed.setText("Current Speed: " + this.traceView.getCurrent_speed() + " km/h");
        this.tv_average_speed.setText("Average Speed: " + this.traceView.getAverage_speed() + " km/h");
        this.tv_overall_time.setText("Overall Time: " + this.traceView.getOverall_time() + " s");
    }

    @Override
    public void onProviderDisabled(String provider)
    {
    }

    @Override
    public void onProviderEnabled(String provider)
    {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
