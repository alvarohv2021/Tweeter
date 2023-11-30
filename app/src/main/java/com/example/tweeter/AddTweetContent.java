package com.example.tweeter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.time.LocalDateTime;

public class AddTweetContent extends AppCompatDialogFragment {

    public EditText viewContenidoTweet;
    private AñadirTweetARecyclerView añadirTweetARecyclerView;

    private int id;

    public AddTweetContent(int id) {
        this.id = id;
    }


    public Dialog onCreateDialog(@NonNull Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.upload_tweet_content, null);

        viewContenidoTweet = view.findViewById(R.id.viewContenidoTweet);

        Log.d("Hola", "Hola mundo desde el listener");

        builder.setView(view).setPositiveButton("Upload", (dialogInterface, i) -> {

            String contenidoTweet = viewContenidoTweet.getText().toString();
            //Fecha de creación del Tweet
            java.util.Date fechaAhora = java.util.Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant());

            Tweet tweet = new Tweet(this.id, "Alvaro", contenidoTweet, fechaAhora, R.drawable.perfil0);
            añadirTweetARecyclerView.añadirTweetARecyclerView(tweet);
        });

        builder.setView(view).setNegativeButton("Cancel", (dialogInterface, i) -> {

        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        añadirTweetARecyclerView = (AñadirTweetARecyclerView) context;
    }

}

