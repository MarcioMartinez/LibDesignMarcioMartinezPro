package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ElementoTextoAutoCompletable extends ElementoTexto {

    private List<?> listado = new ArrayList<>();

    public ElementoTextoAutoCompletable(Context context) {
        super(context);
    }

    public ElementoTextoAutoCompletable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoAutoCompletable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public List<?> getListado() {
        return listado;
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);
    }

    public ElementoTextoAutoCompletable setListadoConstruir(final List<?> list, int buscarDesde, int recursoElemento) {
        this.listado = list;
        txtValor.setAdapter(new ArrayAdapter<>(mContext, recursoElemento, listado));
        txtValor.setThreshold(buscarDesde);

        txtValor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!txtValor.getText().toString().trim().equals("")){
                    setValor(listado.get(i));
                    if (escuchadorValorCambio != null){
                        escuchadorValorCambio.OnValorCambio(listado.get(i));
                    }
                }
            }
        });

        txtTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtValor.showDropDown();
            }
        });
        return this;
    }

    @Override
    public void setValor(Object mValor) {
        this.mValor = mValor;
    }

    public void setElemento(int indice){
        if (this.listado.size() > 0){
            txtValor.setSelection(indice);
            setValor(listado.get(indice));
            if (escuchadorValorCambio != null){
                escuchadorValorCambio.OnValorCambio(listado.get(indice));
            }
        }
    }
}
