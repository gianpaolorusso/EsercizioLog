package com.example.utente5academy.eserciziolog.AdapterRecyclerView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utente5academy.eserciziolog.R;
import com.example.utente5academy.eserciziolog.classi.Commento;

import java.util.ArrayList;

/**
 * Created by utente5.academy on 11/12/2017.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Commento> lista;

    public CommentAdapter(Context context, ArrayList<Commento> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {
        Commento commento = this.lista.get(position);
        holder.data.setText(commento.getData());
        holder.testo.setText(commento.getTesto());
        holder.user.setText(commento.getUser_pubblicazione());

    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView card;
        public TextView data;
        public TextView user;
        public TextView testo;

        public ViewHolder(View itemView) {
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.cardview);
            data = (TextView) itemView.findViewById(R.id.giorno);
            user = (TextView) itemView.findViewById(R.id.nomeUser);
            testo = (TextView) itemView.findViewById(R.id.testo);
        }
    }
}
