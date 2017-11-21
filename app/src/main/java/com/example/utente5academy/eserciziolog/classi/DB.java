package com.example.utente5academy.eserciziolog.classi;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by utente5.academy on 13/11/2017.
 */

public class DB implements Serializable {

    private ArrayList<User> utenti;


    public DB() {
        this.utenti = new ArrayList<User>();
    }

    public void Init(ArrayList<User> ut, ArrayList<Comunity> comunity, ArrayList<Post> posts) {
        for (Comunity com : comunity) {
            for (Post post : posts) {
                com.addPost(post);
            }
            for (User el : ut) {
                el.addComunity(com);
                this.addUtente(el);
            }
        }

    }







    public void addUtente(User utente) {
        utente.setId(String.valueOf(this.utenti.size() + 1));
        this.utenti.add(utente);

    }



    public boolean verificaLog(String username, String password) {
        boolean trovato =false;
        for (User userTemp : this.utenti) {
            if (userTemp.getUsername().equals(username)) {
                if (userTemp.getPassowrd().equals(password)) {
                    trovato =true;
                    break;
                }
            }


        }
        return trovato;

    }

    public User LogIn(String username, String password) {
        User trovato = null;
        for (User userTemp : this.utenti) {
            if (userTemp.getUsername().equals(username)) {
                if (userTemp.getPassowrd().equals(password)) {
                    trovato = new User();
                    trovato = userTemp;
                    break;
                }
            }


        }
        return trovato;
    }

    public User getUtente(String nome) {
        boolean trovato = false;
        User temp = null;
        for (User el : this.utenti) {
            if (nome.equals(el.getNome())) {
                temp = el;
                trovato = true;
                break;
            }
        }
        if (trovato) {
            return temp;
        } else
            return null;
    }


    public User getByUserId(String id) {
        User temp = null;
        for (User el : this.utenti) {
            if (el.getId().equals(id)) {
                temp = el;
            }
        }
        if (temp != null) {
            return temp;
        } else
            return null;
    }

    public Comunity getComunityById(String idutente, String idcomunity) {
        ArrayList<User> listautenti = this.utenti;
        Comunity tem = null;
        User el = listautenti.get(listautenti.indexOf(idutente));

        ArrayList<Comunity> listacomunity = el.getAllComunity();
        for (Comunity cm : listacomunity) {
            if (idcomunity.equals(cm.getId())) {
                tem = cm;
                break;

            }
        }
        if (tem != null) {
            return tem;
        } else return
                null;
    }

    public void modifyUser(User og) {
        User utente = getByUserId(og.getId());
        int index = this.utenti.indexOf(utente);
        this.utenti.remove(index);
        this.utenti.add(og);
    }
}
