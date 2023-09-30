package com.example.app_gladiator;

public class Datos {
    private String TAG;
    private String valor;
    public Datos(String _TAG, String _valor) {
        TAG = _TAG;
        valor = _valor;
    }

    public String getTAG() {
        return TAG;
    }

    public String getValor() {
        return valor;
    }
}
