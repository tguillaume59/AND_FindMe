package com.tguillaume.findme.common.manager.implementation;
/**
 * @Project : AND_FindMe
 *
 * FDMSharedPrefManager.java
 *
 * Created by TARTARA Guillaume on 04/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.tguillaume.findme.common.manager.interfaces.FDMSharedPrefManagerInterface;
import com.tguillaume.findme.common.manager.key.FDMSharedPrefKey;


public class FDMSharedPrefManager  implements FDMSharedPrefManagerInterface{



    private static String TAG = FDMSharedPrefManager.class.getSimpleName();
    private Context mContext;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    public FDMSharedPrefManager(Context sContext){
        mContext = sContext;
        mPrefs = mContext.getSharedPreferences(FDMSharedPrefKey.PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();
    }
    @Override
    public String getString(String sKey) {
        return mPrefs.getString(sKey,"");
    }

    @Override
    public int getInt(String sKey) {
        return mPrefs.getInt(sKey,-1);
    }

    @Override
    public float getFloat(String sKey) {
        return mPrefs.getFloat(sKey, -1);
    }

    @Override
    public long getLong(String sKey) {
        return mPrefs.getLong(sKey,-1);
    }

    @Override
    public boolean getBoolean(String sKey) {
        return mPrefs.getBoolean(sKey,false);
    }

    @Override
    public void putString(String sKey, String sValue) {
        mEditor.putString(sKey, sValue);
        mEditor.commit();
    }

    @Override
    public void putInt(String sKey, int sValue) {
        mEditor.putInt(sKey,sValue);
        mEditor.commit();
    }

    @Override
    public void putFloat(String sKey, float sValue) {
        mEditor.putFloat(sKey,sValue);
        mEditor.commit();
    }

    @Override
    public void putLong(String sKey, long sValue) {
        mEditor.putLong(sKey,sValue);
        mEditor.commit();
    }

    @Override
    public void putBoolean(String sKey, boolean sValue) {
        mEditor.putBoolean(sKey,sValue);
        mEditor.commit();
    }
}
