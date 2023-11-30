package com.example.tweeter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class GeneraciónListaTweets {
    ArrayList<Tweet> listaTweets = new ArrayList<>();
    String nombreUsuario;
    int imagenPerfil;

    public void generarListaTweets() {
        for (int i = 0; i < 20; i++) {
            int idUsuario = i % 4;  // ID de usuario cíclico entre 0, 1, 2 y 3
            LocalDateTime fechaTweet = LocalDateTime.now();
            Date fecha = java.util.Date.from(fechaTweet.atZone(java.time.ZoneId.systemDefault()).toInstant());

            switch (idUsuario) {
                case 0:
                    nombreUsuario = "Álvaro";
                    imagenPerfil = R.drawable.perfil0;
                    break;
                case 1:
                    nombreUsuario = "Luis";
                    imagenPerfil = R.drawable.perfil1;
                    break;
                case 2:
                    nombreUsuario = "Nuria";
                    imagenPerfil = R.drawable.perfil2;
                    break;
                case 3:
                    nombreUsuario = "Marina";
                    imagenPerfil = R.drawable.perfil3;
                    break;
                default:
                    nombreUsuario = "Administrador";
                    imagenPerfil = R.drawable.perfil_default;
            }

            listaTweets.add(new Tweet(idUsuario, nombreUsuario, "Este es el Tweet #" + i, fecha, imagenPerfil));
        }
    }

    public void mostrarListaTweetsUsuario(int idUsuario) {
        for (Tweet tweet : listaTweets) {
            if (tweet.getIdUsuario() == idUsuario) {
                System.out.println(tweet);
            }
        }
    }

    public void remove(int position) {
        listaTweets.remove(position);
    }

    public int getSize() {
        return listaTweets.size();
    }

    public Tweet getTwit(int position) {
        return listaTweets.get(position);
    }

    public void addTweet(Tweet tweet) {
        listaTweets.add(tweet);
    }
}
