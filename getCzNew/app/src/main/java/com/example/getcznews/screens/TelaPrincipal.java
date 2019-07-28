package com.example.getcznews.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.example.getcznews.adapter.AdapterNoticiaPersonalizado;
import com.example.getcznews.domain.Noticia;

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

        List<Noticia> noticias = new ArrayList<>();

        for (int x = 0; x < 30; ++x){
            noticias.add(
                    new Noticia(
                            x,
                            "Título "+ String.valueOf(x +1),
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tristique luctus magna eget iaculis. Pellentesque scelerisque orci ultrices elementum bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Maecenas purus sapien, vestibulum eget tincidunt ac, finibus vitae est. Quisque bibendum condimentum arcu, vel facilisis eros fringilla quis. Aliquam sed nibh ut mauris ultrices ullamcorper quis a ante. Maecenas in mauris dictum, mollis lectus id, fermentum est. Vivamus dictum ornare nunc sed condimentum. Quisque vel lacus diam. Quisque sem elit, auctor sit amet nisl a, accumsan tincidunt felis.",
                            "https://www.phy.cam.ac.uk/students/teaching/teachingfiles/new.gif/image",
                            "https://g1.globo.com/",
                            false)
            );
        }


        AdapterNoticiaPersonalizado adapter = new AdapterNoticiaPersonalizado(noticias,this);
//        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,noticias);
        lvNoticias.setAdapter(adapter);

        getRoot().addView(lvNoticias);

    }
}
