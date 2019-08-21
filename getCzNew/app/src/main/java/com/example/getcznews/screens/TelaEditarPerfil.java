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
import android.widget.Toast;

import com.example.getcznews.TextEdit;
import com.example.getcznews.domain.Usuario;

public class TelaEditarPerfil extends  TelaModeloAtivo {

    private TextEdit tvNome;
    private TextEdit tvLogin;
    private TextEdit tvSenha;


    public TelaEditarPerfil() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getRoot().setPadding(100,50,100,50);

        criarTitulo();

        //NOME
        tvNome = new TextEdit(this,getRoot(),"Nome");
        tvNome.getEt().setText(usuarioService.getUsuarioLogado().getNome());

        //LOGIN
        tvLogin = new TextEdit(this,getRoot(),"Login");
        tvLogin.getEt().setText(usuarioService.getUsuarioLogado().getLogin());

        //SENHA
        tvSenha = new TextEdit(this,getRoot(),"Senha");
        tvSenha.getEt().setTransformationMethod(new PasswordTransformationMethod());
        tvSenha.getEt().setText(usuarioService.getUsuarioLogado().getSenha());

        criarBotoes();


    }

    private void criarBotoes() {
        //LAYOUT DOS BOTÕES
        LinearLayout llBotoes = new LinearLayout(getRoot().getContext());
        llBotoes.setOrientation(LinearLayout.VERTICAL);
        llBotoes.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );
        getRoot().addView(llBotoes);

        //BOTÃO PARA EDITAR O USUÁRIO
        Button btCadastrar = new Button(llBotoes.getContext());
        btCadastrar.setText("Editar");
        btCadastrar.setBackgroundColor(Color.parseColor("#5eb668"));
        btCadastrar.setTextColor(Color.WHITE);
        btCadastrar.setGravity(Gravity.CENTER_HORIZONTAL);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarUsuario(
                        tvNome.getValue(),
                        tvLogin.getValue(),
                        tvSenha.getValue());
            }
        });

        llBotoes.addView(btCadastrar);

    }

    /**************************************
     * Método para criar o título da tela
     ***************************************/
    private void criarTitulo() {
        //TITULO
        TextView tvTitulo = new TextView(getRoot().getContext());
        tvTitulo.setText("Editar Perfil");
        tvTitulo.setTextSize(30);
        tvTitulo.setGravity(Gravity.CENTER_HORIZONTAL);
        getRoot().addView(tvTitulo);
    }

    /*****************************************************************
     * Método para editar o usuário utilizando a classe usuarioService
     *****************************************************************/
    private void editarUsuario(String nome, String login, String senha){

       if (usuarioService.editarUsuario(nome,login,senha)) {
            Toast.makeText(this, "Edição do cadastro realizado com sucesso.", Toast.LENGTH_LONG).show();
            startActivity(
                    new Intent(this, TelaPrincipal.class)
            );
        } else {
            Toast.makeText(this, "Não foi possível realziar a edição do cadastro.", Toast.LENGTH_LONG).show();
        }
    }


}
