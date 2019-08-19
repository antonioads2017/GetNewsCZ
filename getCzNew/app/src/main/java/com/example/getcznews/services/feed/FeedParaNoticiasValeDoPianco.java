package com.example.getcznews.services.feed;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.getcznews.MainActivity;
import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.domain.Fonte;
import com.example.getcznews.domain.Noticia;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class FeedParaNoticiasValeDoPianco extends FeedParaNoticias {

    public FeedParaNoticiasValeDoPianco(NoticiaDAO noticiaDAO, String urlFeed) {
        super(noticiaDAO, urlFeed);
        new ProcessInBackground().execute();
    }

    public class ProcessInBackground extends AsyncTask<Integer,Void,String> {


        Exception exception = null;


        @Override
        protected String doInBackground(Integer... integers) {

            try{
                URL url = new URL(getUrlFeed());
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(url),"UTF_8");
                boolean insiderItem = false;

                int eventType = xpp.getEventType();

                Noticia noticia = new Noticia();
                Fonte fonte = new Fonte();


                while (eventType!=XmlPullParser.END_DOCUMENT){
                    if(eventType==XmlPullParser.START_TAG){
                        if(xpp.getName().equalsIgnoreCase("item")){
                            insiderItem=true;
                        }else if(xpp.getName().equalsIgnoreCase("title")){
                            if(insiderItem){
                                    noticia.setTitulo(xpp.nextText());
                            }
                        }else if(xpp.getName().equalsIgnoreCase("link")){

                            fonte.setFeed("Vale do Piancó Noticias");
                            fonte.setFeed("");
                            fonte.setSite(xpp.nextText());
                            noticia.setFonte(fonte);
                        }else if(xpp.getName().equalsIgnoreCase("description")){
                            noticia.setTexto(xpp.nextText());
                        }
                    }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                        insiderItem=false;
                    }
                    getNoticias().add(noticia);
                    eventType=xpp.next();
                    noticia = new Noticia();
                    fonte = new Fonte();
                }

            }catch (MalformedURLException e){
                exception=e;
            }catch (XmlPullParserException e){
                exception=e;
            }catch (IOException ex){
                exception=ex;
            }
            return null;
        }

    }

}
