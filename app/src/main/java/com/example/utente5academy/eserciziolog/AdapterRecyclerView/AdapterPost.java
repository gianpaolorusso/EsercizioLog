package com.example.utente5academy.eserciziolog.AdapterRecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utente5academy.eserciziolog.R;
import com.example.utente5academy.eserciziolog.classi.Comunity;
import com.example.utente5academy.eserciziolog.classi.Post;
import com.example.utente5academy.eserciziolog.classi.SaveObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by utente5.academy on 14/11/2017.
 */

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolder> {
    private ArrayList<Post> listaPost;
    private String idComunity;
    private Context context;

public String getIdComunity()
{
    return this.idComunity;
}

    public AdapterPost(ArrayList<Post> C, Context c,String idComunity)
    {   this.listaPost=C;
    this.context=c;
    this.idComunity=idComunity;


    }
    @Override
    public AdapterPost.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutpost,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdapterPost.ViewHolder holder, int position) {

        final Post post=this.listaPost.get(position);
        holder.titolo.setText(post.getTitolo());
        holder.data.setText(post.getData());
        holder.nome.setText(post.getNome());
        holder.immagine.setImageResource(R.drawable.immagine);
        final ViewClick click=new ViewClick(this.context);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.clickCardView(holder.itemView,post.getId(),getIdComunity());
    }
});


    }

    @Override
    public int getItemCount() {
        return this.listaPost.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titolo;
        public ImageView immagine;
        public TextView id;
        public TextView nome;
        public TextView data;
        public CardView card;
        public ViewHolder(View itemView) {
            super(itemView);
            card=(CardView)itemView.findViewById(R.id.cardview);
            titolo=(TextView) itemView.findViewById(R.id.titolo);
            id=(TextView)itemView.findViewById(R.id.ID);
            nome=(TextView)itemView.findViewById(R.id.nome);
            data=(TextView) itemView.findViewById(R.id.data);
            immagine=(ImageView)itemView.findViewById(R.id.immagine);

        }
    }
}
