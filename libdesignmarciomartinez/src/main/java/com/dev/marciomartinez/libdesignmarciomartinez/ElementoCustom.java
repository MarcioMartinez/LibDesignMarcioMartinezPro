package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class ElementoCustom extends ElementoBase {

    protected TextView txtTitulo;
    protected LinearLayout contenedor, contenedorValor, contenedorCustom;
    protected View divider;
    
    public ElementoCustom(Context context) {
        super(context);
    }

    public ElementoCustom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoCustom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.styleable_elemento_custom, 0, 0);

        try {
            mTitulo = a.getString(R.styleable.styleable_elemento_custom_custom_texto_titulo);
            mVisible = a.getBoolean(R.styleable.styleable_elemento_custom_custom_visible, true);
            mVisibleValor = a.getBoolean(R.styleable.styleable_elemento_custom_custom_visible_valor, true);
            mVisibleTitulo = a.getBoolean(R.styleable.styleable_elemento_custom_custom_visible_titulo, true);
            mHabilitado = a.getBoolean(R.styleable.styleable_elemento_custom_custom_habilitado, true);
            mHabilitadoValor = a.getBoolean(R.styleable.styleable_elemento_custom_custom_habilitado_valor, true);
            mHabilitadoTitulo = a.getBoolean(R.styleable.styleable_elemento_custom_custom_habilitado_titulo, true);
            mDividerVisible = a.getBoolean(R.styleable.styleable_elemento_custom_custom_divider_visible, true);
            mColorFondo = a.getColor(R.styleable.styleable_elemento_custom_custom_color_fondo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoTitulo = a.getColor(R.styleable.styleable_elemento_custom_custom_color_fondo_titulo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoValor = a.getColor(R.styleable.styleable_elemento_custom_custom_color_fondo_valor, getResources().getColor(R.color.colorElementoFondoMM));
            mColorDivider = a.getColor(R.styleable.styleable_elemento_custom_custom_color_divider, getResources().getColor(R.color.colorDividerMM));
            mColorTitulo = a.getColor(R.styleable.styleable_elemento_custom_custom_color_titulo, getResources().getColor(R.color.colorElementoTituloMM));
            mTamanoTitulo = a.getDimensionPixelSize(R.styleable.styleable_elemento_custom_custom_tamano_titulo, 0);
            mAnchoTitulo = a.getFloat(R.styleable.styleable_elemento_custom_custom_ancho_titulo, getResources().getInteger(R.integer.elementoTextoTituloPesoMM));
            mAnchoValor = a.getFloat(R.styleable.styleable_elemento_custom_custom_ancho_valor, getResources().getInteger(R.integer.elementoTextoValorPesoMM));
        } finally {
            a.recycle();
        }

        //LayoutInflater.from(context).inflate(R.layout.my_edittext, this);
        inflate(context, R.layout.ly_elemento_custom, this);

        contenedor = findViewById(R.id.id_contenedor);

        contenedorValor = findViewById(R.id.id_valor_elemento);

        contenedor.setVisibility(mVisible ? VISIBLE : GONE);
        contenedor.setBackgroundColor(mColorFondo);
        contenedor.setEnabled(mHabilitado);

        divider = findViewById(R.id.id_divider);
        divider.setVisibility(mDividerVisible ? VISIBLE: GONE);
        divider.setBackgroundColor(mColorDivider);




        contenedorValor.setBackgroundColor(mColorFondoValor);

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

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenedorValor.requestFocus();
            }
        });

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

   /* protected LinearLayout encontrarHijo() {
        return this.getChildCount() > 0 && this.getChildAt(0) instanceof LinearLayout ? (LinearLayout) this.getChildAt(0) : null;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        this.contenedorCustom = encontrarHijo();

        if (this.contenedorCustom != null){
            //contenedorValor.addView(this.contenedorCustom);
            contenedorValor.removeView(this.contenedorCustom);
        }

    }*/

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if(contenedorValor == null){
            super.addView(child, index, params);
        } else {
            //Forward these calls to the content view
            contenedorValor.addView(child, index, params);
        }
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
