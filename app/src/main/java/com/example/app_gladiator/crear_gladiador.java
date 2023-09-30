package com.example.app_gladiator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class crear_gladiador extends AppCompatActivity {

    //Objetos de la vista
    TextView txtNumVitalidad,txtNumFuerza, txtNumResistencia, txtNumSuerte, txtNumMaxHabilidad;
    ImageView menosVitalidad,masVitalidad, menosFuerza, masFuerza, menosResistencia, masResistencia, menosSuerte, masSuerte;
    Button btnSiguienteCreacion;
    EditText txtNombreGladiador;

    //Variables globales
    public int numMaxHabilidad = 0;
    public int numVitaActual = 0;
    public int numFuerzaActual = 0;
    public int numResisActual = 0;
    public int numSuerteActual = 0;

    //Funcion de actualizar valor de atributos maximos
    public void actualizaMaxHabilidad(){
        numMaxHabilidad = numVitaActual + numFuerzaActual + numResisActual + numSuerteActual;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_gladiador);

        txtNombreGladiador = (EditText) findViewById(R.id.txtNombreGladiador);

        //Número de habilidad
        txtNumVitalidad = (TextView) findViewById(R.id.txtNumVitalidad);
        txtNumFuerza = (TextView) findViewById(R.id.txtNumFuerza);
        txtNumResistencia = (TextView) findViewById(R.id.txtNumResistencia);
        txtNumSuerte = (TextView) findViewById(R.id.txtNumSuerte);
        txtNumMaxHabilidad = (TextView) findViewById(R.id.txtMaxHabilidad);

        txtNumMaxHabilidad.setText(String.valueOf(6 - numMaxHabilidad) +" disponibles");
        btnSiguienteCreacion = (Button) findViewById(R.id.btnSiguienteCreacion);

        //Botones menos
        menosVitalidad = (ImageView) findViewById(R.id.imgMenosVital);
        menosFuerza = (ImageView) findViewById(R.id.imgMenosFuerza);
        menosResistencia = (ImageView) findViewById(R.id.imgMenosResis);
        menosSuerte = (ImageView) findViewById(R.id.imgMenosSuerte);

        //Botones mas
        masVitalidad = (ImageView) findViewById(R.id.imgMasVital);
        masFuerza = (ImageView) findViewById(R.id.imgMasFuerza);
        masResistencia = (ImageView) findViewById(R.id.imgMasResis);
        masSuerte = (ImageView) findViewById(R.id.imgMasSuerte);

        //Valores de los campos de atributos
        numVitaActual = Integer.parseInt(String.valueOf(txtNumVitalidad.getText()));
        numFuerzaActual = Integer.parseInt(String.valueOf(txtNumFuerza.getText()));
        numResisActual = Integer.parseInt(String.valueOf(txtNumResistencia.getText()));
        numSuerteActual = Integer.parseInt(String.valueOf(txtNumSuerte.getText()));



        //Acciones de restar puntos
        menosVitalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( numVitaActual > 0 ){
                    numVitaActual--;
                    actualizaMaxHabilidad();
                    txtNumVitalidad.setText(String.valueOf(numVitaActual));
                }
                txtNumMaxHabilidad.setText(String.valueOf(6 - numMaxHabilidad) +" disponibles");
            }
        });

        menosFuerza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( numFuerzaActual > 0 ) {
                    numFuerzaActual--;
                    actualizaMaxHabilidad();
                    txtNumFuerza.setText(String.valueOf(numFuerzaActual));
                }
                txtNumMaxHabilidad.setText(String.valueOf(6 - numMaxHabilidad) +" disponibles");
            }
        });

        menosResistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( numResisActual > 0 ) {
                    numResisActual--;
                    actualizaMaxHabilidad();
                    txtNumResistencia.setText(String.valueOf(numResisActual));
                }
                txtNumMaxHabilidad.setText(String.valueOf(6 - numMaxHabilidad) +" disponibles");
            }
        });

        menosSuerte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( numSuerteActual > 0) {
                    numSuerteActual--;
                    actualizaMaxHabilidad();
                    txtNumSuerte.setText(String.valueOf(numSuerteActual));
                }
                txtNumMaxHabilidad.setText(String.valueOf(6 - numMaxHabilidad) +" disponibles");
            }
        });


        //Acciones de sumar puntos
        masVitalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numVitaActual < 6 && (numMaxHabilidad + 1) <= 6) {
                    numVitaActual++;
                    actualizaMaxHabilidad();
                    txtNumVitalidad.setText(String.valueOf(numVitaActual));
                }
                txtNumMaxHabilidad.setText(String.valueOf(6 - numMaxHabilidad) +" disponibles");
            }
        });

        masFuerza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numFuerzaActual < 6 && (numMaxHabilidad + 1) <= 6) {
                    numFuerzaActual++;
                    actualizaMaxHabilidad();
                    txtNumFuerza.setText(String.valueOf(numFuerzaActual));
                }
                txtNumMaxHabilidad.setText(String.valueOf(6 - numMaxHabilidad) +" disponibles");
            }
        });

        masResistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numResisActual < 6 && (numMaxHabilidad + 1) <= 6) {
                    numResisActual++;
                    actualizaMaxHabilidad();
                    txtNumResistencia.setText(String.valueOf(numResisActual));
                }
                txtNumMaxHabilidad.setText(String.valueOf(6 - numMaxHabilidad) +" disponibles");
            }
        });

        masSuerte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numSuerteActual < 6 && (numMaxHabilidad + 1) <= 6) {
                    numSuerteActual++;
                    actualizaMaxHabilidad();
                    txtNumSuerte.setText(String.valueOf(numSuerteActual));
                }
                txtNumMaxHabilidad.setText(String.valueOf(6 - numMaxHabilidad) +" disponibles");
            }
        });

        //boton siguiente creacion
        btnSiguienteCreacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean siguienteCreacion = true;
                String nombreGladiador = txtNombreGladiador.getText().toString().trim();
                if(nombreGladiador.isEmpty()){
                    Toast.makeText(crear_gladiador.this, "Aún no has nombrado a tu gladiador.", Toast.LENGTH_SHORT).show();
                    siguienteCreacion = false;
                }
                if(siguienteCreacion && numMaxHabilidad != 6){
                    Toast.makeText(crear_gladiador.this, "Aún no has asignado todos los puntos de habilidad a tu gladiador.", Toast.LENGTH_SHORT).show();
                    siguienteCreacion = false;
                }
                if(siguienteCreacion){
                    Intent intent = new Intent(crear_gladiador.this, armas_gladiador.class);
                    intent.putExtra("nombreGladiador", txtNombreGladiador.getText().toString());
                    intent.putExtra("vitalidad", numVitaActual);
                    intent.putExtra("fuerza", numFuerzaActual);
                    intent.putExtra("resistencia", numResisActual);
                    intent.putExtra("suerte", numSuerteActual);
                    startActivity(intent);
                }
            }
        });

    }
}