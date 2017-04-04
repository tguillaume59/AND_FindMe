package com.tguillaume.findme.common.manager.interfaces;
/**
 * @Project : AND_FindMe
 *
 * FDMFragmentListener.java
 *
 * Created by TARTARA Guillaume on 04/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import android.os.Bundle;

import com.tguillaume.findme.common.manager.key.FDMEnum;

public interface FDMFragmentListener {

    void showFragment(FDMEnum.EnumNameFragment sNameFragment);

    void showFragment(FDMEnum.EnumNameFragment sNameFragment, Bundle sBundle);

    void onRequestPermission();
}
