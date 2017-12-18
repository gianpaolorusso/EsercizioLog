
package com.example.utente5academy.eserciziolog;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.classi.DB;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button accesso;
    private DB database;

    @Override
    public void onBackPressed() {
        try {
            this.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getBaseContext();
        FirebaseInstanceId.getInstance().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic("push");
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        String utente = preferences.getString("username", "");
        if (!(utente.equals(""))) {
            Intent intent = new Intent(MainActivity.this, ListaComunity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            try {
                pendingIntent.send();

            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }

        } else {
            username = (EditText) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);
            accesso = (Button) findViewById(R.id.accesso);
            final SharedPreferences.Editor editor = preferences.edit();

            accesso.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String user;
                    String pass;
                    if (username.getText().toString().equals("")) {
                        Toast.makeText(getBaseContext(), "Inserire l'username", Toast.LENGTH_SHORT).show();
                    } else if
                            (password.getText().toString().equals("")) {
                        Toast.makeText(getBaseContext(), "Inserire la password", Toast.LENGTH_SHORT).show();
                    } else {

                        user = username.getText().toString();
                        pass = password.getText().toString();
                        editor.putString("username", user);
                        editor.commit();
                        database = new DB(context);
                        database.existUsername(user, pass);
                    }

                }
            });

        }
    }

}
