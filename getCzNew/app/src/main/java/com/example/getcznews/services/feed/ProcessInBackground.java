package com.example.getcznews.services.feed;

import android.os.AsyncTask;
import android.util.Log;

import com.example.getcznews.domain.Noticia;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ProcessInBackground extends AsyncTask<Integer,Void,String> {

    private FeedParaNoticias feedParaNoticias;


    public InputStream getInputStream(URL url) {
        try{
            return url.openConnection().getInputStream();
        }catch (IOException e){
            return null;
        }
    }

    public ProcessInBackground(FeedParaNoticias feedParaNoticias) {
        this.feedParaNoticias = feedParaNoticias;
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
            Log.e("Exception",e.getMessage());
        }
        return null;
    }


}
