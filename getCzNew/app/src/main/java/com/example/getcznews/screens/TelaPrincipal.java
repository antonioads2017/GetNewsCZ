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

/************************************************************
 * Está é a tela principal do aplicativo.
 * Nela é apresentada a lista de notícias
 * e a menu superior onde está localizado
 *  o menu de interação para:
 * - Atualizar o dados do usuário
 * - Desativar o perfil do usuário
 * - Sair(Fazer logoff da aplicação)
 */
public class TelaPrincipal extends TelaModeloAtivo {
    /**********************************************************
     *Nesta constante é informado o tempo entre as atualizações
     * o valor inteiro corresponde a milisegundos 1/1000 segundos
     * o tempo defaul é 300000 milisegundos que corresponde a 5 minutos
    ***********************************************************/
    private static final int DELAY_TIME_WEB = 60000;

    private final String TITULO_LISTA_NOTICIA = "Lista de notícias atualizada em: ";
    private ListView lvNoticias;
    private TextView tvTitulo;

    /**************************************************************
     * Aqui é iniciado o serviço do temporizado para atualiza o feed
     * dos sites cadastrados.
     * É informado o tempo entre as atualização através da variável
     * DELAY_TIME_WEB
     ****************************************************************/
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

    /**************************************
     * Método para criar o título da tela
     **************************************/
    private void criarTitulo(){
        tvTitulo = new TextView(getRoot().getContext());
        tvTitulo.setGravity(Gravity.CENTER);
        getRoot().addView(tvTitulo);
    }

    /******************************************
     * Método para criar a lista de notícicas
     ******************************************/
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

    /********************************************
     *Método que faz a conversão da data e hora atual
     * para String que é o retorno do método
     *******************************************/
    private String getDataComoString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date dataAtual = cal.getTime();
        return dateFormat.format(dataAtual);
    }

    /*******************************************************
     * Método que realiza a atualização da lista de notícias
     * da tela principal do aplicativo.
     * ESTE MÉTODO É PUBLICO!!!!!!!!!!!!!
     * ELE É CHAMADO PELA CLASSE TIMEVIEW!!!!!!!!!!!!!!!!!!
     *******************************************************/
    public void atualizarLista(){

        //Atualizando o titulo da lista de notícias
        tvTitulo.setText(TITULO_LISTA_NOTICIA + getDataComoString());

        List<Noticia> noticias = new NoticiaService(this).getListaNoticias();

        AdapterNoticiaPersonalizado adapter = new AdapterNoticiaPersonalizado(noticias,this);
        lvNoticias.setAdapter(adapter);

    }

    /********************************************************
     * Método que é chamado quando alguma notícia é clicada
     * na lista de notícias.
     * Este método chama a tela TelaVerNotícia
     ********************************************************/
    private void onClickItem(long id){
        Intent intent = new Intent(this, TelaVerNoticia.class);
        intent.putExtra(TelaVerNoticia.EXTRA_MESSAGE,id);
        startActivity(intent);
    }

}
