package com.example.getcznews.screens;


import android.content.Intent;

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
