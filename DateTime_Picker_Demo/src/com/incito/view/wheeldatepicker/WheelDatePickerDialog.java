/*
 * Copyright (c) 2012, Incito Corporation, All Rights Reserved
 */
package com.incito.view.wheeldatepicker;

import com.example.datetime_picker_demo.R;
import com.incito.view.wheelview.NumericWheelAdapter;
import com.incito.view.wheelview.OnWheelChangedListener;
import com.incito.view.wheelview.WheelAdapter;
import com.incito.view.wheelview.WheelView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * @description 
 * @author   tianran
 * @createDate Jan 12, 2015
 * @version  1.0
 */
@SuppressWarnings("unused")
public class WheelDatePickerDialog extends AlertDialog implements OnClickListener{

    private static final String YEAR = "year";
    private static final String YEAR_ZH = "年";
    private static final String MONTH = "month";
    private static final String MONTH_ZH = "月";
    private static final String DAY = "day";
    private static final String DAY_ZH = "日";
    private static final String HOUR = "hour";
    private static final String HOUR_ZH = "时";
    private static final String MINUTE = "minute";
    private static final String MINUTE_ZH = "分";
    private static int START_YEAR = 1970, END_YEAR = 2100;
    private String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
    private String[] months_little = { "4", "6", "9", "11" };
    private final List<String> list_big = Arrays.asList(months_big);
    private final List<String> list_little = Arrays.asList(months_little);

    private final OnWheelDateSetListener mCallBack;
    private final Calendar mCalendar;
    private final WheelView wv_year;
    private final WheelView wv_month;
    private final WheelView wv_day;
    private final WheelView wv_hours;
    private final WheelView wv_mins;
    private NumericWheelAdapter year_NumericWheelAdapter;
    private NumericWheelAdapter month_NumericWheelAdapter;
    private NumericWheelAdapter day_NumericWheelAdapter;
    private NumericWheelAdapter hours_NumericWheelAdapter;
    private NumericWheelAdapter mins_NumericWheelAdapter;
    private OnWheelChangedListener wheelChangedListener_year;
    private OnWheelChangedListener wheelChangedListener_month;
    private int densityDpi = 100;

    private boolean mTitleNeedsUpdate = true;

    /**
     * The callback used to indicate the user is done filling in the date.
     */
    public interface OnWheelDateSetListener {

        /**
         * @param year The year that was set and it must + START_YEAR
         * \n START_YEAR = 1970 or you can set START_YEAR.
         * @param monthOfYear The month that was set (0-11) for compatibility
         *  with {@link java.util.Calendar}.
         * @param dayOfMonth The day of the month that was set must +1 .
         */
        void onDateSet(int year, int monthOfYear, int dayOfMonth, int hours , int mins);
    }

    /**
     * @param context The context the dialog is to run in.
     * @param callBack How the parent is notified that the date is set.
     * @param year The initial year of the dialog.
     * @param monthOfYear The initial month of the dialog.
     * @param dayOfMonth The initial day of the dialog.
     */
    public WheelDatePickerDialog(Context context,
            OnWheelDateSetListener callBack,
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hour,
            int minute) {
        this(context, 0, callBack, year, monthOfYear, dayOfMonth,hour,minute);
    }

    /**
     * @param context The context the dialog is to run in.
     * @param theme the theme to apply to this dialog
     * @param callBack How the parent is notified that the date is set.
     * @param year The initial year of the dialog.
     * @param monthOfYear The initial month of the dialog.
     * @param dayOfMonth The initial day of the dialog.
     */
    @SuppressLint("InflateParams")
    public WheelDatePickerDialog(Context context, int theme, OnWheelDateSetListener callBack, int year, int monthOfYear,
            int dayOfMonth, int hour, int minute) {
        super(context, theme);
        mCallBack = callBack;
        mCalendar = Calendar.getInstance();

        Context themeContext = getContext();
        setButton(BUTTON_POSITIVE, themeContext.getText(android.R.string.ok), this);
        setIcon(0);
        LayoutInflater inflater =
                (LayoutInflater) themeContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.wheel_datepick_dialog_layout, null);
        setView(view);
        wv_year = (WheelView)view.findViewById(R.id.wv_date_picker_year);
        wv_month = (WheelView)view.findViewById(R.id.wv_date_picker_month);
        wv_day = (WheelView)view.findViewById(R.id.wv_date_picker_day);
        wv_hours = (WheelView)view.findViewById(R.id.wv_date_picker_hour);
        wv_mins = (WheelView)view.findViewById(R.id.wv_date_picker_mins);
        
        densityDpi = getDisplayMetricsDPI();
        
        setWheelViewTextSize(wv_year, dayOfMonth);
        setWheelViewTextSize(wv_month, dayOfMonth);
        setWheelViewTextSize(wv_day, dayOfMonth);
        setWheelViewTextSize(wv_hours, dayOfMonth);
        setWheelViewTextSize(wv_mins, dayOfMonth);
        showNowDateTimePicker();
        addCustomWheelChangedListener();
    }


    /**
     * get START_YEAR
     * @description 
     * @author   tianran
     * @createDate Jan 12, 2015
     * @return
     */
    public int getSTART_YEAR() {
        return START_YEAR;
    }
    /**
     * set START_YEAR
     * @description 
     * @author   tianran
     * @createDate Jan 12, 2015
     * @param sTART_YEAR
     */
    public void setSTART_YEAR(int sTART_YEAR) {
        START_YEAR = sTART_YEAR;
    }
    /**
     * get END_YEAR
     * @description 
     * @author   tianran
     * @createDate Jan 12, 2015
     * @return
     */
    public int getEND_YEAR() {
        return END_YEAR;
    }
    /**
     * set END_YEAR
     * @description 
     * @author   tianran
     * @createDate Jan 12, 2015
     * @param eND_YEAR
     */
    public void setEND_YEAR(int eND_YEAR) {
        END_YEAR = eND_YEAR;
    }



    /**
     * show Now Date and time picker
     * @description 
     * @author   tianran
     * @createDate Jan 12, 2015
     */
    private void showNowDateTimePicker(){
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DATE);
        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);

        //Initialize year wheel
        setWheelView(wv_year, 
                bulidNumericWheelAdapter(year_NumericWheelAdapter, START_YEAR, END_YEAR),
                true,
                YEAR_ZH,
                year - START_YEAR);
        //Initialize month wheel
        setWheelView(wv_month, 
                bulidNumericWheelAdapter(month_NumericWheelAdapter, 1, 12),
                true,
                MONTH_ZH,
                month);

        //Initialize dayofMonth wheel;
        String temp = String.valueOf((month + 1));
        boolean isBigMonth = list_big.contains(temp);
        boolean islittleMonth = list_little.contains(temp);
        //Determine the size of the moon and it is a leap year, the result used to determine the "Day"
        if (isBigMonth) {
            day_NumericWheelAdapter = bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 31);
        }else if (islittleMonth ){
            day_NumericWheelAdapter = bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 30);
        }else {
            //if is Leap year
            if (isLeapYear(year)) {
                day_NumericWheelAdapter = bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 29);
            }else {
                day_NumericWheelAdapter = bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 28);
            }
        }
        setWheelView(wv_day, day_NumericWheelAdapter, true, DAY_ZH, (day - 1));
        //Initialize hour wheel;
        setWheelView(wv_hours, 
                bulidNumericWheelAdapter(hours_NumericWheelAdapter, 0, 23),
                true,
                HOUR_ZH,
                hour);
        //Initialize minute wheel;
        setWheelView(wv_mins, 
                bulidNumericWheelAdapter(mins_NumericWheelAdapter, 0, 23),
                true,
                MINUTE_ZH,
                minute);
        mTitleNeedsUpdate = false;
    }
    /**
     * set WheelView
     * @description 
     * @author   tianran
     * @createDate Jan 12, 2015
     * @param tagetWheelView
     * @param wheelAdapter
     * @param setCyclic
     * @param lable
     * @param currentitem
     */
    private void setWheelView(WheelView tagetWheelView, WheelAdapter wheelAdapter, boolean setCyclic , String lable, int currentitem){
        tagetWheelView.setAdapter(wheelAdapter);
        tagetWheelView.setCyclic(setCyclic);
        tagetWheelView.setLabel(lable);
        tagetWheelView.setCurrentItem(currentitem);
    }
    /**
     * Build Numeric Wheel Adapter
     * @description 
     * @author   tianran
     * @createDate Jan 12, 2015
     * @param tagNumericWheelAdapter
     * @param begin
     * @param end
     * @return
     */
    private NumericWheelAdapter bulidNumericWheelAdapter(NumericWheelAdapter tagNumericWheelAdapter, int begin, int end){
        if (tagNumericWheelAdapter == null) {
            tagNumericWheelAdapter = new NumericWheelAdapter(begin, end);
            return tagNumericWheelAdapter;
        }else {
            tagNumericWheelAdapter.setMinValue(begin);
            tagNumericWheelAdapter.setMaxValue(end);
            return tagNumericWheelAdapter;
        }
    }
    /**
     * check out year is leap year
     * @description 
     * @author   tianran
     * @createDate Jan 12, 2015
     * @param tagYear
     * @return true or false
     */
    private boolean isLeapYear(int tagYear){
        if ((tagYear % 4 == 0 && tagYear % 100 != 0)
                || tagYear % 400 == 0) {
            return true;
        }else {
            return false;
        }
    }
    
    /**
     * add custom wheel changed listener
     * @description 
     * @author   tianran
     * @createDate Jan 12, 2015
     */
    private void addCustomWheelChangedListener(){
        if (wheelChangedListener_year == null) {
            wheelChangedListener_year = new OnWheelChangedListener() {

                @Override
                public void onChanged(WheelView wheel, int oldValue, int newValue) {
                    int year_num = newValue + START_YEAR;
                    String tempMonth = String.valueOf(wv_month.getCurrentItem() + 1);
                    boolean isBigMoth = list_big.contains(tempMonth);
                    boolean isLittleMoth = list_little.contains(tempMonth);
                    if (isBigMoth) {
                        bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 31);
                        wv_day.setAdapter(day_NumericWheelAdapter);
                    }else if (isLittleMoth) {
                        bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 30);
                        wv_day.setAdapter(day_NumericWheelAdapter);
                    }else {
                        if (isLeapYear(year_num)) {
                            bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 29);
                            wv_day.setAdapter(day_NumericWheelAdapter);
                        }else {
                            bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 28);
                            wv_day.setAdapter(day_NumericWheelAdapter);
                        }
                    }
                }
            };
        }
        
        if (wheelChangedListener_month == null) {
            wheelChangedListener_month = new OnWheelChangedListener() {
                
                @Override
                public void onChanged(WheelView wheel, int oldValue, int newValue) {
                    int month_num = newValue + 1;
                    String tempMonth = String.valueOf(month_num);
                    boolean isBigMoth = list_big.contains(tempMonth);
                    boolean isLittleMoth = list_little.contains(tempMonth);
                    if (isBigMoth) {
                        bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 31);
                        wv_day.setAdapter(day_NumericWheelAdapter);
                    }else if (isLittleMoth) {
                        bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 30);
                        wv_day.setAdapter(day_NumericWheelAdapter);
                        
                    }else {
                        if (isLeapYear(wv_year.getCurrentItem() + START_YEAR)) {
                            bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 29);
                            wv_day.setAdapter(day_NumericWheelAdapter);
                        }else {
                            bulidNumericWheelAdapter(day_NumericWheelAdapter, 1, 28);
                            wv_day.setAdapter(day_NumericWheelAdapter);
                        }
                    }
                    
                }
            };
        }
        wv_year.addChangingListener(wheelChangedListener_year);
        wv_month.addChangingListener(wheelChangedListener_month);
    }
    
    private void tryNotifyDateSet(){
        if (mCallBack != null) {
            mCallBack.onDateSet(wv_year.getCurrentItem(),
                    wv_month.getCurrentItem(),
                    wv_day.getCurrentItem(),
                    wv_hours.getCurrentItem(),
                    wv_mins.getCurrentItem());
        }
    }
    
    @Override
    public void onClick(DialogInterface dialog, int which) {
        tryNotifyDateSet();
    }
    
//    private void updateDateTime(int year, int month, int dayofMonth, int hour, int minute){
//        
//    }

    @Override
    protected void onStop() {
        tryNotifyDateSet();
        super.onStop();
    }

    @Override
    public Bundle onSaveInstanceState() {
        Bundle state = super.onSaveInstanceState();
        state.putInt(YEAR, wv_year.getCurrentItem());
        state.putInt(MINUTE, wv_month.getCurrentItem());
        state.putInt(DAY, wv_day.getCurrentItem());
        state.putInt(HOUR, wv_hours.getCurrentItem());
        state.putInt(MINUTE, wv_mins.getCurrentItem());
        return state;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int year = savedInstanceState.getInt(YEAR);
        int month = savedInstanceState.getInt(MONTH);
        int day = savedInstanceState.getInt(DAY);
        int hour = savedInstanceState.getInt(HOUR);
        int minute = savedInstanceState.getInt(MINUTE);
        wv_year.setCurrentItem(year);
        wv_month.setCurrentItem(month);
        wv_day.setCurrentItem(day);
        wv_hours.setCurrentItem(hour);
        wv_mins.setCurrentItem(minute);
    }
    /**
     * get Display Metrics DPI
     * DPI（120 / 160 / 240）
     * @description 
     * @author   tianran
     * @createDate Jan 13, 2015
     */
    private int getDisplayMetricsDPI(){
        DisplayMetrics metric = new DisplayMetrics(); 
        WindowManager tempManger = getWindow().getWindowManager();
        if (tempManger != null) {
            tempManger.getDefaultDisplay().getMetrics(metric);
            return metric.densityDpi;
        }else {
            return densityDpi;
        }
    }
    /**
     * set WheelView Text Size
     * @description 
     * @author   tianran
     * @createDate Jan 13, 2015
     * @param wheelview
     * @param textSize
     */
    private void setWheelViewTextSize(WheelView wheelview, int textSize){
        wheelview.TEXT_SIZE = textSize * 3;
    }
}
