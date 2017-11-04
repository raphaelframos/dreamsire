package com.powellapps.dreamsire.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.powellapps.dreamsire.bd.ComandosSql;
import com.powellapps.dreamsire.model.Desejo;
import com.powellapps.dreamsire.utils.ConstantsUtils;

import java.util.ArrayList;

/**
 * Created by raphaelramos on 22/09/17.
 */

public class DesejoDAO extends AbstractDAO{

    public DesejoDAO(Context context) {
        super(context);
    }

    public Long salva(Desejo desejo) {
        if(desejo.getId() == null){
            return adiciona(desejo);
        }else{
            atualiza(desejo);
            return desejo.getId().longValue();
        }

    }

    public Long adiciona(Desejo desejo){
        return getWritableDatabase()
                .insert(ConstantsUtils.TABELA_DESEJO,
                        null, desejo.retornaValues());
    }

    public void atualiza(Desejo desejo){
        Integer id = getWritableDatabase().update(ConstantsUtils.TABELA_DESEJO, desejo.retornaValues(), ConstantsUtils.DESEJO_ID + " = ?", new String[] {desejo.getId().toString()});
        Log.v("", "Olco " + id);
    }

    public int getQuantidadeDeDesejos() {
        Cursor cursorDesejo = getReadableDatabase()
                .query(ConstantsUtils.TABELA_DESEJO, null,null,null,null,null,null);
        int quantidadeDeDesejos = cursorDesejo.getCount();
        fecha(cursorDesejo);
        return quantidadeDeDesejos;
    }

    public ArrayList<Desejo> getDesejos() {
        ArrayList<Desejo> desejos = new ArrayList<>();
        Cursor cursorDesejo = getReadableDatabase()
                .query(ConstantsUtils.TABELA_DESEJO, null,null,null,null,null,null);
        while (cursorDesejo.moveToNext()){
            Desejo desejo = new Desejo(cursorDesejo);
            desejos.add(desejo);
        }
        fecha(cursorDesejo);
        return desejos;
    }

    public ArrayList<Desejo> getDesejos(String idDoUsuario) {

        ArrayList<Desejo> desejos = new ArrayList<>();
        Cursor cursorDesejo = getReadableDatabase()
                .query(ConstantsUtils.TABELA_DESEJO, null, ConstantsUtils.DESEJO_ID_USUARIO + " = ?", new String[] {idDoUsuario},null,null,null);
        while (cursorDesejo.moveToNext()){
            Desejo desejo = new Desejo(cursorDesejo);
            desejos.add(desejo);
        }
        fecha(cursorDesejo);
        return desejos;
    }

    public String getQuantidadeDeDesejos(String idDoUsuario, String status) {
        ArrayList<Desejo> desejos = new ArrayList<>();
        Cursor cursorDesejo = getReadableDatabase()
                .query(ConstantsUtils.TABELA_DESEJO, null, ConstantsUtils.DESEJO_ID_USUARIO + " = ? AND " + ConstantsUtils.DESEJO_STATUS + " = ?", new String[] {idDoUsuario, status},null,null,null);
        Integer quantidade = cursorDesejo.getCount();
        cursorDesejo.close();
        return quantidade.toString();
    }

    public void remove(Integer id) {
        getWritableDatabase().delete(ConstantsUtils.TABELA_DESEJO, ConstantsUtils.DESEJO_ID + " = ? ", new String[] {id.toString()});
    }

    public ArrayList<Desejo> getDesejosOrdenados(String idRedeSocial) {
        ArrayList<Desejo> desejos = new ArrayList<>();
        Cursor cursorDesejo = getReadableDatabase().rawQuery(ComandosSql.SELECIONA_DESEJOS_ORDENADOS, new String[] {idRedeSocial});
        while (cursorDesejo.moveToNext()){
            Desejo desejo = new Desejo(cursorDesejo);
            desejos.add(desejo);
        }
        fecha(cursorDesejo);
        return desejos;
    }
}
