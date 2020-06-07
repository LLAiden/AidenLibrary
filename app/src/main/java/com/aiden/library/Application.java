package com.aiden.library;


import android.content.Context;

import androidx.multidex.MultiDex;

public class Application extends android.app.Application {


    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
        super.attachBaseContext(base);
    }
}
