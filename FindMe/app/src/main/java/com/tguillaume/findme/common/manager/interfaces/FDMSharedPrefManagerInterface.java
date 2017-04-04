package com.tguillaume.findme.common.manager.interfaces;
/**
 * @Project : AND_FindMe
 *
 * FDMSharedPrefManagerInterface.java
 *
 * Created by TARTARA Guillaume on 04/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */


public interface FDMSharedPrefManagerInterface {

    String getString(String sKey);
    int getInt(String sKey);
    float getFloat(String sKey);
    long getLong(String sKey);
    boolean getBoolean(String sKey);

    void putString(String sKey,String sValue);
    void putInt(String sKey, int sValue);
    void putFloat(String sKey, float sValue);
    void putLong(String sKey, long sValue);
    void putBoolean(String sKey, boolean sValue);
}
