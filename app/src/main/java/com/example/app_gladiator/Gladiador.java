package com.example.app_gladiator;


import java.io.Serializable;

public class Gladiador implements Serializable {

    public String nombre;
    public int vitalidad;
    public int fuerza;
    public int resistencia;
    public int suerte;
    public Arma arma;

    public Gladiador(String nombre, int vitalidad, int fuerza, int resistencia, int suerte, Arma arma) {
        this.nombre = nombre;
        //Defino la vitalidad
        int vitalidadBase = 100;
        this.vitalidad = vitalidadBase + (25 * vitalidad);
        //Defino la fuerza
        int fuerzaBase = 9;
        this.fuerza = fuerzaBase + fuerza;
        //Defino la resistencia
        int resistenciaBase = 2;
        this.resistencia = resistenciaBase * resistencia;
        //Defino la suerte
        int suerteBase = 2;
        this.suerte = suerteBase + suerte;
        this.arma = arma;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVitalidad() {
        return vitalidad;
    }

    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getSuerte() {
        return suerte;
    }

    public void setSuerte(int suerte) {
        this.suerte = suerte;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public int atacar(){
        int ataque = (getFuerza() + arma.getDaño()) * critico();
        return ataque;
    }

    public int defender(){
        int defenza = getResistencia() + arma.getDefensa();
        return defenza;
    }

    public int curarse(){
        int maximo = 20;
        int minimo = 10 * critico();
        int curacion = (int)Math.floor(Math.random()*(maximo-minimo+1)+(minimo));
        return curacion;
    }

    public int critico(){
        double random =  Math.random()*11;
        int critico = 1;
        if(random <= getSuerte()){
            critico = 2;
        }
        return critico;
    }
}
