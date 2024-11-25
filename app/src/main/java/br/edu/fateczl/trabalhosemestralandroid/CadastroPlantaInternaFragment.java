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

import br.edu.fateczl.trabalhosemestralandroid.controller.PlantaInternaController;
import br.edu.fateczl.trabalhosemestralandroid.model.PlantaInterna;
import br.edu.fateczl.trabalhosemestralandroid.persistence.PlantaInternaDAO;


public class CadastroPlantaInternaFragment extends Fragment {

    private View view;
    private EditText etNomePlantaInterna, etFrequenciaRegaInterna, etNotasPlantaInterna, etTamanhoVaso, etTempoLuz;
    private Spinner spPreferenciaLuzInterna, spSensibilidadePragasInterna;
    private Button btnCadastrarPlantaInterna, btnVoltar;
    private PlantaInternaController internaController;


    public CadastroPlantaInternaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cadastro_planta_interna, container, false);

        etNomePlantaInterna = view.findViewById(R.id.etNomePlantaInterna);
        etFrequenciaRegaInterna = view.findViewById(R.id.etFrequenciaRegaInterna);
        etNotasPlantaInterna = view.findViewById(R.id.etNotasPlantaInterna);
        spPreferenciaLuzInterna = view.findViewById(R.id.spPreferenciaLuzInterna);
        spSensibilidadePragasInterna = view.findViewById(R.id.spsensibilidadePragasInterna);
        btnCadastrarPlantaInterna = view.findViewById(R.id.btnCadastrarPlantaInterna);
        btnVoltar = view.findViewById(R.id.btnVoltar);

        etTamanhoVaso = view.findViewById(R.id.etTamanhoVaso);
        etTempoLuz = view.findViewById(R.id.etTempoLuz);

        internaController = new PlantaInternaController(new PlantaInternaDAO(view.getContext()));
        fillSpinnerPreferenciaLuz();
        fillSpinnerSensibilidadePragas();

        btnCadastrarPlantaInterna.setOnClickListener(op -> acaoInsert());
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
        PlantaInterna plantaInterna = makePlantaInterna();
        try {
            internaController.insert(plantaInterna);
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
        spPreferenciaLuzInterna.setAdapter(adapter);


    }

    private void fillSpinnerSensibilidadePragas() {
        List<String> lista = new ArrayList<>();
        lista.add("BAIXA");
        lista.add("MEDIA");
        lista.add("ALTA");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSensibilidadePragasInterna.setAdapter(adapter);


    }


    private void clearFields() {
        etNomePlantaInterna.setText("");
        etFrequenciaRegaInterna.setText("");
        etNotasPlantaInterna.setText("");
        etTamanhoVaso.setText("");
        etTempoLuz.setText("");
    }

    private PlantaInterna makePlantaInterna() {
        PlantaInterna plantaInterna = new PlantaInterna();
        plantaInterna.setNome(etNomePlantaInterna.getText().toString());
        plantaInterna.setFrequenciaRega(Integer.parseInt(etFrequenciaRegaInterna.getText().toString()));
        plantaInterna.setTamanhoVaso(Integer.parseInt(etTamanhoVaso.getText().toString()));
        plantaInterna.setNotas((etNotasPlantaInterna.getText().toString()));
        plantaInterna.setSensibilidadePragas((spSensibilidadePragasInterna.getSelectedItem().toString()));
        plantaInterna.setPreferenciaLuz((spPreferenciaLuzInterna.getSelectedItem().toString()));

        return plantaInterna;
    }
}