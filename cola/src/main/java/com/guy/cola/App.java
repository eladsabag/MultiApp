package com.guy.cola;

import android.app.Application;

import com.guy.common.App_Parent;
import com.guy.common.utils.MSP;
import com.guy.common.utils.MySignalV2;

public class App extends App_Parent {

    @Override
    protected String getWeatherApiKey() {
        return BuildConfig.WEATHER_API_KEY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        statsClass = Activity_Stats.class;

    }
}
