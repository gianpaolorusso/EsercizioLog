package com.example.utente5academy.eserciziolog;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.AdapterRecyclerView.AdapterPost;
import com.example.utente5academy.eserciziolog.AdapterRecyclerView.MyAdapter;
import com.example.utente5academy.eserciziolog.classi.Comunity;
import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Interface;
import com.example.utente5academy.eserciziolog.classi.Post;

import java.io.IOException;
import java.util.ArrayList;

public class PostActivity extends AppCompatActivity implements Interface {


    private String titolo;
    private AdapterPost adapter;
    private DB db;
    private ArrayList<Post> listaPosts;
    private String idcomunity;
    private View v;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        idcomunity = getIntent().getStringExtra("IDcomunity");
        titolo = getIntent().getStringExtra("titolo");
        Interface delegate = this;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerpost);
        try {
            delegate.adaptgertPostMmethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void myadaptgetrmethod() throws IOException {

    }

    @Override
    public void adaptgertPostMmethod() throws IOException {
        AdapterPost adapterPost=db.PostAdapater(idcomunity);
        if (adapterPost!=null) {
            recyclerView.setAdapter(adapterPost);
        }
        else
            Toast.makeText(getBaseContext(),"nessun post",Toast.LENGTH_SHORT).show();
    }
}