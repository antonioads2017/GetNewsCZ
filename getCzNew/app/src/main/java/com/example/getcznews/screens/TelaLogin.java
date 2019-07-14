package com.example.getcznews.screens;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.getcznews.TextEdit;
import com.example.getcznews.controler.Login;

public class TelaLogin extends TelaModeloInativo {

    public TelaLogin() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        soLogado = false;

        final LinearLayout root = new LinearLayout(this);
        root.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );

        root.setBackgroundColor(Color.GRAY);
        root.setOrientation(LinearLayout.VERTICAL);
        setContentView(root);

        //TITULO
        TextView tvTitulo = new TextView(root.getContext());
        tvTitulo.setText("GetNews");
        tvTitulo.setTextSize(40);
        tvTitulo.setGravity(Gravity.CENTER_HORIZONTAL);
        root.addView(tvTitulo);

        //LOGIN
        TextView tvLogin = new TextView(root.getContext());
        tvLogin.setText("Login");
        tvLogin.setGravity(Gravity.CENTER_HORIZONTAL);
        root.addView(tvLogin);

        //USUARIO
        TextEdit teUsuario = new TextEdit(
                this,
                root,
                "Usuário"
        );

        //SENHA
        TextEdit teSenha = new TextEdit(
                this,
                root,
                "Senha"
        );
        teSenha.getEt().setTransformationMethod(new PasswordTransformationMethod());


        //LAYOUT DOS BOTÕES
        LinearLayout llBotoes = new LinearLayout(root.getContext());
        llBotoes.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        root.addView(llBotoes);

        //BOTÃO LOGAR
        Button btLogar = new Button(llBotoes.getContext());
        btLogar.setText("Logar");
        llBotoes.addView(btLogar);

        btLogar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       onLogar();
                    }
                }
        );


        //BOTÃO CADASTRAR
        Button btCadastro = new Button(llBotoes.getContext());
        btCadastro.setText("Cadastrar");
        llBotoes.addView(btCadastro);

        btCadastro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cadastro();
                    }
                }
        );
    }

    private void onLogar(){
        Login.getInstance().logar("eu","123");
        redirecionar();
    }
    private void cadastro(){
        startActivity(new Intent(TelaLogin.this,TelaCadastro.class));
    }
}
