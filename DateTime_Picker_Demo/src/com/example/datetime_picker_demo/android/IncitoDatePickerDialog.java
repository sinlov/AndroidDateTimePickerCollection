
package com.example.datetime_picker_demo.android;

import com.example.datetime_picker_demo.R;
import com.incito.view.wheeldatepicker.WheelDatePickerDialog;
import com.incito.view.wheeldatepicker.WheelDatePickerDialog.OnWheelDateSetListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class IncitoDatePickerDialog extends Activity implements OnClickListener{
    private Button btn_incito_datepicker_dialog_show;
    private TextView tv_incito_datepicker_dialog_show_set;
    private WheelDatePickerDialog mWheelDatePickerDialog;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;
    private int hour;
    private int minute;
    private OnWheelDateSetListener mOnWheelDateSetListener;
    private int startYear = 1970;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incito_date_picker_dialog);
        initUI();
    }

    private void initUI() {
        this.tv_incito_datepicker_dialog_show_set = (TextView)findViewById(R.id.tv_incito_datepicker_dialog_show_set);
        this.btn_incito_datepicker_dialog_show = (Button)findViewById(R.id.btn_incito_datepicker_dialog_show);
        btn_incito_datepicker_dialog_show.setOnClickListener(this);
        Calendar calender = Calendar.getInstance();
        this.year = calender.get(Calendar.YEAR);
        this.monthOfYear = calender.get(Calendar.MONTH);
        this.dayOfMonth = calender.get(Calendar.DAY_OF_MONTH);
        this.hour = calender.get(Calendar.HOUR);
        this.minute = calender.get(Calendar.MINUTE);
        mOnWheelDateSetListener = new OnWheelDateSetListener() {

            @Override
            public void onDateSet(int year, int monthOfYear, int dayOfMonth, int hours, int mins) {
                tv_incito_datepicker_dialog_show_set.setText(
                        "year: " + (year + startYear) + "\n" +
                        "monthOfYear: " + ( monthOfYear + 1) +"\n"+
                        "dayOfMonth: " + ( dayOfMonth + 1) +"\n"+
                        "hours: " + hours +"\n"+
                        "mins: " + mins +"\n"
                        );
            }
        };
        mWheelDatePickerDialog = new WheelDatePickerDialog(this, mOnWheelDateSetListener, year, monthOfYear, dayOfMonth, hour, minute);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.incito_date_picker_dialog, menu);
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
            case R.id.btn_incito_datepicker_dialog_show:
                mWheelDatePickerDialog.show();
                break;

            default:
                break;
        }
    }
}
