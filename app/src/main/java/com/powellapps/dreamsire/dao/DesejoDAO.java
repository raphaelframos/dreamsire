package com.powellapps.dreamsire.dao;

import android.content.Context;
import android.database.Cursor;

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

    public void salva(Desejo desejo) {
        getWritableDatabase()
                .insert(ConstantsUtils.TABELA_DESEJO,
                        null, desejo.getValues());
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
}
