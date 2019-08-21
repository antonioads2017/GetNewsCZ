package com.example.getcznews.services;

import android.content.Context;
import android.util.Log;

import com.example.getcznews.dao.FonteDAO;
import com.example.getcznews.dao.FonteDAOImpl;
import com.example.getcznews.dao.NoticiaDAO;
import com.example.getcznews.dao.NoticiaDAOImpl;
import com.example.getcznews.services.feed.FeedParaNoticiasParaibaTotal;
import com.example.getcznews.services.feed.FeedParaNoticiasValeDoPianco;

import java.util.Timer;
import java.util.TimerTask;


/****************************************
 * Classe responsável pelo TIME entre os
 * acessos ao Feed de notícias do site
 * e a partir desta classe é solicitada a
 * atualização da lista de notícias
 * (através da classe TIMEVIEW !!!!!!!!!!!!)
 *
 * Esta classe é inciada no bloco STATIC
 * da clase TelaPrincipal
 *
 *****************************************/
public class TimeWeb extends TimerTask {

    private static final Long TEMPO = 30000L;

    //Este atributo evita um solicitação ante do encerramento da anterior
    private boolean ocupado;
    private static int deleyTimeWeb;

    /***************************************
     * Método para inicial o temporizador
     * entre as requisições de recuperação de
     * notícias no feed dos sítes
     *****************************************/
    public static void init(int _deleyTimeWeb){
        deleyTimeWeb = _deleyTimeWeb;
        new Timer().schedule(new TimeWeb(),TEMPO, deleyTimeWeb);
    }

    public TimeWeb() {
        this.ocupado = false;
    }

    /********************************************
     * ---MÉTODO FUNDAMENTAL DE TODA A LÓGICA DA APLICAÇÃO ------
     * Método invocado no período de tempo definido pelo "deleyTimeWeb"
     *
     * Aqui é iniciada a solicitação para atualiza notícias pela web
     *********************************************/
    public void run(){
        if (ocupado) return;
        ocupado = true; //Início do estado ocupado
        Context context = TimeView.getPrincipal();
        if(context != null)
            atualizarNoticias(context);

        Log.e("Chamou ","Agora pela web");
        ocupado = false;//Final do estao ocupado
    }

    /*******************************************
     * Método que realiza a atualização dos feed
     * a partir das fontes cadastradas na
     * base de dados
     *
     * Ao final solicita a atualização da
     * lista de notícias na Tela principal
     *******************************************/
    private static void atualizarNoticias(Context context){
        NoticiaDAO noticiaDAO = new NoticiaDAOImpl(context);
        FonteDAO fonteDAO = new FonteDAOImpl(context);
        new FeedParaNoticiasValeDoPianco(noticiaDAO,fonteDAO.buscar(1));
        new FeedParaNoticiasParaibaTotal(noticiaDAO,fonteDAO.buscar(2));
        //TimeView.run();

    }
}
