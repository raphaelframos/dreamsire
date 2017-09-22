package com.powellapps.dreamsire.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.powellapps.dreamsire.utils.ConstantsUtils;

import java.math.BigDecimal;

/**
 * Created by raphaelramos on 01/09/17.
 */

public class Desejo {

    private Integer id;
    private String titulo;
    private String tipo;
    private String status;
    private BigDecimal valor;

    public Desejo(){}

    public Desejo(Cursor cursor) {
        setId(cursor.getInt(cursor.getColumnIndex(ConstantsUtils.DESEJO_ID)));
        setTitulo(cursor.getString(cursor.getColumnIndex(ConstantsUtils.DESEJO_TITULO)));
        setStatus(cursor.getString(cursor.getColumnIndex(ConstantsUtils.DESEJO_STATUS)));
        setValor(cursor.getString(cursor.getColumnIndex(ConstantsUtils.DESEJO_VALOR)));
        setTipo(cursor.getString(cursor.getColumnIndex(ConstantsUtils.DESEJO_TIPO)));
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setValor(String valor) {
        setValor(new BigDecimal(valor));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put(ConstantsUtils.DESEJO_TITULO, titulo);
        values.put(ConstantsUtils.DESEJO_STATUS, status);
        values.put(ConstantsUtils.DESEJO_VALOR, valor.toString());
        values.put(ConstantsUtils.DESEJO_TIPO, tipo);
        return values;
    }
}
