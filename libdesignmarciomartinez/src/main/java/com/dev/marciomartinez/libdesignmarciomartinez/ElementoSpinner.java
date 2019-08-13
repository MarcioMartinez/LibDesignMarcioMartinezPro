package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ElementoSpinner extends ElementoBase {

    private List<?> listado = new ArrayList<>();
    protected Spinner cboValor;
    protected TextView txtTitulo;
    protected LinearLayout contenedor;
    protected View divider;

    public ElementoSpinner(Context context) {
        super(context);
    }

    public ElementoSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ElementoSpinner, 0, 0);

        try {
            mTitulo = a.getString(R.styleable.ElementoSpinner_spinner_texto_titulo);
            mVisible = a.getBoolean(R.styleable.ElementoSpinner_spinner_visible, true);
            mVisibleValor = a.getBoolean(R.styleable.ElementoSpinner_spinner_visible_valor, true);
            mVisibleTitulo = a.getBoolean(R.styleable.ElementoSpinner_spinner_visible_titulo, true);
            mHabilitado = a.getBoolean(R.styleable.ElementoSpinner_spinner_habilitado, true);
            mHabilitadoValor = a.getBoolean(R.styleable.ElementoSpinner_spinner_habilitado_valor, true);
            mHabilitadoTitulo = a.getBoolean(R.styleable.ElementoSpinner_spinner_habilitado_titulo, true);
            mDividerVisible = a.getBoolean(R.styleable.ElementoSpinner_spinner_divider_visible, true);
            mColorFondo = a.getColor(R.styleable.ElementoSpinner_spinner_color_fondo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoTitulo = a.getColor(R.styleable.ElementoSpinner_spinner_color_fondo_titulo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoValor = a.getColor(R.styleable.ElementoSpinner_spinner_color_fondo_valor, getResources().getColor(R.color.colorElementoFondoMM));
            mColorDivider = a.getColor(R.styleable.ElementoSpinner_spinner_color_divider, getResources().getColor(R.color.colorDividerMM));
            mColorTitulo = a.getColor(R.styleable.ElementoSpinner_spinner_color_titulo, getResources().getColor(R.color.colorElementoTituloMM));
            mColorValor = a.getColor(R.styleable.ElementoSpinner_spinner_color_valor, getResources().getColor(R.color.colorElementoValorMM));
            mTamanoTitulo = a.getDimensionPixelSize(R.styleable.ElementoSpinner_spinner_tamano_titulo, 0);
            mAnchoTitulo = a.getFloat(R.styleable.ElementoSpinner_spinner_ancho_titulo, getResources().getInteger(R.integer.elementoTextoTituloPesoMM));
            mAnchoValor = a.getFloat(R.styleable.ElementoSpinner_spinner_ancho_valor, getResources().getInteger(R.integer.elementoTextoValorPesoMM));
        } finally {
            a.recycle();
        }

        //LayoutInflater.from(context).inflate(R.layout.my_edittext, this);
        inflate(context, R.layout.ly_elemento_spinner, this);

        contenedor = findViewById(R.id.id_contenedor);
        contenedor.setVisibility(mVisible ? VISIBLE : GONE);
        contenedor.setBackgroundColor(mColorFondo);
        contenedor.setEnabled(mHabilitado);

        divider = findViewById(R.id.id_divider);
        divider.setVisibility(mDividerVisible ? VISIBLE: GONE);
        divider.setBackgroundColor(mColorDivider);

        cboValor = findViewById(R.id.id_valor_elemento);
        cboValor.setBackgroundColor(mColorFondoValor);

        cboValor.setLayoutParams( new LinearLayout.LayoutParams(
                0,
                LayoutParams.WRAP_CONTENT,
                mAnchoValor
        ));
        cboValor.setVisibility(mVisibleValor ? VISIBLE : GONE);
        cboValor.setEnabled(mHabilitadoValor);

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

        txtTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cboValor.performClick();
            }
        });
    }


    public Spinner getElementoValor() {
        return cboValor;
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
    public void setValor(Object mValor) {
        super.setValor(mValor);
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
        cboValor.setVisibility(this.mVisibleValor ? VISIBLE : GONE);
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
        cboValor.setEnabled(this.mHabilitadoValor);
    }

    @Override
    public void setColorFondo(int mColorFondo) {
        super.setColorFondo(mColorFondo);
        contenedor.setBackgroundColor(this.mColorFondo);
    }

    public void ponerFoco(){
        cboValor.requestFocus();
    }

    public ElementoSpinner setListadoConstruir(final List<?> list, int recursoElemento) {
        this.listado = list;
        cboValor.setAdapter(new ArrayAdapter<>(mContext, recursoElemento, listado));

        cboValor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (cboValor.getSelectedItem() != null){
                    setValor(adapterView.getItemAtPosition(i));
                    if (escuchadorValorCambio != null){
                        escuchadorValorCambio.OnValorCambio(listado.get(i));
                    }
                }else{
                    setValor(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txtTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cboValor.requestFocus();
            }
        });
        return this;
    }

    public List<?> getListado() {
        return listado;
    }

    public void setElemento(int indice){
        if (this.listado.size() > 0){
            cboValor.setSelection(indice);
            if (escuchadorValorCambio != null){
                escuchadorValorCambio.OnValorCambio(listado.get(indice));
            }
            setValor(listado.get(indice));
        }
    }

    public Object getElementoSeleccionado(){
        return cboValor.getSelectedItem();
    }

}
