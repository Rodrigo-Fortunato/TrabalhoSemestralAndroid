package br.edu.fateczl.trabalhosemestralandroid.controller;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.fateczl.trabalhosemestralandroid.R;

public class PlantaViewHolder extends RecyclerView.ViewHolder {


    TextView tvNomePlant;
    TextView tvFrequenciaRegas;
    TextView tvPrefLuz;
    public PlantaViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNomePlant = itemView.findViewById(R.id.tvNomePlant);
        tvFrequenciaRegas = itemView.findViewById(R.id.tvFrequenciaRegas);
        tvPrefLuz = itemView.findViewById(R.id.tvPrefLuz);
    }

    public TextView getTvPrefLuz() {
        return tvPrefLuz;
    }

    public void setTvPrefLuz(TextView tvPrefLuz) {
        this.tvPrefLuz = tvPrefLuz;
    }


    public TextView getTvNomePlanta() {
        return tvNomePlant;
    }

    public void setTvNomePlanta(TextView tvNomePlanta) {
        this.tvNomePlant = tvNomePlanta;
    }

    public TextView getTvFrequenciaRega() {
        return tvFrequenciaRegas;
    }

    public void setTvFrequenciaRega(TextView tvFrequenciaRega) {
        this.tvFrequenciaRegas = tvFrequenciaRega;
    }


}
