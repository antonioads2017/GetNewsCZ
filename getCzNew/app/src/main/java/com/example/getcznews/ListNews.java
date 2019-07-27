package com.example.getcznews;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListNews  extends LinearLayout{

    TextView tvTitulo;

    private void init(){
        LinearLayout.LayoutParams p = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
        setLayoutParams(p);
        setBackgroundColor(Color.BLUE);
        setOrientation(LinearLayout.VERTICAL);
        tvTitulo = new TextView(this.getContext());
        tvTitulo.setText("Titulo");
        this.addView(tvTitulo);
    }

    public ListNews(Context context) {
        super(context);
        init();
    }

    public TextView getTvTitulo() {
        return tvTitulo;
    }

    public void setTvTitulo(TextView tvTitulo) {
        this.tvTitulo = tvTitulo;
    }
}
