package com.inception.paycrypt;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.inception.paycrypt.MenuPrincipal.InicioFragment;


public class  MainActivity extends AppCompatActivity implements InicioFragment.OnFragmentInteractionListener {

    Fragment frangmentInicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(view -> {
            TextView textView = findViewById(R.id.textView);
            textView.setText("Welcome to paycrypt");
        });
        frangmentInicio=new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFagments, frangmentInicio).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
