package com.powellapps.dreamsire.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by raphaelramos on 04/11/17.
 */

public class DesejoFirebase implements Comparable<DesejoFirebase>{

    private Usuario usuario;
    private Desejo desejo;
    private Long dataDeCriacao;
    private ArrayList<String> curtidas = new ArrayList<>();

    public DesejoFirebase(){
        dataDeCriacao = Calendar.getInstance().getTimeInMillis();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Desejo getDesejo() {
        return desejo;
    }

    public void setDesejo(Desejo desejo) {
        this.desejo = desejo;
    }

    public Long getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Long dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public String retornaKey() {
        return usuario.getIdRedeSocial() + desejo.getId();
    }

    @Override
    public int compareTo(@NonNull DesejoFirebase o) {
        return o.getDataDeCriacao().compareTo(dataDeCriacao);
    }

    public ArrayList<String> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(ArrayList<String> curtidas) {
        this.curtidas = curtidas;
    }

    public void defineCurtida(String idRedeSocial) {
        if(curtidas == null){
            curtidas = new ArrayList<>();
        }
        if(curtidas.contains(idRedeSocial)){
            curtidas.remove(idRedeSocial);
        }else{
            curtidas.add(idRedeSocial);
        }
    }
}
