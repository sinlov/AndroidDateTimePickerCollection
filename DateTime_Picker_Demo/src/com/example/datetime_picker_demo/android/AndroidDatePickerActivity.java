
package com.example.datetime_picker_demo.android;

import com.example.datetime_picker_demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;

public class AndroidDatePickerActivity extends Activity {
    private DatePicker mDatePicker;
    private TextView mTextView;
    private ToggleButton mToggleButton;
    private Calendar mCalender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_date_picker);
        this.mTextView = (TextView)findViewById(R.id.textView_andoridDatePickerActiviy_show);
        this.mToggleButton = (ToggleButton)findViewById(R.id.toggleButton_andoridDatePickerActiviy);
        
        mDatePicker = (DatePicker)findViewById(R.id.datePicker_andoridDatePickerActiviy);
        this.mCalender = Calendar.getInstance();
        int year = mCalender.get(Calendar.YEAR);
        int monthOfYear = mCalender.get(Calendar.MONTH);
        int dayOfMonth = mCalender.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener() {
            
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mTextView.setText("year: " + year + "\nmonthOfYear: " + monthOfYear + "\ndayOfMonth: " + dayOfMonth);
            }
        });
        
        mToggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mDatePicker.setCalendarViewShown(true);
                }else {
                    mDatePicker.setCalendarViewShown(false);
                    
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.android_date_picker, menu);
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
