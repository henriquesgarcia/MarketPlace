package com.example.henrique.vendas;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;


@Entity
public class Usuario {

    @Id
    private long id;
    private String email;
    private String senha;
    public String nome;
    public String tel;
    public String ende;

    public Usuario() {
    }

    /*public Usuario(String email, String senha, String nome, String tel, String ende, Box<Usuario> user){
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.tel = tel;
        this.ende = ende;

    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEnde() {
        return ende;
    }

    public void setEnde(String ende) {
        this.ende = ende;
    }
}
