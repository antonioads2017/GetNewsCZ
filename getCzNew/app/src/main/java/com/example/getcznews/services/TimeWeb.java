package com.example.getcznews.services;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TimeWeb extends TimerTask{

    private boolean ocupado;
    private static int deleyTimeWeb;

    public static void init(int _deleyTimeWeb){
        deleyTimeWeb = _deleyTimeWeb;
        new Timer().schedule(new TimeWeb(),1000, deleyTimeWeb);
    }

    private TimeWeb() {
        this.ocupado = false;
    }

    public void run(){
        if (ocupado) return;
        ocupado = true;
        Log.e("Chamou ","Agora pela web");
        TimeView.run();
        try {
            Thread.sleep(5000);
        }catch (Exception e){}
        ocupado = false;

    }



}
