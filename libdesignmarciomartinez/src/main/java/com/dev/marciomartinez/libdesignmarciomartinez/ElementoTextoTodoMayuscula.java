package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

public class ElementoTextoTodoMayuscula extends ElementoTextoNormal {
    public ElementoTextoTodoMayuscula(Context context) {
        super(context);
    }

    public ElementoTextoTodoMayuscula(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoTodoMayuscula(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);
        txtValor.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }
}
