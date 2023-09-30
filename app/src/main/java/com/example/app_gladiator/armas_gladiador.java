package com.example.app_gladiator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class armas_gladiador extends AppCompatActivity implements Serializable {

    TextView txtTitulo1, txtTitulo2, txtTitulo3, txtTitulo4;
    TextView txtAtaque1, txtAtaque2, txtAtaque3, txtAtaque4;
    TextView txtDefen1, txtDefen2, txtDefen3, txtDefen4;
    TextView txtDescrip1, txtDescrip2, txtDescrip3, txtDescrip4;

    ImageView imgArma1, imgArma2,imgArma3,imgArma4;

    Button btnCrear,btnRegresar;

    Arma armaGladiador;
    //Jugador
    Gladiador  jugador;

    //Creo armas con la clase
    public Arma arma1 = new Arma("Murmillo", 2,2, "\nEspada con poder medio equipada escudo con defensa media");
    public Arma arma2 = new Arma("Hoplomachus", 3,1, "\nGran lanza con alto poder equipada con un escudo chico con defensa baja");
    public Arma arma3 = new Arma("Dimachaeri", 4,0, "\nDos espadas que juntas tienen alto poder pero sin defensa");
    public Arma arma4 = new Arma("Secutor", 1,3, "\nEspada con poder bajo equipada con un gran escudo con defensa alta");


    public boolean armaSeleccionada = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armas_gladiador);

        //jugador
        if((Gladiador) getIntent().getExtras().getSerializable("Gladiador") != null){
            jugador = (Gladiador) getIntent().getExtras().getSerializable("Gladiador");
        }


        //Busco los campos
        txtTitulo1 = (TextView) findViewById(R.id.txtTitulo1);
        txtTitulo2 = (TextView) findViewById(R.id.txtTitulo2);
        txtTitulo3 = (TextView) findViewById(R.id.txtTitulo3);
        txtTitulo4 = (TextView) findViewById(R.id.txtTitulo4);

        txtAtaque1 = (TextView) findViewById(R.id.txtAtaque1);
        txtAtaque2 = (TextView) findViewById(R.id.txtAtaque2);
        txtAtaque3 = (TextView) findViewById(R.id.txtAtaque3);
        txtAtaque4 = (TextView) findViewById(R.id.txtAtaque4);

        txtDefen1 = (TextView) findViewById(R.id.txtDefen1);
        txtDefen2 = (TextView) findViewById(R.id.txtDefen2);
        txtDefen3 = (TextView) findViewById(R.id.txtDefen3);
        txtDefen4 = (TextView) findViewById(R.id.txtDefen4);

        txtDescrip1 = (TextView) findViewById(R.id.txtHabili1);
        txtDescrip2 = (TextView) findViewById(R.id.txtHabili2);
        txtDescrip3 = (TextView) findViewById(R.id.txtHabili3);
        txtDescrip4 = (TextView) findViewById(R.id.txtHabili4);

        //Imagenes

        imgArma1 = (ImageView) findViewById(R.id.imgArma1);
        imgArma2 = (ImageView) findViewById(R.id.imgArma2);
        imgArma3 = (ImageView) findViewById(R.id.imgArma3);
        imgArma4 = (ImageView) findViewById(R.id.imgArma4);

        //Boton
        btnCrear = (Button) findViewById(R.id.btnCrearGladiador);
        btnRegresar = (Button) findViewById(R.id.btnRegresar4);


        //Titulos
        txtTitulo1.setText(arma1.getNombre());
        txtTitulo2.setText(arma2.getNombre());
        txtTitulo3.setText(arma3.getNombre());
        txtTitulo4.setText(arma4.getNombre());

        //Ataque arma
        txtAtaque1.setText("Poder : +" + String.valueOf(arma1.getDaño()));
        txtAtaque2.setText("Poder : +" + String.valueOf(arma2.getDaño()));
        txtAtaque3.setText("Poder : +" + String.valueOf(arma3.getDaño()));
        txtAtaque4.setText("Poder : +" + String.valueOf(arma4.getDaño()));

        //Defensa arma
        txtDefen1.setText("Defensa : +" + String.valueOf(arma1.getDefensa()));
        txtDefen2.setText("Defensa : +" + String.valueOf(arma2.getDefensa()));
        txtDefen3.setText("Defensa : +" + String.valueOf(arma3.getDefensa()));
        txtDefen4.setText("Defensa : +" + String.valueOf(arma4.getDefensa()));

        //Descripcion
        txtDescrip1.setText(arma1.getDescripcion());
        txtDescrip2.setText(arma2.getDescripcion());
        txtDescrip3.setText(arma3.getDescripcion());
        txtDescrip4.setText(arma4.getDescripcion());

        //Seleccionar el arma
        imgArma1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarArma(imgArma1, arma1);
            }
        });

        imgArma2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarArma(imgArma2, arma2);
            }
        });

        imgArma3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarArma(imgArma3, arma3);
            }
        });

        imgArma4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarArma(imgArma4, arma4);
            }
        });

        //Regresar
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(armas_gladiador.this, crear_gladiador.class);
                intent.putExtra("Gladiador", jugador);
                startActivity(intent);
            }
        });

        //Crear al gladiador
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(armaSeleccionada){
                //Vitalidad
                int vitalidadSubida = getIntent().getIntExtra("vitalidad",0);

                //Fuerza
                int fuerzaSubida = getIntent().getIntExtra("fuerza",0);

                //Resistencia
                int resistenciaSubida = getIntent().getIntExtra("resistencia",0);

                //Suerte
                int suerteSubida = getIntent().getIntExtra("suerte",0);

                //Nombre del Gladiador
                String nombreGladiador = getIntent().getStringExtra("nombreGladiador");

                //Creo el gladiador
                if(jugador == null){
                    jugador = new Gladiador(nombreGladiador, vitalidadSubida, fuerzaSubida, resistenciaSubida, suerteSubida, armaGladiador);
                }

                //Enviar Gladiador
                Intent intent = new Intent(armas_gladiador.this, MainJuego.class);
                intent.putExtra("Gladiador", jugador);
                startActivity(intent);
            }else{
                Toast.makeText(armas_gladiador.this,"Selecciona el arma de tu gladiador", Toast.LENGTH_SHORT).show();
            }
            }
        });

    }

    public void selecionarArma(ImageView idArmaSeleccionada, Arma armaGladiadorSelect){
        armaSeleccionada = true;
        Drawable d = getResources().getDrawable(R.drawable.fondo);
        imgArma1.setBackgroundResource(0);
        imgArma2.setBackgroundResource(0);
        imgArma3.setBackgroundResource(0);
        imgArma4.setBackgroundResource(0);

        armaGladiador = armaGladiadorSelect;

        idArmaSeleccionada.setBackground(d);
    }
}