package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vicmikhailau.maskededittext.MaskedEditText;
import com.vicmikhailau.maskededittext.MaskedWatcher;

public class ElementoTextoMask extends ElementoBase {

   /* ANYTHING KEY = *;
    DIGIT KEY = #;
    UPPERCASE KEY = U;
    LOWERCASE KEY = L;
    ALPHA NUMERIC KEY = A;
    CHARACTER KEY = ?;
    HEX KEY = H;*/

    protected MaskedEditText txtValor;
    protected TextView txtTitulo;
    protected LinearLayout contenedor;
    protected View divider;
    protected KeyListener mKeyListerner;
    protected String mascara;

    public ElementoTextoMask(Context context) {
        super(context);
    }

    public ElementoTextoMask(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoMask(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ElementoTextoMask, 0, 0);

        try {
            mascara = a.getString(R.styleable.ElementoTextoMask_mask_mascara);
            mValor = a.getString(R.styleable.ElementoTextoMask_mask_texto_valor);
            mTitulo = a.getString(R.styleable.ElementoTextoMask_mask_texto_titulo);
            mHint = a.getString(R.styleable.ElementoTextoMask_mask_hint_valor);
            mVisible = a.getBoolean(R.styleable.ElementoTextoMask_mask_visible, true);
            mVisibleValor = a.getBoolean(R.styleable.ElementoTextoMask_mask_visible_valor, true);
            mVisibleTitulo = a.getBoolean(R.styleable.ElementoTextoMask_mask_visible_titulo, true);
            mHabilitado = a.getBoolean(R.styleable.ElementoTextoMask_mask_habilitado, true);
            mHabilitadoValor = a.getBoolean(R.styleable.ElementoTextoMask_mask_habilitado_valor, true);
            mHabilitadoTitulo = a.getBoolean(R.styleable.ElementoTextoMask_mask_habilitado_titulo, true);
            mDividerVisible = a.getBoolean(R.styleable.ElementoTextoMask_mask_divider_visible, true);
            mColorFondo = a.getColor(R.styleable.ElementoTextoMask_mask_color_fondo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoTitulo = a.getColor(R.styleable.ElementoTextoMask_mask_color_fondo_titulo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoValor = a.getColor(R.styleable.ElementoTextoMask_mask_color_fondo_valor, getResources().getColor(R.color.colorElementoFondoMM));
            mColorDivider = a.getColor(R.styleable.ElementoTextoMask_mask_color_divider, getResources().getColor(R.color.colorDividerMM));
            mColorTitulo = a.getColor(R.styleable.ElementoTextoMask_mask_color_titulo, getResources().getColor(R.color.colorElementoTituloMM));
            mColorValor = a.getColor(R.styleable.ElementoTextoMask_mask_color_valor, getResources().getColor(R.color.colorElementoValorMM));
            mTamanoTitulo = a.getDimensionPixelSize(R.styleable.ElementoTextoMask_mask_tamano_titulo, 0);
            mTamanoValor = a.getDimensionPixelSize(R.styleable.ElementoTextoMask_mask_tamano_valor, 0);
            mAnchoTitulo = a.getFloat(R.styleable.ElementoTextoMask_mask_ancho_titulo, getResources().getInteger(R.integer.elementoTextoTituloPesoMM));
            mAnchoValor = a.getFloat(R.styleable.ElementoTextoMask_mask_ancho_valor, getResources().getInteger(R.integer.elementoTextoValorPesoMM));
        } finally {
            a.recycle();
        }

        //LayoutInflater.from(context).inflate(R.layout.my_edittext, this);
        inflate(context, R.layout.ly_elemento_texto_mask, this);

        contenedor = findViewById(R.id.id_contenedor);
        contenedor.setVisibility(mVisible ? VISIBLE : GONE);
        contenedor.setBackgroundColor(mColorFondo);
        contenedor.setEnabled(mHabilitado);

        divider = findViewById(R.id.id_divider);
        divider.setVisibility(mDividerVisible ? VISIBLE: GONE);
        divider.setBackgroundColor(mColorDivider);

        txtValor = findViewById(R.id.id_valor_elemento);
        if (mValor != null){
            txtValor.setText(mValor.toString());
        }else{
            txtValor.setText("");
        }

        if (mascara != null){
            txtValor.setMask(mascara.toString());
        }else{
            mascara = "";
            //txtValor.setMask(mascara.toString());
        }
        txtValor.setBackgroundColor(mColorFondoValor);
        txtValor.setTextColor(mColorValor);
        if (mTamanoValor > 0){
            txtValor.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTamanoValor);
        }
        txtValor.setHint(mHint);
        txtValor.setLayoutParams( new LinearLayout.LayoutParams(
                0,
                LayoutParams.WRAP_CONTENT,
                mAnchoValor
        ));
        txtValor.setVisibility(mVisibleValor ? VISIBLE : GONE);
        txtValor.setEnabled(mHabilitadoValor);

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

        mKeyListerner = txtValor.getKeyListener();
        txtValor.setMaxLines(1);
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtValor.requestFocus();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(txtValor, InputMethodManager.SHOW_IMPLICIT);
                txtValor.setSelection(txtValor.getText().toString().length());
            }
        });

        txtValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mValor = charSequence.toString().trim();
                if (escuchadorValorCambio != null){
                    escuchadorValorCambio.OnValorCambio(charSequence.toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public MaskedEditText getElementoValor() {
        return txtValor;
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
        txtValor.setVisibility(this.mVisibleValor ? VISIBLE : GONE);
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
        txtValor.setEnabled(this.mHabilitadoValor);
    }

    @Override
    public void setColorFondo(int mColorFondo) {
        super.setColorFondo(mColorFondo);
        contenedor.setBackgroundColor(this.mColorFondo);
    }

    public void habilitarEscritura(boolean opcion){
        if(opcion){
            txtValor.setKeyListener(mKeyListerner);
        }else{
            txtValor.setKeyListener(null);
        }
    }

    public void ponerFoco(){
        txtValor.requestFocus();
    }

    public void setOnTextoCambia(TextWatcher evento){
        if (txtValor != null){
            txtValor.addTextChangedListener(evento);
        }
    }

    public boolean tieneTexto(){
        return txtValor.getText().toString().trim().length() > 0;
    }

    public String getTexto(){
        return txtValor.getText().toString().trim();
    }

    @Override
    public void setValor(Object mValor) {
        txtValor.setText(mValor.toString().trim());
    }

    public void limpiar() {
        txtValor.setText("");
    }


    @Override
    public Object getValor() {
        return super.getValor() != null ? super.getValor() : "" ;
    }
}
