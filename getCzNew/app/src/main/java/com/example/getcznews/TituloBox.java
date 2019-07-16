package com.example.getcznews;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TituloBox extends LinearLayout {


    private final String name;
    private String titulo = "GetNews";
    private LinearLayout llTitulo;
    private Button btMenuTitulo;
    private TextView tvTitulo;


    public TituloBox(Context context, String name) {
        super(context);
        this.name = name;
        this.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
//        this.setBackgroundColor(Color.RED);
        this.setOrientation(LinearLayout.HORIZONTAL);
        criarTitulo();
    }

    private void criarTitulo(){
        llTitulo = new LinearLayout(this.getContext());
        llTitulo.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        this.addView(llTitulo);

        //BOTÂO MENU
        btMenuTitulo = new Button(llTitulo.getContext());
        btMenuTitulo.setText("Menu");
        llTitulo.addView(btMenuTitulo);

        //TITULO
        tvTitulo = new TextView(llTitulo.getContext());
        tvTitulo.setText(titulo);
        tvTitulo.setTextSize(40);
        tvTitulo.setGravity(Gravity.CENTER_HORIZONTAL);
        llTitulo.addView(tvTitulo);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        tvTitulo.setText(titulo);
    }
}
