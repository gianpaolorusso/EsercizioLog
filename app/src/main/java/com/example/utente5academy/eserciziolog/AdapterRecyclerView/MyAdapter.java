package com.example.utente5academy.eserciziolog.AdapterRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.utente5academy.eserciziolog.R;
import com.example.utente5academy.eserciziolog.classi.Comunity;

import java.util.ArrayList;

/**
 * Created by utente5.academy on 13/11/2017.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Comunity> listaComunity;
    private Context context;

    public MyAdapter(ArrayList<Comunity> C, Context cx) {
        this.listaComunity = C;
        this.context = cx;

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, int position) {

        Comunity comunity= this.listaComunity.get(position);
        holder.com.setText(comunity.getNome());
        final ViewClick click = new ViewClick(this.context, comunity.getId(), comunity.getNome());
        holder.com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClick(holder.itemView);
            }
        });

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



