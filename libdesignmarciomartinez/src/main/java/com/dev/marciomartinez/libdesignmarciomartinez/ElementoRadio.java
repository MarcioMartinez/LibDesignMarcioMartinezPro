package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class ElementoRadio extends ElementoBase {

    protected RadioButton mRadio1, mRadio2;
    protected TextView txtTitulo;
    protected LinearLayout contenedor, contenedorValor;
    protected View divider;
    protected String mTextoRadio1, mTextoRadio2;
    protected boolean chequeado;
    
    public ElementoRadio(Context context) {
        super(context);
    }

    public ElementoRadio(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoRadio(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.styleable_elemento_radio, 0, 0);

        try {
            mTextoRadio1 = a.getString(R.styleable.styleable_elemento_radio_radio_texto_radio1);
            mTextoRadio2 = a.getString(R.styleable.styleable_elemento_radio_radio_texto_radio2);
            mTitulo = a.getString(R.styleable.styleable_elemento_radio_radio_texto_titulo);
            mVisible = a.getBoolean(R.styleable.styleable_elemento_radio_radio_visible, true);
            chequeado = a.getBoolean(R.styleable.styleable_elemento_radio_radio_chequeado1, true);
            mVisibleValor = a.getBoolean(R.styleable.styleable_elemento_radio_radio_visible_valor, true);
            mVisibleTitulo = a.getBoolean(R.styleable.styleable_elemento_radio_radio_visible_titulo, true);
            mHabilitado = a.getBoolean(R.styleable.styleable_elemento_radio_radio_habilitado, true);
            mHabilitadoValor = a.getBoolean(R.styleable.styleable_elemento_radio_radio_habilitado_valor, true);
            mHabilitadoTitulo = a.getBoolean(R.styleable.styleable_elemento_radio_radio_habilitado_titulo, true);
            mDividerVisible = a.getBoolean(R.styleable.styleable_elemento_radio_radio_divider_visible, true);
            mColorFondo = a.getColor(R.styleable.styleable_elemento_radio_radio_color_fondo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoTitulo = a.getColor(R.styleable.styleable_elemento_radio_radio_color_fondo_titulo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoValor = a.getColor(R.styleable.styleable_elemento_radio_radio_color_fondo_valor, getResources().getColor(R.color.colorElementoFondoMM));
            mColorDivider = a.getColor(R.styleable.styleable_elemento_radio_radio_color_divider, getResources().getColor(R.color.colorDividerMM));
            mColorTitulo = a.getColor(R.styleable.styleable_elemento_radio_radio_color_titulo, getResources().getColor(R.color.colorElementoTituloMM));
            mColorValor = a.getColor(R.styleable.styleable_elemento_radio_radio_color_valor, getResources().getColor(R.color.colorElementoValorMM));
            mTamanoTitulo = a.getDimensionPixelSize(R.styleable.styleable_elemento_radio_radio_tamano_titulo, 0);
            mTamanoValor = a.getDimensionPixelSize(R.styleable.styleable_elemento_radio_radio_tamano_valor, 0);
            mAnchoTitulo = a.getFloat(R.styleable.styleable_elemento_radio_radio_ancho_titulo, getResources().getInteger(R.integer.elementoTextoTituloPesoMM));
            mAnchoValor = a.getFloat(R.styleable.styleable_elemento_radio_radio_ancho_valor, getResources().getInteger(R.integer.elementoTextoValorPesoMM));
        } finally {
            a.recycle();
        }

        //LayoutInflater.from(context).inflate(R.layout.my_edittext, this);
        inflate(context, R.layout.ly_elemento_radio, this);

        contenedor = findViewById(R.id.id_contenedor);
        mRadio1 = findViewById(R.id.id_radio1);
        mRadio2 = findViewById(R.id.id_radio2);

        contenedorValor = findViewById(R.id.id_valor_elemento);

        contenedor.setVisibility(mVisible ? VISIBLE : GONE);
        contenedor.setBackgroundColor(mColorFondo);
        contenedor.setEnabled(mHabilitado);

        divider = findViewById(R.id.id_divider);
        divider.setVisibility(mDividerVisible ? VISIBLE: GONE);
        divider.setBackgroundColor(mColorDivider);

        if (mTextoRadio1 != null){
            mRadio1.setText(mTextoRadio1.toString());
        }else{
            mRadio1.setText("");
        }

        if (mTextoRadio2 != null){
            mRadio2.setText(mTextoRadio2.toString());
        }else{
            mRadio2.setText("");
        }


        contenedorValor.setBackgroundColor(mColorFondoValor);
        mRadio1.setTextColor(mColorValor);
        mRadio2.setTextColor(mColorValor);
        if (mTamanoValor > 0){
            mRadio1.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTamanoValor);
            mRadio2.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTamanoValor);
        }

        contenedorValor.setLayoutParams( new LinearLayout.LayoutParams(
                0,
                LayoutParams.WRAP_CONTENT,
                mAnchoValor
        ));
        contenedorValor.setVisibility(mVisibleValor ? VISIBLE : GONE);
        contenedorValor.setEnabled(mHabilitadoValor);

        txtTitulo = findViewById(R.id.id_titulo_elemento);
        txtTitulo.setText(mTitulo);
        txtTitulo.setBackgroundColor(mColorFondoTitulo);
        txtTitulo.setTextColor(mColorTitulo);
        if (mTamanoTitulo > 0){
            txtTitulo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTamanoTitulo);
        }
        txtTitulo.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LayoutParams.WRAP_CONTENT,
                mAnchoTitulo
        ));
        txtTitulo.setVisibility(mVisibleTitulo ? VISIBLE : GONE);
        txtTitulo.setEnabled(mHabilitadoTitulo);

        mRadio1.setChecked(chequeado);
        mRadio2.setChecked(!chequeado);

        mRadio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                chequeado = b;
                mRadio2.setChecked(!chequeado);
                if (escuchadorValorCambio != null){
                    escuchadorValorCambio.OnValorCambio(chequeado);
                }
            }
        });

        mRadio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                chequeado = !b;
                mRadio1.setChecked(chequeado);
            }
        });

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenedorValor.requestFocus();
            }
        });

    }

    public RadioButton getRadio1() {
        return mRadio1;
    }
    public RadioButton getRadio2() {
        return mRadio2;
    }


    public LinearLayout getContenedorValor() {
        return contenedorValor;
    }

    public String getTextoRadio1() {
        return mTextoRadio1;
    }

    public void setTextoRadio1(String mPositivo) {
        this.mTextoRadio1 = mPositivo;
        mRadio1.setText(this.mTextoRadio1);
    }

    public String getTextoRadio2() {
        return mTextoRadio2;
    }

    public void setTextoRadio2(String mNegativo) {
        this.mTextoRadio2 = mNegativo;
        mRadio2.setText(this.mTextoRadio2);
    }

    public boolean isChequeadoRadio1() {
        return chequeado;
    }

    public void setChequeadoRadio1(boolean chequeado) {
        this.chequeado = chequeado;
        mRadio1.setChecked(chequeado);
        mRadio2.setChecked(!chequeado);
        if (escuchadorValorCambio != null){
            escuchadorValorCambio.OnValorCambio(chequeado);
        }
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
