package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;

public class ElementoTextoMultiLinea extends ElementoTextoNormal {
    public ElementoTextoMultiLinea(Context context) {
        super(context);
    }

    public ElementoTextoMultiLinea(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoMultiLinea(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);
        txtValor.setMaxLines(4);
        txtValor.setSingleLine(false);
        txtValor.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_MULTI_LINE);
    }
}
