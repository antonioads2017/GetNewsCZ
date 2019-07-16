package com.example.getcznews.screens;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.example.getcznews.controler.Login;

public abstract class TelaPadrao  extends Activity {

    protected boolean soLogado;
    private LinearLayout root;

    protected abstract void redirecionar();

    public TelaPadrao(boolean soLogado) {
        this.soLogado = soLogado;
        root = null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verificarLogin();

        root = new LinearLayout(this);

        root.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );

        root.setBackgroundColor(Color.WHITE);
        root.setOrientation(LinearLayout.VERTICAL);
        setContentView(root);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        verificarLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        verificarLogin();
    }

    private void verificarLogin(){
        boolean logado = Login.getInstance().isLogado();


        if (soLogado && !logado)
            redirecionar();

        if(!soLogado && logado)
            redirecionar();
    }

    public LinearLayout getRoot() {
        return root;
    }

    public void setRoot(LinearLayout root) {
        this.root = root;
    }
}
