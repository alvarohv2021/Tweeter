package com.example.tweeter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTweetContent extends AppCompatDialogFragment {

    public EditText viewContenidoTweet;
    private AñadirTweetARecyclerView añadirTweetARecyclerView;
    private int idUsuario;
    private int idTweet;

    public AddTweetContent(int idTweet, int idUsuario) {
        this.idTweet = idTweet;
        this.idUsuario = idUsuario;
    }


    public Dialog onCreateDialog(@NonNull Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.upload_tweet_content, null);

        viewContenidoTweet = view.findViewById(R.id.viewContenidoTweet);

        builder.setView(view).setPositiveButton("Upload", (dialogInterface, i) -> {

            String contenidoTweet = viewContenidoTweet.getText().toString();

            //Fecha de creación del Tweet
            Date fechaAhora = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String fechaAhoraFormateada = sdf.format(fechaAhora);

            Tweet tweet = new Tweet(this.idTweet, this.idUsuario, "Alvaro", contenidoTweet, fechaAhoraFormateada, R.drawable.perfil0);
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

