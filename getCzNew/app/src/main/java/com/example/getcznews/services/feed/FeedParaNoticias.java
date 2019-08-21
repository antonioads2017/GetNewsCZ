package com.example.getcznews.services.feed;

import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.domain.Fonte;
import com.example.getcznews.domain.Noticia;
import com.example.getcznews.services.TimeView;

import org.xmlpull.v1.XmlPullParser;


import java.util.List;

public abstract class FeedParaNoticias {
    protected Fonte fonte;
    protected NoticiaDAO noticiaDAO;


    protected  abstract List<Noticia> xmlParaNoticias(XmlPullParser xpp);

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
    protected enum Tag {
        ITEM("item"),
        TITULO("title"),
        LINK("link"),
        GUID("guid"),
        DESCRICAO("description"),
        DATA("pubDate");

        private final String value;

        Tag(String value){
            this.value=value;
        }

        public String value(){
            return this.value;
        }
    }

}
