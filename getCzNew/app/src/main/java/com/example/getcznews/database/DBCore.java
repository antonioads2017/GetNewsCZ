package com.example.getcznews.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCore extends SQLiteOpenHelper {

    private static final String NOME_DB = "getnewsdb";
    private static final int VERSAO_BD = 1;


    public DBCore(Context context) {
        super(context, NOME_DB, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String criarUsuario = "CREATE TABLE usuario(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL, " +
                " login TEXT NOT NULL UNIQUE, " +
                " senha TEXT NOT NULL);";

        String criarFonte = "CREATE TABLE fonte(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL, " +
                " site TEXT NOT NULL, " +
                " feed TEXT NOT NULL);";

        String criarNoticia = "CREATE TABLE noticia(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " fonte_id INTEGER, " +
                " titulo TEXT," +
                " texto TEXT," +
                " urlimage TEXT," +
                " visualizada INTEGER DEFAULT 0," +
                " CONSTRAINT noticia_fkey " +
                "   FOREIGN KEY (fonte_id) " +
                "   REFERENCES fonte(_id) " +
                "   ON DELETE CASCADE);";

        sqLiteDatabase.execSQL(criarUsuario);
        sqLiteDatabase.execSQL(criarFonte);
        sqLiteDatabase.execSQL(criarNoticia);

        //Criando os dados Default nas tabelas
        inserirDadosDefaul(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropUsuario = "DROP TABLE usuario";
        String dropFonte = "DROP TABLE fonte";
        String dropNoticia = "DROP TABLE noticia";
        sqLiteDatabase.execSQL(dropUsuario);
        sqLiteDatabase.execSQL(dropFonte);
        sqLiteDatabase.execSQL(dropNoticia);
        onCreate(sqLiteDatabase);

    }

    private void inserirDadosDefaul(SQLiteDatabase sqLiteDatabase){
        String[] scriptTabela = new String[]{
                "INSERT INTO usuario(nome, login, senha) VALUES ('Usuário Default','eu','123');",
                "INSERT INTO fonte(nome,site,feed) VALUES ('Diário do Sertão', 'https://www.diariodosertao.com.br', 'https://www.diariodosertao.com.br/feed/');",
                "INSERT INTO fonte(nome,site,feed) VALUES ('Coisas de Cajazeiras', 'https://www.diariodosertao.com.br', 'https://www.coisasdecajazeiras.com.br/feed/');",
                "INSERT INTO fonte(nome,site,feed) VALUES ('Jornal da Paraíba', 'https://www.diariodosertao.com.br', 'http://www.jornaldaparaiba.com.br/feed/');"
        };

        for (String script: scriptTabela) {
            sqLiteDatabase.execSQL(script);
        }

    }
}
