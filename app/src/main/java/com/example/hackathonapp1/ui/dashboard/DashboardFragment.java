package com.example.hackathonapp1.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.hackathonapp1.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        Button presc_btn = root.findViewById(R.id.prescriptions_btn);
        presc_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_notifications);
                }
                }
        );

        Button appt_btn = root.findViewById(R.id.appointments_btn);
        appt_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_home);
                    }
                }
        );

        Button remind_btn = root.findViewById(R.id.reminders_btn);
        remind_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_home);
                    }
                }
        );

        return root;
    }
}