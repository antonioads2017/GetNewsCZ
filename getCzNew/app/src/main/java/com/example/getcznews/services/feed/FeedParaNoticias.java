package com.example.getcznews.services.feed;

import com.example.getcznews.domain.Noticia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public abstract class ConverterFeedEmNoticia  {

    private List<Noticia> notícias;
    private String urlFeed;

    protected abstract List<Noticia> feedToNoticia(String xml);

    public ConverterFeedEmNoticia(String urlFeed) {

        String xml = baixarFeed();
        if(xml == null)
            return;

        notícias = feedToNoticia(xml);
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
}
