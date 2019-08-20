package com.example.getcznews.services.feed;

import android.os.AsyncTask;

import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.domain.Noticia;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class FeedParaNoticias {
    private List<Noticia> noticias;


    protected abstract void xmlParaNoticias(XmlPullParser xpp);


    public class ProcessInBackground extends AsyncTask<Integer,Void,Exception> {
        private String urlFeed;
        private FeedParaNoticias feedParaNoticias;


        public InputStream getInputStream(URL url) {
            try
            {
                return url.openConnection().getInputStream();
            }
            catch (IOException e)
            {
                return null;
            }
        }

        public ProcessInBackground(FeedParaNoticias feedParaNoticias, String urlFeed) {
            this.feedParaNoticias = feedParaNoticias;
            this.urlFeed = urlFeed;
        }

        @Override
        protected Exception doInBackground(Integer... integers) {
            Exception exception = null;

            try{
                URL url = new URL(urlFeed);
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(url),"UTF_8");

                feedParaNoticias.xmlParaNoticias(xpp);

            }catch (Exception e) {
                exception = e;
            }
            return exception;
        }

    }



    public FeedParaNoticias(NoticiaDAO noticiaDAO, String urlFeed) {
        noticias = new ArrayList<>();

        //Chama o método abstract para ler notícias do site
        new ProcessInBackground(this,urlFeed).execute();

        //noticiaDAO.limpar();
        for (Noticia noticia: noticias) {
            noticiaDAO.salvar(noticia);
        }

    }


    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

}
