package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;

public class ElementoTextoCorreo extends ElementoTextoNormal {
    public ElementoTextoCorreo(Context context) {
        super(context);
    }

    public ElementoTextoCorreo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoCorreo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);
        txtValor.setRawInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }
}
