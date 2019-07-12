package com.example.getcznews.screens;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.LinearLayout;

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
