package com.example.getcznews.screens;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.SpannableString;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.getcznews.R;
import com.example.getcznews.domain.Noticia;
import com.example.getcznews.services.NoticiaService;
import com.squareup.picasso.Picasso;

public class TelaVerNoticia extends TelaModeloAtivo {

    public static final String EXTRA_MESSAGE = "com.example.getcznews.screens.TelaVerNoticia";


    private final int LARGURA_IMAGEM = 300;
    private final int ALTURA_IMAGEM = 300;


    private Noticia noticia;

    private TextView tvTitulo;
    private TextView tvFonte;
    private TextView tvCorpo;
    private ImageView ivFoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long idNoticia = getIntent().getLongExtra(EXTRA_MESSAGE,0);
        noticia = new NoticiaService(this).getNoticiaId(idNoticia);
        criarTitulo();
        criarFonte();
        criarImagem();
        criarCorpo();
    }

    private void criarTitulo() {
        tvTitulo = new TextView(getRoot().getContext());
        tvTitulo.setText(noticia.getTitulo());
        tvTitulo.setTextSize(14);
        tvTitulo.setGravity(Gravity.CENTER_HORIZONTAL);
        tvTitulo.setTypeface(null, Typeface.BOLD);
        getRoot().addView(tvTitulo);
    }

    private void criarFonte() {
        tvFonte = new TextView(getRoot().getContext());
        tvFonte.setText(noticia.getFonte().getNome());
        tvFonte.setTextSize(10);
        tvFonte.setGravity(Gravity.CENTER_HORIZONTAL);
        tvFonte.setTypeface(null, Typeface.ITALIC);
        getRoot().addView(tvFonte);
    }

    private void criarCorpo() {
        tvCorpo = new TextView(getRoot().getContext());
        tvCorpo.setText(noticia.getTexto());
        tvCorpo.setTextSize(12);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvCorpo.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
        getRoot().addView(tvCorpo);
    }

    private void criarImagem() {
        ivFoto = new ImageView(getRoot().getContext());

        Picasso.get()
                .load(noticia.getUrlImage())
                .centerCrop()
                .resize(LARGURA_IMAGEM,ALTURA_IMAGEM)
                .error(R.drawable.ic_launcher_foreground)
                .into(ivFoto);
        getRoot().addView(ivFoto);
    }

    }
