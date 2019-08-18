package com.example.getcznews.screens;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.getcznews.controler.Login;
import com.example.getcznews.services.TimeView;
import com.example.getcznews.services.UsuarioService;

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
        TimeView.setPrincipal(null);
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
                onEditarPerfil();
                break;
            case ITEM_INDEX_DESATIVAR_PERFIL:
                Toast.makeText(this, "Desativar Perfil",Toast.LENGTH_SHORT).show();
                onDesativarPerfil();
                break;
            case ITEM_INDEX_SAIR:
                Toast.makeText(this, "Sair",Toast.LENGTH_SHORT).show();
                onLogoff();
                break;
        }
        return true;
    }

    private void onDesativarPerfil() {

        new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Desativar Usuário")
                .setMessage("Deseja desativar o usuário:\n " + usuarioService.getUsuarioLogado().getNome())
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        usuarioService.desativarPerfil();
                        redirecionar();
                    }
                })
                .setNegativeButton("Não",null)
                .show();
    }


    private  void onEditarPerfil(){
        startActivity(
                new Intent(this, TelaEditarPerfil.class)
        );
    }

    private void onLogoff(){
        usuarioService.sair();
        redirecionar();
    }

}

