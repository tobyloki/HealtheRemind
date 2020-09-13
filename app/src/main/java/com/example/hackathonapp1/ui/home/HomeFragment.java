package com.example.hackathonapp1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.hackathonapp1.R;
import com.example.hackathonapp1.appointmentItem;
import com.example.hackathonapp1.ui.EditItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import android.widget.Toast;


public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);


        /* JSON Objects */
        JSONObject obj = new JSONObject();
        try {
            obj.put("name", "adam"); /* encode */
            obj.put("age", new Integer(20));
            obj.put("net worth", new Double(100000000));
            Log.v("MYAPP", "JSON Object created");
            Log.v("MYAPP", obj.toString());
            String name = (String) obj.get("name"); /* decode */
            Log.v("MYAPP", "My name is " + name);
        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }

        /* JSON Arrays */
        JSONArray arr = new JSONArray();
        arr.put("Hello");
        arr.put(new Integer(14));
        Log.v("MYAPP", "JSON Array created");
        Log.v("MYAPP", arr.toString());


        /*
            Our JSON Objects:
            {
                prescriptions: [ { title: '1' }, { title: '2' } ],
                appointments: [],
                id: '0',
             }
        */

        final JSONArray elements = new JSONArray();
        JSONObject element1 = new JSONObject(); /* create and add element */
        try {
            /* prescription array */
            JSONArray prescriptions_arr = new JSONArray();
            JSONObject prescriptions_item1 = new JSONObject();
            prescriptions_item1.put("title", "myPrescription1");
            prescriptions_arr.put(prescriptions_item1);
            JSONObject prescriptions_item2 = new JSONObject();
            prescriptions_item2.put("refills", "2");
            prescriptions_arr.put(prescriptions_item2);
            JSONObject prescriptions_item3 = new JSONObject();
            prescriptions_item3.put("pharmacy", "myPharmacy");
            prescriptions_arr.put(prescriptions_item3);
            JSONObject prescriptions_item4 = new JSONObject();
            prescriptions_item4.put("drugName", "drugs");
            prescriptions_arr.put(prescriptions_item4);
            JSONObject prescriptions_item5 = new JSONObject();
            prescriptions_item5.put("whenToTake", "Daily @ 6PM");
            prescriptions_arr.put(prescriptions_item5);
            element1.put("prescriptions", prescriptions_arr);
            /* appointment array */
            JSONArray appointments_arr = new JSONArray();
            JSONObject appointments_item1 = new JSONObject();
            appointments_item1.put("title", "myAppointment1");
            appointments_arr.put(appointments_item1);
            JSONObject appointments_item2 = new JSONObject();
            appointments_item2.put("date", "September 10th");
            appointments_arr.put(appointments_item2);
            JSONObject appointments_item3 = new JSONObject();
            appointments_item3.put("time", "7PM");
            appointments_arr.put(appointments_item3);
            JSONObject appointments_item4 = new JSONObject();
            appointments_item4.put("location", "Zoom");
            appointments_arr.put(appointments_item4);
            JSONObject appointments_item5 = new JSONObject();
            appointments_item5.put("details", "Very Busy");
            appointments_arr.put(appointments_item5);
            element1.put("appointments", appointments_arr);
            /* Other stuff */
            element1.put("id", "0"); /* give element id */
            Log.v("MYAPP", "Element 1: " + element1.toString()); /* print element */
            elements.put(element1); /* add element to array */
        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }

        JSONObject element2 = new JSONObject(); /* create and add element */
        try {
            JSONArray prescriptions_arr = new JSONArray();
            JSONObject prescriptions_title = new JSONObject();
            prescriptions_title.put("title", "3");
            prescriptions_arr.put(prescriptions_title);
            JSONObject prescriptions_refills = new JSONObject();
            prescriptions_refills.put("refills", "4");
            prescriptions_arr.put(prescriptions_refills);
            //prescriptions_arr.put("prescription 1");
            element2.put("prescriptions", prescriptions_arr);

            /* appointment array */
            JSONArray appointments_arr = new JSONArray();
            JSONObject appointments_item1 = new JSONObject();
            appointments_item1.put("title", "myAppointment2");
            appointments_arr.put(appointments_item1);
            JSONObject appointments_item2 = new JSONObject();
            appointments_item2.put("date", "September 12th");
            appointments_arr.put(appointments_item2);
            JSONObject appointments_item3 = new JSONObject();
            appointments_item3.put("time", "7PM");
            appointments_arr.put(appointments_item3);
            JSONObject appointments_item4 = new JSONObject();
            appointments_item4.put("location", "Zoom");
            appointments_arr.put(appointments_item4);
            JSONObject appointments_item5 = new JSONObject();
            appointments_item5.put("details", "Still Busy");
            appointments_arr.put(appointments_item5);
            element2.put("appointments", appointments_arr);

            element2.put("id", "1");


            Log.v("MYAPP", "Element 2: " + element2.toString()); /* print element */
            elements.put(element2); /* add element to array */
        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }

        Log.v("MYAPP", "Elements Array: " + elements.toString()); /* print array */



        /* Making Lists */
        final ListView list = root.findViewById(R.id.list);
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            //JSONArray ja = element1.getJSONArray("appointments");
            //String[] props = {"title", "date", "time", "location", "details"}
            for(int i = 0; i < elements.length(); i++) {
                arrayList.add(elements.getJSONObject(i).getJSONArray("appointments").getJSONObject(0).getString("title").toString());
                //arrayList.add(props[i] + ": " + ja.getJSONObject(i).getString(props[i]).toString());
            }

        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
        final TextView details = root.findViewById(R.id.details);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("MYAPP", "Appointment Item clicked on");
                Intent intent = new Intent(requireContext(), EditItem.class);
                Log.v("MYAPP", "About to start the activity");
                startActivity(intent);
//                //String clickedItem=(String) list.getItemAtPosition(position);
//                //Toast.makeText(getActivity(),clickedItem,Toast.LENGTH_LONG).show();
////                try {
//
//                    /* SONArray ja = elements.getJSONObject(position).getJSONArray("appointments");
//                    appointmentItem.setTitle(ja.getJSONObject(0).getString("title").toString());
//                    appointmentItem.setDate(ja.getJSONObject(1).getString("date").toString());
//                    appointmentItem.setTime(ja.getJSONObject(2).getString("time").toString());
//                    appointmentItem.setLocation(ja.getJSONObject(3).getString("location").toString());
//                    appointmentItem.setDetails(ja.getJSONObject(4).getString("details").toString());
//                    Log.v("MYAPP", "Your appointment item: " + appointmentItem.info()); */
////                } catch (JSONException e) {
////                    Log.e("MYAPP", "unexpected JSON exception", e);
////                }
            }
        });


        return root;
    }
}