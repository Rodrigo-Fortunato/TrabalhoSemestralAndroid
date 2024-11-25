package br.edu.fateczl.trabalhosemestralandroid.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.edu.fateczl.trabalhosemestralandroid.R;
import br.edu.fateczl.trabalhosemestralandroid.model.Planta;
import br.edu.fateczl.trabalhosemestralandroid.model.PlantaInterna;

public class PlantaAdapter extends RecyclerView.Adapter<PlantaViewHolder> {


    private IClickListener<Planta> plantaIClickListener ;
    private Context context;
    private ArrayList<Planta> plantas;

    public PlantaAdapter(IClickListener<Planta> plantaIClickListener, Context context, ArrayList<Planta> plantas) {
        this.plantaIClickListener = plantaIClickListener;
        this.context = context;
        this.plantas = plantas;
    }

    @NonNull
    @Override
    public PlantaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_planta,parent,false);
        return new PlantaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantaViewHolder plantaViewHolder, int position) {

        plantaViewHolder.tvNomePlant.setText(plantas.get(position).getNome());
        plantaViewHolder.tvFrequenciaRegas.setText(String.valueOf(plantas.get(position).getFrequenciaRega()));
        plantaViewHolder.tvPrefLuz.setText(String.valueOf(plantas.get(position).getPreferenciaLuz()));

        plantaViewHolder.itemView.setOnClickListener(v -> plantaIClickListener.onClick(plantas.get(position)) );


    }

    @Override
    public int getItemCount() {
        return plantas.size() ;
    }
}
