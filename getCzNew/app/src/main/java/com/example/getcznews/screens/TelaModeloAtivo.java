package com.example.getcznews.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.getcznews.TituloBox;

public abstract class TelaModeloAtivo extends TelaPadrao{

    private TituloBox tituloBox;

    protected TelaModeloAtivo() {
        super(true);
    }

    @Override
    protected void redirecionar() {
        startActivity(
                new Intent(this, TelaLogin.class)
        );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tituloBox = new TituloBox(getRoot().getContext(),"titulo");
        getRoot().addView(tituloBox);
    }

}
