package com.niroshan.temperjobportal.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.niroshan.temperjobportal.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtils {

    public static boolean checkNetworkConnection(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }

        NetworkInfo activeNetworks = connectivityManager.getActiveNetworkInfo();
        if (activeNetworks != null && activeNetworks.isConnected()) {
            return activeNetworks.isConnectedOrConnecting();
        }
        return false;
    }

    //start an activity - Niroshan
    public static void startActivity(Activity activity, Class<? extends Activity> aClass) {
        Intent intent = new Intent(activity, aClass);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    //close an activity
    public static void closeActivity(Activity activity) {

        activity.overridePendingTransition(R.anim.left_in, R.anim.slide_to_right);
    }

    public static void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int status) {
        activity.startActivityForResult(intent, status);
        activity.overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    public static String convertDateWithTime(String strTime) {

        try {

            Date date;
            DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            date = formatter.parse(strTime);

            DateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy");

            return formatter2.format(date);
        } catch (ParseException e) {
        } catch (Exception e) {
        }

        return strTime;
    }

    public static float getDistance(double lat1, double lon1, double lat2, double lon2) {
        float[] distance = new float[2];
        Location.distanceBetween(lat1, lon1, lat2, lon2, distance);
        return (distance[0]/1000);
    }
}
