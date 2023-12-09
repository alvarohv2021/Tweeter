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
    private ArrayList<Tweet> listaTweets;
    private ArrayList<Tweet> tweetsUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        tweetsUsuario = getListaTweetsByUser(getContenidoIntent(), 0);
        listaTweets = getContenidoIntent();


        RecyclerView recyclerView = findViewById(R.id.recyclerViewProfilePage);
        tweet_recyclerViewAdapter = new Tweet_RecyclerViewAdapter(recyclerView.getContext(), tweetsUsuario);

        recyclerView.setAdapter(tweet_recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        imagenCabezeraProfilePage = findViewById(R.id.imagenCabezeraProfilePageView);
        imagenCabezeraProfilePage.setOnClickListener(v -> {

            tweetsUsuario = tweet_recyclerViewAdapter.getListaTweetsRecyclerView();
            listaTweets = fetchArrayLists(listaTweets, tweetsUsuario);

            Intent intent = new Intent(profile_page.this, homePage.class);
            intent.putExtra("listaTweets", listaTweets);
            startActivity(intent);
        });
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

    public ArrayList<Tweet> fetchArrayLists(ArrayList<Tweet> listaTweets, ArrayList<Tweet> tweetsUsuario) {

        for (int i = 0; i < listaTweets.size(); i++) {
            if (listaTweets.get(i).getIdUsuario() == 0 && !tweetsUsuario.contains(listaTweets.get(i))) {
                listaTweets.remove(i);
            }


        }
        return listaTweets;
    }
}