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




    public String getQuantidadeDeDesejos(String status) {
        Integer quantidade;
        try {
            Cursor cursorDesejo = getReadableDatabase()
                    .query(ConstantsUtils.TABELA_DESEJO, null, ConstantsUtils.DESEJO_STATUS + " = ?", new String[]{status}, null, null, null);
            quantidade = cursorDesejo.getCount();
            cursorDesejo.close();
        }catch (Exception e){
            return "0";
        }
        return quantidade.toString();
    }

    public void remove(Integer id) {
        getWritableDatabase().delete(ConstantsUtils.TABELA_DESEJO, ConstantsUtils.DESEJO_ID + " = ? ", new String[] {id.toString()});
    }



    public void formata() {
        getWritableDatabase().delete(ConstantsUtils.TABELA_DESEJO, null, null);
    }


}
