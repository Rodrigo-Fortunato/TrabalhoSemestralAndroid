package br.edu.fateczl.trabalhosemestralandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    /*
     *@author:<Rodrigo Fortunato Martins Neves>
     */

    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            carregaFragment(bundle);
        }else{
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment,new InicioFragment());
            fragmentTransaction.commit();
        }


    }

    private void carregaFragment(Bundle bundle) {
        String tipo = bundle.getString("tipo");
        if (tipo.equals("plantaInterna")) {
            fragment = new CadastroPlantaInternaFragment();
        }
        if (tipo.equals("plantaExterna")) {
            fragment = new CadastroPlantaExternaFragment();
        }
        if (tipo.equals("plantaEstufa")) {
            fragment = new CadastroPlantaEstufaFragment();
        }
//        if (tipo.equals("plantaInterna")) {
//            fragment = new CadastroPlantaInternaFragment();
//        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);
        if (id == R.id.CadastroPlantaInterna){
            bundle.putString("tipo","plantaInterna");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;

        }
        if (id == R.id.CadastroPlantaExterna){
            bundle.putString("tipo","plantaExterna");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;

        }
        if (id == R.id.CadastroPlantaEstufa){
            bundle.putString("tipo","plantaEstufa");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;

        }


        return true;

    }
}