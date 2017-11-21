
package com.example.utente5academy.eserciziolog.classi;

import java.io.Serializable;
import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;

/**
 * Created by utente5.academy on 13/11/2017.
 */

public class Comunity implements Serializable {
    private String id;
    private String nome;
    private ArrayList<Post> posts = new ArrayList<Post>();


    public Comunity() {
        this.id = null;
        this.nome = null;
        this.posts = null;
    }

    public Comunity(String nome) {
        this.nome = nome;
    }
    public  void AddListaPost(ArrayList<Post> listapost)
    {
        this.posts=listapost;
    }

    public int lastPostPosizion()
    {
        return this.posts.size();
    }

    public void addPost(Post post) {
        post.setId(String.valueOf(lastPostPosizion()));
        this.posts.add(post);
    }


    public ArrayList<Post> getAllPost() {
        return posts;
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
}
