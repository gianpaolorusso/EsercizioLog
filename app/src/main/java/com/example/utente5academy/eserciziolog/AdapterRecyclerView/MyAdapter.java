package com.example.utente5academy.eserciziolog.AdapterRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.utente5academy.eserciziolog.ListaComunity;
import com.example.utente5academy.eserciziolog.PostActivity;
import com.example.utente5academy.eserciziolog.R;
import com.example.utente5academy.eserciziolog.classi.Comunity;
import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Post;
import com.example.utente5academy.eserciziolog.classi.SaveObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by utente5.academy on 13/11/2017.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> listaComunity;

    public MyAdapter(ArrayList<String> C) {
        this.listaComunity = C;

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, int position) {
        String nomeComunity = this.listaComunity.get(position);
        holder.com.setText(nomeComunity);
      /*  final ViewClick click = new ViewClick(this.context, comunity.getId(),this.idutente,nomeComunity);
        holder.com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClick(holder.itemView);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return listaComunity.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Button com;


        public ViewHolder(View itemView) {
            super(itemView);

            com = (Button) itemView.findViewById(R.id.comunity);

        }

    }


}



