package com.dev.marciomartinez.libdesignmarciomartinez;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ElementoTextoFechaTiempo extends ElementoTexto {
    private Calendar mCalendarCurrentDate;
    private DatePickerDialog mDatePickerDialog;
    private TimePickerDialog mTimerPickerDialog;
    private String dateFormat="", dateFormat2="";
    public ElementoTextoFechaTiempo(Context context) {
        super(context);
    }

    public ElementoTextoFechaTiempo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementoTextoFechaTiempo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

                        if (dateFormat.trim().equals("")){
                            dateFormat = "yyyy-MM-dd";
                        }

                        if (dateFormat2.trim().equals("")){
                            dateFormat2 = " KK:mm a";
                        }

                        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat + dateFormat2, Locale.US);
                        String newValue = sdfDate.format(mCalendarCurrentDate.getTime());
                        setValor(newValue);

                        mTimerPickerDialog.show();
                    }
                },
                mCalendarCurrentDate.get(Calendar.YEAR),
                mCalendarCurrentDate.get(Calendar.MONTH),
                mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));


        mTimerPickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        mCalendarCurrentDate.set(Calendar.HOUR, i);
                        mCalendarCurrentDate.set(Calendar.MINUTE, i1);

                        if (dateFormat.trim().equals("")){
                            dateFormat = "yyyy-MM-dd";
                        }

                        if (dateFormat2.trim().equals("")){
                            dateFormat2 = " KK:mm a";
                        }

                        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat + dateFormat2, Locale.US);
                        String newValue = sdfDate.format(mCalendarCurrentDate.getTime());
                        setValor(newValue.trim());
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

    public ElementoTextoFechaTiempo setFormatoFecha(String format) {
        checkValidDateFormat(format);
        this.dateFormat = format;
        if (dateFormat2 == null){
            dateFormat2 = "";
        }
        if (dateFormat2.trim().equals("")){
            dateFormat2 = " KK:mm a";
        }
        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat + dateFormat2, Locale.US);
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

    public ElementoTextoFechaTiempo setFormatoTiempo(String format) {
        checkValidTimeFormat(format);
        this.dateFormat2 = format;
        if (dateFormat == null){
            dateFormat = "";
        }
        if (dateFormat.trim().equals("")){
            dateFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat + " " + dateFormat2, Locale.US);
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

                        if (dateFormat.trim().equals("")){
                            dateFormat = "yyyy-MM-dd";
                        }

                        if (dateFormat2.trim().equals("")){
                            dateFormat2 = " KK:mm a";
                        }

                        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat + dateFormat2, Locale.US);
                        String newValue = sdfDate.format(mCalendarCurrentDate.getTime());
                        setValor(newValue);

                        mTimerPickerDialog.show();
                    }
                },
                mCalendarCurrentDate.get(Calendar.YEAR),
                mCalendarCurrentDate.get(Calendar.MONTH),
                mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));


        mTimerPickerDialog = new TimePickerDialog(mContext,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        mCalendarCurrentDate.set(Calendar.HOUR, i);
                        mCalendarCurrentDate.set(Calendar.MINUTE, i1);

                        if (dateFormat.trim().equals("")){
                            dateFormat = "yyyy-MM-dd";
                        }

                        if (dateFormat2.trim().equals("")){
                            dateFormat2 = " KK:mm a";
                        }

                        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat + dateFormat2, Locale.US);
                        String newValue = sdfDate.format(mCalendarCurrentDate.getTime());
                        setValor(newValue.trim());
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
