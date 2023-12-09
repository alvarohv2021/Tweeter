package com.example.tweeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class homePage extends AppCompatActivity implements AñadirTweetARecyclerView {
    private Tweet_RecyclerViewAdapter tweet_recyclerViewAdapter;
    private GeneracionListaTweets generacionListaTweets;
    private FloatingActionButton añadirTweet;
    private int idUsuario;
    private MaterialCardView perfilCabezeraView;
    private ArrayList<Tweet> listaTweets;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        generacionListaTweets = new GeneracionListaTweets();


        if (getContenidoIntentPerfil() == null){
            listaTweets = generacionListaTweets.generarListaTweets();
        }else {
            listaTweets = getContenidoIntentPerfil();
        }

        recyclerView = findViewById(R.id.tweet_list_recycler);
        tweet_recyclerViewAdapter = new Tweet_RecyclerViewAdapter(recyclerView.getContext(), listaTweets);

        recyclerView.setAdapter(tweet_recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Esto envia el id del usuario para añadirlo al nuevo tweet
        añadirTweet = findViewById(R.id.añadirTweetButton);
        añadirTweet.setOnClickListener(v -> {
            idUsuario = getContenidoIntentRegister();
            AddTweetContent addTweetContent = new AddTweetContent(listaTweets.size() + 1, idUsuario);
            addTweetContent.show(getSupportFragmentManager(), "");
        });

        // Esta parte haze de la imágen de perfil de usuario, un botón que inicia una activity "profile_page.java"
        perfilCabezeraView = findViewById(R.id.homeImagenCabezeraView);
        perfilCabezeraView.setOnClickListener(v -> {
            Intent intent = new Intent(homePage.this, profile_page.class);
            intent.putExtra("listaTweets", listaTweets);
            startActivity(intent);
        });
    }

    /*public void onResume() {
        super.onResume();
        //Esto detecta si se le ha pasado una lista de Tweets por el perfil, y si es el caso, DEBERIA mostrarla
        listaTweets = getContenidoIntentPerfil();

        tweet_recyclerViewAdapter = new Tweet_RecyclerViewAdapter(recyclerView.getContext(), listaTweets);

        recyclerView.setAdapter(tweet_recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }*/

    @Override
    public void añadirTweetARecyclerView(Tweet tweet) {
        generacionListaTweets.addTweet(tweet);
        tweet_recyclerViewAdapter.notifyDataSetChanged();
    }

    public int getContenidoIntentRegister() {
        Intent intent = getIntent();
        int idUsuario = intent.getIntExtra("usuario", -1);

        return idUsuario;
    }

    public ArrayList<Tweet> getContenidoIntentPerfil() {
        Intent intent = getIntent();
        ArrayList<Tweet> listaTweetsPerfil = (ArrayList<Tweet>) intent.getSerializableExtra("listaTweets");

        return listaTweetsPerfil;
    }
}