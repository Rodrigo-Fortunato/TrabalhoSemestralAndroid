package br.edu.fateczl.trabalhosemestralandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.trabalhosemestralandroid.controller.PlantaExternaController;
import br.edu.fateczl.trabalhosemestralandroid.model.PlantaExterna;
import br.edu.fateczl.trabalhosemestralandroid.persistence.PlantaExternaDAO;


public class CadastroPlantaExternaFragment extends Fragment {

    private View view;
    private EditText etNomePlantaExterna, etFrequenciaRegaExterna, etNotasPlantaExterna;
    private Spinner spPreferenciaLuzExterna, spSensibilidadePragasExterna, spToleranciaVento, spResistenciaChuva;
    private Button btnCadastrarPlantaExterna, btnVoltar;
    private PlantaExternaController externaController;

    public CadastroPlantaExternaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cadastro_planta_externa, container, false);

        etNomePlantaExterna = view.findViewById(R.id.etNomePlantaExterna);
        etFrequenciaRegaExterna = view.findViewById(R.id.etFrequenciaRegaExterna);
        etNotasPlantaExterna = view.findViewById(R.id.etNotasPlantaExterna);
        spPreferenciaLuzExterna = view.findViewById(R.id.spPreferenciaLuzExterna);
        spSensibilidadePragasExterna = view.findViewById(R.id.spsensibilidadePragasExterna);
        btnCadastrarPlantaExterna = view.findViewById(R.id.btnCadastrarPlantaExterna);
        btnVoltar = view.findViewById(R.id.btnVoltar2);

        spToleranciaVento = view.findViewById(R.id.spToleranciaVento);
        spResistenciaChuva = view.findViewById(R.id.spResistenciaChuva);

        externaController = new PlantaExternaController(new PlantaExternaDAO(view.getContext()));
        fillSpinnerPreferenciaLuz();
        fillSpinnerSensibilidadePragas();

        btnCadastrarPlantaExterna.setOnClickListener(op -> acaoInsert());
        btnVoltar.setOnClickListener(op -> acaoVoltar());

        return view;
    }

    private void acaoVoltar() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, new InicioFragment());
        transaction.commit();
    }

    private void acaoInsert() {
        PlantaExterna plantaExterna = makePlantaExterna();
        try {
            externaController.insert(plantaExterna);
            Toast.makeText(view.getContext(), "Planta cadastrada com sucesso.", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            clearFields();
        }
    }

    private void fillSpinnerPreferenciaLuz() {
        List<String> lista = new ArrayList<>();
        lista.add("plena luz");
        lista.add("sombra");
        lista.add("semi-sombra");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPreferenciaLuzExterna.setAdapter(adapter);


    }

    private void fillSpinnerSensibilidadePragas() {
        List<String> lista = new ArrayList<>();
        lista.add("BAIXA");
        lista.add("MEDIA");
        lista.add("ALTA");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSensibilidadePragasExterna.setAdapter(adapter);
        spToleranciaVento.setAdapter(adapter);
        spResistenciaChuva.setAdapter(adapter);


    }

    private void clearFields() {
        etNomePlantaExterna.setText("");
        etFrequenciaRegaExterna.setText("");
        etNotasPlantaExterna.setText("");

    }

    private PlantaExterna makePlantaExterna() {
        PlantaExterna plantaExterna = new PlantaExterna();
        plantaExterna.setNome(etNomePlantaExterna.getText().toString());
        plantaExterna.setFrequenciaRega(Integer.parseInt(etFrequenciaRegaExterna.getText().toString()));
        plantaExterna.setNotas((etNotasPlantaExterna.getText().toString()));
        plantaExterna.setSensibilidadePragas((spSensibilidadePragasExterna.getSelectedItem().toString()));
        plantaExterna.setPreferenciaLuz((spPreferenciaLuzExterna.getSelectedItem().toString()));
        plantaExterna.setResistenciaChuva((spResistenciaChuva.getSelectedItem().toString()));
        plantaExterna.setToleranciaVento((spToleranciaVento.getSelectedItem().toString()));


        return plantaExterna;
    }
}