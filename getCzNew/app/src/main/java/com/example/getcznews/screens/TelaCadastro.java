package com.example.getcznews.screens;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getcznews.ButtonBox;
import com.example.getcznews.R;
import com.example.getcznews.TextEdit;
import com.example.getcznews.services.UsuarioService;

/*************************************************************
 * Classe responsável pela tela de cadastro do usuário
 ************************************************************/
public class TelaCadastro extends TelaModeloInativo {

    private TextEdit tvNome;
    private TextEdit tvLogin;
    private TextEdit tvSenha;
    private ImageView logo;
    private LinearLayout llBotoes;
    private Button btCadastrar;

    public TelaCadastro(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getRoot().setPadding(100,50,100,50);

        criarTitulo();
        criarTexto();


        //NOME
        tvNome = new TextEdit(this,getRoot(),"Nome");

        //LOGIN
        tvLogin = new TextEdit(this,getRoot(),"Login");

        //SENHA
        tvSenha = new TextEdit(this,getRoot(),"Senha");
        tvSenha.getEt().setTransformationMethod(new PasswordTransformationMethod());

        criarBotoes();


    }

    private void criarBotoes() {
        //LAYOUT DOS BOTÕES
        llBotoes = new LinearLayout(getRoot().getContext());
        llBotoes.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );
        llBotoes.setOrientation(LinearLayout.VERTICAL);

        getRoot().addView(llBotoes);

        //BOTAO PARA REALIZA O CADASTRO DO USUÁRIO
        btCadastrar = new Button(llBotoes.getContext());
        btCadastrar.setGravity(Gravity.CENTER_HORIZONTAL);
        btCadastrar.setTextColor(Color.WHITE);
        btCadastrar.setBackgroundColor(Color.parseColor("#5eb668"));
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
        logo = new ImageView(this);
        logo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.logoteste));
        getRoot().addView(logo);
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
