package com.guy.common;

import android.app.Application;

import com.guy.common.utils.MSP;
import com.guy.common.utils.MySignalV2;

public abstract class App_Parent extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MySignalV2.initHelper(this);
        MSP.initHelper(this);
    }
}
