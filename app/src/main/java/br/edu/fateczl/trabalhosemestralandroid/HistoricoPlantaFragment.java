package br.edu.fateczl.trabalhosemestralandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;

import br.edu.fateczl.trabalhosemestralandroid.controller.IController;
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


public class HistoricoPlantaFragment extends Fragment {

    private Button btnVoltar,btnEditar,btnExcluir;
    private TextView tvResultNomePlanta,tvResultPreferenciaLuz,tvResultFrequenciaRega,tvResultSensibilidadePragas,
            tvNotas,tvResultGenerico1,tvResultGenerico2,tvResultGenerico3,tvGenerico1,tvGenerico2,tvGenerico3;
    Bundle bundle;
    private View view;
    IController controller;

    public HistoricoPlantaFragment(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_historico_planta, container, false);


        tvResultNomePlanta = view.findViewById(R.id.tvResultNomePlanta);
        tvResultPreferenciaLuz = view.findViewById(R.id.tvResultPreferenciaLuz);
        tvResultFrequenciaRega = view.findViewById(R.id.tvResultFrequenciaRega);
        tvResultSensibilidadePragas = view.findViewById(R.id.tvResultSensibilidadePragas);
        tvResultGenerico1 = view.findViewById(R.id.tvResultGenerico1);
        tvResultGenerico2 = view.findViewById(R.id.tvResultGenerico2);
        tvResultGenerico3 = view.findViewById(R.id.tvResultGenerico3);
        tvGenerico1 = view.findViewById(R.id.tvGenerico1);
        tvGenerico2 = view.findViewById(R.id.tvGenerico2);
        tvGenerico3 = view.findViewById(R.id.tvGenerico3);
        tvNotas = view.findViewById(R.id.tvNotas);
        btnVoltar = view.findViewById(R.id.btnVoltar4);
        btnEditar = view.findViewById(R.id.btnEditar);
        btnExcluir = view.findViewById(R.id.btnExcluir);


        PreencherDadosTela();

        btnExcluir.setOnClickListener(op -> ExcluirPlanta());
        btnVoltar.setOnClickListener(op-> VoltarInicio());



        return view;
    }

    private void VoltarInicio() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, new InicioFragment());
        transaction.commit();
    }


    private void ExcluirPlanta() {
        String tipo = bundle.getString("tipo");
        String id = bundle.getString("id");
        if (tipo.equals("PlantaInterna")) {
            controller = new PlantaInternaController(new PlantaInternaDAO(view.getContext()));
            try {
                PlantaInterna planta = new PlantaInterna();
                planta.setId(Integer.parseInt(id));
                controller.delete(planta);


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (tipo.equals("PlantaExterna")) {
            controller = new PlantaExternaController(new PlantaExternaDAO(view.getContext()));
            try {
                PlantaExterna planta = new PlantaExterna();
                planta.setId(Integer.parseInt(id));
                controller.delete(planta);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (tipo.equals("PlantaEstufa")) {
            controller = new PlantaEstufaController(new PlantaEstufaDAO(view.getContext()));
            try {
                PlantaEstufa planta = new PlantaEstufa();
                planta.setId(Integer.parseInt(id));
                controller.delete(planta);


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
     VoltarInicio();
    }

    private void PreencherDadosTela(){

        String tipo = bundle.getString("tipo");
        String id = bundle.getString("id");

        if (tipo.equals("PlantaInterna")) {
            controller = new PlantaInternaController(new PlantaInternaDAO(view.getContext()));
            try {
                PlantaInterna planta = new PlantaInterna();
                planta.setId(Integer.parseInt(id));
                planta = (PlantaInterna) controller.findOne(planta);

                tvGenerico1.setText(R.string.tvTamanhoVaso);
                tvGenerico2.setText(R.string.tvTempoLuz);


                tvResultNomePlanta.setText(planta.getNome());
                tvResultPreferenciaLuz.setText(planta.getPreferenciaLuz());
                tvResultFrequenciaRega.setText(String.valueOf(planta.getFrequenciaRega()));
                tvResultSensibilidadePragas.setText(planta.getSensibilidadePragas());
                tvResultGenerico1.setText(String.valueOf(planta.getTamanhoVaso()));
                tvResultGenerico2.setText(String.valueOf(planta.getTempoLuz()));
                tvNotas.setText(planta.getNotas());


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (tipo.equals("PlantaExterna")) {
            controller = new PlantaExternaController(new PlantaExternaDAO(view.getContext()));
            try {
                PlantaExterna planta = new PlantaExterna();
                planta.setId(Integer.parseInt(id));
                planta = (PlantaExterna) controller.findOne(planta);

                tvGenerico1.setText(R.string.tvResistenciaChuva);
                tvGenerico2.setText(R.string.tvToleranciaVento);

                tvResultNomePlanta.setText(planta.getNome());
                tvResultPreferenciaLuz.setText(planta.getPreferenciaLuz());
                tvResultFrequenciaRega.setText(String.valueOf(planta.getFrequenciaRega()));
                tvResultSensibilidadePragas.setText(planta.getSensibilidadePragas());
                tvResultGenerico1.setText(planta.getResistenciaChuva());
                tvResultGenerico2.setText(planta.getToleranciaVento());
                tvNotas.setText(planta.getNotas());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (tipo.equals("PlantaEstufa")) {
            controller = new PlantaEstufaController(new PlantaEstufaDAO(view.getContext()));
            try {
                PlantaEstufa planta = new PlantaEstufa();
                planta.setId(Integer.parseInt(id));
                planta = (PlantaEstufa)  controller.findOne(planta);

                tvGenerico1.setText(R.string.tvHumidadeIdeal);
                tvGenerico2.setText(R.string.tvTemperaturaIdeal);
                tvGenerico3.setText(R.string.tvResistenciaAlteracoesAmbientais);

                tvResultNomePlanta.setText(planta.getNome());
                tvResultPreferenciaLuz.setText(planta.getPreferenciaLuz());
                tvResultFrequenciaRega.setText(String.valueOf(planta.getFrequenciaRega()));
                tvResultSensibilidadePragas.setText(planta.getSensibilidadePragas());
                tvResultGenerico1.setText(String.valueOf(planta.getHumidadeIdeal()));
                tvResultGenerico2.setText(String.valueOf(planta.getTemperaturaIdeal()));
                tvResultGenerico3.setText(planta.getResistAlteracoesAmbientais());
                tvNotas.setText(planta.getNotas());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }


}