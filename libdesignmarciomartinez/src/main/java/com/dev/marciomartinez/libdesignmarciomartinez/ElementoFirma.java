package com.dev.marciomartinez.libdesignmarciomartinez;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;

import android.support.annotation.Nullable;

import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.gcacace.signaturepad.views.SignaturePad;

public class ElementoFirma extends ElementoBase {
    protected SignaturePad firma;
    protected TextView txtTitulo;
    protected LinearLayout contenedor, contenedorValor;
    protected View divider;

    public ElementoFirma(Context context) {
        super(context);
    }

    public ElementoFirma(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoFirma(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ElementoFirma, 0, 0);

        try {
            mTitulo = a.getString(R.styleable.ElementoFirma_firma_texto_titulo);
            mVisible = a.getBoolean(R.styleable.ElementoFirma_firma_visible, true);
            mVisibleValor = a.getBoolean(R.styleable.ElementoFirma_firma_visible_valor, true);
            mVisibleTitulo = a.getBoolean(R.styleable.ElementoFirma_firma_visible_titulo, true);
            mHabilitado = a.getBoolean(R.styleable.ElementoFirma_firma_habilitado, true);
            mHabilitadoValor = a.getBoolean(R.styleable.ElementoFirma_firma_habilitado_valor, true);
            mHabilitadoTitulo = a.getBoolean(R.styleable.ElementoFirma_firma_habilitado_titulo, true);
            mDividerVisible = a.getBoolean(R.styleable.ElementoFirma_firma_divider_visible, true);
            mColorFondo = a.getColor(R.styleable.ElementoFirma_firma_color_fondo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoTitulo = a.getColor(R.styleable.ElementoFirma_firma_color_fondo_titulo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoValor = a.getColor(R.styleable.ElementoFirma_firma_color_fondo_valor, getResources().getColor(R.color.colorElementoFondoMM));
            mColorDivider = a.getColor(R.styleable.ElementoFirma_firma_color_divider, getResources().getColor(R.color.colorDividerMM));
            mColorTitulo = a.getColor(R.styleable.ElementoFirma_firma_color_titulo, getResources().getColor(R.color.colorElementoTituloMM));
            mTamanoTitulo = a.getDimensionPixelSize(R.styleable.ElementoFirma_firma_tamano_titulo, 0);
        } finally {
            a.recycle();
        }

        //LayoutInflater.from(context).inflate(R.layout.my_edittext, this);
        inflate(context, R.layout.ly_elemento_firma, this);

        contenedor = findViewById(R.id.id_contenedor);
        firma = findViewById(R.id.signature_pad);

        contenedorValor = findViewById(R.id.id_valor_elemento);

        contenedor.setVisibility(mVisible ? VISIBLE : GONE);
        contenedor.setBackgroundColor(mColorFondo);
        contenedor.setEnabled(mHabilitado);

        divider = findViewById(R.id.id_divider);
        divider.setVisibility(mDividerVisible ? VISIBLE: GONE);
        divider.setBackgroundColor(mColorDivider);


        contenedorValor.setBackgroundColor(mColorFondoValor);


        contenedorValor.setVisibility(mVisibleValor ? VISIBLE : GONE);
        contenedorValor.setEnabled(mHabilitadoValor);

        txtTitulo = findViewById(R.id.id_titulo_elemento);
        txtTitulo.setText(mTitulo);
        txtTitulo.setBackgroundColor(mColorFondoTitulo);
        txtTitulo.setTextColor(mColorTitulo);
        if (mTamanoTitulo > 0){
            txtTitulo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTamanoTitulo);
        }
        txtTitulo.setVisibility(mVisibleTitulo ? VISIBLE : GONE);
        txtTitulo.setEnabled(mHabilitadoTitulo);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                contenedorValor.requestFocus();
            }
        });


        findViewById(R.id.btnLimpiar).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                firma.clear();
            }
        });
    }

    public SignaturePad getElementoFirma() {
        return firma;
    }

    public Bitmap getFirma() {
        return firma.getSignatureBitmap();
    }

    public boolean isFirmaVacia(){
        return firma.isEmpty();
    }

    public LinearLayout getContenedorValor() {
        return contenedorValor;
    }

    public TextView getElementoTitulo() {
        return txtTitulo;
    }

    public LinearLayout getContenedor() {
        return contenedor;
    }

    public View getDivider() {
        return divider;
    }

    @Override
    public void setTitulo(String mTitulo) {
        super.setTitulo(mTitulo);
        txtTitulo.setText(this.mTitulo);
    }

    @Override
    public void setVisible(boolean mVisible) {
        super.setVisible(mVisible);
        contenedor.setVisibility(this.mVisible ? VISIBLE : GONE);
    }

    @Override
    public void setVisibleTitulo(boolean mVisibleTitulo) {
        super.setVisibleTitulo(mVisibleTitulo);
        txtTitulo.setVisibility(this.mVisibleTitulo ? VISIBLE : GONE);
    }

    @Override
    public void setVisibleValor(boolean mVisibleValor) {
        super.setVisibleValor(mVisibleValor);
        contenedorValor.setVisibility(this.mVisibleValor ? VISIBLE : GONE);
    }

    @Override
    public void setDividerVisible(boolean mDividerVisible) {
        super.setDividerVisible(mDividerVisible);
        divider.setVisibility(this.mDividerVisible ? VISIBLE: GONE);
    }

    @Override
    public void setHabilitado(boolean mHabilitado) {
        super.setHabilitado(mHabilitado);
        contenedor.setEnabled(this.mHabilitado);
    }

    @Override
    public void setHabilitadoTitulo(boolean mHabilitadoTitulo) {
        super.setHabilitadoTitulo(mHabilitadoTitulo);
        txtTitulo.setEnabled(this.mHabilitadoTitulo);
    }

    @Override
    public void setHabilitadoValor(boolean mHabilitadoValor) {
        super.setHabilitadoValor(mHabilitadoValor);
        contenedorValor.setEnabled(this.mHabilitadoValor);
    }

    @Override
    public void setColorFondo(int mColorFondo) {
        super.setColorFondo(mColorFondo);
        contenedor.setBackgroundColor(this.mColorFondo);
    }

    public void ponerFoco(){
        contenedorValor.requestFocus();
    }


}
