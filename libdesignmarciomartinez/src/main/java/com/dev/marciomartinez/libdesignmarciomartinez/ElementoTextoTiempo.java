package com.dev.marciomartinez.libdesignmarciomartinez;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ElementoTextoTiempo extends ElementoTexto {
    private Calendar mCalendarCurrentDate;
    private TimePickerDialog mTimerPickerDialog;
    private String dateFormat2;
    public ElementoTextoTiempo(Context context) {
        super(context);
    }

    public ElementoTextoTiempo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoTiempo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);
        habilitarEscritura(false);
        txtValor.setFocusableInTouchMode(false);
        mCalendarCurrentDate = java.util.Calendar.getInstance();

        setFormatoTiempo("KK:mm a");

        mTimerPickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        mCalendarCurrentDate.set(Calendar.HOUR, i);
                        mCalendarCurrentDate.set(Calendar.MINUTE, i1);

                        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat2, Locale.US);
                        String newValue = sdfDate.format(mCalendarCurrentDate.getTime());
                        setValor(newValue);
                        if (escuchadorValorCambio != null){
                            escuchadorValorCambio.OnValorCambio(newValue);
                        }
                    }
                },
                mCalendarCurrentDate.get(Calendar.HOUR),
                mCalendarCurrentDate.get(Calendar.MINUTE),
                false);

        txtTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimerPickerDialog.show();
            }
        });

        txtValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimerPickerDialog.show();
            }
        });

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimerPickerDialog.show();
            }
        });
    }

    public ElementoTextoTiempo setFormatoTiempo(String format) {
        checkValidTimeFormat(format);
        this.dateFormat2 = format;
        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat2, Locale.US);
        String newValue = sdfDate.format(mCalendarCurrentDate.getTime());
        setValor(newValue);
        if (escuchadorValorCambio != null){
            escuchadorValorCambio.OnValorCambio(newValue);
        }
        return this;
    }

    private void checkValidTimeFormat(String format) {
        try {
            new SimpleDateFormat(format, Locale.US);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Time format is not correct: " + e.getMessage());
        }
    }
}
