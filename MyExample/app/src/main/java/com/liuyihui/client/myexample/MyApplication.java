package com.liuyihui.client.myexample;

import android.app.Application;
import android.content.Context;

/**
 * 实现在任何地方都能获得context
 * Created by liuyh on 2016/10/27.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContextObject() {
        return context;
    }
}
