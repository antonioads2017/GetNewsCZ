package com.example.getcznews.screens;


import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getcznews.R;
import com.example.getcznews.TextEdit;
import com.example.getcznews.controler.Login;
import com.example.getcznews.domain.Usuario;
import com.example.getcznews.services.UsuarioService;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.reflect.Array;

/*******************************************************
 * Classe responsável pela tela de login no sistema
 * Nesta tela também é possível criar um novo usuário
 * através do botão cadastrar
 *******************************************************/
public class TelaLogin extends TelaModeloInativo {



    private ImageView logo;
    private TextEdit teLogin;
    private TextEdit teSenha;

    private LinearLayout llBotoes;
    private Button btLogar;
    private Button btCadastro;


    public TelaLogin() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getRoot().setPadding(100,50,100,50);
        //Criando os textos
        criarTitulo();
        criarTexto();
        //Criando os campos
        criarEditLogin();
        criarEditSenha();

        //CriandoBotões
        criarBotoes();
    }

    /***************************************
     * Método resposável por criar o botão
     **************************************/
    private void criarBotoes() {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(30, 20, 30, 0);


        //LAYOUT DOS BOTÕES
        llBotoes = new LinearLayout(getRoot().getContext());
        llBotoes.setOrientation(LinearLayout.VERTICAL);
        llBotoes.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );
        getRoot().addView(llBotoes);


        //BOTÃO LOGAR
        btLogar = new Button(llBotoes.getContext());
        btLogar.setText("Logar");
        btLogar.setBackgroundColor(Color.parseColor("#5eb668"));
        btLogar.setTextColor(Color.WHITE);
        btLogar.setGravity(Gravity.CENTER_HORIZONTAL);
        btLogar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onLogar(teLogin.getValue(),teSenha.getValue());
                    }
                }
        );
        llBotoes.addView(btLogar, layoutParams);


        //BOTÃO CADASTRAR
        btCadastro = new Button(llBotoes.getContext());
        btCadastro.setText("Cadastrar");
        btCadastro.setBackgroundColor(Color.parseColor("#5eb668"));
        btCadastro.setTextColor(Color.WHITE);
        btCadastro.setGravity(Gravity.CENTER_HORIZONTAL);
        btCadastro.setPadding(10,10,10,10);
        btCadastro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cadastro();
                    }
                }
        );
        llBotoes.addView(btCadastro, layoutParams);

    }

    /***********************************************
     * Método responsável por criar o título da tela
     ***********************************************/
    private void criarTitulo() {
       logo = new ImageView(this);
       logo.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.logoteste));
        getRoot().addView(logo);
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
//    Criar logo


    /*******************************************************
     * Método responsável por chamar a tela de cadastro
     *******************************************************/
    private void cadastro(){
        startActivity(new Intent(TelaLogin.this,TelaCadastro.class));
    }
}
