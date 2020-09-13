/* package com.example.hackathonapp1.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tempapp1.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.entity.mime.Header;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class HomeFragment extends Fragment {
    final static String TAG = "HomeFragment";
    String Res;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("test");

     //   new GetInfo().execute();
        new UpdateInfo().execute();
     //   new DeleteInfo().execute();

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
                        Log.e(TAG, "Status code: " + statusCode);
                        Log.e(TAG, "Response: " + response);
                    }
                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String response) {
                        Log.i(TAG, "Status code: " + statusCode);
                        Log.i(TAG, "Response: " + response);
                        try {
                            JSONObject mainObject = new JSONObject(response);
                            Log.i("tag",String.valueOf(mainObject.get("prescriptions")));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }



                });

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private class DeleteInfo extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... param) {
            try {
                String id = "0";
                String type = "prescription";
                int index = 0;
                AsyncHttpClient client = new SyncHttpClient();
                client.delete("https://xvq171cl74.execute-api.us-east-1.amazonaws.com/release/" + id + "/" + type + "/" + index, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String response, Throwable throwable) {
                        Log.e(TAG, "Status code: " + statusCode);
                        Log.e(TAG, "Response: " + response);
                    }

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String response) {
                        Log.i(TAG, "Status code: " + statusCode);
                        Log.i(TAG, "Response: " + response);
                        try {
                            JSONObject mainObject = new JSONObject(response);
                            Log.i("id: ",String.valueOf(mainObject.get("id")));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class UpdateInfo extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... param) {
            try {
                String id = "0";
                JSONObject params = new JSONObject() {{}};
                StringEntity entity = new StringEntity(params.toString());
                AsyncHttpClient client = new SyncHttpClient();
                client.post(requireContext(), "https://xvq171cl74.execute-api.us-east-1.amazonaws.com/release/" + id, entity, "application/json", new TextHttpResponseHandler() {
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
                            Log.i("appointments: ",String.valueOf(mainObject.get("appointments")));
                        } catch (JSONException e) {
                            e.printStackTrace();
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


*/
