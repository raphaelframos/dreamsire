package com.powellapps.dreamsire.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.powellapps.dreamsire.utils.ConstantsUtils;

/**
 * Created by raphaelramos on 20/10/17.
 */

public class Usuario {

    private Integer id;
    private String nome;
    private String foto;
    private String idRedeSocial;

    public Usuario(Cursor cursor) {
        setIdRedeSocial(cursor.getString(cursor.getColumnIndex(ConstantsUtils.USUARIO_ID_REDE_SOCIAL)));
        setId(cursor.getInt(cursor.getColumnIndex(ConstantsUtils.USUARIO_ID)));
        setNome(cursor.getString(cursor.getColumnIndex(ConstantsUtils.USUARIO_NOME)));
        setFoto(cursor.getString(cursor.getColumnIndex(ConstantsUtils.USUARIO_FOTO)));
    }

    public Usuario() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ContentValues getValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantsUtils.USUARIO_ID_REDE_SOCIAL, idRedeSocial);
        contentValues.put(ConstantsUtils.USUARIO_ID, id);
        contentValues.put(ConstantsUtils.USUARIO_NOME, nome);
        contentValues.put(ConstantsUtils.USUARIO_FOTO, foto);
        return contentValues;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getIdRedeSocial() {
        return idRedeSocial;
    }

    public void setIdRedeSocial(String idRedeSocial) {
        this.idRedeSocial = idRedeSocial;
    }
}
