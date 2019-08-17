package com.example.getcznews.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getcznews.adapter.AdapterNoticiaPersonalizado;
import com.example.getcznews.domain.Noticia;
import com.example.getcznews.services.NoticiaService;
import com.example.getcznews.services.TimeView;
import com.example.getcznews.services.TimeWeb;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class TelaPrincipal extends TelaModeloAtivo {

    private static final int DELAY_TIME_WEB = 60000;

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
        tvTitulo.setGravity(Gravity.CENTER);
        getRoot().addView(tvTitulo);
    }

    private void criarListaNoticias(){
        lvNoticias = new ListView(getRoot().getContext());
        getRoot().addView(lvNoticias);

        lvNoticias.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        onClickItem(l);
                    }
                }
        );

    }

    private String getDataComoString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm");
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

        AdapterNoticiaPersonalizado adapter = new AdapterNoticiaPersonalizado(noticias,this);
        lvNoticias.setAdapter(adapter);

    }

    private void onClickItem(long id){

        Toast.makeText(this, "Clicou "+id+" !!!!!!!!!!", LENGTH_SHORT).show();

        Intent intent = new Intent(this, TelaVerNoticia.class);
        intent.putExtra(TelaVerNoticia.EXTRA_MESSAGE,id);
        startActivity(intent);
    }

}
