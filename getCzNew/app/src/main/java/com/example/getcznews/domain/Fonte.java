package com.example.getcznews.domain;

import java.io.Serializable;
import java.nio.file.SecureDirectoryStream;
import java.util.Objects;


/*******************************************
 * Classe responsável pela fonte das notícias
 *******************************************/
public class Fonte implements Serializable {

    private long id;
    private String nome;
    private String site;
    private String feed;

    public Fonte() {}

    public Fonte(long id, String nome, String site, String feed) {
        this.id = id;
        this.nome = nome;
        this.site = site;
        this.feed = feed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fonte fonte = (Fonte) o;
        return id == fonte.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
