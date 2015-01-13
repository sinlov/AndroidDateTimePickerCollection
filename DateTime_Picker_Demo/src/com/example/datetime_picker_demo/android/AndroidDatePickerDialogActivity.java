
package com.example.datetime_picker_demo.android;

import com.example.datetime_picker_demo.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;

public class AndroidDatePickerDialogActivity extends Activity implements OnClickListener {

    private TextView tv_android_datepicker_dialog_show;
    private Button btn_android_datepicker_dialog_show;
    private Button btn_android_datepicker_dialog_show_noCalendar;
    private Button btn_android_datepicker_dialog_show_THEME_HOLO_DARK;
    private Button btn_android_datepicker_dialog_show_THEME_SELF;
    @SuppressWarnings("unused")
    private static final int DIALOG_DATE_ONE = 1001;
    @SuppressWarnings("unused")
    private static final int DIALOG_DATE_TWO = 1002;
    @SuppressWarnings("unused")
    private static final int DIALOG_DATE_THREE = 1003;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;
    private OnDateSetListener mOnDateSetListener;
    private DatePickerDialog mDatePickDialog;
    private DatePickerDialog mDatePickDialog_noCalendar;
    private DatePickerDialog mDatePickDialog_theme_one;
    private DatePickerDialog mDatePickDialog_theme_SELF;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_dialog);
        initUI();
    }

    private void initUI() {
        this.btn_android_datepicker_dialog_show = (Button)findViewById(R.id.btn_android_datepicker_dialog_show);
        btn_android_datepicker_dialog_show.setOnClickListener(this);
        this.btn_android_datepicker_dialog_show_noCalendar = (Button)findViewById(R.id.btn_android_datepicker_dialog_show_noCalendar);
        btn_android_datepicker_dialog_show_noCalendar.setOnClickListener(this);
        this.btn_android_datepicker_dialog_show_THEME_HOLO_DARK = (Button)findViewById(R.id.btn_android_datepicker_dialog_show_theme_HOLO_DARK);
        btn_android_datepicker_dialog_show_THEME_HOLO_DARK.setOnClickListener(this);
        this.btn_android_datepicker_dialog_show_THEME_SELF = (Button)findViewById(R.id.btn_android_datepicker_dialog_show_theme_SELF);
        btn_android_datepicker_dialog_show_THEME_SELF.setOnClickListener(this);
        
        this.tv_android_datepicker_dialog_show = (TextView)findViewById(R.id.tv_android_datepicker_dialog_show);
        
        Calendar tempCalendar = Calendar.getInstance();
        this.year = tempCalendar.get(Calendar.YEAR);
        this.monthOfYear = tempCalendar.get(Calendar.MONTH);
        this.dayOfMonth = tempCalendar.get(Calendar.DAY_OF_MONTH);
        this.mOnDateSetListener = new OnDateSetListener() {
            
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tv_android_datepicker_dialog_show.setText(
                        "year: " + year + 
                        "\nmonthOfYear: " + (monthOfYear + 1) +
                        "\ndayOfMonth" + dayOfMonth);
            }
        };
        this.mDatePickDialog = new DatePickerDialog(this, mOnDateSetListener, year, monthOfYear, dayOfMonth);
        this.mDatePickDialog_noCalendar = new DatePickerDialog(this, mOnDateSetListener, year, monthOfYear, dayOfMonth);
        this.mDatePickDialog_theme_one = new DatePickerDialog(this, DatePickerDialog.THEME_HOLO_DARK, mOnDateSetListener, year, monthOfYear, dayOfMonth);
        this.mDatePickDialog_theme_SELF = new DatePickerDialog(this, mOnDateSetListener, year, monthOfYear, dayOfMonth);
       
    }
    
    @Override
    protected void onResume() {
        super.onResume();
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.date_picker_dialog, menu);
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

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.btn_android_datepicker_dialog_show:
                mDatePickDialog.show();
                break;
            case R.id.btn_android_datepicker_dialog_show_noCalendar:
                mDatePickDialog_noCalendar.getDatePicker().setCalendarViewShown(false);
                mDatePickDialog_noCalendar.show();
                break;
            case R.id.btn_android_datepicker_dialog_show_theme_HOLO_DARK:
                mDatePickDialog_theme_one.show();
                break;
            case R.id.btn_android_datepicker_dialog_show_theme_SELF:
                mDatePickDialog_theme_SELF.show();
            default:
                break;
        }
    
    }
    
    /**
     * from Dialog to find out DatePicker object
     * @description 
     * @author   tianran
     * @createDate Jan 12, 2015
     * @param group
     * @return
     */
    private DatePicker findDatePicker(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof DatePicker) {
                    Log.d("this findDatePicker child", " ____" + (child == null) );
                    return (DatePicker) child;
                } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                    Log.d("this findDatePicker result", " ____" + (result == null) );
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }
    @SuppressWarnings("unused")
    private void findNumberPicker(){
        ViewGroup tempViewGroup = (ViewGroup) mDatePickDialog_noCalendar.getWindow().getDecorView();
        DatePicker tempDatePicker = findDatePicker(tempViewGroup);
        Log.d("this tempDatePicker", " ____" + (tempDatePicker == null) );
        if (tempDatePicker != null) {
            NumberPicker numberPicker = (NumberPicker) tempDatePicker.getChildAt(0);
            if (numberPicker != null) {
                Log.d("this numberPicker", " ____" + numberPicker.getValue() );
            }
        }
    }
}
