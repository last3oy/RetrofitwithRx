package com.example.choco3.rxretrofitlab;


import android.app.Application;

import com.example.choco3.rxretrofitlab.http.Contextor;

/**
 * Created by Choco3 on 30/9/2559.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }
}
