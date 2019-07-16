package com.example.getcznews.screens;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.getcznews.TextEdit;

public class TelaCadastro extends TelaModeloInativo {

    public TelaCadastro(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        soLogado=false;

        final LinearLayout root = new LinearLayout(this);
        root.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );

        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setBackgroundColor(Color.WHITE);
        root.setOrientation(LinearLayout.VERTICAL);
        setContentView(root);

        //TITULO
        TextView tvTitulo = new TextView(root.getContext());
        tvTitulo.setText("GetNews");
        tvTitulo.setTextSize(40);
        tvTitulo.setGravity(Gravity.CENTER_HORIZONTAL);
        root.addView(tvTitulo);

        //CADASTRO
        TextView tvCadastro = new TextView(root.getContext());
        tvCadastro.setText("Cadastre-se");
        tvCadastro.setGravity(Gravity.CENTER_HORIZONTAL);
        root.addView(tvCadastro);

        //NOME
        TextEdit tvNome = new TextEdit(this,root,"Nome");

        //USUARIO
        TextEdit tvUser = new TextEdit(this,root,"Usuário");

        //SENHA
        TextEdit tvSenha = new TextEdit(this,root,"Senha");
        tvSenha.getEt().setTransformationMethod(new PasswordTransformationMethod());

        //LAYOUT DOS BOTÕES
        LinearLayout llBotoes = new LinearLayout(root.getContext());
        llBotoes.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        llBotoes.setGravity(Gravity.CENTER_HORIZONTAL);
        root.addView(llBotoes);

        Button btCadastrar = new Button(llBotoes.getContext());
        btCadastrar.setText("Cadastrar");
        btCadastrar.setGravity(Gravity.CENTER_HORIZONTAL);
        llBotoes.addView(btCadastrar);




    }
}
