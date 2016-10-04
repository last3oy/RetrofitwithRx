package com.example.choco3.rxretrofitlab.http;

import android.content.Context;

/**
 * Created by Choco3 on 30/9/2559.
 */

public class Contextor {

    private static Contextor instance;
    private Context mContext;

    public static Contextor getInstance() {
        if (instance == null) {
            instance = new Contextor();
        }
        return instance;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    private Contextor() {

    }

    public Context getContext() {
        return mContext;
    }
}
