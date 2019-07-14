package com.example.getcznews.screens;


import android.content.Intent;

public abstract class TelaModeloInativo extends TelaPadrao{


    public TelaModeloInativo() {
        super(false);
    }

    @Override
    protected void redirecionar() {
        startActivity(
                new Intent(this, TelaPrincipal.class)
        );
    }
}
