package com.example.utente5academy.eserciziolog;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.AdapterRecyclerView.AdapterPost;
import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Post;

import java.io.IOException;
import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {

    private SwipeRefreshLayout refreshLayout;
    private String titolo;
    private AdapterPost adapter;
    private DB db;
    private ArrayList<Post> listaPosts;
    private String idcomunity;
    private View v;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        idcomunity = getIntent().getStringExtra("IDcomunity");
        titolo = getIntent().getStringExtra("titolo");
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        db = new DB(getBaseContext());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerpost);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
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

                try {
                    listaPosts = db.PostAdapater(idcomunity, getBaseContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        if (listaPosts.size()>0) {
                            AdapterPost adapter=new AdapterPost(listaPosts,getBaseContext());
                            recyclerView.setAdapter(adapter);
                        } else
                            Toast.makeText(getBaseContext(), "nessun post", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        });
        try {
            adaptgertPostMmethod();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void adaptgertPostMmethod() throws IOException {
        ArrayList<Post> adapterPost = db.PostAdapater(idcomunity, getBaseContext());
        if (adapterPost.size()>0) {
            AdapterPost adapter=new AdapterPost(adapterPost,getBaseContext());
            recyclerView.setAdapter(adapter);
        } else
            Toast.makeText(getBaseContext(), "nessun post", Toast.LENGTH_SHORT).show();
    }


}