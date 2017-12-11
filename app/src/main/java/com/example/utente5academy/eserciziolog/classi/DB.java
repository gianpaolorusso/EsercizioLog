package com.example.utente5academy.eserciziolog.classi;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utente5academy.eserciziolog.AdapterRecyclerView.AdapterPost;
import com.example.utente5academy.eserciziolog.AdapterRecyclerView.MyAdapter;
import com.example.utente5academy.eserciziolog.ErrorActivity;
import com.example.utente5academy.eserciziolog.ListaComunity;
import com.example.utente5academy.eserciziolog.MainActivity;
import com.example.utente5academy.eserciziolog.PostActivity;
import com.example.utente5academy.eserciziolog.PublicPost;
import com.example.utente5academy.eserciziolog.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
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
    private Post post = null;

    public DB(Context c) {

        this.c = c;
    }


    public void logIn(final String username, final String password) {
        final OkHttpClient client = getClient();
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


                    if (jsonObject.getString("username").toString().equals(username)) {
                        if (jsonObject.getString("password").toString().equals(password)) {
                            call.cancel();
                            Intent intent = new Intent(c, ListaComunity.class);
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


    public void existUsername(String username, String password) {
        final String us = username;
        final String pass = password;
        final OkHttpClient client = getClient();
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

        });
    }

    public ArrayList<Comunity> listComunity(String username) {
        final ArrayList<Comunity> lista = new ArrayList<>();
        final OkHttpClient client = getClient();
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
        final OkHttpClient client = getClient();
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

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return listaPost;

    }

    public void comment(String testo, String data, String username, String idpost) {
        OkHttpClient client = getClient();
        RequestBody body = new FormBody.Builder()
                .add("testo", testo)
                .add("data", data)
                .add("nome", username)
                .add("idpost", idpost)
                .build();
        final Request request = new Request.Builder()
                .post(body)
                .url("http://192.168.43.126/php/publicComment.php")
                .build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                response.close();
            }
        });


    }

    public ArrayList<Commento> getCommentList(String idpost) {
        final ArrayList<Commento> list = new ArrayList<>();
        OkHttpClient client = getClient();
        RequestBody body = new FormBody.Builder()
                .add("idpost", idpost)
                .build();
        final Request request = new Request.Builder()
                .post(body)
                .url("http://192.168.43.126/php/getComments.php")
                .build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                JSONArray jsonArray = null;
                JSONObject jsonObject = null;
                try {
                    jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = (JSONObject) jsonArray.get(i);
                        Commento commento = new Commento();
                        commento.setData(jsonObject.getString("giorno").toString());
                        commento.setTesto(jsonObject.getString("testo").toString());
                        commento.setUser_pubblicazione(jsonObject.getString("utente").toString());
                        list.add(commento);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        return list;
    }

    public void getPost(final String idPost, final TextView titolo, final TextView nome,
                        final TextView data, final ImageView image) {
        final OkHttpClient client = getClient();
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
                    post = new Post();
                    jsonArray = new JSONArray(response.body().string());
                    jsonObject = (JSONObject) jsonArray.get(0);
                    titolo.setText(jsonObject.getString("titolo"));
                    image.setImageResource(R.drawable.immagine);
                    nome.setText(jsonObject.getString("nome"));
                    data.setText(jsonObject.getString("giorno"));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void insertPost(String titolo, String data, String testo, final String idcoomunity) throws IOException {
        final OkHttpClient client = getClient();
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
                Intent i = new Intent(c, PostActivity.class);
                i.putExtra("idcomunity", idcoomunity);
                PendingIntent pendingIntent = PendingIntent.getActivity(c, 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MyAdapter getAdapter(String username) {
        ArrayList<Comunity> lista = listComunity(username);
        MyAdapter adapter = new MyAdapter(lista, c);
        return adapter;
    }

    public AdapterPost PostAdapater(String idComunity) throws IOException {

        ArrayList<Post> lista = getListPost(idComunity);
        AdapterPost adapterPost = new AdapterPost(lista, c);
        return adapterPost;

    }

    private OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(6, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS).build();
        return client;

    }
}