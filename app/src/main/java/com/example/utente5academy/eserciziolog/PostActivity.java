package com.example.utente5academy.eserciziolog;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.AdapterRecyclerView.AdapterPost;
import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Interface;

import java.io.IOException;

public class PostActivity extends AppCompatActivity implements Interface {

    private SwipeRefreshLayout refreshLayout;
    private String titolo;
    private DB db;
    private AdapterPost adapter;
    private String idcomunity;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private Interface delegate;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        idcomunity = getIntent().getStringExtra("IDcomunity");
        titolo = getIntent().getStringExtra("titolo");
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        db = new DB(getBaseContext());
        this.setTitle(titolo);
        delegate = this;
        preferences=getSharedPreferences("preferences", MODE_PRIVATE);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerpost);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        delegate.adaptgetPostMethod();
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingnbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PostActivity.this, PublicPost.class);
                i.putExtra("idcomunity", idcomunity);
                PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                delegate.adaptgetPostMethod();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);


                    }
                }, 3000);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        idcomunity = getIntent().getStringExtra("IDcomunity");
        delegate.adaptgetPostMethod();
    }

    @Override
    public void myadaptgetrmethod() {

    }

    @Override
    public void adaptgetPostMethod() {

        try {
            adapter = db.PostAdapater(idcomunity);
            Thread.sleep(100);
            if (adapter.getItemCount() > 0) {
                recyclerView.setAdapter(adapter);
            } else
                Toast.makeText(getBaseContext(), "nessun post", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void CommentList() {

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
                Intent intent = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
                break;
        }
        return true;
    }

}
