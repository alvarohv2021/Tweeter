package com.example.tweeter;

import java.io.Serializable;
import java.util.Date;

public class Tweet implements Serializable {
    private int idUsuario;
    private String nombreUsuario;
    private String contenido;
    private Date fecha;
    private int imagen;

    public Tweet(int idUsuario, String nombreUsuario, String contenido, Date fecha, int imagen) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contenido = contenido;
        this.fecha = fecha;
        this.imagen = imagen;
    }

    // Getters y setters

    @Override
    public String toString() {
        return "ID Usuario: " + idUsuario + ", Contenido: " + contenido + ", Fecha: " + fecha;
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

    public Date getFecha() {
        return this.fecha;
    }

    public int getImagenPerfil() {
        return imagen;
    }
}


