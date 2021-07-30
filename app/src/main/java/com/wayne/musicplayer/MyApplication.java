package com.wayne.musicplayer;

import android.app.Application;
import android.content.Context;

/**
 * @author Wayne
 */
public class MyApplication extends Application {
    @SuppressWarnings("StaticFieldLeak")
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
