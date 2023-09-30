package com.example.app_gladiator;

import java.io.Serializable;

public class Arma implements Serializable {

    public String nombre;
    public int daño;
    public int defensa;
    public String descripcion;


    public Arma(String nombre, int daño, int defensa, String descripcion) {
        this.nombre = nombre;
        this.daño = daño;
        this.defensa = defensa;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDaño() {
        return daño;
    }

    public int getDefensa() {
        return defensa;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
