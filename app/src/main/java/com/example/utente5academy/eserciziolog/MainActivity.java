
package com.example.utente5academy.eserciziolog;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.classi.DB;

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
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        accesso = (Button) findViewById(R.id.accesso);

        accesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String USER = preferences.getString("user", null);
                if (USER.equals(null)) {


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
                        database = new DB(context);
                        database.existUsername(user, pass);
                    }
                }else
                {
                    Intent i=new Intent(MainActivity.this,ListaComunity.class);
                    i.putExtra("username",USER);
                    PendingIntent pendingIntent=PendingIntent.getActivity(getBaseContext(),1,i,PendingIntent.FLAG_UPDATE_CURRENT);
                    try {
                        pendingIntent.send();
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


    }
}
