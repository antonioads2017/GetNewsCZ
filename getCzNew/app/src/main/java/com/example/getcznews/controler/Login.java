package com.example.getcznews.controler;

import android.content.Context;
import android.util.Log;

import com.example.getcznews.dao.UsuarioDAO;
import com.example.getcznews.domain.Usuario;

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

    private Usuario usuario;


    private static Login instance = new Login();

    private boolean logado;

    private Login (){sair();}

    public static Login getInstance(){
        return instance;
    }

    public boolean isLogado(){
        return logado;
    }

    public Usuario logar(UsuarioDAO usuarioDAO, String login, String senha){
        sair();

        if (usuarioDAO == null)
            return null;

        Usuario logUsuario = usuarioDAO.buscarPeloLogin(login);

        if (logUsuario == null)
            return null;

        if(!logUsuario.getSenha().equals(senha))
            return null;

        usuario = logUsuario;
        logado = true;

        return usuario;
    }

    public void sair(){
        usuario = null;
        logado = false;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
