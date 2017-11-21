package com.example.utente5academy.eserciziolog.classi;

import android.renderscript.Sampler;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by utente5.academy on 13/11/2017.
 */

public class User implements Serializable {
    private String username;
    private String passowrd;
    private String nome;
    private String id;
    private ArrayList<Comunity> comunity = new ArrayList<Comunity>();

    public User() {
        username = null;
        passowrd = null;
        nome = null;
        comunity = null;
    }

    public void setAllComunity(ArrayList<Comunity> array) {
        comunity = array;
    }

    public ArrayList<Comunity> getAllComunity() {
        return this.comunity;
    }

    public User(String nome, String username, String passowrd, String id) {
        this.passowrd = passowrd;
        this.username = username;
        this.nome = nome;
        this.id = id;
    }

    public Comunity getIdComunity(String id) {
        Comunity tempo = null;
        for (Comunity el : this.comunity) {
            if (el.getId().equals(id)) {
                tempo = el;
                break;
            }
        }
        if (tempo != null)
            return tempo;
        else
            return null;
    }

    public Post getPostByIdComunity(String idcomunity, String idPost) {
        ArrayList<Post> listaPost = null;
        Post postCercato = null;
        for (Comunity el : this.comunity) {
            listaPost = el.getAllPost();
            for(Post elpost:listaPost)
            {
                if(idPost.equals(elpost.getId()))
                {
                    postCercato=elpost;
                    break;
                }
            }
        }
        if(postCercato!=null)
        {
            return postCercato;
        }
        else
            return null;
    }

    private int lastPosizion() {
        int last = 0;
        if (this.comunity.size() == 0) {
            return last;
        }
        return this.comunity.size();
    }

    public void addComunity(Comunity og) {
        og.setId(String.valueOf(lastPosizion()));
        this.comunity.add(og);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
