package com.example.utente5academy.eserciziolog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.AdapterRecyclerView.AdapterPost;
import com.example.utente5academy.eserciziolog.AdapterRecyclerView.MyAdapter;
import com.example.utente5academy.eserciziolog.classi.Comunity;
import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Interface;

import java.io.IOException;
import java.util.ArrayList;

public class ListaComunity extends AppCompatActivity implements Interface {
    private String username;
    private DB db;
    private RecyclerView recyclerView;
    private ArrayList<Comunity> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comunity);
        username = getIntent().getStringExtra("username");
        db = new DB(getBaseContext());
        lista = db.listComunity(username);
        Interface delegate = this;
        recyclerView = (RecyclerView) findViewById(R.id.listaComunity);
        try {
            delegate.myadaptgetrmethod();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void myadaptgetrmethod() throws IOException {
        MyAdapter adapter = db.getAdapter(username);
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
    } else
            Toast.makeText(getBaseContext(), "Nessuna comunity",Toast.LENGTH_SHORT).show();

}

    @Override
    public void adaptgertPostMmethod() throws IOException {
    }
}