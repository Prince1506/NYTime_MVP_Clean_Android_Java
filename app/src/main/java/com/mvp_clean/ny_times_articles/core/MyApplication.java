package com.mvp_clean.ny_times_articles.core;


import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    static MyApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    public static MyApplication getMyApplicationContext(){
        return context;
    }
//    override fun onCreate() {
//        super.onCreate()
//        cntxt = this
//    }
//
//    companion object {
//        var cntxt: Context? = null
//
//        /*
//            get app context
//        */
//        fun getMyApplicationContext() = cntxt
//    }
}