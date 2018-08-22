package com.powellapps.dreamsire.model;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by raphaelramos on 04/11/17.
 */

public class DesejoFirebase implements Comparable<DesejoFirebase>, Serializable{

    private String id;
    private FirebaseUser usuario;
    private Desejo desejo;
    private Long dataDeCriacao;
    private ArrayList<String> curtidas = new ArrayList<>();

    public DesejoFirebase(){
        dataDeCriacao = Calendar.getInstance().getTimeInMillis();
    }

    public FirebaseUser getUsuario() {
        return usuario;
    }

    public void setUsuario(FirebaseUser usuario) {
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

    @Override
    public int compareTo(DesejoFirebase o) {
        return o.getDataDeCriacao().compareTo(dataDeCriacao);
    }

    public ArrayList<String> getCurtidas() {
        return curtidas;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
