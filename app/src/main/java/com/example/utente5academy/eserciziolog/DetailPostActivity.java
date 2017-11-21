package com.example.utente5academy.eserciziolog;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Post;
import com.example.utente5academy.eserciziolog.classi.SaveObject;
import com.example.utente5academy.eserciziolog.classi.User;

import java.io.IOException;

public class DetailPostActivity extends AppCompatActivity {
    Post post = null;
    User user = null;
    SaveObject saveObject = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        String idcomunity = getIntent().getStringExtra("idComunity");
        String idPost= getIntent().getStringExtra("idPost");
        saveObject = new SaveObject();
        Context context = getBaseContext();

        try { user=saveObject.leggiUtente("utenteloggato", context);

            post=user.getPostByIdComunity(idcomunity,idPost);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TextView titolo = (TextView) findViewById(R.id.titolo);
        titolo.setText(post.getTitolo());
        this.setTitle(post.getTitolo());
        TextView nome = (TextView) findViewById(R.id.nome);
        nome.setText(post.getNome());
        TextView data = (TextView) findViewById(R.id.data);
        data.setText(post.getData());
        TextView id = (TextView) findViewById(R.id.idpost);
        id.setText(post.getId());
        ImageView imageView = (ImageView) findViewById(R.id.immagine);
        String img = post.getImmagine().toString();
        imageView.setImageResource(R.drawable.immagine);
    }
}
