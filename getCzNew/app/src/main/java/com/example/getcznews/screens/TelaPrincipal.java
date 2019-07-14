package com.example.getcznews.screens;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.getcznews.TextEdit;

public class TelaPrincipal extends TelaModeloAtivo {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TITULO
        TextView tvTitulo = new TextView(getRoot().getContext());
        tvTitulo.setText("Lista de Notícias");
        getRoot().addView(tvTitulo);

        //LISTVIEW

        ListView lvNoticias = new ListView(getRoot().getContext());

        String[] dados = {"Notícia 1", "Notícia 2", "Notícia 3", "Notícia 4", "Notícia 5"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dados);
        lvNoticias.setAdapter(adapter);

        getRoot().addView(lvNoticias);

    }
}
