package com.example.getcznews.dao;

import com.example.getcznews.domain.Usuario;

public interface UsuarioDAO extends DefaulDAO<Usuario> {

    Usuario buscarPeloLogin(String usuario);


}
