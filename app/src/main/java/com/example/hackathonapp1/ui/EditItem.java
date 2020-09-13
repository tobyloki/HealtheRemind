package com.example.hackathonapp1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
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
import com.example.hackathonapp1.appointmentItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.entity.StringEntity;

public class EditItem extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    final static String TAG = "TESTING";

    private EditText editTitle;
    private EditText editDate;
    private EditText editTime;
    private EditText editDescription;

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

        /* Fill current info */
        editTitle = findViewById(R.id.editTextTextPersonName);
        editTitle.setText(appointmentItem.getTitle());
        editDescription = findViewById(R.id.editTextTextPersonName2);
        editDescription.setText(appointmentItem.getDetails());
        editDate.setText(appointmentItem.getDate());
        editTime.setText(appointmentItem.getTime());
        Log.v("MYAPP", "This item exists in row " + appointmentItem.getRow());

        new UpdateInfo().execute();
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

    private class UpdateInfo extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... param) {
            try {
                String id = "0";
                JSONObject params = new JSONObject() {{
                    put("title", editTitle.getText());
                    Log.v("MYAPP", "Updated Title: " + editTitle.getText());
                    put("date", editDate.getText());
                    Log.v("MYAPP", "Updated Date: " + editDate.getText());
                    put("time", editTime.getText());
                    Log.v("MYAPP", "Updated Time: " + editTime.getText());
                    put("description", editDescription.getText());
                    Log.v("MYAPP", "Updated Description: " + editDescription.getText());
                }};
                StringEntity entity = new StringEntity(params.toString());
                AsyncHttpClient client = new SyncHttpClient();
                client.put(getApplicationContext(), "https://xvq171cl74.execute-api.us-east-1.amazonaws.com/release/" + id + "/" + appointmentItem.getType() + "/" + appointmentItem.getRow(), entity, "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String response, Throwable throwable) {
                        Log.i(TAG, "Status code: " + statusCode);
                        Log.i(TAG, "Response: " + response);
                    }

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String response) {
                        Log.i(TAG, "Status code: " + statusCode);
                        Log.i(TAG, "Response: " + response);
                        try {
                            JSONObject mainObject = new JSONObject(response);
                            Log.i("MYAPP: ",String.valueOf(mainObject.get(appointmentItem.getType())));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Button saveBtn = findViewById(R.id.saveBtn);
                        saveBtn.setOnClickListener(
                                new Button.OnClickListener(){
                                    public void onClick(View v){
                                        runOnUiThread (new Thread(new Runnable() {
                                            public void run() {
                                                finish();
                                            }
                                        }));
                                    }
                                }
                        );
                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}