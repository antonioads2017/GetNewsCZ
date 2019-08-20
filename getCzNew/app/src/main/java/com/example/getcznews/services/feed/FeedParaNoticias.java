package com.example.getcznews.services.feed;

import android.os.AsyncTask;
import android.util.Log;

import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.domain.Fonte;
import com.example.getcznews.domain.Noticia;
import com.example.getcznews.services.TimeView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class FeedParaNoticias {
    protected Fonte fonte;
    protected NoticiaDAO noticiaDAO;


    protected  abstract List<Noticia> xmlParaNoticias(XmlPullParser xpp);


    public class ProcessInBackground extends AsyncTask<Integer,Void,String> {

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

        public ProcessInBackground(FeedParaNoticias feedParaNoticias) {
            this.feedParaNoticias = feedParaNoticias;
//            this.urlFeed = urlFeed;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            Exception exception = null;

            try{
                URL url = new URL(fonte.getFeed());
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(url),"UTF_8");

                persistirNoticias(
                        feedParaNoticias
                                .xmlParaNoticias(xpp));

            }catch (Exception e) {
                Log.e("Exception",e.getMessage());
            }
            return null;
        }


    }



    public FeedParaNoticias(NoticiaDAO noticiaDAO, Fonte fonte) {
        this.fonte = fonte;
        this.noticiaDAO = noticiaDAO;

        //Chama o método abstract para ler notícias do site
        new ProcessInBackground(this).execute();


    }

    protected void persistirNoticias(List<Noticia> noticias){

        for (Noticia noticia: noticias) {
            noticiaDAO.salvar(noticia);
        }


        TimeView.run();

    }

}
