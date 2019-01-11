# LibDesignMarcioMartinezPro 2019 (JAVA)
Esta es una librería desarrollada con el fin de agilizar el diseño y programación de aplicaciones móviles nativas de android, con una agradable interfaz en cada vista, fue desarrollada por Marcio Martinez

# Implementación:
  ### Paso 1. Agrega el repositorio JitPack a tu archivo de compilación
  Agréguelo a su root build.gradle al final de los repositorios:
```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```
          
  ### Paso 2. Añadir la dependencia.
```
dependencies {
	 implementation 'com.github.MarcioMartinez:LibDesignMarcioMartinezPro:Tag'
}
```
  #### Nota: 
  Tag = última versión de la librería
  
  ### Ejemplos:
  #### La librería cuenta con mas de 25 Vistas de diferentes funcionalidades, algunas de ellas:
```
<com.dev.marciomartinez.libdesignmarciomartinez.ElementoHeader
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      app:header_texto_bold="true"
      app:header_texto="Texto del Encabezado"
      app:header_texto_center="true"
      app:header_tamano_titulo="18dp">
</com.dev.marciomartinez.libdesignmarciomartinez.ElementoHeader>

<com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoFecha
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:texto_texto_titulo="Fecha">
</com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoFecha>

<com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoMultiLinea
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:texto_texto_titulo="Comentario:">
</com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoMultiLinea>

<com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoPersonaNombre
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:texto_texto_titulo="Comentario:">
</com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoPersonaNombre>

<com.dev.marciomartinez.libdesignmarciomartinez.ElementoRadio
           android:id="@+id/ra"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           app:radio_texto_radio1="Esquema"
           app:radio_texto_radio2="Javac">
</com.dev.marciomartinez.libdesignmarciomartinez.ElementoRadio>

<com.dev.marciomartinez.libdesignmarciomartinez.ElementoSwitch
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           app:switch_texto_titulo="Estado:"
           app:switch_texto_positivo="Si"
           app:switch_texto_negativo="No">
</com.dev.marciomartinez.libdesignmarciomartinez.ElementoSwitch>

<com.dev.marciomartinez.libdesignmarciomartinez.ElementoSpinner
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           app:spinner_texto_titulo="Paises:"
           app:spinner_divider_visible="false">
</com.dev.marciomartinez.libdesignmarciomartinez.ElementoSpinner>

<com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoContrasena
           android:layout_width="match_parent"
           android:layout_height="wrap_content">
</com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoContrasena>

<com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoNumero
       android:layout_width="match_parent"
       android:layout_height="wrap_content">
</com.dev.marciomartinez.libdesignmarciomartinez.ElementoTextoNumero>
```  

### Si deseas tener una vista de manera generica, puedes usar la vista que fue desarrollada para que inyectes tu propio diseño (Vistas):
```
<com.dev.marciomartinez.libdesignmarciomartinez.ElementoCustom
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       app:custom_texto_titulo="Elementos Custom:"
       >
      <LinearLayout
          android:layout_width="match_parent"
          android:layout_height="match_parent">
         <Button
             android:text="boton1"
             android:layout_width="0dp"
             android:layout_weight="1"
             android:layout_height="match_parent" />
         <Button
             android:text="boton2"
             android:layout_width="0dp"
             android:layout_weight="1"
             android:layout_height="wrap_content" />
      </LinearLayout>
 </com.dev.marciomartinez.libdesignmarciomartinez.ElementoCustom>
```
La librería tiene una gran cantidad de funciones para que puedas sacar el mayor provecho a tus proyectos. El desarrollo de esta librería esta orientadas a objetos POO

# Ejemplo del código de una vista (Clase) en el cual hay mas de 30 clases para el desarrollo de esta librería:
```java
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

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ElementoCustom, 0, 0);

        try {
            mTitulo = a.getString(R.styleable.ElementoCustom_custom_texto_titulo);
            mVisible = a.getBoolean(R.styleable.ElementoCustom_custom_visible, true);
            mVisibleValor = a.getBoolean(R.styleable.ElementoCustom_custom_visible_valor, true);
            mVisibleTitulo = a.getBoolean(R.styleable.ElementoCustom_custom_visible_titulo, true);
            mHabilitado = a.getBoolean(R.styleable.ElementoCustom_custom_habilitado, true);
            mHabilitadoValor = a.getBoolean(R.styleable.ElementoCustom_custom_habilitado_valor, true);
            mHabilitadoTitulo = a.getBoolean(R.styleable.ElementoCustom_custom_habilitado_titulo, true);
            mDividerVisible = a.getBoolean(R.styleable.ElementoCustom_custom_divider_visible, true);
            mColorFondo = a.getColor(R.styleable.ElementoCustom_custom_color_fondo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoTitulo = a.getColor(R.styleable.ElementoCustom_custom_color_fondo_titulo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoValor = a.getColor(R.styleable.ElementoCustom_custom_color_fondo_valor, getResources().getColor(R.color.colorElementoFondoMM));
            mColorDivider = a.getColor(R.styleable.ElementoCustom_custom_color_divider, getResources().getColor(R.color.colorDividerMM));
            mColorTitulo = a.getColor(R.styleable.ElementoCustom_custom_color_titulo, getResources().getColor(R.color.colorElementoTituloMM));
            mTamanoTitulo = a.getDimensionPixelSize(R.styleable.ElementoCustom_custom_tamano_titulo, 0);
            mAnchoTitulo = a.getFloat(R.styleable.ElementoCustom_custom_ancho_titulo, getResources().getInteger(R.integer.elementoTextoTituloPesoMM));
            mAnchoValor = a.getFloat(R.styleable.ElementoCustom_custom_ancho_valor, getResources().getInteger(R.integer.elementoTextoValorPesoMM));
        } finally {
            a.recycle();
        }
        
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

    protected LinearLayout encontrarHijo() {
        return this.getChildCount() > 0 && this.getChildAt(0) instanceof LinearLayout ? (LinearLayout) this.getChildAt(0) : null;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.contenedorCustom = encontrarHijo();
        if (this.contenedorCustom != null){
            contenedorValor.addView(this.contenedorCustom);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if(contenedorValor == null){
            super.addView(child, index, params);
        } else {
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
```


# Recomendaciones: 
  - Te recomiendo acompañar el desarrollo con esta librería, el uso de librerias como ButterKnife y Dagger 2, para que la estructura de tu proyecto sea de calidad y estilo.
  
# Actualizaciones:
La librería estará siendo actualizada constantemente, para que le saques el mayor provecho posible

## Contactos:
### Programador: Marcio Francisco Martinez Ochoa
### Correo: marciomartinez500@gmail.com


  
