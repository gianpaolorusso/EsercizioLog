package com.example.utente5academy.eserciziolog.classi;

import java.io.Serializable;

/**
 * Created by utente5.academy on 13/11/2017.
 */

public class Post extends Comunity implements Serializable {
    private String id;
    private String nome;
    private String data;
    private String Titolo;
    private String immagine="immagine";

    public Post(){
        this.id=null;
        nome=null;
        data=null;
        Titolo=null;
    }

    public Post(String nome,String data,String titolo)
    {
        this.Titolo=titolo;
        this.data=data;
        this.nome=nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitolo() {
        return Titolo;
    }

    public void setTitolo(String titolo) {
        Titolo = titolo;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
}
