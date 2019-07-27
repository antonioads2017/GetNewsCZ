package com.example.getcznews.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.getcznews.ListNews;

import java.util.ArrayList;
import java.util.List;

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

        List<ListNews> listNews = new ArrayList<>();

        for (int x = 0; x < 30; ++x){;
            ListNews ln = new ListNews(lvNoticias.getContext());
            ln.getTvTitulo().setText("Noticia " + String.valueOf(x +1));
            listNews.add(ln);
        }


        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listNews);
        lvNoticias.setAdapter(adapter);

        getRoot().addView(lvNoticias);

    }
}
