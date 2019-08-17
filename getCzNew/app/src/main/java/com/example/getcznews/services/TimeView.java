package com.example.getcznews.services;

import android.widget.Toast;

import com.example.getcznews.screens.TelaPrincipal;

import java.util.Timer;
import java.util.TimerTask;

public class TimeView extends TimerTask {

    private static TelaPrincipal principal;
    private RodarPulso rodarPulso;

    public TimeView() {
        this.rodarPulso = new RodarPulso();
    }

    static {
        principal = null;
//        new Timer().scheduleAtFixedRate(new TimeView(), 1000, 5000);

    }

    private class RodarPulso implements Runnable{
        public void run() {
            if (principal == null) return;

            Toast.makeText(principal, "Chamou!!!!",Toast.LENGTH_SHORT).show();
            principal.atualizarLista();

        }
    }

    public void run() {
        if (principal == null) return;
        principal.runOnUiThread(rodarPulso);

    }

    public static TelaPrincipal getPrincipal() {
        return principal;
    }

    public static void setPrincipal(TelaPrincipal principal) {
        TimeView.principal = principal;
    }
}
