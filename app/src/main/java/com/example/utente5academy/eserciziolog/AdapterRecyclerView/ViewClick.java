package com.example.utente5academy.eserciziolog.AdapterRecyclerView;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.utente5academy.eserciziolog.DetailPostActivity;
import com.example.utente5academy.eserciziolog.PostActivity;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by utente5.academy on 14/11/2017.
 */

public class ViewClick implements View.OnClickListener {
    private String idcomunity;
    private Context context;
    private String nomecomunity;
    private Context contextPost;
    private String username;


    @Override
    public void onClick(View v) {

        Intent i = new Intent(context, PostActivity.class);
        i.putExtra("titolo", this.nomecomunity);
        i.putExtra("IDcomunity", this.idcomunity);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,1,i,PendingIntent.FLAG_UPDATE_CURRENT);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    public ViewClick(Context context, String idcomunity, String nome) {
        this.context = context;
        this.nomecomunity = nome;
        this.idcomunity = idcomunity;
    }

    public ViewClick(Context context) {
        this.contextPost = context;
    }

    public void clickCardView(View v, String idPost,String titolo) {
        Intent i = new Intent(contextPost, DetailPostActivity.class);
        i.putExtra("idPost", idPost);
        i.putExtra("titolo",titolo);
        PendingIntent pendingIntent=PendingIntent.getActivity(contextPost,1,i,PendingIntent.FLAG_UPDATE_CURRENT);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }

    }

}
