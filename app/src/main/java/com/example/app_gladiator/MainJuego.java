package com.example.app_gladiator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;

public class MainJuego extends AppCompatActivity implements Serializable {

    TextView nombreGladiador, vidaGladiador, fuerzaGladiador, resistenciaGladiador, suerteGladiador, dañoArma, defensaArma, nombreArma;
    ImageView imagenArmaGladi;
    GifImageView gifJugador;
    Button btnPelear,btnRegresar;
    //Jugador
    Gladiador  jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_juego);

        //jugador
        jugador = (Gladiador) getIntent().getExtras().getSerializable("Gladiador");

        //Campos
        nombreGladiador = (TextView) findViewById(R.id.nombreGladiador);
        vidaGladiador = (TextView) findViewById(R.id.vidaGladiador);
        fuerzaGladiador = (TextView) findViewById(R.id.fuerzaGladiador);
        resistenciaGladiador = (TextView) findViewById(R.id.resistenciaGladiador);
        suerteGladiador = (TextView) findViewById(R.id.suerteGladiador);
        dañoArma = (TextView) findViewById(R.id.dañoArma);
        defensaArma = (TextView) findViewById(R.id.defensaArma);
        nombreArma = (TextView) findViewById(R.id.nombreArma);


        //imagen del arma
        imagenArmaGladi = (ImageView) findViewById(R.id.imgArmaGladiador);

        //imagen del jugador
        gifJugador = (GifImageView) findViewById(R.id.gifJugadorArena);

        //Boton
        btnPelear = (Button) findViewById(R.id.btnPelear);
        btnRegresar = (Button) findViewById(R.id.btnRegresar2);


        //LLenar campos
        nombreGladiador.setText(jugador.getNombre() + "\n El gladiador");
        vidaGladiador.setText(String.valueOf(jugador.getVitalidad()));
        fuerzaGladiador.setText(String.valueOf(jugador.getFuerza()));
        resistenciaGladiador.setText(String.valueOf(jugador.getResistencia()));
        suerteGladiador.setText(String.valueOf(jugador.getSuerte()));
        dañoArma.setText(String.valueOf(jugador.getArma().getDaño()));
        defensaArma.setText(String.valueOf(jugador.getArma().getDefensa()));
        nombreArma.setText(String.valueOf(jugador.getArma().getNombre()));

        //Cambia la imagen del arma
        String uri = "@drawable/arma_"+jugador.getArma().getNombre().toLowerCase();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        imagenArmaGladi.setImageDrawable(imagen);


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

        btnPelear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Enviar Gladiador
                Intent intent = new Intent(MainJuego.this, Arena.class);
                intent.putExtra("Gladiador", jugador);
                startActivity(intent);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainJuego.this, armas_gladiador.class);
                intent.putExtra("Gladiador", jugador);
                Log.e("JUGADOR",jugador.getNombre());
                startActivity(intent);
            }
        });
    }
}