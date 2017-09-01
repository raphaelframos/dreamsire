package com.powellapps.dreamsire.model;

import java.math.BigDecimal;

/**
 * Created by raphaelramos on 01/09/17.
 */

public class Desejo {

    private String titulo;
    private String tipo;
    private String status;
    private BigDecimal valor;

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
}
