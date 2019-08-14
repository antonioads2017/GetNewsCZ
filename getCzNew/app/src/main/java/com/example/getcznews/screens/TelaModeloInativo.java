package com.example.getcznews.screens;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/***********************
 * TelaModeloInativo
 * Esta classe é uma extenção de TelaPadrão.
 * Todas as telas que derivam de dela só poderão permanecer
 * caso não estja Logado.
 *************************/

public abstract class TelaModeloInativo extends TelaPadrao{

    //Construção da classe atribindo o valor 'false' para soLogado
    public TelaModeloInativo() {
        super(false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ocultando o ActionBar
        ActionBar ab = getActionBar();
        ab.hide();

    }

    /****************************************************
     * Caso o médoto redirecionar() da classe TelaPadrão
     * Seja chamado, será redirecionado para TelaPrincipal
     *****************************************************/
    @Override
    protected void redirecionar() {
        startActivity(
                new Intent(this, TelaPrincipal.class)
        );
    }
}
