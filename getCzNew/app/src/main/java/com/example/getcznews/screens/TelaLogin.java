package com.example.getcznews.screens;

import android.app.ActionBar;
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
import android.widget.Toast;

import com.example.getcznews.TextEdit;
import com.example.getcznews.controler.Login;

import java.lang.reflect.Array;

public class TelaLogin extends TelaModeloInativo {

    private TextEdit teUsuario;
    private TextEdit teSenha;

    public TelaLogin() {}

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

        root.setBackgroundColor(Color.WHITE);
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
        teUsuario = new TextEdit(
                this,
                root,
                "Usuário"
        );

        teUsuario.getEt().setText("eu");

        //SENHA
        teSenha = new TextEdit(
                this,
                root,
                "Senha"
        );

        teSenha.getEt().setText("123");

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
        if (Login.getInstance().logar(
                teUsuario.getValue(),
                teSenha.getValue()))
            redirecionar();
        else
            Toast.makeText(this, "Usuário ou senha inválido",Toast.LENGTH_LONG).show();
    }
    private void cadastro(){
        startActivity(new Intent(TelaLogin.this,TelaCadastro.class));
    }
}
