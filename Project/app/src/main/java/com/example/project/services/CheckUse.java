package com.example.project.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class CheckUse extends Service {

    public CheckUse(){}



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
