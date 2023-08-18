package com.dev.marciomartinez.libdesignmarciomartinez;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.co.senab.photoview.PhotoViewAttacher;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ElementoFoto extends ElementoBase {
    protected ImageView imgFoto;
    protected TextView txtTitulo;
    protected LinearLayout contenedor, contenedorValor, contenedorBotones;
    protected View divider;
    protected Button btnNinguna, btnSeleccionar, btnTomar;
    protected Drawable foto, fotoNinguna, fotoSeleccionar, fotoTomar;
    protected String textoNinguna, textoSeleccionar, textoTomar;
    protected boolean zoom;

    public ElementoFoto(Context context) {
        super(context);
    }

    public ElementoFoto(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoFoto(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ElementoFoto, 0, 0);

        try {
            foto = a.getDrawable(R.styleable.ElementoFoto_foto_foto_valor);
            fotoNinguna = a.getDrawable(R.styleable.ElementoFoto_foto_foto_boton_ninguno);
            fotoSeleccionar = a.getDrawable(R.styleable.ElementoFoto_foto_foto_boton_seleccionar);
            fotoTomar = a.getDrawable(R.styleable.ElementoFoto_foto_foto_boton_tomar);
            mTitulo = a.getString(R.styleable.ElementoFoto_foto_texto_titulo);
            textoNinguna = a.getString(R.styleable.ElementoFoto_foto_texto_ninguna);
            textoSeleccionar = a.getString(R.styleable.ElementoFoto_foto_texto_seleccionar);
            textoTomar = a.getString(R.styleable.ElementoFoto_foto_texto_tomar);
            mVisible = a.getBoolean(R.styleable.ElementoFoto_foto_visible, true);
            zoom = a.getBoolean(R.styleable.ElementoFoto_foto_zoom_foto, true);
            mVisibleValor = a.getBoolean(R.styleable.ElementoFoto_foto_visible_valor, true);
            mVisibleTitulo = a.getBoolean(R.styleable.ElementoFoto_foto_visible_titulo, true);
            mHabilitado = a.getBoolean(R.styleable.ElementoFoto_foto_habilitado, true);
            mHabilitadoValor = a.getBoolean(R.styleable.ElementoFoto_foto_habilitado_valor, true);
            mHabilitadoTitulo = a.getBoolean(R.styleable.ElementoFoto_foto_habilitado_titulo, true);
            mDividerVisible = a.getBoolean(R.styleable.ElementoFoto_foto_divider_visible, true);
            mColorFondo = a.getColor(R.styleable.ElementoFoto_foto_color_fondo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoTitulo = a.getColor(R.styleable.ElementoFoto_foto_color_fondo_titulo, getResources().getColor(R.color.colorElementoFondoMM));
            mColorFondoValor = a.getColor(R.styleable.ElementoFoto_foto_color_fondo_valor, getResources().getColor(R.color.colorElementoFondoMM));
            mColorDivider = a.getColor(R.styleable.ElementoFoto_foto_color_divider, getResources().getColor(R.color.colorDividerMM));
            mColorTitulo = a.getColor(R.styleable.ElementoFoto_foto_color_titulo, getResources().getColor(R.color.colorElementoTituloMM));
            mTamanoTitulo = a.getDimensionPixelSize(R.styleable.ElementoFoto_foto_tamano_titulo, 0);
            mAnchoTitulo = a.getFloat(R.styleable.ElementoFoto_foto_ancho_titulo, getResources().getInteger(R.integer.elementoTextoTituloPesoMM));
            mAnchoValor = a.getFloat(R.styleable.ElementoFoto_foto_ancho_valor, getResources().getInteger(R.integer.elementoTextoValorPesoMM));
        } finally {
            a.recycle();
        }

        //LayoutInflater.from(context).inflate(R.layout.my_edittext, this);
        inflate(context, R.layout.ly_elemento_foto, this);

        contenedor = findViewById(R.id.id_contenedor);
        btnNinguna = findViewById(R.id.btnNuevaF);
        btnSeleccionar = findViewById(R.id.btnSeleccionarF);
        btnTomar = findViewById(R.id.btnTomarF);
        imgFoto = findViewById(R.id.imgFoto);

        contenedorValor = findViewById(R.id.id_valor_elemento);
        contenedorBotones = findViewById(R.id.id_valor_elemento2);

        contenedor.setVisibility(mVisible ? VISIBLE : GONE);
        contenedor.setBackgroundColor(mColorFondo);
        contenedor.setEnabled(mHabilitado);

        divider = findViewById(R.id.id_divider);
        divider.setVisibility(mDividerVisible ? VISIBLE: GONE);
        divider.setBackgroundColor(mColorDivider);


        contenedorValor.setBackgroundColor(mColorFondoValor);
        contenedorBotones.setBackgroundColor(mColorFondoValor);


        contenedorValor.setLayoutParams( new LinearLayout.LayoutParams(
                0,
                LayoutParams.WRAP_CONTENT,
                mAnchoValor
        ));
        contenedorValor.setVisibility(mVisibleValor ? VISIBLE : GONE);
        contenedorBotones.setVisibility(mVisibleValor ? VISIBLE : GONE);
        contenedorValor.setEnabled(mHabilitadoValor);
        contenedorBotones.setEnabled(mHabilitadoValor);

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

        if (textoTomar != null){
            btnTomar.setText(textoTomar);
        }

        if (textoSeleccionar != null){
            btnSeleccionar.setText(textoSeleccionar);
        }

        if (textoNinguna != null){
            btnNinguna.setText(textoNinguna);
        }

        if (foto != null){
            imgFoto.setImageDrawable(foto);
        }

        if (fotoTomar != null){
            btnTomar.setCompoundDrawablesWithIntrinsicBounds(null, null, fotoTomar, null);
        }

        if (fotoSeleccionar != null){
            btnSeleccionar.setCompoundDrawablesWithIntrinsicBounds(null, null, fotoSeleccionar, null);
        }

        if (fotoNinguna != null){
            btnNinguna.setCompoundDrawablesWithIntrinsicBounds(null, null, fotoNinguna, null);
        }

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenedorValor.requestFocus();
            }
        });

        btnTomar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validaPermisos()){
                    openBackCamera();
                }
            }
        });
        btnNinguna.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                imgFoto.setImageDrawable(null);
            }
        });
        btnSeleccionar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgFoto.getDrawable() != null && zoom){
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                    View mView = ((Activity) mContext).getLayoutInflater().inflate(R.layout.layoutvisualizarfoto, null);
                    final TextView txtTitulo = (TextView) mView.findViewById(R.id.txtTituloImg);
                    final ImageView imagenV = (ImageView) mView.findViewById(R.id.ImagenVisualizar);
                    imagenV.setImageDrawable(imgFoto.getDrawable());

                    //ESTO ES PARA EL ZOOM DEL IMAGEVIEW
                    PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imagenV);
                    photoViewAttacher.update();

                    mBuilder.setView(mView);
                    final AlertDialog  dialog1 = mBuilder.create();
                    dialog1.show();
                }
            }
        });
    }

    public ImageView getImgFoto() {
        return imgFoto;
    }

    public Drawable getFoto() {
        return imgFoto.getDrawable();
    }

    public boolean tieneFoto() {
        return imgFoto.getDrawable() != null;
    }

    public Button getBtnNinguna() {
        return btnNinguna;
    }

    public Button getBtnSeleccionar() {
        return btnSeleccionar;
    }

    public Button getBtnTomar() {
        return btnTomar;
    }

    public LinearLayout getContenedorValor() {
        return contenedorValor;
    }
    public LinearLayout getContenedorBotones() {
        return contenedorBotones;
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
        contenedorBotones.setVisibility(this.mVisibleValor ? VISIBLE : GONE);
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
        contenedorBotones.setEnabled(this.mHabilitadoValor);
    }

    @Override
    public void setColorFondo(int mColorFondo) {
        super.setColorFondo(mColorFondo);
        contenedor.setBackgroundColor(this.mColorFondo);
    }

    public void ponerFoco(){
        contenedorValor.requestFocus();
    }











    public void setCodigoSolicitud(int cod){
        PICK_IMAGE = cod;
    }


    private int PICK_IMAGE = 0;

    Uri imageUri;

    public void metodoOnActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == ((Activity) mContext).RESULT_OK && requestCode == PICK_IMAGE){
            if(data != null){
                if(data.getClipData() != null){
                    int count = data.getClipData().getItemCount();

                    if(count == 1){
                        for(int i = 0; i < count; i++){
                            //Uri imageUri = data.getClipData().getItemAt(i).getUri();

                            imageUri = data.getClipData().getItemAt(i).getUri();
                            // imgFoto.setImageURI(imageUri);

                            try {
                                Bitmap Mibitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), imageUri);
                                Bitmap miImagen = rotateImageIfRequired(Mibitmap,mContext, imageUri);

                                //int width = 1600;
                                //int height = 2048;
                                /*float aspectRatio = miImagen.getWidth() /
                                        (float) miImagen.getHeight();
                                int width = 1000;
                                int height = Math.round(width / aspectRatio);

                                Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                                        miImagen, width, height, false);*/

                                int maxHeight = 2000;
                                int maxWidth = 2000;
                                float scale = Math.min(((float)maxHeight / miImagen.getWidth()), ((float)maxWidth / miImagen.getHeight()));

                                Matrix matrix = new Matrix();
                                matrix.postScale(scale, scale);

                                Bitmap resizedBitmap = Bitmap.createBitmap(miImagen, 0, 0, miImagen.getWidth(), miImagen.getHeight(), matrix, true);




                                //imgFoto.setImageBitmap(miImagen);
                                imgFoto.setImageBitmap(resizedBitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }else if(data.getData() != null){
                    imageUri = data.getData();
                    // imgFoto.setImageURI(imageUri);

                    try {
                        Bitmap Mibitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), imageUri);
                        Bitmap miImagen = rotateImageIfRequired(Mibitmap,mContext, imageUri);
                        /*Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                                miImagen, 1600, 2048, false);*/

                        int maxHeight = 2000;
                        int maxWidth = 2000;
                        float scale = Math.min(((float)maxHeight / miImagen.getWidth()), ((float)maxWidth / miImagen.getHeight()));

                        Matrix matrix = new Matrix();
                        matrix.postScale(scale, scale);

                        Bitmap resizedBitmap = Bitmap.createBitmap(miImagen, 0, 0, miImagen.getWidth(), miImagen.getHeight(), matrix, true);

                        //imgFoto.setImageBitmap(miImagen);
                        imgFoto.setImageBitmap(resizedBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }else{
            try {
                switch (requestCode) {
                    case 1:
                        if (resultCode == ((Activity) mContext).RESULT_OK) {
                            /*try {
                                Bitmap photo = (Bitmap) data.getExtras().get("data");

                                imgFoto.setImageBitmap(photo);

                            } catch (Exception e) {
                                Toast.makeText(this, "Error al cargar la imgFoto", Toast.LENGTH_LONG).show();
                            }*/

                            File imgFile = new  File(pictureImagePath);
                            if(imgFile.exists()){
                                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                                // imgFoto.setImageBitmap(myBitmap);


                                ExifInterface exifInterface = null;
                                try{
                                    exifInterface = new ExifInterface(imgFile.getAbsolutePath());
                                }catch (IOException e){
                                    e.printStackTrace();
                                }
                                int orientacion = exifInterface.getAttributeInt(exifInterface.TAG_ORIENTATION, exifInterface.ORIENTATION_UNDEFINED);
                                Matrix matrix = new Matrix();
                                switch (orientacion){
                                    case ExifInterface.ORIENTATION_ROTATE_90:
                                        matrix.setRotate(90);
                                        break;
                                    case ExifInterface.ORIENTATION_ROTATE_180:
                                        matrix.setRotate(180);
                                        break;
                                    case ExifInterface.ORIENTATION_ROTATE_270:
                                        matrix.setRotate(270);
                                        break;
                                    default:
                                }
                                Bitmap imagenRotada = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(),myBitmap.getHeight(),matrix,true);
                                imgFoto.setImageBitmap(imagenRotada);


                            }
                        }else{
                            if(!pictureImagePath.equals("")){
                                File imgFile = new  File(pictureImagePath);
                                if(imgFile.exists()){
                                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                                    // imgFoto.setImageBitmap(myBitmap);

                                    ExifInterface exifInterface = null;
                                    try{
                                        exifInterface = new ExifInterface(imgFile.getAbsolutePath());
                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }
                                    int orientacion = exifInterface.getAttributeInt(exifInterface.TAG_ORIENTATION, exifInterface.ORIENTATION_UNDEFINED);
                                    Matrix matrix = new Matrix();
                                    switch (orientacion){
                                        case ExifInterface.ORIENTATION_ROTATE_90:
                                            matrix.setRotate(90);
                                            break;
                                        case ExifInterface.ORIENTATION_ROTATE_180:
                                            matrix.setRotate(180);
                                            break;
                                        case ExifInterface.ORIENTATION_ROTATE_270:
                                            matrix.setRotate(270);
                                            break;
                                        default:
                                    }
                                    Bitmap imagenRotada = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(),myBitmap.getHeight(),matrix,true);
                                    imgFoto.setImageBitmap(imagenRotada);


                                }
                            }
                        }
                        break;
                }
            } catch (Exception e) {
            }
        }

    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.setType("image/");
        gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        //startActivityForResult(gallery, PICK_IMAGE);
        ((Activity) mContext).startActivityForResult(gallery.createChooser(gallery,"Seleccione la Aplicación"), PICK_IMAGE);
    }
    private String pictureImagePath = "";
    private void openBackCamera() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;

        File file = new File(pictureImagePath);
        Uri outputFileUri = Uri.fromFile(file);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
       /* cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, 1);*/

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
        {
            String authorities=mContext.getApplicationContext().getPackageName()+".provider";
            Uri imageUri= FileProvider.getUriForFile(((Activity) mContext).getApplicationContext(),authorities,file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }else
        {
            // cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen)); o
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        }

        ((Activity) mContext).startActivityForResult(cameraIntent, 1);


        /*if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
        {
            String authorities=getApplicationContext().getPackageName()+".provider";
            Uri imageUri=FileProvider.getUriForFile(this,authorities,imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }else
        {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        }*/
    }

    public static Bitmap rotateImageIfRequired(Bitmap img, Context context, Uri selectedImage) throws IOException {

        if (selectedImage.getScheme().equals("content")) {
            String[] projection = { MediaStore.Images.ImageColumns.ORIENTATION };
            Cursor c = context.getContentResolver().query(selectedImage, projection, null, null, null);
            if (c.moveToFirst()) {
                final int rotation = c.getInt(0);
                c.close();
                return rotateImage(img, rotation);
            }
            return img;
        } else {
            ExifInterface ei = new ExifInterface(selectedImage.getPath());
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateImage(img, 90);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateImage(img, 180);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateImage(img, 270);
                default:
                    return img;
            }
        }
    }
    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        return rotatedImg;
    }

    private boolean validaPermisos() {

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((((Activity) mContext).checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED)&&
                (((Activity) mContext).checkSelfPermission(WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if((((Activity) mContext).shouldShowRequestPermissionRationale(CAMERA)) ||
                (((Activity) mContext).shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();
        }else{
            ((Activity) mContext).requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},PICK_IMAGE);
        }

        return false;
    }
    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(mContext);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((Activity) mContext).requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},PICK_IMAGE);
            }
        });
        dialogo.show();
    }

    public void metodoOnRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if(requestCode==PICK_IMAGE){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                openBackCamera();
            }else{
                solicitarPermisosManual();
            }
        }
    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si","no"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(mContext);
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("si")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",mContext.getPackageName(),null);
                    intent.setData(uri);
                    mContext.startActivity(intent);
                }else{
                    Toast.makeText(mContext,"Los permisos no fueron aceptados",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }

    public Bitmap redimensionarImagen(Bitmap bitmap, float anchoNuevo, float altoNuevo)
    {
        int ancho = bitmap.getWidth();
        int alto = bitmap.getHeight();

        if(ancho>anchoNuevo || alto>altoNuevo)
        {
            float escalaAncho = anchoNuevo/ancho;
            float escalaAlto = altoNuevo/alto;

            Matrix matrix = new Matrix();
            matrix.postScale(escalaAncho,escalaAlto);

            return  Bitmap.createBitmap(bitmap,0,0,ancho,alto,matrix,false);
        }else{
            return bitmap;
        }
    }
}
