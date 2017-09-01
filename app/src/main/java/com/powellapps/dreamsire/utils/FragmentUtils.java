package com.powellapps.dreamsire.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by raphaelramos on 01/09/17.
 */

public class FragmentUtils {

    public static void replace(FragmentActivity activity, int id, Fragment fragment){
        activity.getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }
}
