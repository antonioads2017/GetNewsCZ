package com.example.getcznews.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.getcznews.views.NoticiaView;
import com.example.getcznews.domain.Noticia;

import java.util.List;

public class AdapterNoticiaPersonalizado extends BaseAdapter {

    private final List<Noticia> noticias;
    private final Activity act;

    public AdapterNoticiaPersonalizado(List<Noticia> noticias, Activity act) {
        this.noticias = noticias;
        this.act = act;
    }

    @Override
    public int getCount() {
        return noticias.size();
    }

    @Override
    public Object getItem(int position) {
        return noticias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = new NoticiaView(parent.getContext(),(Noticia) getItem(position));
        return view;
    }
}
