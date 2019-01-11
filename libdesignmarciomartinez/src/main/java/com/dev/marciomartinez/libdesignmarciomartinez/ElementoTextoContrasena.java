package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;

public class ElementoTextoContrasena extends ElementoTextoNormal {
    public ElementoTextoContrasena(Context context) {
        super(context);
    }

    public ElementoTextoContrasena(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoContrasena(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);
        txtValor.setRawInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        txtValor.setTransformationMethod(new PasswordTransformationMethod());
        //txtValor.setTransformationMethod(null);
    }
}
