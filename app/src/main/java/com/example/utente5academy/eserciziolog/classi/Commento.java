
package com.example.utente5academy.eserciziolog.classi;

/**
 * Created by utente5.academy on 11/12/2017.
 */

public class Commento {

    private String testo;
    private String user_pubblicazione;
    private String data;

    public Commento() {
        testo = "";
        user_pubblicazione = "";
        data = "";
    }


    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getUser_pubblicazione() {
        return user_pubblicazione;
    }

    public void setUser_pubblicazione(String user_pubblicazione) {
        this.user_pubblicazione = user_pubblicazione;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
