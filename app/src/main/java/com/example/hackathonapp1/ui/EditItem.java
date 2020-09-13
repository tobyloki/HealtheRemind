package com.example.hackathonapp1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.hackathonapp1.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class EditItem extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    final static String TAG = "TESTING";

    private EditText editDate;
    private EditText editTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("MYAPP", "Started edit item.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editDate = findViewById(R.id.editDate);
        editTime = findViewById(R.id.editTime);

        editDate.setInputType(InputType.TYPE_NULL);
        editTime.setInputType(InputType.TYPE_NULL);

        editDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DialogFragment datePicker = new DatePickerFragment();
                    datePicker.show(getSupportFragmentManager(), "date picker");
                }
            }
        });

        editTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(getSupportFragmentManager(), "time picker");
                }
            }
        });
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String fMonth = String.format("%02d", month);
        String fDay = String.format("%02d", day);

        String date = year + "-" + fMonth + "-" + fDay;

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        Log.i(TAG, date);
        Log.i(TAG, currentDateString);

        editDate.setText(date);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        String fHour = String.format("%02d", hour);
        String fMin = String.format("%02d", minute);

        String time = fHour + ":" + fMin;

        Log.i(TAG, time);

        editTime.setText(time);
    }
}