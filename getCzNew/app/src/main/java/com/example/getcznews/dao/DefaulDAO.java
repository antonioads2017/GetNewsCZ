package com.example.getcznews.dao;

import java.util.List;

public interface DefaulDAO <T> {

    void salvar(T object);
    void editar(T object);
    void remover(T object);
    List<T> listar();
    T buscar(Object key);
}
