package com.example.getcznews.dao;

import android.arch.lifecycle.AndroidViewModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.getcznews.database.DBCore;
import com.example.getcznews.domain.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private SQLiteDatabase dataBase;
    private Context context;

    private String[] colunas;

    private Usuario lerUsuarioDaTebela(Cursor cursor){
        return new Usuario(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
    }

    public UsuarioDAOImpl(Context context) {
        this.context = context;
//        dataBase = new DBCore(context).getWritableDatabase();
        colunas = new String[]{"_id","nome","login", "senha"};
    }

    private SQLiteDatabase abrir(){
        if (dataBase == null)
            dataBase = new DBCore(context).getWritableDatabase();
        return dataBase;
    }

    private void fechar(){
        if (dataBase != null) {
            dataBase.close();
            dataBase = null;
        }
    }


    @Override
    public void salvar(Usuario object) {
        ContentValues valores = new ContentValues();
        valores.put("nome", object.getNome());
        valores.put("login", object.getLogin());
        valores.put("senha", object.getSenha());
        abrir().insert("usuario",null, valores);
        fechar();
    }

    @Override
    public void editar(Usuario object) {
        ContentValues valores = new ContentValues();
        valores.put("nome",object.getNome());
        valores.put("login",object.getLogin());
        valores.put("senha",object.getSenha());
        abrir().update(
                "usuario",
                valores,
                "_id = ?",
                new String[]{""+object.getId()});
        fechar();
    }

    @Override
    public void remover(Usuario object) {
        abrir().delete(
                "usuario",
                "_id=?",
                new String[]{""+object.getId()});
        fechar();
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = abrir()
                .query(
                        "usuario",
                        colunas,
                        null,
                        null,
                        null,
                        null,
                        "nome ASC");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                usuarios.add(lerUsuarioDaTebela(cursor));
            } while (cursor.moveToNext());
        }
        fechar();
        return usuarios;

    }

    @Override
    public Usuario buscar(Object key) {
        Cursor cursor = abrir()
                .query(
                        "usuario",
                        colunas,
                        "_id = ?",
                        new String[]{""+key},
                        null,
                        null,
                        null);
        if (cursor.getCount() == 0)
            return new Usuario();
        cursor.moveToFirst();

        Usuario usuario = lerUsuarioDaTebela(cursor);
        fechar();

        return usuario;

    }

    @Override
    public Usuario buscarPeloLogin(String login) {
        Cursor cursor = abrir()
                .query(
                        "usuario",
                        colunas,
                        "login = ?",
                        new String[]{"" + login},
                        null,
                        null,
                        null);
        if (cursor.getCount() == 0)
            return null;
        cursor.moveToFirst();

        Usuario usuario = lerUsuarioDaTebela(cursor);
        fechar();

        return usuario;
    }
}
