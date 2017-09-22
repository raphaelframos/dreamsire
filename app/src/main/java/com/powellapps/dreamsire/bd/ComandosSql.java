package com.powellapps.dreamsire.bd;

import com.powellapps.dreamsire.utils.ConstantsUtils;

/**
 * Created by raphaelramos on 22/09/17.
 */

public class ComandosSql {

    public static final String CRIAR_TABELA_DESEJO = "CREATE TABLE " + ConstantsUtils.TABELA_DESEJO +
            " (" + ConstantsUtils.DESEJO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ConstantsUtils.DESEJO_STATUS + " TEXT, " + ConstantsUtils.DESEJO_TIPO + " TEXT, " +
            ConstantsUtils.DESEJO_TITULO + " TEXT, " + ConstantsUtils.DESEJO_VALOR + " REAL)";
}
