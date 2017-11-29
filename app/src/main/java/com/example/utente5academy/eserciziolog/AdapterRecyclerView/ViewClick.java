package com.example.utente5academy.eserciziolog.AdapterRecyclerView;

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


    @Override
    public void onClick(View v) {

        Intent i = new Intent(context, PostActivity.class);
        i.putExtra("titolo", nomecomunity);
        i.putExtra("IDcomunity", idcomunity);
        startActivity(context, i, null);
    }

    public ViewClick(Context context, String idcomunity, String nome) {
        this.context = context;
        this.nomecomunity = nome;
        this.idcomunity = idcomunity;
    }

    public ViewClick(Context context) {
        this.context = context;
    }

    public void clickCardView(View v, String idPost) {
        Intent i = new Intent(context, DetailPostActivity.class);
        i.putExtra("idPost", idPost);
        startActivity(context, i, null);
    }
}
