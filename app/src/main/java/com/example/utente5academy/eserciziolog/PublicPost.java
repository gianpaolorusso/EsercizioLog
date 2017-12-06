package com.example.utente5academy.eserciziolog;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.classi.DB;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PublicPost extends AppCompatActivity {
    private EditText titolo;
    private EditText testo;
    private Button pubblic;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_post);
        final String idcomunity = getIntent().getStringExtra("idcomunity");
        titolo = (EditText) findViewById(R.id.titolo);
        testo = (EditText) findViewById(R.id.testo);
        db = new DB(getApplicationContext());
        pubblic = (Button) findViewById(R.id.pubblica);
        pubblic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titolo.getText().toString().equals("") || testo.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Completare tutti i campi", Toast.LENGTH_SHORT).show();
                } else {
                    String title = titolo.getText().toString();
                    String nome = testo.getText().toString();
                    GregorianCalendar calendar = new GregorianCalendar();
                    int giorno = calendar.get(Calendar.DATE);
                    int mese = calendar.get(Calendar.MONTH) + 1;
                    int anno = calendar.get(Calendar.YEAR);
                    String data = String.valueOf(anno) + "-" + String.valueOf(mese) + "-" + String.valueOf(giorno);
                    try {
                        db.insertPost(title, data, nome, idcomunity);
                        Intent i = new Intent(PublicPost.this, PostActivity.class);
                        PendingIntent pendingIntent=PendingIntent.getActivity(getBaseContext(),1,i,PendingIntent.FLAG_UPDATE_CURRENT);
                   pendingIntent.send();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();
                    }


                }
            }
        });


    }
}
