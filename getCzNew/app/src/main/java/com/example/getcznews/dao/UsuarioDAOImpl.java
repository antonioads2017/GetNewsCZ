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

    private String[] colunas;

    private Usuario lerUsuarioDaTebela(Cursor cursor){
        return new Usuario(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
    }

    public UsuarioDAOImpl(Context context) {
        dataBase = new DBCore(context).getWritableDatabase();
        colunas = new String[]{"_id","nome","login", "senha"};
    }

    @Override
    public void salvar(Usuario object) {
        ContentValues valores = new ContentValues();
        valores.put("nome", object.getNome());
        valores.put("login", object.getLogin());
        valores.put("senha", object.getSenha());
        dataBase.insert("usuario",null, valores);
    }

    @Override
    public void editar(Usuario object) {
        ContentValues valores = new ContentValues();
        valores.put("nome",object.getNome());
        valores.put("login",object.getLogin());
        valores.put("senha",object.getSenha());
        dataBase.update(
                "usuario",
                valores,
                "_id = ?",
                new String[]{""+object.getId()});
    }

    @Override
    public void remover(Usuario object) {
        dataBase.delete(
                "usuario",
                "_id=?",
                new String[]{""+object.getId()});
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = dataBase
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
        return usuarios;

    }

    @Override
    public Usuario buscar(Object key) {
        Cursor cursor = dataBase
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

        return lerUsuarioDaTebela(cursor);

    }

    @Override
    public Usuario buscarPeloLogin(String login) {
        Cursor cursor = dataBase
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

        return lerUsuarioDaTebela(cursor);
    }
}
