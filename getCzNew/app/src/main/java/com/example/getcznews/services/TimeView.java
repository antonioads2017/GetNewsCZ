package com.example.getcznews.services;

import android.widget.Toast;

import com.example.getcznews.screens.TelaPrincipal;

public class TimeView  {

    private static TelaPrincipal principal;

    static {
        principal = null;
    }

    public static void run() {
        if (principal == null) return;
        principal.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(principal, "Chamou!!!!",Toast.LENGTH_SHORT).show();
                principal.atualizarLista();
            }
        });

    }

    public static void setPrincipal(TelaPrincipal principal) {
        TimeView.principal = principal;
    }
    public static TelaPrincipal getPrincipal() {return principal;}
}
