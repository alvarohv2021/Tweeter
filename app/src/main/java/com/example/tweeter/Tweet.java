package com.example.tweeter;

import java.io.Serializable;
import java.util.Date;

public class Tweet implements Serializable {
    public int idTweet;
    private int idUsuario;
    private String nombreUsuario;
    private String contenido;
    private String fecha;
    private int imagen;

    public Tweet(int idTweet, int idUsuario, String nombreUsuario, String contenido, String fecha, int imagen) {

        this.idTweet = idTweet;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contenido = contenido;
        this.fecha = fecha;
        this.imagen = imagen;
    }

    // Getters y setters

    @Override
    public String toString() {
        return "ID Tweet " + idTweet + " ID Usuario: " + idUsuario + ", Contenido: " + contenido + ", Fecha: " + fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return this.nombreUsuario;
    }

    public String getContenido() {
        return this.contenido;
    }

    public String getFecha() {
        return this.fecha;
    }

    public int getImagenPerfil() {
        return imagen;
    }
}


