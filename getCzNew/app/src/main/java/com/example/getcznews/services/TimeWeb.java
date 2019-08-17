package com.example.getcznews.services;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TimeWeb extends TimerTask{

    private boolean ocupado;

    public static void init(){
        new Timer().schedule(new TimeWeb(),1000, 1000);
    }

    private TimeWeb() {
        this.ocupado = false;
    }

    public void run(){
        if (ocupado) return;
        ocupado = true;
        Log.e("Chamou ","Agora pela web");
        new Timer().schedule(new TimeView(), 1000);
        try {
            Thread.sleep(5000);
        }catch (Exception e){}
        ocupado = false;

    }



}
