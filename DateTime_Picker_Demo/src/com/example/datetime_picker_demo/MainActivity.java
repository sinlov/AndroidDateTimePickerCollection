
package com.example.datetime_picker_demo;

import com.example.datetime_picker_demo.android.AndroidDatePickerActivity;
import com.example.datetime_picker_demo.android.AndroidDatePickerDialogActivity;
import com.example.datetime_picker_demo.android.AndroidTimePickerActiviy;
import com.example.datetime_picker_demo.android.IncitoDatePickerDialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView)findViewById(R.id.listview_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new String[]{"One System TimePicker"
                        , "Two System DatePicker"
                        , "Three System DatePickerDialog"
                        , "Four incito DatePickerDialog"
        });
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getApplication(), AndroidTimePickerActiviy.class));
                        break;
                    case 1:
                        startActivity(new Intent(getApplication(), AndroidDatePickerActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getApplication(), AndroidDatePickerDialogActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getApplication(), IncitoDatePickerDialog.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
