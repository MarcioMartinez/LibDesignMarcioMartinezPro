package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ElementoTextoComboAlert extends ElementoTexto {
    private List<?> listado = new ArrayList<>();

    public ElementoTextoComboAlert(Context context) {
        super(context);
    }

    public ElementoTextoComboAlert(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoComboAlert(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);
        habilitarEscritura(false);
        txtValor.setFocusableInTouchMode(false);
        txtValor.setMaxLines(4);
        txtValor.setSingleLine(false);
    }

    public List<?> getListado() {
        return listado;
    }


    public ElementoTextoComboAlert setListadoConstruir(final List<?> list, String titulo, String negativoTexto) {
        this.listado = list;
        final CharSequence[] options = new CharSequence[listado.size()];
        for (int i = 0; i < listado.size(); i++) {
            options[i] = listado.get(i).toString();
        }

        final AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle(titulo)
                .setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        txtValor.setText(options[which]);
                        setValor(listado.get(which));
                        if (escuchadorValorCambio != null){
                            escuchadorValorCambio.OnValorCambio(listado.get(which));
                        }
                    }
                })
                .setPositiveButton(negativoTexto, null)
                .create();

        txtValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        txtTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        return this;
    }

    public void setElemento(int indice){
        if (this.listado.size() > 0){
            setValor(listado.get(indice));
            if (escuchadorValorCambio != null){
                escuchadorValorCambio.OnValorCambio(listado.get(indice));
            }
        }
    }
}
