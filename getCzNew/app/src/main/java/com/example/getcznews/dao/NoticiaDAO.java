package com.example.getcznews.dao;

import com.example.getcznews.domain.Noticia;

public interface NoticiaDAO extends DefaulDAO<Noticia> {

    void excluirVisualizada();
    void limpar();
}
