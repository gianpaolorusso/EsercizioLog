package com.example.utente5academy.eserciziolog.classi;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by utente5.academy on 13/11/2017.
 */

public class SaveObject {
    FileOutputStream file = null;
    ObjectOutputStream objectDatabase;
    FileInputStream fileInput;
    ObjectInputStream oggettoInput;


    public void salva(Object db, Context c, String key) throws IOException {
        file = c.openFileOutput(key, Context.MODE_PRIVATE);
        objectDatabase = new ObjectOutputStream(file);
        objectDatabase.writeObject(db);
        objectDatabase.close();
        file.close();
    }
   /* public void delete (Object db, Context c, String key)throws IOException, ClassNotFoundException
    {
        fileInput = c.openFileInput(key);
        oggettoInput=new ObjectInputStream(fileInput);
        oggettoInput.
        DB db;
        db = (DB) oggettoInput.readObject();
        return db;*/


    public DB leggi(String key, Context c) throws IOException, ClassNotFoundException {
        fileInput = c.openFileInput(key);
        oggettoInput = new ObjectInputStream(fileInput);
        DB db;
        db = (DB) oggettoInput.readObject();
        return db;
    }
    public User leggiUtente(String key, Context c) throws IOException, ClassNotFoundException {
        fileInput = c.openFileInput(key);
        oggettoInput = new ObjectInputStream(fileInput);
       User db;
        db = (User) oggettoInput.readObject();
        return db;
    }


    public void salvaPost(Object post, Context c, String key) throws IOException {
        file = c.openFileOutput(key, Context.MODE_PRIVATE);
        objectDatabase = new ObjectOutputStream(file);
        objectDatabase.writeObject(post);
        objectDatabase.close();
        file.close();
    }

    public Post getPostDetail(String key, Context c) throws IOException, ClassNotFoundException {
        fileInput = c.openFileInput(key);
        oggettoInput = new ObjectInputStream(fileInput);
        Post post;
        post = (Post) oggettoInput.readObject();
        return post;

    }
}
