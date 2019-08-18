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

/*************************************************************
 * Classe responsável pela tela de cadastro do usuário
 ************************************************************/
public class TelaCadastro extends TelaModeloInativo {

    public TelaCadastro(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        criarTitulo();
        criarTexto();

        //NOME
        final TextEdit tvNome = new TextEdit(this,getRoot(),"Nome");

        //LOGIN
        final TextEdit tvLogin = new TextEdit(this,getRoot(),"Login");

        //SENHA
        final TextEdit tvSenha = new TextEdit(this,getRoot(),"Senha");
        tvSenha.getEt().setTransformationMethod(new PasswordTransformationMethod());

        //LAYOUT DOS BOTÕES
        LinearLayout llBotoes = new LinearLayout(getRoot().getContext());
        llBotoes.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        getRoot().addView(llBotoes);
        //BOTAO PARA REALIZA O CADASTRO DO USUÁRIO
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


    /***************************************
     * Método para criar o título da página
     ****************************************/
    private void criarTitulo() {
        //TITULO
        TextView tvTitulo = new TextView(getRoot().getContext());
        tvTitulo.setText("GetNews");
        tvTitulo.setTextSize(40);
        tvTitulo.setGravity(Gravity.CENTER_HORIZONTAL);
        getRoot().addView(tvTitulo);
    }

    /*****************************************
     * Método para criar o texto da página
     ******************************************/
    private void criarTexto() {
        //CADASTRO
        TextView tvCadastro = new TextView(getRoot().getContext());
        tvCadastro.setText("Cadastre-se");
        tvCadastro.setGravity(Gravity.CENTER_HORIZONTAL);
        getRoot().addView(tvCadastro);
    }


    /***************************************************************************
     *Método que realiza o cadastro do usuário atraves da classe usuarioService
     *****************************************************************************/
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
