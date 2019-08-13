package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ElementoBase extends LinearLayout {
    // ATRIBUTOS
    protected Context mContext;
    protected Object mValor;
    protected String mTitulo = "";
    protected String mHint;
    protected boolean mVisible;
    protected boolean mVisibleValor;
    protected boolean mVisibleTitulo;
    protected boolean mHabilitado;
    protected boolean mHabilitadoValor;
    protected boolean mHabilitadoTitulo;
    protected boolean mDividerVisible;
    protected int mColorFondo;
    protected int mColorDivider;
    protected int mColorTitulo;
    protected int mColorValor;
    protected int mColorFondoTitulo;
    protected int mColorFondoValor;
    protected float mTamanoTitulo;
    protected float mTamanoValor;
    protected float mAnchoTitulo;
    protected float mAnchoValor;
    protected EscuchadorValorCambio escuchadorValorCambio;

    public ElementoBase(Context context) {
        super(context);
        init(context, null);
    }

    public ElementoBase(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ElementoBase(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attributeSet){
        mContext = context;
    }

    public Object getValor() {
        return mValor;
    }

    public void setValor(Object mValor) {
        this.mValor = mValor;
    }

    public String getTitulo() {
        return mTitulo;
    }

    public void setTitulo(String mTitulo) {
        this.mTitulo = mTitulo;
    }

    public boolean isVisible() {
        return mVisible;
    }

    public void setVisible(boolean mVisible) {
        this.mVisible = mVisible;
    }

    public boolean isVisibleValor() {
        return mVisibleValor;
    }

    public void setVisibleValor(boolean mVisibleValor) {
        this.mVisibleValor = mVisibleValor;
    }

    public boolean isVisibleTitulo() {
        return mVisibleTitulo;
    }

    public void setVisibleTitulo(boolean mVisibleTitulo) {
        this.mVisibleTitulo = mVisibleTitulo;
    }

    public boolean isHabilitado() {
        return mHabilitado;
    }

    public void setHabilitado(boolean mHabilitado) {
        this.mHabilitado = mHabilitado;
    }

    public boolean isHabilitadoValor() {
        return mHabilitadoValor;
    }

    public void setHabilitadoValor(boolean mHabilitadoValor) {
        this.mHabilitadoValor = mHabilitadoValor;
    }

    public boolean isHabilitadoTitulo() {
        return mHabilitadoTitulo;
    }

    public void setHabilitadoTitulo(boolean mHabilitadoTitulo) {
        this.mHabilitadoTitulo = mHabilitadoTitulo;
    }

    public boolean isDividerVisible() {
        return mDividerVisible;
    }

    public void setDividerVisible(boolean mDividerVisible) {
        this.mDividerVisible = mDividerVisible;
    }

    public int getColorFondo() {
        return mColorFondo;
    }

    public void setColorFondo(int mColorFondo) {
        this.mColorFondo = mColorFondo;
    }

    public void setOnValorCambio(EscuchadorValorCambio evento){
        escuchadorValorCambio = evento;
    }

    public double valorDouble(){
        return this.mValor == null ? null : Double.parseDouble(this.mValor.toString().trim());
    }

    public double valorFloat(){
        return this.mValor == null ? null : Float.parseFloat(this.mValor.toString().trim());
    }

    public String valorUpperCase(){
        return this.mValor == null ? "" : this.mValor.toString().trim().toUpperCase();
    }

    public String valorLowerCase(){
        return this.mValor == null ? "" : this.mValor.toString().trim().toLowerCase();
    }

    public boolean valorEsNulo(){
        return this.mValor == null;
    }

    public boolean valorNoEsNulo(){
        return this.mValor != null;
    }

    public boolean valorEsVacio(){
        return this.mValor.toString().trim().equals("");
    }
}
