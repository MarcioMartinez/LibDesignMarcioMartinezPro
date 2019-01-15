package com.dev.marciomartinez.libreriadiseno;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.dev.marciomartinez.libdesignmarciomartinez.ElementoFirma;
import com.dev.marciomartinez.libdesignmarciomartinez.ElementoFoto;
import com.dev.marciomartinez.libdesignmarciomartinez.ElementoRadio;
import com.dev.marciomartinez.libdesignmarciomartinez.ElementoSpinner;
import com.dev.marciomartinez.libdesignmarciomartinez.ElementoSwitch;
import com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoAutoCompletable;
import com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoComboAlert;
import com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoComboMultiAlert;
import com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoContrasena;
import com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoFecha;
import com.dev.marciomartinez.libdesignmarciomartinez.EscuchadorValorCambio;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> x = new ArrayList<>();
    ElementoFoto f;
    ElementoFoto f2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        f = findViewById(R.id.foto);
        f.setCodigoSolicitud(100);



        ElementoRadio r = findViewById(R.id.ra);
        ElementoSwitch es = findViewById(R.id.chk);
        ElementoTextoAutoCompletable e = findViewById(R.id.combo);
        ElementoTextoContrasena e2 = findViewById(R.id.contra);
        ElementoTextoFecha e3 = findViewById(R.id.fecha);
        ElementoSpinner e4 = findViewById(R.id.spi);
        x.add("da1");
        x.add("daadad1");
        x.add("da2");
        x.add("daadad2");
        x.add("da3");
        x.add("daadad3");
        x.add("da4");
        x.add("daadad4");
        e.setListadoConstruir(x, 1, R.layout.support_simple_spinner_dropdown_item);
        e4.setListadoConstruir(x, android.R.layout.simple_list_item_1);
        e4.setElemento(4);

        e.setOnValorCambio(new EscuchadorValorCambio() {
            @Override
            public void OnValorCambio(Object valor) {
                Toast.makeText(MainActivity.this, valor.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        e2.setOnValorCambio(new EscuchadorValorCambio() {
            @Override
            public void OnValorCambio(Object valor) {
                Toast.makeText(MainActivity.this, valor.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        e3.setOnValorCambio(new EscuchadorValorCambio() {
            @Override
            public void OnValorCambio(Object valor) {
                Toast.makeText(MainActivity.this, valor.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        e4.setOnValorCambio(new EscuchadorValorCambio() {
            @Override
            public void OnValorCambio(Object valor) {
                Toast.makeText(MainActivity.this, valor.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        r.setTextoRadio1("POO");
        r.setChequeadoRadio1(false);
        r.setOnValorCambio(new EscuchadorValorCambio() {
            @Override
            public void OnValorCambio(Object valor) {
                Toast.makeText(MainActivity.this, valor.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        es.setOnValorCambio(new EscuchadorValorCambio() {
            @Override
            public void OnValorCambio(Object valor) {
                Toast.makeText(MainActivity.this, valor.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Botton1", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Botton2", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        f.metodoOnActivityResult(requestCode, resultCode, data);
        f2.metodoOnActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        f.metodoOnRequestPermissionsResult(requestCode, permissions, grantResults);
        f2.metodoOnRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
