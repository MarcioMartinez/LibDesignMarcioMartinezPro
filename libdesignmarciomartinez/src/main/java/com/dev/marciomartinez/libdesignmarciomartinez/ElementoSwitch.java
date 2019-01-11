package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class ElementoSwitch extends ElementoBase {

    protected Switch mSwitch;
    protected TextView txtTitulo, txtPositivo, txtNegativo;
    protected LinearLayout contenedor, contenedorValor;
    protected View divider;
    protected String mPositivo, mNegativo;
    protected boolean chequeado;

    public ElementoSwitch(Context context) {
        super(context);
    }

    public ElementoSwitch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoSwitch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ElementoSwitch, 0, 0);

        try {
            mPositivo = a.getString(R.styleable.ElementoSwitch_switch_texto_positivo);
            mNegativo = a.getString(R.styleable.ElementoSwitch_switch_texto_negativo);
            mTitulo = a.getString(R.styleable.ElementoSwitch_switch_texto_titulo);
            mVisible = a.getBoolean(R.styleable.ElementoSwitch_switch_visible, true);
            chequeado = a.getBoolean(R.styleable.ElementoSwitch_switch_chequeado, false);
            mVisibleValor = a.getBoolean(R.styleable.ElementoSwitch_switch_visible_valor, true);
            mVisibleTitulo = a.getBoolean(R.styleable.ElementoSwitch_switch_visible_titulo, true);
            mHabilitado = a.getBoolean(R.styleable.ElementoSwitch_switch_habilitado, true);
            mHabilitadoValor = a.getBoolean(R.styleable.ElementoSwitch_switch_habilitado_valor, true);
            mHabilitadoTitulo = a.getBoolean(R.styleable.ElementoSwitch_switch_habilitado_titulo, true);
            mDividerVisible = a.getBoolean(R.styleable.ElementoSwitch_switch_divider_visible, true);
            mColorFondo = a.getColor(R.styleable.ElementoSwitch_switch_color_fondo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoTitulo = a.getColor(R.styleable.ElementoSwitch_switch_color_fondo_titulo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoValor = a.getColor(R.styleable.ElementoSwitch_switch_color_fondo_valor, getResources().getColor(R.color.colorElementoFondoMM));
            mColorDivider = a.getColor(R.styleable.ElementoSwitch_switch_color_divider, getResources().getColor(R.color.colorDividerMM));
            mColorTitulo = a.getColor(R.styleable.ElementoSwitch_switch_color_titulo, getResources().getColor(R.color.colorElementoTituloMM));
            mColorValor = a.getColor(R.styleable.ElementoSwitch_switch_color_valor, getResources().getColor(R.color.colorElementoValorMM));
            mTamanoTitulo = a.getDimensionPixelSize(R.styleable.ElementoSwitch_switch_tamano_titulo, 0);
            mTamanoValor = a.getDimensionPixelSize(R.styleable.ElementoSwitch_switch_tamano_valor, 0);
            mAnchoTitulo = a.getFloat(R.styleable.ElementoSwitch_switch_ancho_titulo, getResources().getInteger(R.integer.elementoTextoTituloPesoMM));
            mAnchoValor = a.getFloat(R.styleable.ElementoSwitch_switch_ancho_valor, getResources().getInteger(R.integer.elementoTextoValorPesoMM));
        } finally {
            a.recycle();
        }

        //LayoutInflater.from(context).inflate(R.layout.my_edittext, this);
        inflate(context, R.layout.ly_elemento_switch, this);

        contenedor = findViewById(R.id.id_contenedor);
        mSwitch = findViewById(R.id.id_switch);

        contenedorValor = findViewById(R.id.id_valor_elemento);

        contenedor.setVisibility(mVisible ? VISIBLE : GONE);
        contenedor.setBackgroundColor(mColorFondo);
        contenedor.setEnabled(mHabilitado);

        divider = findViewById(R.id.id_divider);
        divider.setVisibility(mDividerVisible ? VISIBLE: GONE);
        divider.setBackgroundColor(mColorDivider);

        txtPositivo = findViewById(R.id.formElementPositiveText);
        txtNegativo = findViewById(R.id.formElementNegativeText);
        if (mPositivo != null){
            txtPositivo.setText(mPositivo.toString());
        }else{
            txtPositivo.setText("");
        }

        if (mNegativo != null){
            txtNegativo.setHint(mNegativo.toString());
        }else{
            txtNegativo.setHint("");
        }


        contenedorValor.setBackgroundColor(mColorFondoValor);
        txtPositivo.setTextColor(mColorValor);
        txtNegativo.setTextColor(mColorValor);
        if (mTamanoValor > 0){
            txtPositivo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTamanoValor);
            txtNegativo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTamanoValor);
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

        mSwitch.setChecked(chequeado);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                chequeado = b;
                if (escuchadorValorCambio != null){
                    escuchadorValorCambio.OnValorCambio(chequeado);
                }
            }
        });

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwitch.requestFocus();
            }
        });

    }

    public TextView getTxtPositivo() {
        return txtPositivo;
    }

    public TextView getTxtNegativo() {
        return txtNegativo;
    }

    public LinearLayout getContenedorValor() {
        return contenedorValor;
    }

    public String getTextoPositivo() {
        return mPositivo;
    }

    public void setTextoPositivo(String mPositivo) {
        this.mPositivo = mPositivo;
        txtPositivo.setText(this.mPositivo);
    }

    public String getTextoNegativo() {
        return mNegativo;
    }

    public void setTextoNegativo(String mNegativo) {
        this.mNegativo = mNegativo;
        txtNegativo.setHint(this.mNegativo);
    }

    public boolean isChequeado() {
        return chequeado;
    }

    public void setChequeado(boolean chequeado) {
        this.chequeado = chequeado;
        mSwitch.setChecked(chequeado);
    }


    public Switch getElementoValor() {
        return mSwitch;
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
        mSwitch.requestFocus();
    }

}
