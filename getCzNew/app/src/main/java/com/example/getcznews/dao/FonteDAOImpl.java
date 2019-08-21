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


    private Context context;
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
        this.context = context;
//        dataBase = new DBCore(context).getWritableDatabase();
        colunas = new String[]{
                "_id",
                "nome",
                "site",
                "feed"};
    }

    private SQLiteDatabase abrir(){
        if(dataBase == null)
            dataBase = new DBCore(context).getWritableDatabase();
        return dataBase;
    }

    private void fechar(){
        if(dataBase != null) {
            dataBase.close();
            dataBase = null;
        }
    }

    @Override
    public void salvar(Fonte object) {
        ContentValues valores = new ContentValues();
        valores.put("nome",object.getNome());
        valores.put("site",object.getSite());
        valores.put("feed",object.getFeed());
        abrir().insert("feed",null,valores);
        fechar();
    }

    @Override
    public void editar(Fonte object) {
        ContentValues valores = new ContentValues();
        valores.put("nome",object.getNome());
        valores.put("site",object.getSite());
        valores.put("feed",object.getFeed());
        abrir().update(
                "fonte",
                valores,
                "_id = ?",
                new String[]{""+object.getId()});
        fechar();
    }

    @Override
    public void remover(Fonte object) {
        abrir().delete(
                "fonte",
                "_id = ?",
                new String[]{""+object.getId()});
        fechar();
    }

    @Override
    public List<Fonte> listar() {
        List<Fonte> fontes = new ArrayList<>();
        Cursor cursor = abrir()
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
        fechar();
        return fontes;
    }

    @Override
    public Fonte buscar(Object key) {
        Cursor cursor = abrir()
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
        Fonte fonte = lerFonteDaTabela(cursor);
        fechar();
        return fonte;
    }
}
