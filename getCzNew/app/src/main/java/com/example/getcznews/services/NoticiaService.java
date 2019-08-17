package com.example.getcznews.services;

import android.content.Context;

import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.dao.NoticiaDAOImpl;
import com.example.getcznews.domain.Noticia;

import java.util.List;

public class NoticiaService {

    private NoticiaDAO noticiaDAO;

    public NoticiaService(Context context) {
        noticiaDAO = new NoticiaDAOImpl(context);
    }

    public List<Noticia> getListaNoticias(){
        return noticiaDAO.listar();
    }

    public Noticia getNoticiaId(long id){
        Noticia noticia = noticiaDAO.buscar(id);

        if (noticia == null)
            noticia = new Noticia();

        return noticia;

    }
}
