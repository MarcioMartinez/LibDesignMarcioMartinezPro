package com.dev.marciomartinez.libdesignmarciomartinez;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ElementoTextoFecha extends ElementoTexto {
    private Calendar mCalendarCurrentDate;
    private DatePickerDialog mDatePickerDialog;
    private String dateFormat;
    public ElementoTextoFecha(Context context) {
        super(context);
    }

    public ElementoTextoFecha(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoFecha(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        super.init(context, attributeSet);
        habilitarEscritura(false);
        txtValor.setFocusableInTouchMode(false);
        mCalendarCurrentDate = java.util.Calendar.getInstance();

        setFormatoFecha("yyyy-MM-dd");

        mDatePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mCalendarCurrentDate.set(Calendar.YEAR, year);
                        mCalendarCurrentDate.set(Calendar.MONTH, monthOfYear);
                        mCalendarCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat, Locale.US);
                        String newValue = sdfDate.format(mCalendarCurrentDate.getTime());
                        setValor(newValue);
                        if (escuchadorValorCambio != null){
                            escuchadorValorCambio.OnValorCambio(newValue);
                        }
                    }
                },
                mCalendarCurrentDate.get(Calendar.YEAR),
                mCalendarCurrentDate.get(Calendar.MONTH),
                mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));

        txtTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });

        txtValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });

         setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });
    }


    public ElementoTextoFecha setFormatoFecha(String format) {
        checkValidDateFormat(format);
        this.dateFormat = format;
        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat, Locale.US);
        String newValue = sdfDate.format(mCalendarCurrentDate.getTime());
        setValor(newValue);
        if (escuchadorValorCambio != null){
            escuchadorValorCambio.OnValorCambio(newValue);
        }
        return this;
    }

    private void checkValidDateFormat(String format) {
        try {
            new SimpleDateFormat(format, Locale.US);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Date format is not correct: " + e.getMessage());
        }
    }

    public void resetear(){
        habilitarEscritura(false);
        txtValor.setFocusableInTouchMode(false);
        mCalendarCurrentDate = java.util.Calendar.getInstance();

        setFormatoFecha("yyyy-MM-dd");

        mDatePickerDialog = new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mCalendarCurrentDate.set(Calendar.YEAR, year);
                        mCalendarCurrentDate.set(Calendar.MONTH, monthOfYear);
                        mCalendarCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat, Locale.US);
                        String newValue = sdfDate.format(mCalendarCurrentDate.getTime());
                        setValor(newValue);
                        if (escuchadorValorCambio != null){
                            escuchadorValorCambio.OnValorCambio(newValue);
                        }
                    }
                },
                mCalendarCurrentDate.get(Calendar.YEAR),
                mCalendarCurrentDate.get(Calendar.MONTH),
                mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));

        txtTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });

        txtValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });
    }
}
