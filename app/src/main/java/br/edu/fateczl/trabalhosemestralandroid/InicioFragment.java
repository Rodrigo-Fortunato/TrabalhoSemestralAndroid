package br.edu.fateczl.trabalhosemestralandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.trabalhosemestralandroid.controller.IClickListener;
import br.edu.fateczl.trabalhosemestralandroid.controller.IController;
import br.edu.fateczl.trabalhosemestralandroid.controller.PlantaAdapter;
import br.edu.fateczl.trabalhosemestralandroid.controller.PlantaEstufaController;
import br.edu.fateczl.trabalhosemestralandroid.controller.PlantaExternaController;
import br.edu.fateczl.trabalhosemestralandroid.controller.PlantaInternaController;
import br.edu.fateczl.trabalhosemestralandroid.model.Planta;
import br.edu.fateczl.trabalhosemestralandroid.model.PlantaEstufa;
import br.edu.fateczl.trabalhosemestralandroid.model.PlantaExterna;
import br.edu.fateczl.trabalhosemestralandroid.model.PlantaInterna;
import br.edu.fateczl.trabalhosemestralandroid.persistence.PlantaEstufaDAO;
import br.edu.fateczl.trabalhosemestralandroid.persistence.PlantaExternaDAO;
import br.edu.fateczl.trabalhosemestralandroid.persistence.PlantaInternaDAO;


public class InicioFragment extends Fragment implements IClickListener<Planta> {

    private RecyclerView recyclerView;
    private PlantaAdapter plantaAdapter;

    IController controller;
    private View view;

    public InicioFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        recyclerView = view.findViewById(R.id.rvListaPlantas);
        ArrayList<Planta> plantas = new ArrayList<>();
        IController<Planta> controller;



          plantas =ListarPlantas();


        plantaAdapter = new PlantaAdapter(this,view.getContext(), plantas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(plantaAdapter);

        return view;
    }

    private ArrayList<Planta> ListarPlantas() {

        ArrayList<Planta> plantas = new ArrayList<>();
        try {
            controller = new PlantaInternaController(new PlantaInternaDAO(view.getContext()));
            plantas.addAll(controller.findALL());
            for (Planta planta:plantas){
                Log.i("Teste nome",String.valueOf(planta.getNome()));
                Log.i("Teste rega", String.valueOf(planta.getFrequenciaRega()));
                Log.i("Teste luz",planta.getPreferenciaLuz());
            }
            controller = new PlantaExternaController(new PlantaExternaDAO(view.getContext()));
            plantas.addAll(controller.findALL());
            controller = new PlantaEstufaController(new PlantaEstufaDAO(view.getContext()));
            plantas.addAll(controller.findALL());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  plantas;
    }

    @Override
    public void onClick(Planta planta) {
        Bundle bundle = new Bundle();
        if (planta instanceof PlantaInterna){
            bundle.putString("tipo","PlantaInterna");
            Log.i("tipo","PlantaInterna");
            bundle.putString("id", String.valueOf(planta.getId()));
        }
        if (planta instanceof PlantaExterna){
            bundle.putString("tipo","PlantaExterna");
            Log.i("tipo","PlantaExterna");
            bundle.putString("id", String.valueOf(planta.getId()));
        }
        if (planta instanceof PlantaEstufa){
            bundle.putString("tipo","PlantaEstufa");
            Log.i("tipo","PlantaEstufa");
            bundle.putString("id", String.valueOf(planta.getId()));
        }


        bundle.putString("id", String.valueOf(planta.getId()));
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, new HistoricoPlantaFragment(bundle));
        transaction.addToBackStack(null);
        transaction.commit();
    }




}