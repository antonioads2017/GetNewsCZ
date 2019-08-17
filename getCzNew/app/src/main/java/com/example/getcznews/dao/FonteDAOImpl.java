package com.example.getcznews.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.getcznews.database.DBCore;
import com.example.getcznews.domain.Fonte;

import java.util.ArrayList;
import java.util.List;

public class FonteDAOImpl implements FonteDAO {


    private SQLiteDatabase dataBase;

    private String[] colunas;

    private Fonte lerFonteDaTabela(Cursor cursor){
        return new Fonte(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
    }

    public FonteDAOImpl(Context context) {
        dataBase = new DBCore(context).getWritableDatabase();
        colunas = new String[]{
                "_id",
                "nome",
                "site",
                "feed"};
    }

    @Override
    public void salvar(Fonte object) {
        ContentValues valores = new ContentValues();
        valores.put("nome",object.getNome());
        valores.put("site",object.getSite());
        valores.put("feed",object.getFeed());
        dataBase.insert("feed",null,valores);
    }

    @Override
    public void editar(Fonte object) {
        ContentValues valores = new ContentValues();
        valores.put("nome",object.getNome());
        valores.put("site",object.getSite());
        valores.put("feed",object.getFeed());
        dataBase.update(
                "fonte",
                valores,
                "_id = ?",
                new String[]{""+object.getId()});
    }

    @Override
    public void remover(Fonte object) {
        dataBase.delete(
                "fonte",
                "_id = ?",
                new String[]{""+object.getId()});
    }

    @Override
    public List<Fonte> listar() {
        List<Fonte> fontes = new ArrayList<>();
        Cursor cursor = dataBase
                .query(
                        "fonte",
                        colunas,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                fontes.add(lerFonteDaTabela(cursor));
            }while(cursor.moveToNext());
        }
        return fontes;
    }

    @Override
    public Fonte buscar(Object key) {
        Cursor cursor = dataBase
                .query(
                        "fonte",
                        colunas,
                        "_id = ?",
                        new String[]{""+key},
                        null,
                        null,
                        null);
        if (cursor.getCount() == 0)
            return null;
        cursor.moveToFirst();
        return lerFonteDaTabela(cursor);
    }
}
