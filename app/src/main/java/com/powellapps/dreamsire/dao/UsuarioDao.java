package com.powellapps.dreamsire.dao;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.powellapps.dreamsire.model.Usuario;
import com.powellapps.dreamsire.utils.ConstantsUtils;

/**
 * Created by raphaelramos on 20/10/17.
 */

public class UsuarioDao extends AbstractDAO{

    public UsuarioDao(Context context) {
        super(context);
    }

    public void salva(Usuario usuario) {
        if(usuario.getId() == null || usuario.getId() == 0){
            getWritableDatabase().insert(ConstantsUtils.TABELA_USUARIO, null, usuario.getValues());
        }else{
            getWritableDatabase().update(ConstantsUtils.TABELA_USUARIO, usuario.getValues(), ConstantsUtils.USUARIO_ID + " = ?",
                    new String[] {usuario.getId().toString()});
        }
    }

    public boolean existeUsuario() {
        try{
        return getUsuario() != null;
        }catch (Exception e){
            return false;
        }
    }

    public Usuario getUsuario() {
        Cursor cursor = getReadableDatabase().query(ConstantsUtils.TABELA_USUARIO, null, null, null, null,null,null);
        cursor.moveToFirst();
        Usuario usuario = new Usuario(cursor);
        cursor.close();
        return usuario;
    }

    public void deleta() {

    }
}
