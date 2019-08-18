package com.example.getcznews.services;

import android.widget.Toast;

import com.example.getcznews.screens.TelaPrincipal;

/********************************************
 * Classe responsável pelo acesso a TelaPrincipal
 * pela classe "ASSÍNCRONA" TimeWeb.
 *
 * Nesta classe é desencadeada a solicitação para
 * atualiza a lista de notícias na TelaPrincipal
 ****************************************/
public class TimeView  {

    private static TelaPrincipal principal;

    static {
        principal = null;
    }

    /****************************************
     * Método responsável por solictar
     * a atualização da lista de notícias
     * na TelaPrincipal
     ****************************************/
    public static void run() {
        if (principal == null) return;
        principal.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(principal, "Atualizou!!!!",Toast.LENGTH_SHORT).show();
                principal.atualizarLista();
            }
        });

    }

    public static void setPrincipal(TelaPrincipal principal) {
        TimeView.principal = principal;
    }
    public static TelaPrincipal getPrincipal() {return principal;}
}
