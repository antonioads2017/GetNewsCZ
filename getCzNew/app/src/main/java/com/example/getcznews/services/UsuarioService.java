package com.example.getcznews.services;

import android.content.Context;
import android.security.keystore.UserPresenceUnavailableException;

import com.example.getcznews.controler.Login;
import com.example.getcznews.dao.UsuarioDAO;
import com.example.getcznews.dao.UsuarioDAOImpl;
import com.example.getcznews.domain.Usuario;

import java.util.Objects;


public class UsuarioService {

    private final int TAM_NOME = 4;
    private final int TAM_LOGIN = 4;
    private final int TAM_SENHA = 4;

    private UsuarioDAO usuarioDAO;

    public UsuarioService(Context context) {
        usuarioDAO = new UsuarioDAOImpl(context);
    }

    public boolean isLogado(){
        return Login.getInstance().isLogado();
    }

    public Usuario getUsuarioLogado(){
        Usuario usuario = Login.getInstance().getUsuario();
        if (usuario == null)
            usuario = new Usuario();
        return usuario;
    }

    public boolean logar(String login, String senha){
        return Login.getInstance().logar(usuarioDAO,login,senha) != null;
    }

    public void sair(){
        Login.getInstance().sair();
    }

    public boolean criarUsuario(String nome, String login, String senha){
        if (nome == null || senha == null || login == null)
            return false;
        if (nome.length() < TAM_NOME || senha.length() < TAM_SENHA || login.length() < TAM_LOGIN)
            return false;
        if( usuarioDAO.buscarPeloLogin(login) != null) return false;

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);

        usuarioDAO.salvar(usuario);

        return true;
    }

    public void desativarPerfil(){
        if (!isLogado())
            return;
        usuarioDAO.remover(getUsuarioLogado());
        sair();
    }


}
