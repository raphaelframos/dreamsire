package com.powellapps.dreamsire.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.powellapps.dreamsire.utils.ConstantsUtils;

/**
 * Created by raphaelramos on 22/09/17.
 */

public class BancoDeDados extends SQLiteOpenHelper {

    public BancoDeDados(Context context) {
        super(context, ConstantsUtils.BANCO_NOME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      //  db.execSQL("DROP TABLE IF EXISTS " + ConstantsUtils.TABELA_DESEJO);
        db.execSQL(ComandosSql.CRIAR_TABELA_DESEJO);
        db.execSQL(ComandosSql.CRIAR_TABELA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
