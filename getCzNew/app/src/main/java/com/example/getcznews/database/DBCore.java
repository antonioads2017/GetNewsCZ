package com.example.getcznews.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/****************************************************
 * Classe responsável por realizar a conexão com
 * a base de dados do SQLite e sobre o versionamento
 * desta base de dados do aplicativo
 ****************************************************/

public class DBCore extends SQLiteOpenHelper {

    private static final String NOME_DB = "getnewsdb";
    private static final int VERSAO_BD = 1;


    public DBCore(Context context) {
        super(context, NOME_DB, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Script de criação da tabela usuário
        String criarUsuario = "CREATE TABLE usuario(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL, " +
                " login TEXT NOT NULL UNIQUE, " +
                " senha TEXT NOT NULL);";
        //Script de criação da fonte da notícia
        String criarFonte = "CREATE TABLE fonte(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL, " +
                " site TEXT NOT NULL, " +
                " feed TEXT NOT NULL);";
        //Script de criação da notícia
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

        //Execução dos script de criação das tabelas
        sqLiteDatabase.execSQL(criarUsuario);
        sqLiteDatabase.execSQL(criarFonte);
        sqLiteDatabase.execSQL(criarNoticia);

        //Criando os dados Default nas tabelas
        inserirDadosDefaul(sqLiteDatabase);
    }

    /*****************************************************************
     * Método responsável por realizar o versionamento da base de dados
     * do aplicativo, sendo chamado quando a versão for INCREMENTADA
     * ou na criação da base de dados
     ******************************************************************/
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

    /*****************************************************************
     * Método responsável pelo povoamento inicial da base de dados
     ******************************************************************/
    private void inserirDadosDefaul(SQLiteDatabase sqLiteDatabase){
        String[] scriptTabela = new String[]{
                "INSERT INTO usuario(nome, login, senha) VALUES ('Usuário Default','eu','123');",
                "INSERT INTO fonte(_id, nome,site,feed) VALUES (1, 'Vale do Piancó Notícias', 'http://www.valedopianconoticias.com.br', 'http://www.valedopianconoticias.com.br/noticias/index.rss');",
                "INSERT INTO fonte(_id, nome,site,feed) VALUES (2, 'Coisas de Cajazeiras', 'https://www.diariodosertao.com.br', 'https://www.coisasdecajazeiras.com.br/feed/');",
                "INSERT INTO fonte(_id, nome,site,feed) VALUES (3, 'Jornal da Paraíba', 'https://www.diariodosertao.com.br', 'http://www.jornaldaparaiba.com.br/feed/');"
//                "INSERT INTO noticia(fonte_id,titulo,texto,urlimage)" +
//                        "VALUES (1,'Titulo da notícia 1','Texto da notícia 1','http://s2.glbimg.com/SdxxinMsy8zFCv2Oa0d3-jxcnbo=/0x0:2048x1365/695x463/s.glbimg.com/po/tt2/f/original/2016/10/26/img_0126_1.jpg');",
//                "INSERT INTO noticia(fonte_id,titulo,texto,urlimage)" +
//                        "VALUES (2,'Titulo da notícia 2','Texto da notícia 2','https://www.dicasdemulher.com.br/wp-content/uploads/2018/06/feed-do-instagram-12.jpg');",
//                "INSERT INTO noticia(fonte_id,titulo,texto,urlimage)" +
//                        "VALUES (3,'Titulo da notícia 3','Texto da notícia 3','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNAT0qaNNhHK9WUA2UysDtCzb5xnGYr755HMAUJfogyCPs_c1t');"

        };

        for (String script: scriptTabela) {
            sqLiteDatabase.execSQL(script);
        }

    }
}
