package com.powellapps.dreamsire.model;

/**
 * Created by raphaelramos on 25/10/17.
 */

public class Singleton {

    public static Singleton instance;
    private boolean estaEditando;

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public boolean estaEditando() {
        return estaEditando;
    }

    public void setEstaEditando(boolean estaEditando) {
        this.estaEditando = estaEditando;
    }
}
