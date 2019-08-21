package com.example.getcznews.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;

import com.example.getcznews.database.DBCore;
import com.example.getcznews.domain.Fonte;
import com.example.getcznews.domain.Noticia;

import java.util.ArrayList;
import java.util.List;

public class NoticiaDAOImpl implements NoticiaDAO {


    private Context context;
    private SQLiteDatabase dataBase;
    private FonteDAO fonteDAO;

    private String[] colunas;

    private Noticia lerNoticiaDaTabela(Cursor cursor){

        Fonte fonte = fonteDAO.buscar(cursor.getLong(1));
        if (fonte == null)
            fonte = new Fonte();

        return new Noticia(
                cursor.getLong(0),
                fonte,
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5)==1);
    }

    public NoticiaDAOImpl(Context context) {
        this.context = context;
//
        fonteDAO = new FonteDAOImpl(context);
        colunas = new String[]{
                "_id",
                "fonte_id",
                "titulo",
                "texto",
                "urlimage",
                "visualizada"};
    }

    private SQLiteDatabase abrir(){
        if(dataBase == null)
            dataBase = new DBCore(context).getWritableDatabase();
        return dataBase;
    }

    private void fechar(){
        if(dataBase != null){
            dataBase.close();
            dataBase = null;
        }
    }

    @Override
    public void salvar(Noticia object) {
        ContentValues valores = new ContentValues();
        valores.put("fonte_id",object.getFonte().getId());
        valores.put("titulo",object.getTitulo());
        valores.put("texto",object.getTexto());
        valores.put("urlimage",object.getUrlImage());
        valores.put("visualizada",object.isVisualizada()?1:0);
        abrir().insert("noticia",null,valores);
        fechar();
    }

    @Override
    public void editar(Noticia object) {
        ContentValues valores = new ContentValues();
        valores.put("fonte_id",object.getFonte().getId());
        valores.put("titulo",object.getTitulo());
        valores.put("texto",object.getTexto());
        valores.put("urlimage",object.getUrlImage());
        valores.put("visualizada",object.isVisualizada()?1:0);
        abrir().update(
                "noticia",
                valores,
                "_id = ?",
                new String[]{""+object.getId()});
        fechar();
    }

    @Override
    public void remover(Noticia object) {
        abrir().delete(
                "noticia",
                "_id = ?",
                new String[]{""+object.getId()});
        fechar();
    }

    @Override
    public List<Noticia> listar() {
        List<Noticia> noticias = new ArrayList<>();
        Cursor cursor = abrir()
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
        fechar();
        return noticias;
    }

    @Override
    public Noticia buscar(Object key) {
        Cursor cursor = abrir()
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
        Noticia noticia = lerNoticiaDaTabela(cursor);
        fechar();
        return noticia;
    }

    @Override
    public void excluirVisualizada() {
        abrir().delete(
                "noticia",
                "visualizada = ?",
                new String[]{"1"});
        fechar();
    }

    @Override
    public void limpar(long fonte_id) {
        abrir().delete(
                "noticia",

                "fonte_id = ?",
                new String[]{""+fonte_id});
        fechar();
    }
}
