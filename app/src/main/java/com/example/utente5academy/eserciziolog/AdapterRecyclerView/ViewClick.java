package com.example.utente5academy.eserciziolog.AdapterRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.utente5academy.eserciziolog.DetailPostActivity;
import com.example.utente5academy.eserciziolog.ListaComunity;
import com.example.utente5academy.eserciziolog.PostActivity;
import com.example.utente5academy.eserciziolog.classi.SaveObject;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by utente5.academy on 14/11/2017.
 */

public class ViewClick implements View.OnClickListener {
    private String idcomunity;
    private Context context ;
    private  String idutente;
    private String NomeComunity;


    @Override
    public void onClick(View v) {

        Intent i = new Intent(context,PostActivity.class);
        i.putExtra("IDcomunity",this.idcomunity);
        i.putExtra("IDutente",idutente);
        i.putExtra("NomeComunity",NomeComunity);

        startActivity(context,i,null);
    }

    public ViewClick(Context context, String idcomunity,String idutente,String comunity) {
        this.context = context;
        this.idcomunity= idcomunity;
        this.idutente=idutente;
        this.NomeComunity=comunity;

    }
    public ViewClick(Context context){
        this.context=context;
    }
    public void clickCardView(View v,String idPost,String idcomunity)
    {
        Intent i = new Intent(context,DetailPostActivity.class);
        i.putExtra("idPost",idPost);
        i.putExtra("idComunity",idcomunity);
        startActivity(context,i,null);
    }
}
