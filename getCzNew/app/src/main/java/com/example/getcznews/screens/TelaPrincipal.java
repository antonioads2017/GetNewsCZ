package com.example.getcznews.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.example.getcznews.adapter.AdapterNoticiaPersonalizado;
import com.example.getcznews.domain.Noticia;
import com.example.getcznews.services.TimeView;
import com.example.getcznews.services.TimeWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class TelaPrincipal extends TelaModeloAtivo {

    private ListView lvNoticias;

    static{
        TimeWeb.init();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TITULO
        TextView tvTitulo = new TextView(getRoot().getContext());
        tvTitulo.setText("Lista de Notícias");
        getRoot().addView(tvTitulo);

        //LISTVIEW
        lvNoticias = new ListView(getRoot().getContext());




        getRoot().addView(lvNoticias);

    }

    @Override
    protected void onPause() {
        super.onPause();
        TimeView.setPrincipal(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TimeView.setPrincipal(this);
    }


    public void atualizarLista(){

        List<Noticia> noticias = new ArrayList<>();

        for (int x = 0; x < 3; ++x){
            noticias.add(
                    new Noticia(
                            x,
                            "Título "+ String.valueOf(x +1) + " " + Long.toString(System.currentTimeMillis()),
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tristique luctus magna eget iaculis. Pellentesque scelerisque orci ultrices elementum bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Maecenas purus sapien, vestibulum eget tincidunt ac, finibus vitae est. Quisque bibendum condimentum arcu, vel facilisis eros fringilla quis. Aliquam sed nibh ut mauris ultrices ullamcorper quis a ante. Maecenas in mauris dictum, mollis lectus id, fermentum est. Vivamus dictum ornare nunc sed condimentum. Quisque vel lacus diam. Quisque sem elit, auctor sit amet nisl a, accumsan tincidunt felis.",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKj3fglpYyBCh6XAUkSCFXbX8x5yi7vHg-XCSv06Xr_XqEoCzk",
                            "https://g1.globo.com/",
                            false)
            );
        }

        AdapterNoticiaPersonalizado adapter = new AdapterNoticiaPersonalizado(noticias,this);
        lvNoticias.setAdapter(adapter);

    }

}
