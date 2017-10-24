package com.powellapps.dreamsire.bd;

import com.powellapps.dreamsire.utils.ConstantsUtils;

/**
 * Created by raphaelramos on 22/09/17.
 */

public class ComandosSql {

    public static final String CRIAR_TABELA_DESEJO = "CREATE TABLE " + ConstantsUtils.TABELA_DESEJO +
            " (" + ConstantsUtils.DESEJO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ConstantsUtils.DESEJO_STATUS + " TEXT, " + ConstantsUtils.DESEJO_TIPO + " TEXT, " +
            ConstantsUtils.DESEJO_TITULO + " TEXT, " + ConstantsUtils.DESEJO_VALOR + " REAL, " +
            ConstantsUtils.DESEJO_ID_USUARIO + " TEXT)";

    public static final String CRIAR_TABELA_USUARIO = "CREATE TABLE " + ConstantsUtils.TABELA_USUARIO +
            " (" + ConstantsUtils.USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ConstantsUtils.USUARIO_NOME + " TEXT, " +
            ConstantsUtils.USUARIO_ID_REDE_SOCIAL + " TEXT, " + ConstantsUtils.USUARIO_FOTO + " TEXT" +
            ")";
    public static final String SELECIONA_DESEJO_DO_USUARIO = "SELECT * FROM " + ConstantsUtils.TABELA_USUARIO + " WHERE " + ConstantsUtils.DESEJO_ID_USUARIO + " = 1";
}
