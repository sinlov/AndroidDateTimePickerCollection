
package com.example.datetime_picker_demo.android;

import com.example.datetime_picker_demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import android.widget.TimePicker.OnTimeChangedListener;

public class AndroidTimePickerActiviy extends Activity {
    private TextView mTextView;
    private ToggleButton mToggleButton;
    private TimePicker mTimePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_activiy);
        this.mTextView = (TextView)findViewById(R.id.textView_timepickeractiviy_show);
        
        this.mTimePicker = (TimePicker)findViewById(R.id.timePicker_timepickeractiviy);
        mTimePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
            
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mTextView.setText("hourOfDay: " + hourOfDay + "\n" + "minute: " + minute);
            }
        });
        
        this.mToggleButton = (ToggleButton)findViewById(R.id.toggleButton_timepickeractiviy_set24h);
        mToggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mTimePicker.setIs24HourView(true);
                }else {
                    mTimePicker.setIs24HourView(false);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.data_picker_activiy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
