package com.example.getcznews.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.getcznews.TextEdit;

public class TelaPrincipal extends TelaModeloAtivo {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //USUARIO
        TextEdit teUsuario = new TextEdit(
                this,
                getRoot(),
                "Usuário"
        );
    }
}
