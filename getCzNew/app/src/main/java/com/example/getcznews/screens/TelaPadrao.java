package com.example.getcznews.screens;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.example.getcznews.controler.Login;

/****************************************
 * TelaPadrao
 * Classe padrão para as tela do aplicativo
 * onde todas as tela do aplicativos serão
 * especializacões desta classe.
 *
 * Nesta classe existe a variável
 * soLogado que é um boolean.
 *
 * soLogado = False (não aceita permanecer
 * nesta tela quando logado).
 *
 * soLogado = True (não aceita permanecer
 * nesta tela quando não logado.
 ***************************************/

public abstract class TelaPadrao  extends Activity {

    //Variavel que atribui a caracteristica de permanencia na tela logado ou não
    protected boolean soLogado;
    //Variavel de Layout inicial de todas as telas
    private LinearLayout root;

    /*************************************
     * Esta classe utiliza o Padrão de Projeto TEMPLATE METHOD
     * onde a classe que instanciar deverá implementar este método.
     * Esta classe abstract deverá direcionar
     * para a tela específica quando o usuário
     * não puder permanecer devido o seu estado
     * de login.
     **************************************/
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
        //Verifica a possibilidade de permanencia na tela devido seu estado de login
        verificarLogin();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Verifica a possibilidade de permanencia na tela devido seu estado de login
        verificarLogin();
    }

    private void verificarLogin(){
        /***************************
         * Login
         *Classe Singleton que define o estado de login do usuário.
         * Retorna para a variável logado o estado de login atual
         **************************/
        boolean logado = Login.getInstance().isLogado();


        /***************************
         * Caso soLogado == True
         * Só é possível permanecer na tela entando logado
         * como o valor de !logado == True
         * Será redirecionando para a tela de Login (que será intacinada nas classe especialistas)
         ****************************/
        if (soLogado && !logado)
            redirecionar(); //Método abstract

        /***************************
         * Caso soLogado == False
         * Só é possível permanecer na tela NÃO entando logado
         * como o valor de logado == True
         * Será redirecionando para a tela Principal (que será instanciada nas classe especialistas)
         ****************************/
        if(!soLogado && logado)
            redirecionar(); //Método abstract
    }

    public LinearLayout getRoot() {
        return root;
    }

    public void setRoot(LinearLayout root) {
        this.root = root;
    }
}
