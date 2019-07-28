package com.example.getcznews.views;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.getcznews.R;
import com.example.getcznews.domain.Noticia;

public class NoticiaView extends LinearLayout{

    private Noticia noticia;

    private void init(){
        LinearLayout.LayoutParams p = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
        setLayoutParams(p);

        setBackgroundColor(Color.GREEN);
        setOrientation(LinearLayout.VERTICAL);
        criarTexto(noticia.getTitulo());
        criarImagem();
        criarTexto(noticia.getTexto());
    }

    public NoticiaView(Context context, Noticia noticia) {
        super(context);
        this.noticia = noticia;
        init();
    }

    private void criarTexto(String texto){
        TextView textView = new TextView(this.getContext());
        textView.setText(texto);
        this.addView(textView);
    }

    private void criarImagem(){
        View v = new ImageView(getContext());
        ImageView imageView = new ImageView(this.getContext());
        imageView.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_launcher_foreground));
        this.addView(imageView);

    }


}
