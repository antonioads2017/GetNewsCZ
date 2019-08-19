package com.example.getcznews.services;

import android.content.Context;

import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.dao.NoticiaDAOImpl;
import com.example.getcznews.domain.Noticia;

import java.util.List;

/***************************************
 * Classe de Serviço da notícia.
 * Nesta classe é aplicada a regra de
 * negócio das notícias
 *****************************************/
public class NoticiaService {

    private NoticiaDAO noticiaDAO;

    public NoticiaService(Context context) {
        noticiaDAO = new NoticiaDAOImpl(context);
    }

    /*****************************************
     * Método que retorna a lista de todas
     * as notícias
     *****************************************/
    public List<Noticia> getListaNoticias(){
        return noticiaDAO.listar();
    }

    /***************************************
     * Método que retorna uma notícia específica
     * a partir do identificador da notícia.
     * Caso o identificado seja inválido
     * será retornada uma notícia vazia.
     ***************************************/
    public Noticia getNoticiaId(long id){
        Noticia noticia = noticiaDAO.buscar(id);

        if (noticia == null)
            noticia = new Noticia();

        return noticia;

    }
}
