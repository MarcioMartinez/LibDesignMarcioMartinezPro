package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;

public class ElementoTextoNumeroDecimal extends ElementoTextoNormal {
    public ElementoTextoNumeroDecimal(Context context) {
        super(context);
    }

    public ElementoTextoNumeroDecimal(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoNumeroDecimal(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);
        txtValor.setRawInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }
}
