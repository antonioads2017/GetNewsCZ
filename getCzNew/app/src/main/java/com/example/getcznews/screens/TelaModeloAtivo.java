package com.example.getcznews.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.getcznews.R;
import com.example.getcznews.TituloBox;

/***********************
 * TelaModeloAtivo
 * Esta classe é uma extenção de TelaPadrão.
 * Todas as telas que derivam de dela só poderão permanecer
 * caso  esteja Logado.
 *************************/

public abstract class TelaModeloAtivo extends TelaPadrao{

    //Construção da classe atribindo o valor 'true' para soLogado
    protected TelaModeloAtivo() {
        super(true);

    }


    /****************************************************
     * Caso o médoto redirecionar() da classe TelaPadrão
     * Seja chamado, será redirecionado para TelaLogin
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



//        android.support.v7.widget.Toolbar toolbar = new Toolbar(this);
//        toolbar.setLayoutParams(getRoot().getLayoutParams());
//        toolbar.setPopupTheme(R.style.AppTheme);
//        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        toolbar.setTitle("This is the title");
//        toolbar.setVisibility(View.VISIBLE);
//        getRoot().addView(toolbar);
        //setSupportActionBar(toolbar);
//
//        tituloBox = new TituloBox(getRoot().getContext(),"titulo");
//        getRoot().addView(tituloBox);

//        ActionBar ab = getActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem m1 = menu.add(0,0,0,"Item 1");
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        MenuItem m2 = menu.add(0,1,1,"Item 2");
        m2.setIcon(R.drawable.ic_launcher_foreground);
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        MenuItem m3 = menu.add(0,2,2,"Item 3");
        m3.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem m4 = menu.add(0,3,3,"Item 4");
        m4.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int panel, MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Toast.makeText(this, "Home",Toast.LENGTH_SHORT).show();
                break;
            case 0:
                Toast.makeText(this, "Item 1",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "Item 2",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "Item 3",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "Item 4",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

}
