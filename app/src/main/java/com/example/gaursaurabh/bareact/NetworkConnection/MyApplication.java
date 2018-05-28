package com.example.gaursaurabh.bareact.NetworkConnection;

import android.app.Application;

import com.example.gaursaurabh.bareact.CacheFile.AppController;

/**
 * Created by Saurabh Gaur on 9/22/2017.
 */

public class MyApplication extends AppController {

    // Gloabl declaration of variable to use in whole app

    public static boolean activityVisible; // Variable that will check the
    // current activity state

    public static boolean isActivityVisible() {
        return activityVisible; // return true or false
    }

    public static void activityResumed() {
        activityVisible = true;// this will set true when activity resumed

    }

    public static void activityPaused() {
        activityVisible = false;// this will set false when activity paused

    }
}
