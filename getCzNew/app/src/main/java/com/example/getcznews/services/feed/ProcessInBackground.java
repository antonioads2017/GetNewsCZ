package com.example.getcznews.services.feed;

import android.os.AsyncTask;
import android.util.Log;

import com.example.getcznews.R;
import com.example.getcznews.domain.Noticia;
import com.example.getcznews.screens.TelaPrincipal;
import com.example.getcznews.services.NotificationManager;
import com.example.getcznews.services.TimeView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ProcessInBackground extends AsyncTask<Integer,Void,String> {

    private FeedParaNoticias feedParaNoticias;
    private NotificationManager nm;


    public InputStream getInputStream(URL url) {
        try{
            return url.openConnection().getInputStream();
        }catch (IOException e){
            return null;
        }
    }

    public ProcessInBackground(FeedParaNoticias feedParaNoticias) {
        this.feedParaNoticias = feedParaNoticias;
        this.nm=new NotificationManager(TimeView.getPrincipal());
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... integers) {
        try{
            URL url = new URL(feedParaNoticias.fonte.getFeed());
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(getInputStream(url),"UTF_8");


            List<Noticia> noticias = feedParaNoticias.xmlParaNoticias(xpp);
            feedParaNoticias.persistirNoticias(noticias);

        }catch (Exception e) {
                nm.sendNotification(R.drawable.small,"GetNews CZ","Noticias não atualizadas, verifique a rede", TelaPrincipal.class);

            Log.e("Exception",e.getMessage());
        }
        return null;
    }


}
