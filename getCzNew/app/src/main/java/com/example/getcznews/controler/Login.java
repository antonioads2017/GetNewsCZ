package com.example.getcznews.controler;

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


}
