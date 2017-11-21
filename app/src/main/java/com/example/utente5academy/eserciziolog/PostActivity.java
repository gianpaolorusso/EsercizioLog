package com.example.utente5academy.eserciziolog;

import android.Manifest;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.AdapterRecyclerView.AdapterPost;
import com.example.utente5academy.eserciziolog.classi.Comunity;
import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Post;
import com.example.utente5academy.eserciziolog.classi.SaveObject;
import com.example.utente5academy.eserciziolog.classi.User;

import java.io.IOException;
import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        String nomecomunity=getIntent().getStringExtra("NomeComunity").toString();
        this.setTitle(nomecomunity);
        String idcomunity = getIntent().getStringExtra("IDcomunity").toString();
        String idutente = getIntent().getStringExtra("IDutente").toString();
        SaveObject saveObject = new SaveObject();
        User user = null;
        try {
           user = saveObject.leggiUtente("utenteloggato", getBaseContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Comunity comunity = new Comunity();

        comunity=user.getIdComunity(idcomunity);
        ArrayList<Post> listapost = comunity.getAllPost();
        if (listapost.size()>0) {;
            AdapterPost adapterPost = new AdapterPost(listapost, getBaseContext(),comunity.getId());
            RecyclerView recyclerView =(RecyclerView) findViewById(R.id.recyclerpost);
            recyclerView.setAdapter(adapterPost);
        } else {
            Toast.makeText(getBaseContext(), "Nessuna post", Toast.LENGTH_SHORT).show();
        }
        }
    }
