package com.example.app_gladiator;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {
    ArrayList<Datos> listDatos;

    public AdapterDatos(ArrayList<Datos> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datos,null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;
        RelativeLayout layout;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato = (TextView) itemView.findViewById(R.id.txtHistorial);
        }

        public void asignarDatos(Datos datos) {
            dato.setText(datos.getValor());
            Log.e("Datos",datos.getTAG());
            if(datos.getTAG().equals("Rendir")){
                dato.setTextColor(Color.parseColor("#853D90"));
            }else if(datos.getTAG().equals("Atacar")){
                dato.setTextColor(Color.parseColor("#3A7F85"));
            }else if(datos.getTAG().equals("Cura")){
                dato.setTextColor(Color.parseColor("#5F853A"));
            }else if(datos.getTAG().equals("Monstruo")){
                dato.setTextColor(Color.parseColor("#894339"));
            }

        }
    }
}
