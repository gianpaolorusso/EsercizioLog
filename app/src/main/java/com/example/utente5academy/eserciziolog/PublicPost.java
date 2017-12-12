package com.example.utente5academy.eserciziolog;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView foto;
    private final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_post);
        final String idcomunity = getIntent().getStringExtra("idcomunity");
        titolo = (EditText) findViewById(R.id.titolo);
        GregorianCalendar calendar = new GregorianCalendar();
        final int giorno = calendar.get(Calendar.DATE);
        foto = (ImageView) findViewById(R.id.foto);
        final int mese = calendar.get(Calendar.MONTH) + 1;
        final int anno = calendar.get(Calendar.YEAR);
        testo = (EditText) findViewById(R.id.testo);
        db = new DB(getBaseContext());
        pubblic = (Button) findViewById(R.id.pubblica);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatchTakePictureIntent();

            }
        });
        pubblic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titolo.getText().toString().equals("") || testo.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Completare tutti i campi", Toast.LENGTH_SHORT).show();
                } else {
                    String title = titolo.getText().toString();
                    String nome = testo.getText().toString();
                    String data = String.valueOf(anno) + "-" + String.valueOf(mese) + "-" + String.valueOf(giorno);
                    try {
                        db.insertPost(getBaseContext(), title, data, nome, idcomunity);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        });


    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            foto.setImageBitmap(imageBitmap);
        }
    }
}
