package com.example.utente5academy.eserciziolog;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.utente5academy.eserciziolog.AdapterRecyclerView.MyAdapter;
import com.example.utente5academy.eserciziolog.classi.Comunity;
import com.example.utente5academy.eserciziolog.classi.DB;
import com.example.utente5academy.eserciziolog.classi.Post;
import com.example.utente5academy.eserciziolog.classi.SaveObject;
import com.example.utente5academy.eserciziolog.classi.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.EventListener;

import static android.support.v7.widget.LinearLayoutManager.*;

public class ListaComunity extends AppCompatActivity {
    private String idutente;
    private SaveObject readObject;
    ArrayList<String> comunity = new ArrayList<>();
    User tempObject = null;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referenceDB = database.getReferenceFromUrl("https://esercizio-log.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comunity);
        // idutente = getIntent().getStringExtra("IDutente").toString();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference referenceDB = database.getReferenceFromUrl("https://esercizio-log.firebaseio.com");
        referenceDB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                comunity.add(dataSnapshot.getValue().toString());


                MyAdapter adapter = new MyAdapter(comunity);
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listaComunity);
                recyclerView.setAdapter(adapter);
                int index = comunity.size() - 1;
                String messaggio = "Sei stato aggiunto alla comunity " + comunity.get(index);
                NotificationReceive(dataSnapshot, messaggio);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String messaggio = "Sei stato eliminato dalla comunity " + dataSnapshot.getValue().toString();
                NotificationReceive(dataSnapshot, messaggio);
                comunity.remove(dataSnapshot.getValue().toString());
                MyAdapter adapter = new MyAdapter(comunity);
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listaComunity);
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        finishAffinity();

    }

    private User readObject() throws IOException, ClassNotFoundException {
        readObject = new SaveObject();
        User object = readObject.leggiUtente("utenteloggato", getBaseContext());
        return object;

    }

    private void NotificationReceive(DataSnapshot dataSnapshot, String messaggio) {
        Intent i = new Intent(this, ListaComunity.class);
        PendingIntent pending = PendingIntent.getActivity(this, 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifica = new NotificationCompat.Builder(getBaseContext());
        notifica.setContentText(messaggio)
                .setContentTitle("Comunity")
                .setSmallIcon(R.drawable.immagine)
                .setContentIntent(pending);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notifica.build());
    }

    /* private void showRecyclerview(String id) throws IOException {


        try {
            tempObject = readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.setTitle(tempObject.getUsername());
       // ArrayList<Comunity> listaComunity = tempObject.getAllComunity();
        MyAdapter adapter = new MyAdapter(listaComunity, getBaseContext(), id);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listaComunity);
        recyclerView.setAdapter(adapter);


    }*/

}

