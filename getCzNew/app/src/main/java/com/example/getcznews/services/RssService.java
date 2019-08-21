package com.example.getcznews.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Classe de Serviço responsavel por atualizar as noticias
 * mesmo com a aplicação fechada, ou seja, em segundo plano
 * @author Antonio Miguel
 */
public class RssService extends Service {

    private TimeWeb timeWeb;

    public RssService() {
        super();
        Log.e("Services","Construtor");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Services","onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Log.e("Services","onCreate");
        super.onCreate();
    }

    /**
     * Metodo para startar o servico com delay denifinido
     * @param intent intenção de onde sera startado
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("RssService","INICIANDO RSS SERVICE");

        timeWeb = new TimeWeb();
        TimeWeb.init(300000);


        Log.e("Services","onStartCommand");

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("Services","onDestroy");
        super.onDestroy();
    }



}
