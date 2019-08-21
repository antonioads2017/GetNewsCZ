package com.example.getcznews.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.getcznews.R;

/**
 * Classe de Gerencia de Notificação do Android
 * @author Antonio Miguel
 */
public class NotificationManager {
    private final Context ctx;
    private final String channelID = "Noticia";

    public NotificationManager(Context ctx){
        this.ctx = ctx;
    }

    /**
     * Classe responsavel por criar e lançar a notificação ao dispositivo,
     * com vibração e efeito sonoro
     * @param smallIcon icone pequeno da notificação
     * @param title titulo da notificação
     * @param content conteudo da notificação
     * @param actClass classe do intent pendente
     */
    public void sendNotification(int smallIcon, String title, String content, Class<?> actClass){
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(ctx, actClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, intent, 0);
        //create notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, channelID)
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //notify
        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctx);
        notificationManager.notify(0, builder.build());
        notificationManager.notify(R.drawable.small, n);
        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(ctx, som);
            toque.play();
        }
        catch(Exception e){}
    }

}
