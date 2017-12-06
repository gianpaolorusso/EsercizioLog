package com.example.utente5academy.eserciziolog.classi;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.AdapterRecyclerView.AdapterPost;
import com.example.utente5academy.eserciziolog.AdapterRecyclerView.MyAdapter;
import com.example.utente5academy.eserciziolog.ErrorActivity;
import com.example.utente5academy.eserciziolog.ListaComunity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by utente5.academy on 13/11/2017.
 */
public class DB {

    private Context c;
    private boolean trovato = false;

    public DB(Context c) {

        this.c = c;
    }

    Intent intent;

    public void logIn(final String username, final String password) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(6, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectTimeout(5, TimeUnit.SECONDS).build();
        RequestBody body = new FormBody.Builder()
                .add("username", username)
                .build();
        final Request request = new Request.Builder()
                .post(body)
                .url("http://192.168.43.126/php/accesso.php")
                .build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                JSONObject jsonObject = null;
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response.body().string());
                    jsonObject = (JSONObject) jsonArray.get(0);
                    call.cancel();


                    if (jsonObject.getString("username").toString().equals(username)) {
                        if (jsonObject.getString("password").toString().equals(password)) {
                            trovato = true;
                            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(c);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("user",username);
                            intent = new Intent(c, ListaComunity.class);
                            intent.putExtra("username", username);
                            PendingIntent pendingIntent = PendingIntent.getActivity(c, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            try {
                                pendingIntent.send();
                            } catch (PendingIntent.CanceledException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(c, "Password errata", LENGTH_LONG).show();
                        }
                    } else
                        Toast.makeText(c, "Username errato", LENGTH_LONG).show();

                } catch (Exception e) {

                }

            }
        });
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

    }

    public void existUsername(String username, String password) {
        final String us = username;
        final String pass = password;

        final OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(6, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS).build();
        RequestBody body = new FormBody.Builder()
                .add("username", us)
                .build();
        final Request request = new Request.Builder()
                .post(body)
                .url("http://192.168.43.126/php/exsistUsername.php")
                .build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.body().string().equals("11\r\n")) {
                    call.cancel();
                    logIn(us, pass);

                } else {
                    try {
                        this.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }

            }

            @Override
            protected void finalize() throws Throwable {
                Intent i = new Intent(c, ErrorActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(c, 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
                pendingIntent.send();
            }
        });
    }

    public ArrayList<Comunity> listComunity(String username) {
        final ArrayList<Comunity> lista = new ArrayList<>();
        final OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .readTimeout(6, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS).build();
        RequestBody body = new FormBody.Builder()
                .add("username", username)
                .build();
        final Request request = new Request.Builder()
                .post(body)
                .url("http://192.168.43.126/php/getComunityUser.php")
                .build();
        final okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                JSONObject jsonObject = null;
                JSONArray jsonArray = null;

                try {
                    jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Comunity comunity = new Comunity();
                        jsonObject = (JSONObject) jsonArray.get(i);
                        comunity.setNome(jsonObject.getString("nome"));
                        comunity.setId(jsonObject.getString("id"));
                        lista.add(comunity);
                    }
                    call.cancel();
                    response.cacheResponse().close();
                    client.dispatcher().cancelAll();
                } catch (JSONException e) {
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }


            }


        });
        return lista;
    }

    public ArrayList<Post> getListPost(String idcomunity) throws IOException {
        final ArrayList<Post> listaPost = new ArrayList<>();
        final OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS).build();
        RequestBody body = new FormBody.Builder()
                .add("idcomunity", idcomunity)
                .build();
        final Request request = new Request.Builder()
                .post(body)
                .url("http://192.168.43.126/php/getPostsComunity.php")
                .build();
        final okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                JSONObject jsonObject = null;
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Post post = new Post();
                        jsonObject = (JSONObject) jsonArray.get(i);
                        post.setNome(jsonObject.getString("nome"));
                        post.setId(jsonObject.getString("id"));
                        post.setData(jsonObject.getString("giorno"));
                        post.setTitolo(jsonObject.getString("titolo"));
                        listaPost.add(post);
                    }


                    client.cache().close();
                    client.cache().delete();
                    call.cancel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return listaPost;

    }

    public void getPost(String idPost, final TextView titolo, final TextView data,
                        final TextView id, final TextView nome) {
        final Post post = new Post();
        final OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.SECONDS).build();
        RequestBody body = new FormBody.Builder()
                .add("idpost", idPost)
                .build();
        final Request request = new Request.Builder()
                .post(body)
                .url("http://192.168.43.126/php/getPost.php")
                .build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                JSONObject jsonObject = null;
                JSONArray jsonArray = null;
                try {

                    jsonArray = new JSONArray(response.body().string());
                    jsonObject = (JSONObject) jsonArray.get(0);
                    nome.setText(jsonObject.getString("nome"));
                    id.setText(jsonObject.getString("id"));
                    data.setText(jsonObject.getString("giorno"));
                    titolo.setText(jsonObject.getString("titolo"));

                    call.cancel();
                    //  response.cacheResponse().close();
                    client.dispatcher().cancelAll();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void insertPost(String titolo, String data, String testo, String idcoomunity) throws IOException {
        final OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.SECONDS).build();
        RequestBody body = new FormBody.Builder()
                .add("idcomunity", idcoomunity)
                .add("data", data)
                .add("titolo", titolo)
                .add("nome", testo)
                .build();
        final Request request = new Request.Builder()
                .post(body)
                .url("http://192.168.43.126/php/publicPost.php")
                .build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

            }
        });
    }

    public MyAdapter getAdapter(String username) {
        ArrayList<Comunity> lista = listComunity(username);
        MyAdapter adapter = new MyAdapter(lista, c);
        return adapter;
    }

    public ArrayList<Post> PostAdapater(String idComunity, Context cx) throws IOException {
        ArrayList<Post> lista = getListPost(idComunity);
        AdapterPost adapterPost = new AdapterPost(lista, cx);
        return lista;

    }

}