package com.example.getcznews.services;

import android.content.Context;
import android.util.Log;

import com.example.getcznews.dao.FonteDAO;
import com.example.getcznews.dao.FonteDAOImpl;
import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.dao.NoticiaDAOImpl;
import com.example.getcznews.domain.Fonte;
import com.example.getcznews.services.feed.FeedParaNoticiasDiarioSertao;

import java.util.List;
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
        Context context = TimeView.getPrincipal();
        if(context != null)
            atualizarNoticias(context);


//        Log.e("Chamou ","Agora pela web");
//        TimeView.run();
//        try {
//            Thread.sleep(5000);
//        }catch (Exception e){}
        ocupado = false;
    }

    private static void atualizarNoticias(Context context){
        NoticiaDAO noticiaDAO = new NoticiaDAOImpl(context);
        new FeedParaNoticiasDiarioSertao(noticiaDAO, "http://www.coisasdecajazeiras.com.br/feed/");

    }



}
