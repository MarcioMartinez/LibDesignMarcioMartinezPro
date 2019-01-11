package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ElementoTextoComboMultiAlert extends ElementoTexto {

    private List<?> listado = new ArrayList<>();
    private List<?> listadoSeleccionados = new ArrayList<>();
    private CharSequence[] options = new CharSequence[listado.size()];
    private boolean[] optionsSelected = new boolean[listado.size()];
    private ArrayList<Integer> mSelectedItems = new ArrayList();

    public ElementoTextoComboMultiAlert(Context context) {
        super(context);
    }

    public ElementoTextoComboMultiAlert(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoComboMultiAlert(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    public List<?> getListadoSeleccionados() {
        return listadoSeleccionados;
    }

    private void setListadoSeleccionados(List<Object> listadoSeleccionados) {
        this.listadoSeleccionados = listadoSeleccionados;
    }

    public ElementoTextoComboMultiAlert setListadoConstruir(List<?> list, String titulo, String positivoTexto, String negativoTexto){
        this.listado = list;


        for (int i = 0; i < listado.size(); i++) {
            options[i] = (CharSequence) listado.get(i).toString();
            optionsSelected[i] = false;

           /* if (getValor().toString().trim().contains(options[i])) {
                optionsSelected[i] = true;
                mSelectedItems.add(i);
            }*/
        }

        // prepare the dialog
        final AlertDialog dialog  = new AlertDialog.Builder(mContext)
                .setTitle(titulo)
                .setMultiChoiceItems(options, optionsSelected,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(which);
                                    } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton(positivoTexto, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String s = "";
                        final List<Object> seleccionados = new ArrayList<>();
                        for (int i = 0; i < mSelectedItems.size(); i++) {
                            s += options[mSelectedItems.get(i)];
                            seleccionados.add(listado.get(i));
                            if (i < mSelectedItems.size() - 1) {
                                s += ", ";
                            }
                        }

                        setValor(s);
                        setListadoSeleccionados(seleccionados);
                        if (escuchadorValorCambio != null){
                            escuchadorValorCambio.OnValorCambio(seleccionados);
                        }
                    }
                })
                .setNegativeButton(negativoTexto, null)
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

    public void setElementos(List<Integer> indices){
        if (this.listado.size() > 0){
           mSelectedItems.clear();
           optionsSelected = new boolean[listado.size()];

           for (int i: indices){
               mSelectedItems.add(i);
               optionsSelected[i] = true;
           }

            String s = "";
            final List<Object> seleccionados = new ArrayList<>();
            for (int i = 0; i < mSelectedItems.size(); i++) {
                s += options[mSelectedItems.get(i)];
                seleccionados.add(listado.get(i));
                if (i < mSelectedItems.size() - 1) {
                    s += ", ";
                }
            }

            setValor(s);
            setListadoSeleccionados(seleccionados);
            if (escuchadorValorCambio != null){
                escuchadorValorCambio.OnValorCambio(seleccionados);
            }
        }
    }
}
