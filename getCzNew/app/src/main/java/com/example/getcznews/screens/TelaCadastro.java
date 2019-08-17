package com.example.getcznews.screens;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getcznews.TextEdit;
import com.example.getcznews.services.UsuarioService;

public class TelaCadastro extends TelaModeloInativo {

    public TelaCadastro(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        //CADASTRO
        TextView tvCadastro = new TextView(root.getContext());
        tvCadastro.setText("Cadastre-se");
        tvCadastro.setGravity(Gravity.CENTER_HORIZONTAL);
        root.addView(tvCadastro);

        //NOME
        final TextEdit tvNome = new TextEdit(this,root,"Nome");

        //LOGIN
        final TextEdit tvLogin = new TextEdit(this,root,"Login");

        //SENHA
        final TextEdit tvSenha = new TextEdit(this,root,"Senha");
        tvSenha.getEt().setTransformationMethod(new PasswordTransformationMethod());

        //LAYOUT DOS BOTÕES
        LinearLayout llBotoes = new LinearLayout(root.getContext());
        llBotoes.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        root.addView(llBotoes);

        Button btCadastrar = new Button(llBotoes.getContext());
        btCadastrar.setText("Cadastrar");
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarUsuario(
                        tvNome.getValue(),
                        tvLogin.getValue(),
                        tvSenha.getValue());
            }
        });

        llBotoes.addView(btCadastrar);
    }


    private void cadastrarUsuario(String nome, String login, String senha){

        if (usuarioService.criarUsuario(nome,login,senha)) {
            Toast.makeText(this, "Cadastro realizado com sucesso.", Toast.LENGTH_LONG).show();
            startActivity(
                    new Intent(this, TelaLogin.class)
            );
        } else {
            Toast.makeText(this, "Não foi possível realziar o cadastro.", Toast.LENGTH_LONG).show();
        }
    }

}
