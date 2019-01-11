package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;

public class ElementoTextoPrimeraMayuscula extends ElementoTextoNormal {
    public ElementoTextoPrimeraMayuscula(Context context) {
        super(context);
    }

    public ElementoTextoPrimeraMayuscula(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoPrimeraMayuscula(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);
        txtValor.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }
}
