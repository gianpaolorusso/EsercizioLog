package com.example.utente5academy.eserciziolog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.classi.Comunity;
import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Post;
import com.example.utente5academy.eserciziolog.classi.SaveObject;
import com.example.utente5academy.eserciziolog.classi.User;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button accesso;
    private DB database=null;
    private User user=null;
    private User user2=null;
    private User user3=null;
    User userLoggato=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SaveObject saveObject = new SaveObject();
        Context context = getBaseContext();
        try {
            userLoggato=saveObject.leggiUtente("utenteloggato",context);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(userLoggato!=null)
        {
            Intent i = new Intent(MainActivity.this, ListaComunity.class);
            i.putExtra("IDutente", userLoggato.getId());
            startActivity(i);
        }
        else{
        {

        }
        database = new DB();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        accesso = (Button) findViewById(R.id.accesso);
        Comunity comunity1 = new Comunity();
        user = new User("giacomo", "giacomo", "tre", "");
        user2 = new User("francesco", "francesco22", "password", "");
        user3 = new User("pippo", "rossi", "pass", "");
        ArrayList<User> listautenti = new ArrayList<User>();
        listautenti.add(user);
        listautenti.add(user2);
        listautenti.add(user3);
        Comunity comunity2 = new Comunity("Musica");
        Comunity comunity3 = new Comunity("Film");
        Comunity comunity4 = new Comunity("Sport");
        Comunity comunity5 = new Comunity("Fiction");
        Comunity comunity6 = new Comunity("Informatica");

        ArrayList<Comunity> arraycomunity = new ArrayList<Comunity>();

        arraycomunity.add(comunity2);
        arraycomunity.add(comunity3);
        arraycomunity.add(comunity4);
        arraycomunity.add(comunity5);
        arraycomunity.add(comunity6);
        Post post1 = new Post("post1","16/11/2017","post inutile");
        Post post2 = new Post("post2","16/11/2017","post inutile");
        Post post3 = new Post("post3","16/11/2017","post inutile");
        Post post4 = new Post("post4","16/11/2017","post inutile");
        Post post6 = new Post("post5","16/11/2017","post inutile");
        Post post5 = new Post("post6","16/11/2017","post inutile");
ArrayList<Post> listapost=new ArrayList<Post>();
listapost.add(post1);
listapost.add(post2);
listapost.add(post3);
listapost.add(post4);
listapost.add(post5);
listapost.add(post6);
        database.Init(listautenti,arraycomunity,listapost);
        try {
            saveObject.salva(database,context,"listautenti");
        } catch (IOException e) {
            e.printStackTrace();
        }
        accesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Inserire l'username", Toast.LENGTH_SHORT).show();

                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Inserire lapassword", Toast.LENGTH_SHORT).show();
                } else if (database.verificaLog(username.getText().toString(), password.getText().toString())) {
                    userLoggato=database.LogIn(username.getText().toString(), password.getText().toString());
                    SaveObject saveObject = new SaveObject();
                    try {
                        saveObject.salva(userLoggato, getBaseContext(), "utenteloggato");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(MainActivity.this, ListaComunity.class);
                    i.putExtra("IDutente", userLoggato.getId());
                    startActivity(i);

                } else {
                    Toast.makeText(getBaseContext(), "Inserire i dati corretti", Toast.LENGTH_SHORT).show();
                }
            }

        });}

    }
}
