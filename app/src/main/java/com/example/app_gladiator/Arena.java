package com.example.app_gladiator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class Arena extends AppCompatActivity {

    Button btnAtacar;
    Button btnCurar, btnRegresar;
    ProgressBar barraVidaJugador;
    ProgressBar barraVidaEnemigo;
    TextView numVidaJugador, numVidaEnemigo, nombreJugador, nombreEnemigo, resultadoBatalla;
    GifImageView gifJugador, gifEnemigo;
    Gladiador enemigoSeleccionado = null;
    public int MaxCuraciones = 3;

    private RecyclerView historialMovimientos;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Datos> listDatos;

    private Boolean jugadorGana = false;
    private Boolean enemigoGana = false;
    private Boolean turnoJugador = true;

    //Jugador
    Gladiador  jugador = null;
    //Armas
    public Arma arma1 = new Arma("Murmillo", 2,2, "\nEspada con poder medio equipada escudo con defensa media");
    public Arma arma2 = new Arma("Hoplomachus", 3,1, "\nGran lanza con alto poder equipada con un pequeño escudo con defensa baja");
    public Arma arma3 = new Arma("Dimachaeri", 4,0, "\nDos espadas que juntas tienen alto poder pero sin defensa");
    public Arma arma4 = new Arma("Secutor", 1,3, "\nEspada con poder bajo equipada con un gran escudo con defensa alta");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);


        jugador = (Gladiador) getIntent().getExtras().getSerializable("Gladiador");

        numVidaJugador = (TextView) findViewById(R.id.numVidaJugadorArena);
        numVidaEnemigo = (TextView) findViewById(R.id.numVidaEnemigoArena);
        nombreJugador = (TextView) findViewById(R.id.nombreJugadorArena);
        nombreEnemigo = (TextView) findViewById(R.id.nombreEnemigoArena);
        btnAtacar = (Button) findViewById(R.id.btnAtacar);
        btnCurar = (Button) findViewById(R.id.btnCurar);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);
        barraVidaJugador = (ProgressBar) findViewById(R.id.progressVidaJugador);
        barraVidaEnemigo = (ProgressBar) findViewById(R.id.progressVidaEnemigo);
        resultadoBatalla = (TextView) findViewById(R.id.resultadoBatalla);

        //Historial de movimientos
        historialMovimientos = (RecyclerView) findViewById(R.id.revHistorialMovi);
        historialMovimientos.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        historialMovimientos.setLayoutManager(layoutManager);

        listDatos = new ArrayList<Datos>();


        //LLeno los campos
        nombreJugador.setText(jugador.getNombre());
        numVidaJugador.setText(String.valueOf(jugador.getVitalidad()));
        barraVidaJugador.setMax(jugador.getVitalidad());
        barraVidaJugador.setProgress(jugador.getVitalidad());
        Log.e("ERROR_ARENA", String.valueOf(barraVidaJugador.getProgress()));

        //imagen del jugador
        gifJugador = (GifImageView) findViewById(R.id.gifJugadorArena);
        gifEnemigo = (GifImageView) findViewById(R.id.gifEnemigoArena);


        //Creo a los enemigos
        Gladiador enemigo1 = new Gladiador("Gannicus", 3, 3, 2, 1, arma3);
        Gladiador enemigo2 = new Gladiador("Spartacus", 2, 3, 1, 2, arma1);
        Gladiador enemigo3 = new Gladiador("Enomao", 4, 3, 2, 1, arma2);

        //selecciono a un enemigo
        int numeroEnemigo = (int) (Math.random() * 3) + 1;
        switch (numeroEnemigo){
            case 1:
                enemigoSeleccionado = enemigo1;
                break;
            case 2:
                enemigoSeleccionado = enemigo2;
                break;
            case 3:
                enemigoSeleccionado = enemigo3;
                break;
        }

        //lleno los campos
        nombreEnemigo.setText(enemigoSeleccionado.getNombre());
        numVidaEnemigo.setText(String.valueOf(enemigoSeleccionado.getVitalidad()));
        barraVidaEnemigo.setMax(enemigoSeleccionado.getVitalidad());
        barraVidaEnemigo.setProgress(enemigoSeleccionado.getVitalidad());
        Log.e("ERROR_ARENA", String.valueOf(barraVidaEnemigo.getProgress()));

        //Cambia el gif del jugador
        GifDrawable gifFromResource = null;
        String uriGif = "@drawable/gladiador_jugador_"+jugador.getArma().getNombre().toLowerCase()+"_1";
        int imageResourceGif = getResources().getIdentifier(uriGif, null, getPackageName());
        try {
            gifFromResource = new GifDrawable( getResources(),imageResourceGif );
        } catch (IOException e) {
            e.printStackTrace();
        }

        gifJugador.setImageDrawable(gifFromResource);

        //Cambia el gif del enemigo
        GifDrawable gifFromResourceEnemigo = null;
        String uriGifEnemigo = "@drawable/gladiador_enemigo_"+enemigoSeleccionado.getArma().getNombre().toLowerCase()+"_1";
        //String uriGifEnemigo = "@drawable/gladiador_enemigo_dimachaeri_1";
        int imageResourceGifEnemigo = getResources().getIdentifier(uriGifEnemigo, null, getPackageName());
        try {
            gifFromResourceEnemigo = new GifDrawable( getResources(),imageResourceGifEnemigo );
        } catch (IOException e) {
            e.printStackTrace();
        }

        gifEnemigo.setImageDrawable(gifFromResourceEnemigo);

        //Accion de atacar
        btnAtacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jugadorAtaca();
            }
        });

        //Accion de curar
        btnCurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MaxCuraciones > 0){
                    MaxCuraciones--;
                    btnCurar.setText("CURARSE ("+ String.valueOf(MaxCuraciones)+ ")");
                    jugadorCura();
                    if(MaxCuraciones == 0){
                        btnCurar.setVisibility(View.INVISIBLE);
                    }
                }

            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Arena.this, MainJuego.class);
                intent.putExtra("Gladiador", jugador);
                startActivity(intent);
            }
        });

    }

    public void jugadorAtaca(){
        int dañoJugador = jugador.atacar();
        int vidaEnemigo = barraVidaEnemigo.getProgress();
        int vidaNuevaEnemigo = vidaEnemigo - (dañoJugador - enemigoSeleccionado.defender());
        Log.e("ERROR_ARENA", String.valueOf(vidaEnemigo) + " - " + String.valueOf(vidaNuevaEnemigo));
        barraVidaEnemigo.setProgress(vidaNuevaEnemigo);
        numVidaEnemigo.setText( String.valueOf(barraVidaEnemigo.getProgress()));
        listDatos.add(new Datos("Ataca", jugador.getNombre() + " golpeo a "+ enemigoSeleccionado.getNombre() +" por " + dañoJugador+ " y "+ enemigoSeleccionado.getNombre()+ " defendio por " + enemigoSeleccionado.defender() + " haciendole " + (dañoJugador - enemigoSeleccionado.defender()) + " de poder." ));
        if(vidaNuevaEnemigo > 0){
            accionEnemigo();
        }
        if(vidaNuevaEnemigo <= 0){
            jugadorGana = true;
            btnAtacar.setVisibility(View.INVISIBLE);
            btnCurar.setVisibility(View.INVISIBLE);
            resultadoBatalla.setVisibility(View.VISIBLE);
            btnRegresar.setVisibility(View.VISIBLE);
            resultadoBatalla.setText(jugador.getNombre() + " Gana");
        }
        AdapterDatos adapter =  new AdapterDatos(listDatos);
        historialMovimientos.setAdapter(adapter);
    }

    public void jugadorCura(){
        int vidaJugador = barraVidaJugador.getProgress();
        int curaJugador = jugador.curarse();
        int vidaNuevaJugador = vidaJugador + curaJugador;
        barraVidaJugador.setProgress(vidaNuevaJugador);
        String lblVidaJugador = String.valueOf(barraVidaJugador.getProgress());
        numVidaJugador.setText(lblVidaJugador);
        listDatos.add(new Datos("Cura", jugador.getNombre() + " se curo por " + curaJugador));
        accionEnemigo();
        AdapterDatos adapter =  new AdapterDatos(listDatos);
        historialMovimientos.setAdapter(adapter);
    }

    public void accionEnemigo(){
        int dañoEnemigo = enemigoSeleccionado.atacar();
        int vidaJugador = barraVidaJugador.getProgress();
        int vidaNuevaJugador = vidaJugador - (dañoEnemigo-jugador.defender());
        barraVidaJugador.setProgress(vidaNuevaJugador);
        String lblVidaJugador = String.valueOf(barraVidaJugador.getProgress());
        numVidaJugador.setText(lblVidaJugador );
        listDatos.add(new Datos ("Monstruo",enemigoSeleccionado.getNombre() + " golpeo por " + dañoEnemigo + " y "+ jugador.getNombre()+ " defendio por " + jugador.defender() + " haciendole " + (dañoEnemigo - jugador.defender()) + " de poder." ));
        if(vidaNuevaJugador <= 0){
            enemigoGana = true;
            btnAtacar.setVisibility(View.INVISIBLE);
            btnCurar.setVisibility(View.INVISIBLE);

            resultadoBatalla.setVisibility(View.VISIBLE);
            btnRegresar.setVisibility(View.VISIBLE);
            resultadoBatalla.setText(enemigoSeleccionado.getNombre() + " Gana");
        }
        AdapterDatos adapter =  new AdapterDatos(listDatos);
        historialMovimientos.setAdapter(adapter);
    }
}