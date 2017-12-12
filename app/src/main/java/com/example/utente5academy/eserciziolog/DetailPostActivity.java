package com.example.utente5academy.eserciziolog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.AdapterRecyclerView.AdapterPost;
import com.example.utente5academy.eserciziolog.AdapterRecyclerView.CommentAdapter;
import com.example.utente5academy.eserciziolog.classi.Commento;
import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Interface;
import com.example.utente5academy.eserciziolog.classi.Post;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;

public class DetailPostActivity extends AppCompatActivity implements Interface {
    private TextView titolo;
    private TextView nome;
    private TextView txdata;
    private String idpost;
    private DB db = null;
    private String titoloActivity;
    private ImageView immagine;
    private RecyclerView recyclerView;
    private AdapterPost adapterPost;
    private EditText testoCommento;
    private Button pubblicCommento;
    private String username;
    private Interface delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        idpost = getIntent().getStringExtra("idPost");
        titoloActivity = getIntent().getStringExtra("titolo");
        SharedPreferences preferences = getSharedPreferences("preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        username=preferences.getString("username","nonloggato");
        this.setTitle(titoloActivity);
        testoCommento = (EditText) findViewById(R.id.testoCommento);
        pubblicCommento = (Button) findViewById(R.id.pubblicaCommento);
        db = new DB(getBaseContext());
        recyclerView = (RecyclerView) findViewById(R.id.listaCommenti);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        immagine = (ImageView) findViewById(R.id.immagine);
        titolo = (TextView) findViewById(R.id.titolo);
        nome = (TextView) findViewById(R.id.nome);
        txdata = (TextView) findViewById(R.id.txdata);
        db.getPost(idpost, titolo, nome, txdata, immagine);
        delegate = this;
        recyclerView.setVisibility(View.GONE);
        delegate.CommentList();
        pubblicCommento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (testoCommento.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Scrivere il commento", Toast.LENGTH_SHORT).show();
                } else {

                    GregorianCalendar calendar = new GregorianCalendar();
                    int giorno = calendar.get(Calendar.DATE);
                    int mese = calendar.get(Calendar.MONTH) + 1;
                    int anno = calendar.get(Calendar.YEAR);
                    String data = String.valueOf(anno) + "-" + String.valueOf(mese) + "-" + String.valueOf(giorno);
                    db.comment(testoCommento.getText().toString(), data, username,idpost);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    delegate.CommentList();
                    testoCommento.setText("");
                }
            }
        });


    }

    @Override
    public void myadaptgetrmethod() {

    }

    @Override
    public void adaptgetPostMethod() {

    }

    @Override
    public void CommentList() {
        ArrayList<Commento>lista=db.getCommentList(idpost);
        try {
            Thread.sleep(1000);
            if (lista !=null && lista.size() > 0) {
                recyclerView.setVisibility(View.VISIBLE);
                CommentAdapter commentAdapter = new CommentAdapter(getBaseContext(), lista);
                recyclerView.setAdapter(commentAdapter);
            } else {
                Toast.makeText(getBaseContext(), "Nessun Commento", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
