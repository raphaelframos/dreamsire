package com.powellapps.dreamsire.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.powellapps.dreamsire.utils.ConstantsUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by raphaelramos on 01/09/17.
 */

public class Desejo implements Serializable{

    private String id;
    private String titulo;
    private String tipo;
    private String status;
    private String valor;
    private String descricao;
    private String idUsuario;

    public Desejo(){}


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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public boolean esta(String status) {
        return this.status.equalsIgnoreCase(status);
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
