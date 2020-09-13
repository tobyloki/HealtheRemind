package com.example.hackathonapp1.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.hackathonapp1.R;
import com.example.hackathonapp1.appointmentItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.example.hackathonapp1.ui.EditItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import android.widget.Toast;


public class HomeFragment extends Fragment {

    JSONArray elements = new JSONArray();
    View root;
    Context activity;
    int resource;
    ListView list;
    ArrayList<String> arrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);

        activity = getActivity();
        resource = android.R.layout.simple_list_item_1;
        list = root.findViewById(R.id.list);
        try {
            String str_result = new GetInfo().execute().get();
        } catch (Exception e) {
            Log.v("MYAPP", "ERROR: " + e);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity, resource, arrayList);
        try {
            list.setAdapter(arrayAdapter);
        } catch (Exception e) {
            Log.v("MYAPP", "ERROR: " + e);
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Log.v("MYAPP", "Creating appointment item");
                    appointmentItem.setTitle(elements.getJSONObject(position).getString("title").toString());
                    appointmentItem.setDate(elements.getJSONObject(position).getString("date").toString());
                    appointmentItem.setTime(elements.getJSONObject(position).getString("time").toString());
                    appointmentItem.setDetails(elements.getJSONObject(position).getString("description").toString());
                    appointmentItem.setRow(String.valueOf(position));
                    appointmentItem.setType("appointments");
                    Log.v("MYAPP", "Your appointment item: " + appointmentItem.info());
                } catch (JSONException e) {
                    Log.e("MYAPP", "unexpected JSON exception", e);
                }
                Intent intent = new Intent(requireContext(), EditItem.class);
                startActivity(intent);

            }
        });
        Button createNew = root.findViewById(R.id.create);
        createNew.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        appointmentItem.setTitle("");
                        appointmentItem.setDate("");
                        appointmentItem.setTime("");
                        appointmentItem.setDetails("");
                        appointmentItem.setRow(String.valueOf(elements.length()));
                        appointmentItem.setType("appointments");
                        Intent intent = new Intent(requireContext(), EditItem.class);
                        startActivity(intent);
                    }
                }
        );

        return root;
    }

    private class GetInfo extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... param) {
            try {
                String id = "0";
                AsyncHttpClient client = new SyncHttpClient();
                String url = "https://xvq171cl74.execute-api.us-east-1.amazonaws.com/release/" + id;
                client.get(url, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String response, Throwable throwable) {
                        Log.e("MYAPP", "Status code: " + statusCode);
                        Log.e("MYAPP", "Response: " + response);
                    }
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String response) {
                        Log.i("MYAPP", "Status code: " + statusCode);
                        Log.i("MYAPP", "Response: " + response);
                        try {
                            JSONObject mainObject = new JSONObject(response);
                            Log.v("MYAPP",String.valueOf(mainObject.get("appointments")));
                            elements = mainObject.getJSONArray("appointments");
                            Log.v("MYAPP", "Elements: " + elements.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /* Making Lists */

                        arrayList = new ArrayList<>();
                        try {
                            for(int i = 0; i < elements.length(); i++) {
                                //Log.v("MYAPP", "Element " + i + ": " + elements.getJSONObject(i).getString("title").toString());
                                if(elements.getJSONObject(i).getString("title") != null) {
                                    arrayList.add(elements.getJSONObject(i).getString("title").toString());
                                } else {
                                    arrayList.add("Null " + i);
                                }
                            }
                        } catch (JSONException e) {
                            Log.e("MYAPP", "unexpected JSON exception", e);
                        }

                    }



                });

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}