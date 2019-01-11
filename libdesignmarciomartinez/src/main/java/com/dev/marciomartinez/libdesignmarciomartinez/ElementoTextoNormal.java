package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

public class ElementoTextoNormal extends ElementoTexto {
    public ElementoTextoNormal(Context context) {
        super(context);
    }

    public ElementoTextoNormal(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoNormal(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);

        txtValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (escuchadorValorCambio != null){
                    escuchadorValorCambio.OnValorCambio(charSequence.toString().trim());
                }
                setValor(charSequence.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void setValor(Object mValor) {
        this.mValor = mValor;
    }
}
