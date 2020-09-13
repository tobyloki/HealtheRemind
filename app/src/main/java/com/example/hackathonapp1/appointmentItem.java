package com.example.hackathonapp1;

import androidx.annotation.NonNull;

public class appointmentItem {

    private static String title;
    private static String time;
    private static String date;
    private static String location;
    private static String details;

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String val) {
        title = val;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String val) {
        time = val;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String val) {
        date = val;
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String val) {
        location = val;
    }

    public static String getDetails() {
        return details;
    }

    public static void setDetails(String val) {
        details = val;
    }

    public static String info() {
        return "Appointment: " + title + ", " + location + " @ " + date + " " + time+ ". Details: " + details;
    }
}
