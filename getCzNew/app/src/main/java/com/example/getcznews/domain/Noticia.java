package com.example.getcznews.domain;

import java.io.Serializable;
import java.util.Objects;

/***************************************
 * Classe correndente ao objeto notícia
 *************************************/
public class Noticia implements Serializable {

    private long id;
    private Fonte fonte;
    private String titulo;
    private String texto;
    private String urlImage;
    private boolean visualizada;

    public Noticia() {}

    public Noticia(long id, Fonte fonte, String titulo, String texto, String urlImage, boolean visualizada) {
        this.id = id;
        this.fonte = fonte;
        this.titulo = titulo;
        this.texto = texto;
        this.urlImage = urlImage;
        this.visualizada = visualizada;
    }

    public Noticia(String titulo, String texto, String urlImage, Fonte fonte) {
        this.titulo = titulo;
        this.texto = texto;
        this.urlImage = urlImage;
        this.visualizada = false;
        this.fonte = fonte;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Fonte getFonte() {
        return fonte;
    }

    public void setFonte(Fonte fonte) {
        this.fonte = fonte;
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

    @Override
    public String toString() {
        return "Noticia{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}
