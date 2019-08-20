package com.example.getcznews.services.feed;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

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
    }

    @Override
    protected void xmlParaNoticias(XmlPullParser xpp) {
        try {

            Fonte fonte = new Fonte();
            fonte.setId(1);
            fonte.setFeed("Vale do Piancó Noticias");

            Noticia noticia = null;

            boolean insiderItem = false;

            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                noticia = null;
                if (eventType == XmlPullParser.START_TAG) {

                    noticia = new Noticia(
                            "Título",
                            "Texto",
                            "https://maladeaventuras.com/wp-content/uploads/2018/12/mockup-celular-e1544448308875-750x400.jpg",
                            fonte);

                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insiderItem = true;
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insiderItem) {
                            noticia.setTitulo(xpp.nextText());
                        }
                    } else if (xpp.getName().equalsIgnoreCase("link")) {


                        fonte.setSite(xpp.nextText());
                        noticia.setFonte(fonte);
                    } else if (xpp.getName().equalsIgnoreCase("description")) {
                        noticia.setTexto(xpp.nextText());
                    }
                } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                    insiderItem = false;
                }
                if (noticia != null) {
                    Log.e("Notícia",noticia.toString());
                    getNoticias().add(noticia);
                }

                eventType = xpp.next();
//                noticia = new Noticia();
//                fonte = new Fonte();
            }
        }catch (Exception e) {
        }

    }

}
