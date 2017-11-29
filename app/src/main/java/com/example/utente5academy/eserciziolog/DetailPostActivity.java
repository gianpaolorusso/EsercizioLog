package com.example.utente5academy.eserciziolog;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Post;

import java.io.IOException;

public class DetailPostActivity extends AppCompatActivity {
    private TextView titolo;
    private TextView nome;
    private TextView data;
    private TextView idPost;
    private String idpost;
    private Post post = null;
    private DB db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        idpost = getIntent().getStringExtra("idPost");
        String Titolo = getIntent().getStringExtra("titolo");
        db = new DB(getBaseContext());

        titolo = (TextView) findViewById(R.id.titolo);
        nome = (TextView) findViewById(R.id.nome);
        data = (TextView) findViewById(R.id.data);
        idPost = (TextView) findViewById(R.id.idpost);
        db.getPost(idpost,titolo,data,idPost,nome);
        this.setTitle(Titolo);

    }
}
