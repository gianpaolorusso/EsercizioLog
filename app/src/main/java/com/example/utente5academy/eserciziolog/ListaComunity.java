package com.example.utente5academy.eserciziolog;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.AdapterRecyclerView.MyAdapter;
import com.example.utente5academy.eserciziolog.classi.Comunity;
import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Interface;

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
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        username = getIntent().getStringExtra("username");
        db = new DB(getBaseContext());
        lista = db.listComunity(username);
        final Interface delegate = this;
        recyclerView = (RecyclerView) findViewById(R.id.listaComunity);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        delegate.myadaptgetrmethod();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                final MyAdapter adapter = db.getAdapter(username);
                boolean b = new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        if (adapter != null) {
                            recyclerView.setAdapter(adapter);
                        } else
                            Toast.makeText(getBaseContext(), "Nessuna comunity", Toast.LENGTH_SHORT).show();

                    }
                },3000);
            }
        });


    }

    @Override
    public void myadaptgetrmethod() {

        MyAdapter adapter = db.getAdapter(username);
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        } else
            Toast.makeText(getBaseContext(), "Nessuna comunity", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        try {
            this.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void adaptgertPostMethod() {
    }
}