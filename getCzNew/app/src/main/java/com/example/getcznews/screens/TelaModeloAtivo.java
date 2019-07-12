package com.example.getcznews.screens;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.getcznews.controler.Login;

public abstract class TelaModeloAtivo extends Activity {

    private  LinearLayout root;
    private LinearLayout llTitulo;
    private Button btMenuTitulo;

    protected TelaModeloAtivo() {
        root = null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        root =  new LinearLayout(this);

        //VERIFICAR O LOGIN
        verificarLogin();

        root.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );

        root.setBackgroundColor(Color.GRAY);
        root.setOrientation(LinearLayout.VERTICAL);
        setContentView(root);

        criarTitulo();
    }

    private void verificarLogin(){
        if (!Login.getInstance().isLogado())
            startActivity(
                    new Intent(this, TelaLogin.class)
            );
    }

    private void criarTitulo(){

        llTitulo = new LinearLayout(root.getContext());
        llTitulo.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );
        root.addView(llTitulo);

        //BOTÂO MENU
        btMenuTitulo = new Button(llTitulo.getContext());
        btMenuTitulo.setText("Menu");
        llTitulo.addView(btMenuTitulo);

        //TITULO
        TextView tvTitulo = new TextView(llTitulo.getContext());
        tvTitulo.setText("GetNews");
        tvTitulo.setTextSize(40);
        tvTitulo.setGravity(Gravity.CENTER_HORIZONTAL);
        llTitulo.addView(tvTitulo);


    }

    public LinearLayout getRoot() {
        return root;
    }

    public void setRoot(LinearLayout root) {
        this.root = root;
    }

    public LinearLayout getLlTitulo() {
        return llTitulo;
    }

    public void setLlTitulo(LinearLayout llTitulo) {
        this.llTitulo = llTitulo;
    }

    public Button getBtMenuTitulo() {
        return btMenuTitulo;
    }

    public void setBtMenuTitulo(Button btMenuTitulo) {
        this.btMenuTitulo = btMenuTitulo;
    }
}
