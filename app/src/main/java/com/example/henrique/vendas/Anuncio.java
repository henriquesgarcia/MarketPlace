package com.example.henrique.vendas;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Anuncio {
    @Id
    private long id;
    private String titulo;
    private double valor;
    private String localizacao;
    private long idDono;
    //private ToOne<Usuario> dono;

    public Anuncio() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public long getIdDono() {
        return idDono;
    }

    public void setIddono(long idDono) {
        this.idDono = idDono;
    }

    /*public ToOne<Usuario> getDono() {
        return dono;
    }

    public void setDono(ToOne<Usuario> dono) {
        this.dono = dono;
    }*/
}
