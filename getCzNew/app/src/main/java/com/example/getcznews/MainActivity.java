package com.example.getcznews;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.getcznews.controler.Login;
import com.example.getcznews.screens.TelaLogin;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        Log.d("AGDEBUG", "cade tu?");

        LinearLayout root = new LinearLayout(this);
        root.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )
        );

        root.setBackgroundColor(Color.RED);
        root.setOrientation(LinearLayout.VERTICAL);
        setContentView(root);

        //VERIFICAR O LOGIN
        verificarLogin();



    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarLogin();
    }

    private void verificarLogin(){
        if (!Login.getInstance().isLogado())
            startActivity(
                    new Intent(this, TelaLogin.class)
            );
    }
}
