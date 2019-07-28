package com.example.getcznews.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.Layout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.getcznews.R;
import com.example.getcznews.domain.Noticia;
import com.squareup.picasso.Picasso;

public class NoticiaView extends LinearLayout{

    private final int TAM_TEXTO  = 200;
    private final int TAM_TITULO = 12;
    private final int TAM_LINK = 32;

    private Noticia noticia;
    private ImageView imageView;

    private void init(){
        LinearLayout.LayoutParams p = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
        setPadding(30,30,30,30);
        setLayoutParams(p);


        setBackgroundColor(Color.WHITE);
        setOrientation(LinearLayout.HORIZONTAL);
        criarImagem();

        LinearLayout painelTexto = new LinearLayout(this.getContext());
        painelTexto.setOrientation(LinearLayout.VERTICAL);
        painelTexto.setPadding(30,0,0,0);

        this.addView(painelTexto);

        criarTitulo(painelTexto);
        criarTexto(painelTexto);
        criarOrigem(painelTexto);
    }

    public NoticiaView(Context context, Noticia noticia) {
        super(context);
        this.noticia = noticia;
        init();
    }

    /********************************************
     * Criando o título da notícia no ListView
     ********************************************/
    private void criarTitulo(LinearLayout linearLayout){

        TextView titulo = criarTextView(
                subTexto(noticia.getTitulo(),TAM_TITULO),
                linearLayout);
        titulo.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        titulo.setTextSize(14);
    }

    /****************************************
     * Criando o texto da notícia no listView
     ****************************************/
    private void criarTexto(LinearLayout linearLayout){
        TextView texto = criarTextView(
                subTexto(noticia.getTexto(),TAM_TEXTO),
                linearLayout);
        texto.setTextSize(12);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            texto.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
    }

    /************************************************
     * Criando o link da origem da notícia
     *************************************************/
    private void criarOrigem(LinearLayout linearLayout){
        String texto = "Origem: <"+subTexto(noticia.getLink(),TAM_LINK)+">";
        TextView origem = criarTextView(
                texto,
                linearLayout);
        origem.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        origem.setTextSize(10);
    }

    /*******************************************
     * Criação do TextView
     *********************************************/
    private TextView criarTextView(String texto, LinearLayout linearLayout){
        TextView textView = new TextView(linearLayout.getContext());
        textView.setText(texto);
        linearLayout.addView(textView);
        return textView;
    }

    /**************************************
     * Criando a imagem da notícia
     **************************************/
    private void criarImagem(){

        imageView = new ImageView(this.getContext());

        Picasso.get()
                .load(noticia.getUrlImage())
                .centerCrop()
                .resize(200,200)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView);

        this.addView(imageView);
    }

    /*****************************************
     * Cortando o texto de acordo com o tamanho informado
     *****************************************/
    private String subTexto(String texto, int tamanho){
        int tamTexto = texto.length();
        if (tamTexto < tamanho)
            return texto;
        tamanho = tamanho > 3?tamanho -3: tamanho;
        return texto.substring(0,tamanho - 3) + "..." ;
    }

}
