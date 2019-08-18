package com.example.getcznews.services;

import android.content.Context;
import android.security.keystore.UserPresenceUnavailableException;

import com.example.getcznews.controler.Login;
import com.example.getcznews.dao.UsuarioDAO;
import com.example.getcznews.dao.UsuarioDAOImpl;
import com.example.getcznews.domain.Usuario;

import java.util.Objects;


/*****************************************
 * Classe de Serviços do usuário.
 * Implentação da regra de negócios
 * do usuário na aplicação
 ********************************************/
public class UsuarioService {

    private final int TAM_NOME = 4;
    private final int TAM_LOGIN = 4;
    private final int TAM_SENHA = 4;

    private UsuarioDAO usuarioDAO;

    public UsuarioService(Context context) {
        usuarioDAO = new UsuarioDAOImpl(context);
    }

    /*********************************************
     * Método que retorna se o usuário está logado
     *********************************************/
    public boolean isLogado(){
        return Login.getInstance().isLogado();
    }

    /**********************************************
     * Retorna o usuário logado.
     * Caso não esteja logado o método
     * retorna um usuário vazio
     **********************************************/
    public Usuario getUsuarioLogado(){
        Usuario usuario = Login.getInstance().getUsuario();
        if (usuario == null)
            usuario = new Usuario();
        return usuario;
    }

    /*************************************************
     * Método que realiza o login do usuário no
     * sistema e retorna se o procedimento
     * teve sucesso ou não.
     ****************************************************/
    public boolean logar(String login, String senha){
        return Login.getInstance().logar(usuarioDAO,login,senha) != null;
    }

    /**********************************
     * Método para realizar o logoff do usuário
     ***************************************/
    public void sair(){
        Login.getInstance().sair();
    }

    /**************************************
     * Método que cria um novo usuário
     **************************************/
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

    /*************************************
     * Método para editar o usuário logado
     *************************************/
    public boolean editarUsuario(String nome, String login, String senha){
        if (nome == null || senha == null || login == null)
            return false;
        if (nome.length() < TAM_NOME || senha.length() < TAM_SENHA || login.length() < TAM_LOGIN)
            return false;

        Usuario usuario = usuarioDAO.buscarPeloLogin(login);

        if( usuario != null && usuario.getId() != getUsuarioLogado().getId()){
            return false;
        }
        usuario = getUsuarioLogado();
        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuarioDAO.editar(usuario);
        return true;
    }

    /***************************
     * Método para desativar o perfil do
     * usuário logado
     ****************************/
    public void desativarPerfil(){
        if (!isLogado())
            return;
        usuarioDAO.remover(getUsuarioLogado());
        sair();
    }


}
