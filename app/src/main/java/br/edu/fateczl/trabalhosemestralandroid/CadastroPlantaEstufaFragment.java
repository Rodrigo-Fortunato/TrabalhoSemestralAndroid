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

import br.edu.fateczl.trabalhosemestralandroid.controller.PlantaEstufaController;
import br.edu.fateczl.trabalhosemestralandroid.model.PlantaEstufa;
import br.edu.fateczl.trabalhosemestralandroid.persistence.PlantaEstufaDAO;


public class CadastroPlantaEstufaFragment extends Fragment {
    private View view;
    private EditText etNomePlantaEstufa, etFrequenciaRegaEstufa, etNotasPlantaEstufa, etHumidadeIdeal, etTemperaturaIdeal;
    private Spinner spPreferenciaLuzEstufa, spSensibilidadePragasEstufa, spResistAlteracoesAmbientais;
    private Button btnCadastrarPlantaEstufa, btnVoltar;
    private PlantaEstufaController estufaController;

    public CadastroPlantaEstufaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cadastro_planta_estufa, container, false);


        etNomePlantaEstufa = view.findViewById(R.id.etNomePlantaEstufa);
        etFrequenciaRegaEstufa = view.findViewById(R.id.etFrequenciaRegaEstufa);
        etNotasPlantaEstufa = view.findViewById(R.id.etNotasPlantaEstufa);
        spPreferenciaLuzEstufa = view.findViewById(R.id.spPreferenciaLuzEstufa);
        spSensibilidadePragasEstufa = view.findViewById(R.id.spsensibilidadePragasEstufa);
        btnCadastrarPlantaEstufa = view.findViewById(R.id.btnCadastrarPlantaEstufa);
        btnVoltar = view.findViewById(R.id.btnVoltar3);

        etTemperaturaIdeal = view.findViewById(R.id.etTemperaturaIdeal);
        etHumidadeIdeal = view.findViewById(R.id.etHumidadeIdeal);
        spResistAlteracoesAmbientais = view.findViewById(R.id.spResistAlteracoesAmbientais);

        estufaController = new PlantaEstufaController(new PlantaEstufaDAO(view.getContext()));
        fillSpinnerPreferenciaLuz();
        fillSpinnerSensibilidadePragas();
        btnCadastrarPlantaEstufa.setOnClickListener(op -> acaoInsert());
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
        PlantaEstufa plantaEstufa = makePlantaEstufa();
        try {
            estufaController.insert(plantaEstufa);
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
        spPreferenciaLuzEstufa.setAdapter(adapter);


    }

    private void fillSpinnerSensibilidadePragas() {
        List<String> lista = new ArrayList<>();
        lista.add("BAIXA");
        lista.add("MEDIA");
        lista.add("ALTA");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSensibilidadePragasEstufa.setAdapter(adapter);
        spResistAlteracoesAmbientais.setAdapter(adapter);


    }

    private void clearFields() {
        etNomePlantaEstufa.setText("");
        etFrequenciaRegaEstufa.setText("");
        etNotasPlantaEstufa.setText("");
        etHumidadeIdeal.setText("");
        etTemperaturaIdeal.setText("");
    }

    private PlantaEstufa makePlantaEstufa() {
        PlantaEstufa plantaEstufa = new PlantaEstufa();
        plantaEstufa.setNome(etNomePlantaEstufa.getText().toString());
        plantaEstufa.setFrequenciaRega(Integer.parseInt(etFrequenciaRegaEstufa.getText().toString()));
        plantaEstufa.setHumidadeIdeal(Integer.parseInt(etHumidadeIdeal.getText().toString()));
        plantaEstufa.setTemperaturaIdeal(Integer.parseInt(etTemperaturaIdeal.getText().toString()));
        plantaEstufa.setNotas((etNotasPlantaEstufa.getText().toString()));
        plantaEstufa.setSensibilidadePragas((spSensibilidadePragasEstufa.getSelectedItem().toString()));
        plantaEstufa.setPreferenciaLuz((spPreferenciaLuzEstufa.getSelectedItem().toString()));
        plantaEstufa.setResistAlteracoesAmbientais((spResistAlteracoesAmbientais.getSelectedItem().toString()));

        return plantaEstufa;

    }
}