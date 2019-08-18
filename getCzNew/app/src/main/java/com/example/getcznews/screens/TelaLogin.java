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
import com.example.getcznews.domain.Usuario;
import com.example.getcznews.services.UsuarioService;

import java.lang.reflect.Array;

/*******************************************************
 * Classe responsável pela tela de login no sistema
 * Nesta tela também é possível criar um novo usuário
 * através do botão cadastrar
 *******************************************************/
public class TelaLogin extends TelaModeloInativo {

    private TextEdit teLogin;
    private TextEdit teSenha;

    public TelaLogin() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Criando os textos
        criarTitulo();
        criarTexto();
        //Criando os campos
        criarEditLogin();
        criarEditSenha();


        //LAYOUT DOS BOTÕES
        LinearLayout llBotoes = new LinearLayout(getRoot().getContext());
        llBotoes.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        getRoot().addView(llBotoes);

        //BOTÃO LOGAR
        Button btLogar = new Button(llBotoes.getContext());
        btLogar.setText("Logar");
        llBotoes.addView(btLogar);

        btLogar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       onLogar(teLogin.getValue(),teSenha.getValue());
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

    /***********************************************
     * Método responsável por criar o título da tela
     ***********************************************/
    private void criarTitulo() {
        //TITULO
        TextView tvTitulo = new TextView(getRoot().getContext());
        tvTitulo.setText("GetNews");
        tvTitulo.setTextSize(40);
        tvTitulo.setGravity(Gravity.CENTER_HORIZONTAL);
        getRoot().addView(tvTitulo);
    }

    /**************************************************
     * Método responsável pela crianção do texto da tela
     **************************************************/
    private void criarTexto() {
        //LOGIN
        TextView tvLogin = new TextView(getRoot().getContext());
        tvLogin.setText("Login");
        tvLogin.setGravity(Gravity.CENTER_HORIZONTAL);
        getRoot().addView(tvLogin);
    }

    /*****************************************************
     * Método responsável por criar o editLogin
     * Para facilitar o acesso durante a programação
     * o edit já vem preenchido
     * com o nome do usuário  default "eu"
     ****************************************************/
    private void criarEditLogin() {
        teLogin = new TextEdit(
                this,
                getRoot(),
                "Usuário"
        );
        teLogin.getEt().setText("eu");//Valor default
    }

    /*****************************************************
     * Método responsável por criar o editSenha
     * Para facilitar o acesso durante a programação
     * o edit já vem preenchido
     * com a senha do usuário  default "123"
     ****************************************************/
    private void criarEditSenha() {
        teSenha = new TextEdit(
                this,
                getRoot(),
                "Senha"
        );
        teSenha.getEt().setText("123");//Valor default
        teSenha.getEt().setTransformationMethod(new PasswordTransformationMethod());
    }

    /*******************************************************
     * Método responsável por realizar o login no sistema
     *******************************************************/
    private void onLogar(String login, String senha){

        if (usuarioService.logar(login,senha)){
            Toast.makeText(this, "Usuário logado com sucesso.",Toast.LENGTH_LONG).show();
            redirecionar();
        } else {
            Toast.makeText(this, "Usuário ou senha inválido",Toast.LENGTH_LONG).show();
        }

    }

    /*******************************************************
     * Método responsável por chamar a tela de cadastro
     *******************************************************/
    private void cadastro(){
        startActivity(new Intent(TelaLogin.this,TelaCadastro.class));
    }
}
