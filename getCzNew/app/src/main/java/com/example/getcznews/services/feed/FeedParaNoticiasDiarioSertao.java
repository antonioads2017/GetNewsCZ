package com.example.getcznews.services.feed;

import android.util.Log;

import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.domain.Noticia;

import java.util.ArrayList;
import java.util.List;

public class FeedParaNoticiasDiarioSertao extends FeedParaNoticias {

    public FeedParaNoticiasDiarioSertao(NoticiaDAO noticiaDAO, String urlFeed) {
        super(noticiaDAO, urlFeed);
    }

    @Override
    protected List<Noticia> feedToNoticia(String xml) {
        List<Noticia> noticias = new ArrayList<>();

        Log.e("XML",xml);

        return noticias;
    }
}
