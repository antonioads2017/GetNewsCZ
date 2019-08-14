package com.example.getcznews.screens;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.getcznews.R;
import com.example.getcznews.controler.Login;

/***********************
 * TelaModeloAtivo
 * Esta classe é uma extenção de TelaPadrão.
 * Todas as telas que derivam de dela só poderão permanecer
 * caso  esteja Logado.
 *************************/

public abstract class TelaModeloAtivo extends TelaPadrao{

    /******************************************
     * Valores das ordens e identificadores do menu
     ******************************************/
    private final int ITEM_INDEX_MODIFICAR_DADOS     = 0;
    private final int ITEM_INDEX_DESATIVAR_PERFIL    = 1;
    private final int ITEM_INDEX_SAIR                = 2;

    //Construção da classe atribindo o valor 'true' para soLogado
    protected TelaModeloAtivo() {
        super(true);
    }


    /****************************************************
     * Caso o médoto redirecionar() da classe TelaPadrão
     * seja chamado, será redirecionado para TelaLogin
     *****************************************************/
    @Override
    protected void redirecionar() {
        startActivity(
                new Intent(this, TelaLogin.class)
        );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        /*******************
         * Criação do Menu
         *******************/

        //Menu Modificar Dados
        MenuItem miModificarDados = menu.add(
                0,
                ITEM_INDEX_MODIFICAR_DADOS,
                ITEM_INDEX_MODIFICAR_DADOS,
                "Modificar Dados");
        miModificarDados.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        //Menu Desativar Perfil
        MenuItem miDesativarPerfil = menu.add(
                0,
                ITEM_INDEX_DESATIVAR_PERFIL,
                ITEM_INDEX_DESATIVAR_PERFIL,
                "DesativarPerfil");
        miDesativarPerfil.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        //Menu Sair
        MenuItem miSair = menu.add(
                0,
                ITEM_INDEX_SAIR,
                ITEM_INDEX_SAIR,
                "Sair");
        miSair.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
    }

    @Override
    public boolean onMenuItemSelected(int panel, MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(
                        new Intent(this, TelaPrincipal.class)
                );
                Toast.makeText(this, "Home",Toast.LENGTH_SHORT).show();
                break;
            case ITEM_INDEX_MODIFICAR_DADOS:
                Toast.makeText(this, "Modificar Dados",Toast.LENGTH_SHORT).show();
                break;
            case ITEM_INDEX_DESATIVAR_PERFIL:
                Toast.makeText(this, "Desativar Perfil",Toast.LENGTH_SHORT).show();
                break;
            case ITEM_INDEX_SAIR:
                Toast.makeText(this, "Sair",Toast.LENGTH_SHORT).show();
                onLogoff();
                break;
        }
        return true;
    }


    private void onLogoff(){
        Login.getInstance().sair();
        startActivity(
                new Intent(this, TelaLogin.class)
        );
    }

}

