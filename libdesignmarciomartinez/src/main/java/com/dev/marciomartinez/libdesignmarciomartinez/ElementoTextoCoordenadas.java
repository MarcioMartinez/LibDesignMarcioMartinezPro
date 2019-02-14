package com.dev.marciomartinez.libdesignmarciomartinez;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import im.delight.android.location.SimpleLocation;

public class ElementoTextoCoordenadas extends ElementoBase {

    protected AutoCompleteTextView txtValor;
    protected TextView txtTitulo;
    protected LinearLayout contenedor, contenedorValor;
    protected ImageView elementoIcono;
    protected View divider;
    protected KeyListener mKeyListerner;
    protected boolean iconoVisible;
    protected Drawable iconoMapa;
    protected String latitud = "", longitud = "", coordenadas = "";


    private SimpleLocation location;
    
    
    public ElementoTextoCoordenadas(Context context) {
        super(context);
    }

    public ElementoTextoCoordenadas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoCoordenadas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(final Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);

        super.init(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ElementoTextoCoordenadas, 0, 0);

        try {
            iconoMapa = a.getDrawable(R.styleable.ElementoTextoCoordenadas_coordenadas_icono_mapa);
            mValor = a.getString(R.styleable.ElementoTextoCoordenadas_coordenadas_texto_valor);
            mTitulo = a.getString(R.styleable.ElementoTextoCoordenadas_coordenadas_texto_titulo);
            mHint = a.getString(R.styleable.ElementoTextoCoordenadas_coordenadas_hint_valor);
            mVisible = a.getBoolean(R.styleable.ElementoTextoCoordenadas_coordenadas_visible, true);
            iconoVisible = a.getBoolean(R.styleable.ElementoTextoCoordenadas_coordenadas_icono_visible, true);
            mVisibleValor = a.getBoolean(R.styleable.ElementoTextoCoordenadas_coordenadas_visible_valor, true);
            mVisibleTitulo = a.getBoolean(R.styleable.ElementoTextoCoordenadas_coordenadas_visible_titulo, true);
            mHabilitado = a.getBoolean(R.styleable.ElementoTextoCoordenadas_coordenadas_habilitado, true);
            mHabilitadoValor = a.getBoolean(R.styleable.ElementoTextoCoordenadas_coordenadas_habilitado_valor, true);
            mHabilitadoTitulo = a.getBoolean(R.styleable.ElementoTextoCoordenadas_coordenadas_habilitado_titulo, true);
            mDividerVisible = a.getBoolean(R.styleable.ElementoTextoCoordenadas_coordenadas_divider_visible, true);
            mColorFondo = a.getColor(R.styleable.ElementoTextoCoordenadas_coordenadas_color_fondo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoTitulo = a.getColor(R.styleable.ElementoTextoCoordenadas_coordenadas_color_fondo_titulo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoValor = a.getColor(R.styleable.ElementoTextoCoordenadas_coordenadas_color_fondo_valor, getResources().getColor(R.color.colorElementoFondoMM));
            mColorDivider = a.getColor(R.styleable.ElementoTextoCoordenadas_coordenadas_color_divider, getResources().getColor(R.color.colorDividerMM));
            mColorTitulo = a.getColor(R.styleable.ElementoTextoCoordenadas_coordenadas_color_titulo, getResources().getColor(R.color.colorElementoTituloMM));
            mColorValor = a.getColor(R.styleable.ElementoTextoCoordenadas_coordenadas_color_valor, getResources().getColor(R.color.colorElementoValorMM));
            mTamanoTitulo = a.getDimensionPixelSize(R.styleable.ElementoTextoCoordenadas_coordenadas_tamano_titulo, 0);
            mTamanoValor = a.getDimensionPixelSize(R.styleable.ElementoTextoCoordenadas_coordenadas_tamano_valor, 0);
            mAnchoTitulo = a.getFloat(R.styleable.ElementoTextoCoordenadas_coordenadas_ancho_titulo, getResources().getInteger(R.integer.elementoTextoTituloPesoMM));
            mAnchoValor = a.getFloat(R.styleable.ElementoTextoCoordenadas_coordenadas_ancho_valor, getResources().getInteger(R.integer.elementoTextoValorPesoMM));
        } finally {
            a.recycle();
        }

        //LayoutInflater.from(context).inflate(R.layout.my_edittext, this);
        inflate(context, R.layout.ly_elemento_texto_coordenadas, this);

        contenedor = findViewById(R.id.id_contenedor);
        contenedorValor = findViewById(R.id.id_contenedor_valor);

        elementoIcono = findViewById(R.id.imgIcono);

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

        txtValor.setEnabled(false);
        txtValor.setKeyListener(null);




        txtValor.setBackgroundColor(mColorFondoValor);
        txtValor.setTextColor(mColorValor);
        if (mTamanoValor > 0){
            txtValor.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTamanoValor);
        }
        txtValor.setHint(mHint);

        contenedorValor.setLayoutParams( new LinearLayout.LayoutParams(
                0,
                LayoutParams.WRAP_CONTENT,
                mAnchoValor
        ));
        contenedorValor.setVisibility(mVisibleValor ? VISIBLE : GONE);
        contenedorValor.setBackgroundColor(mColorFondoValor);
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

        if (iconoMapa != null){
            elementoIcono.setImageDrawable(iconoMapa);
        }

        elementoIcono.setVisibility(iconoVisible ? VISIBLE : GONE);

        elementoIcono.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(((Activity) mContext), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, CODE);
                }else{
                    location = new SimpleLocation(mContext);
                    if (!location.hasLocationEnabled()) {
                        SimpleLocation.openSettings(mContext);
                    }

                    latitud = String.valueOf(location.getLatitude());
                    longitud = String.valueOf(location.getLongitude());
                    coordenadas = latitud.concat("; ").concat(longitud);
                    txtValor.setText(latitud.concat("; ").concat(longitud));
                }
            }
        });

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


    public AutoCompleteTextView getElementoValor() {
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

    public LinearLayout getContenedorValor() {
        return contenedorValor;
    }

    public ImageView getElementoOjo() {
        return elementoIcono;
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


    private int CODE = 0;

    public void setCodigoSolicitud(int cod){
        CODE = cod;
    }

    public void metodoOnRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if(requestCode==CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                location = new SimpleLocation(mContext);
                if (!location.hasLocationEnabled()) {
                    SimpleLocation.openSettings(mContext);
                }

                latitud = String.valueOf(location.getLatitude());
                longitud = String.valueOf(location.getLongitude());
                coordenadas = latitud.concat("; ").concat(longitud);
                txtValor.setText(latitud.concat("; ").concat(longitud));
                return;
            }
        }
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public SimpleLocation getLocation() {
        return location;
    }

}
