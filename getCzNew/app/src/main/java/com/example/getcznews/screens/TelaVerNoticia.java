package com.example.getcznews.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class TelaVerNoticia extends TelaModeloAtivo {

    public static final String EXTRA_MESSAGE = "com.example.getcznews.screens.TelaVerNoticia";

    private long idNoticia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idNoticia = getIntent().getLongExtra(EXTRA_MESSAGE,0);

        Log.e("Notícia",""+idNoticia);
    }
}
