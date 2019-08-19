package com.example.getcznews.services.feed;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.domain.Noticia;
import com.example.getcznews.screens.NoticiaView;
import com.example.getcznews.screens.TelaPrincipal;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class FeedParaNoticias {
    private List<Noticia> noticias = new ArrayList<>();
    private String urlFeed;



    public FeedParaNoticias(NoticiaDAO noticiaDAO, String urlFeed) {
        this.urlFeed = urlFeed;

        String xml = baixarFeed();
        if(xml == null)
            return;
        Log.e("XML",xml);
        //noticiaDAO.limpar();
        for (Noticia noticia: noticias) {
            noticiaDAO.salvar(noticia);
        }

    }

    private String baixarFeed(){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try{

            URL url = new URL(urlFeed);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String linha;

            StringBuffer buffer = new StringBuffer();
            while((linha = reader.readLine()) != null){
                buffer.append(linha);
                buffer.append("\n");
            }

            return  buffer.toString();

        }catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null){
                urlConnection.disconnect();
            }

            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
            }
        }
        return null;

    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    public String getUrlFeed() {
        return urlFeed;
    }

    public void setUrlFeed(String urlFeed) {
        this.urlFeed = urlFeed;
    }
}