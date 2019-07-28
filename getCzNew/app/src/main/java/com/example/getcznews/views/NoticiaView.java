package com.example.getcznews.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.getcznews.R;
import com.example.getcznews.domain.Noticia;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class NoticiaView extends LinearLayout{

    private Noticia noticia;
    private ImageView imageView;

    private void init(){
        LinearLayout.LayoutParams p = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
        setLayoutParams(p);

        setBackgroundColor(Color.GREEN);
        setOrientation(LinearLayout.HORIZONTAL);
        criarImagem();

        LinearLayout painelTexto = new LinearLayout(this.getContext());
        painelTexto.setOrientation(LinearLayout.VERTICAL);
        this.addView(painelTexto);

        criarTexto(noticia.getTitulo(),painelTexto);
        criarTexto(noticia.getTexto(), painelTexto);
    }

    public NoticiaView(Context context, Noticia noticia) {
        super(context);
        this.noticia = noticia;
        init();
    }

    private void criarTexto(String texto, LinearLayout linearLayout){
        TextView textView = new TextView(linearLayout.getContext());
        textView.setText(texto);
        linearLayout.addView(textView);
    }

    private void criarImagem(){

        imageView = new ImageView(this.getContext());

//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

//        imageView.setImageDrawable(
//                new ImageView(getContext())
//                        .getResources()
//                        .getDrawable(
//                                R.drawable.ic_launcher_foreground));

        Picasso.get()
                .load(noticia.getUrlImage())
                .centerCrop()
                .resize(200,200)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView);


//        View v = new ImageView(getContext());
//        ImageView imageView = new ImageView(this.getContext());
//        imageView.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_launcher_foreground));

//        Picasso.get()
//                .load(noticia.getUrlImage())
//                .resize(50,50)
//                .centerCrop()
//                .into(imageView);

        this.addView(imageView);

//        new DownloadImageTask(imageView)
//                .execute(noticia.getUrlImage());

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
