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

        String criarLink = "CREATE TABLE link(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " site TEXT NOT NULL, " +
                " feed TEXT NOT NULL);";

        String criarNoticia = "CREATE TABLE noticia(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " link_id TEXT NOT NULL, " +
                " titulo TEXT," +
                " texto TEXT," +
                " urlimage TEXT," +
                " visualizada INTEGER DEFAULT 0," +
                " CONSTRAINT noticia_fkey " +
                "   FOREIGN KEY (link_id) " +
                "   REFERENCES link(_id) " +
                "   ON DELETE CASCADE);";

        sqLiteDatabase.execSQL(criarUsuario);
        sqLiteDatabase.execSQL(criarLink);
        sqLiteDatabase.execSQL(criarNoticia);

        //Criando os dados Default nas tabelas
        inserirDadosDefaul(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropUsuario = "DROP TABLE usuario";
        String dropLink = "DROP TABLE link";
        String dropNoticia = "DROP TABLE noticia";
        sqLiteDatabase.execSQL(dropUsuario);
        sqLiteDatabase.execSQL(dropLink);
        sqLiteDatabase.execSQL(dropNoticia);
        onCreate(sqLiteDatabase);

    }

    private void inserirDadosDefaul(SQLiteDatabase sqLiteDatabase){
        String[] scriptTabela = new String[]{
                "INSERT INTO usuario(nome, login, senha) VALUES ('Usuário Default','eu','123');",
                "INSERT INTO link(site,feed) VALUES ('Diário do Sertão','https://www.diariodosertao.com.br/feed/');",
                "INSERT INTO link(site,feed) VALUES ('Coisas de Cajazeiras','https://www.coisasdecajazeiras.com.br/feed/');",
                "INSERT INTO link(site,feed) VALUES ('Jornal da Paraíba','http://www.jornaldaparaiba.com.br/feed/');"
        };

        for (String script: scriptTabela) {
            sqLiteDatabase.execSQL(script);
        }

    }
}
