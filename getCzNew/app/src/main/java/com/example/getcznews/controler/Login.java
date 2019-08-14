package com.example.getcznews.controler;

/****************************
 * Login
 * Classe Singleton que gerencia o login do usuário.
 *
 * Método isLogado que
 * retorna 'true' caso o usuário esteja logado
 * retorn 'false' caso o usuário não esteja logado
 *
 * Método logar efetua o login do usuário
 *****************/

public final class Login {

    private final String USUARIO = "eu";
    private final String SENHA = "123";

    private static Login instance = new Login();

    private boolean logado;

    private Login (){
        this.logado = false;
    }

    public static Login getInstance(){
        return instance;
    }

    public boolean isLogado(){
        return logado;
    }

    public boolean logar(String usuario, String senha){
        logado = usuario.equals(USUARIO) &&
                senha.equals(SENHA);
        return logado;
    }

    public void sair(){
        logado = false;
    }


}
