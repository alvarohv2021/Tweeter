package com.example.tweeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class profile_page extends AppCompatActivity {
    private Tweet_RecyclerViewAdapter tweet_recyclerViewAdapter;
    private ImageView imagenCabezeraProfilePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);


        imagenCabezeraProfilePage = findViewById(R.id.imagenCabezeraProfilePageView);
        imagenCabezeraProfilePage.setOnClickListener(v -> {
            Intent intent = new Intent(profile_page.this, homePage.class);
            intent.putExtra("listaTweets", listaTweets);
            startActivity(intent);
        });

        ArrayList<Tweet> tweetsUsuario = getListaTweetsByUser(getContenidoIntent(), 0);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProfilePage);
        tweet_recyclerViewAdapter = new Tweet_RecyclerViewAdapter(recyclerView.getContext(), tweetsUsuario);

        recyclerView.setAdapter(tweet_recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public ArrayList<Tweet> getContenidoIntent() {
        Intent intent = getIntent();
        ArrayList<Tweet> listaTweets = (ArrayList<Tweet>) intent.getSerializableExtra("listaTweets");

        return listaTweets;
    }

    public ArrayList<Tweet> getListaTweetsByUser(ArrayList<Tweet> listaTweets, int idUsuario) {
        ArrayList<Tweet> tweetsPropios = new ArrayList<>();

        for (Tweet tweet : listaTweets) {
            if (tweet.getIdUsuario() == idUsuario) {
                tweetsPropios.add(tweet);
            }
        }

        return tweetsPropios;
    }

}