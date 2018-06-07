package com.niroshan.temperjobportal.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.niroshan.temperjobportal.R;

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
}
