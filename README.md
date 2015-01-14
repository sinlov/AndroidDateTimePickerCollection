# AndroidDateTimePickerCollection
this is android date or time picker collection. it has system DatePicker, and more custom AlertDialog.

this one deom about DateTime Picker Collection
Android default DatePicker or DatePickerDialog way to use, I can't say it.

Use WheelDatePickerDialog:

it must be initialize by
WheelDatePickerDialog(Context context, OnWheelDateSetListener callBack, int year, int monthOfYear, int dayOfMonth, int hour, int minute)

so initialize 

        Calendar calender = Calendar.getInstance();
        this.year = calender.get(Calendar.YEAR);
        this.monthOfYear = calender.get(Calendar.MONTH);
        this.dayOfMonth = calender.get(Calendar.DAY_OF_MONTH);
        this.hour = calender.get(Calendar.HOUR);
        this.minute = calender.get(Calendar.MINUTE);
        mOnWheelDateSetListener

and initialize OnWheelDateSetListener like this:
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
<li>Take care of the time format

year The year that was set and it must + START_YEAR START_YEAR = 1970 or you can set START_YEAR.

monthOfYear The month that was set (0-11) for compatibility with java.util.Calendar.

~dayOfMonth The day of the month that was set must +1 .

