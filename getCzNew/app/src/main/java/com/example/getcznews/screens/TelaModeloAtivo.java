package com.example.getcznews.screens;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getcznews.R;
import com.example.getcznews.services.TimeView;

/***********************
 * TelaModeloAtivo
 * Esta classe é uma extenção de TelaPadrão.
 * Todas as telas que derem dela só poderão permanecer
 * caso  estejam Logado, caso contrário,
 * serão redirecionadas para a tela de Login
 *************************/

public abstract class TelaModeloAtivo extends TelaPadrao{

    /******************************************
     * Valores das ordens e identificadores do menu
     ******************************************/
    private final int ITEM_INDEX_MODIFICAR_DADOS     = 0;
    private final int ITEM_INDEX_DESATIVAR_PERFIL    = 1;
    private final int ITEM_INDEX_SAIR                = 2;

    /**********************************************
     * No contrutor desta classe o valor soLogado
     * será atribuído com 'true' ou seja,
     * Só é permitido a permanência nesta tela
     * caso o usuário esteja logado
     */
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
        ab.setSubtitle("Onde as notícias chegam primeiro.");
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5eb668")));
        ab.setIcon(R.drawable.small);
    }

    /*********************************************
     * Método de criação do menu de opções
     * Ítens do menu:
     *  - ITEM_INDEX_MODIFICAR_DADOS
     *  - ITEM_INDEX_DESATIVAR_PERFIL
     *  - ITEM_INDEX_SAIR
     **********************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
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

    /*******************************************************************
     * Método que faz o tratamento após algum item do menu seja clicado
     *******************************************************************/
    @Override
    public boolean onMenuItemSelected(int panel, MenuItem item){
        switch (item.getItemId()){
            //Campo voltar lateral esqueda do acvitveBar
            case android.R.id.home:
                startActivity(
                        new Intent(this, TelaPrincipal.class)
                );
                Toast.makeText(this, "Home",Toast.LENGTH_SHORT).show();
                break;
            //Item Menu modificar dados
            case ITEM_INDEX_MODIFICAR_DADOS:
                Toast.makeText(this, "Modificar Dados",Toast.LENGTH_SHORT).show();
                onEditarPerfil();
                break;
            // Item desativar perfil
            case ITEM_INDEX_DESATIVAR_PERFIL:
                Toast.makeText(this, "Desativar Perfil",Toast.LENGTH_SHORT).show();
                onDesativarPerfil();
                break;
            //Item fazer logoff do aplicativo
            case ITEM_INDEX_SAIR:
                Toast.makeText(this, "Sair",Toast.LENGTH_SHORT).show();
                onLogoff();
                break;
        }
        return true;
    }

    /*************************************
     * Método chamado ao clicar no item:
     *  - Desativar Perfil
     ************************************/
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

    /*************************************
     * Método chamado ao clicar no item:
     *  - Editar Perfil
     ************************************/
    private  void onEditarPerfil(){
        startActivity(
                new Intent(this, TelaEditarPerfil.class)
        );
    }

    /*************************************
     * Método chamado ao clicar no item:
     *  - Sair
     ************************************/
    private void onLogoff(){
        usuarioService.sair();
        redirecionar();
    }

}

