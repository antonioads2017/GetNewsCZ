package com.example.getcznews.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.example.getcznews.adapter.AdapterNoticiaPersonalizado;
import com.example.getcznews.domain.Noticia;
import com.example.getcznews.services.NoticiaService;
import com.example.getcznews.services.TimeView;
import com.example.getcznews.services.TimeWeb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TelaPrincipal extends TelaModeloAtivo {

    private static final int DELAY_TIME_WEB = 5000;

    private final String TITULO_LISTA_NOTICIA = "Lista de notícias atualizada em: ";
    private ListView lvNoticias;
    private TextView tvTitulo;

    static{ TimeWeb.init(DELAY_TIME_WEB); }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        criarTitulo();
        criarListaNoticias();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TimeView.setPrincipal(this);
        atualizarLista();
    }

    @Override
    protected void onPause() {
        super.onPause();
        TimeView.setPrincipal(null);
    }

    private void criarTitulo(){
        tvTitulo = new TextView(getRoot().getContext());
        getRoot().addView(tvTitulo);
    }

    private void criarListaNoticias(){
        lvNoticias = new ListView(getRoot().getContext());
        getRoot().addView(lvNoticias);
    }

    private String getDataComoString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date dataAtual = cal.getTime();
        return dateFormat.format(dataAtual);
    }


    public void atualizarLista(){

        //Atualizando o titulo da lista de notícias
        tvTitulo.setText(TITULO_LISTA_NOTICIA + getDataComoString());

        List<Noticia> noticias = new NoticiaService(this).getListaNoticias();

//        List<Noticia> noticias = new ArrayList<>();
//
//        for (int x = 0; x < 13; ++x){
//            noticias.add(
//                    new Noticia(
//                            x,
//                            "Título "+ String.valueOf(x +1) + " " + Long.toString(System.currentTimeMillis()),
//                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tristique luctus magna eget iaculis. Pellentesque scelerisque orci ultrices elementum bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Maecenas purus sapien, vestibulum eget tincidunt ac, finibus vitae est. Quisque bibendum condimentum arcu, vel facilisis eros fringilla quis. Aliquam sed nibh ut mauris ultrices ullamcorper quis a ante. Maecenas in mauris dictum, mollis lectus id, fermentum est. Vivamus dictum ornare nunc sed condimentum. Quisque vel lacus diam. Quisque sem elit, auctor sit amet nisl a, accumsan tincidunt felis.",
//                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKj3fglpYyBCh6XAUkSCFXbX8x5yi7vHg-XCSv06Xr_XqEoCzk",
//                            "https://g1.globo.com/",
//                            false)
//            );
//        }

        AdapterNoticiaPersonalizado adapter = new AdapterNoticiaPersonalizado(noticias,this);
        lvNoticias.setAdapter(adapter);

    }

}
