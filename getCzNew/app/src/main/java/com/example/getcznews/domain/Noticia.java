package com.example.getcznews.domain;

import java.io.Serializable;
import java.util.Objects;

public class Noticia implements Serializable {

    private int id;
    private String titulo;
    private String texto;
    private String urlImage;
    private String link;
    private boolean visualizada;

    public Noticia() {}

    public Noticia(String titulo, String texto, String urlImage, String link, boolean visualizada) {
        this.titulo = titulo;
        this.texto = texto;
        this.urlImage = urlImage;
        this.link = link;
        this.visualizada = visualizada;
    }

    public Noticia(int id, String titulo, String texto, String urlImage, String link, boolean visualizada) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.urlImage = urlImage;
        this.link = link;
        this.visualizada = visualizada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isVisualizada() {
        return visualizada;
    }

    public void setVisualizada(boolean visualizada) {
        this.visualizada = visualizada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noticia noticia = (Noticia) o;
        return id == noticia.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
