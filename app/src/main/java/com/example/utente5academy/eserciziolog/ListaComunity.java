package com.example.utente5academy.eserciziolog;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private SharedPreferences preferences;
    private Interface delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comunity);
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        username = preferences.getString("username", "");
        db = new DB(getBaseContext());
        lista = db.listComunity(username);
        delegate= this;
        recyclerView = (RecyclerView) findViewById(R.id.listaComunity);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
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
                }, 3000);
            }
        });
        try {
            delegate.myadaptgetrmethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.layout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.esci:
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", "");
                editor.commit();
                editor.apply();
                Intent intent=new Intent(this,MainActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(getBaseContext(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
                break;
        }
        return true;
    }

    @Override
    public void myadaptgetrmethod() throws InterruptedException {
        MyAdapter adapter = db.getAdapter(username);
        Thread.sleep(300);
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        } else
            Toast.makeText(getBaseContext(), "Nessuna comunity", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void adaptgetPostMethod() {

    }

    @Override
    public void CommentList() {

    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        try {
            delegate.myadaptgetrmethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}