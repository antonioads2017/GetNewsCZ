package com.example.getcznews.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.getcznews.database.DBCore;
import com.example.getcznews.domain.Noticia;

import java.util.ArrayList;
import java.util.List;

public class NoticiaDAOImpl implements NoticiaDAO {


    private SQLiteDatabase dataBase;

    private String[] colunas;

    private Noticia lerNoticiaDaTabela(Cursor cursor){
        return new Noticia(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5)==1);
    }

    public NoticiaDAOImpl(Context context) {
        dataBase = new DBCore(context).getWritableDatabase();
        colunas = new String[]{
                "_id",
                "titulo",
                "texto",
                "urlimage",
                "link",
                "visualizada"};
    }

    @Override
    public void salvar(Noticia object) {
        ContentValues valores = new ContentValues();
        valores.put("titulo",object.getTitulo());
        valores.put("texto",object.getTexto());
        valores.put("urlimage",object.getUrlImage());
        valores.put("link",object.getLink());
        valores.put("visualizada",object.isVisualizada()?1:0);
        dataBase.insert("noticia",null,valores);
    }

    @Override
    public void editar(Noticia object) {
        ContentValues valores = new ContentValues();
        valores.put("titulo",object.getTitulo());
        valores.put("texto",object.getTexto());
        valores.put("urlimage",object.getUrlImage());
        valores.put("link",object.getLink());
        valores.put("visualizada",object.isVisualizada()?1:0);
        dataBase.update(
                "noticia",
                valores,
                "_id = ?",
                new String[]{""+object.getId()});
    }

    @Override
    public void remover(Noticia object) {
        dataBase.delete(
                "noticia",
                "_id = ?",
                new String[]{""+object.getId()});
    }

    @Override
    public List<Noticia> listar() {
        List<Noticia> noticias = new ArrayList<>();
        Cursor cursor = dataBase
                .query(
                        "noticia",
                        colunas,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                noticias.add(lerNoticiaDaTabela(cursor));
            }while(cursor.moveToNext());
        }
        return noticias;
    }

    @Override
    public Noticia buscar(Object key) {
        Cursor cursor = dataBase
                .query(
                        "noticia",
                        colunas,
                        "_id = ?",
                        new String[]{""+key},
                        null,
                        null,
                        null);
        if (cursor.getCount() == 0)
            return null;
        cursor.moveToFirst();

        return lerNoticiaDaTabela(cursor);
    }

    @Override
    public void excluirVisualizada() {
        dataBase.delete(
                "noticia",
                "visualizada = ?",
                new String[]{"1"});
    }
}
