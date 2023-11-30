package com.example.tweeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class homePage extends AppCompatActivity implements AñadirTweetARecyclerView {
    private Tweet_RecyclerViewAdapter tweet_recyclerViewAdapter;
    private GeneraciónListaTweets generaciónListaTweets;
    private FloatingActionButton añadirTweet;
    private int idUsuario;
    private ImageView perfilCabezeraView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        perfilCabezeraView.setOnClickListener(v -> {
            Intent intent = new Intent(homePage.this, profile_page.class);
            intent.putExtra("idUsuario", 0);
        });


        generaciónListaTweets = new GeneraciónListaTweets();
        generaciónListaTweets.generarListaTweets();

        RecyclerView recyclerView = findViewById(R.id.tweet_list_recycler);
        tweet_recyclerViewAdapter = new Tweet_RecyclerViewAdapter(recyclerView.getContext(), generaciónListaTweets);

        recyclerView.setAdapter(tweet_recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Esto envia el id del usuario para añadirlo al nuevo tweet
        añadirTweet = findViewById(R.id.añadirTweetButton);
        añadirTweet.setOnClickListener(v -> {
            idUsuario = getContenidoIntent();
            AddTweetContent addTweetContent = new AddTweetContent(idUsuario);
            addTweetContent.show(getSupportFragmentManager(), "");
        });
    }

    @Override
    public void añadirTweetARecyclerView(Tweet tweet) {
        generaciónListaTweets.addTweet(tweet);
        tweet_recyclerViewAdapter.notifyDataSetChanged();
    }

    public int getContenidoIntent() {
        Intent intent = getIntent();
        int idUsuario = intent.getIntExtra("usuario", -1);

        return idUsuario;
    }
}