package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ElementoHeader extends ElementoBase {

    protected TextView txtTitulo;
    protected boolean mCenter, mBold;

    public ElementoHeader(Context context) {
        super(context);
    }

    public ElementoHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ElementoHeader, 0, 0);

        try {
            mTitulo = a.getString(R.styleable.ElementoHeader_header_texto);
            mVisible = a.getBoolean(R.styleable.ElementoHeader_header_visible, true);
            mVisibleTitulo = a.getBoolean(R.styleable.ElementoHeader_header_visible_titulo, true);
            mColorFondo = a.getColor(R.styleable.ElementoHeader_header_color_fondo, getResources().getColor(R.color.colorHeaderFondoMM));
            mColorFondoTitulo = a.getColor(R.styleable.ElementoHeader_header_texto_fondo, getResources().getColor(R.color.colorHeaderFondoMM));
            mColorTitulo = a.getColor(R.styleable.ElementoHeader_header_color_titulo, getResources().getColor(R.color.colorHeaderTextoMM));
            mTamanoTitulo = a.getDimensionPixelSize(R.styleable.ElementoHeader_header_tamano_titulo, 0);
            mCenter = a.getBoolean(R.styleable.ElementoHeader_header_texto_center, false);
            mBold = a.getBoolean(R.styleable.ElementoHeader_header_texto_bold, false);
        } finally {
            a.recycle();
        }

        //LayoutInflater.from(context).inflate(R.layout.my_edittext, this);
        inflate(context, R.layout.ly_header, this);


        setVisibility(mVisible ? VISIBLE : GONE);
        findViewById(R.id.id_fondoHeader).setBackgroundColor(mColorFondo);
        setEnabled(mHabilitado);

        txtTitulo = findViewById(R.id.id_titulo_elemento);
        txtTitulo.setText(mTitulo);
        txtTitulo.setBackgroundColor(mColorFondoTitulo);
        txtTitulo.setTextColor(mColorTitulo);
        if (mCenter){
            txtTitulo.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        }

        if (mBold){
            txtTitulo.setTypeface(null, Typeface.BOLD);
        }
        if (mTamanoTitulo > 0){
            txtTitulo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTamanoTitulo);
        }
        txtTitulo.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT,
                mAnchoTitulo
        ));
        txtTitulo.setVisibility(mVisibleTitulo ? VISIBLE : GONE);
        txtTitulo.setEnabled(mHabilitadoTitulo);
    }

    public TextView getElementoTitulo() {
        return txtTitulo;
    }

    @Override
    public void setTitulo(String mTitulo) {
        super.setTitulo(mTitulo);
        txtTitulo.setText(this.mTitulo);
    }

    @Override
    public void setVisible(boolean mVisible) {
        super.setVisible(mVisible);
        setVisibility(this.mVisible ? VISIBLE : GONE);
    }

    @Override
    public void setVisibleTitulo(boolean mVisibleTitulo) {
        super.setVisibleTitulo(mVisibleTitulo);
        txtTitulo.setVisibility(this.mVisibleTitulo ? VISIBLE : GONE);
    }

    @Override
    public void setColorFondo(int mColorFondo) {
        super.setColorFondo(mColorFondo);
        setBackgroundColor(this.mColorFondo);
    }

    @Override
    public void setValor(Object mValor) {
        super.setValor(mValor);
        txtTitulo.setText(this.mValor.toString());
    }
}
