
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


    public Comunity() {
        this.id = null;
        this.nome = null;
    }

    public Comunity(String nome) {
        this.nome = nome;
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
