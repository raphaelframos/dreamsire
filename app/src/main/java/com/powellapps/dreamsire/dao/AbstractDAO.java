package com.powellapps.dreamsire.dao;

import android.content.Context;
import android.database.Cursor;

import com.powellapps.dreamsire.bd.BancoDeDados;

/**
 * Created by raphaelramos on 22/09/17.
 */

public abstract class AbstractDAO extends BancoDeDados {

    public AbstractDAO(Context context) {
        super(context);
    }

    public void fecha(Cursor cursor){
        try{
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
