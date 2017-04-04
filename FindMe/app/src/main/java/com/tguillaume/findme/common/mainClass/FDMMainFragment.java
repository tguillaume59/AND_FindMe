package com.tguillaume.findme.common.mainClass;


import android.content.Context;
import android.support.v4.app.Fragment;
import com.tguillaume.findme.common.manager.implementation.FDMSharedPrefManager;
import com.tguillaume.findme.common.manager.interfaces.FDMFragmentListener;


import java.io.IOException;

/**
 * @Project : AND_FindMe
 *
 * FDMMainFragment.java
 *
 * Created by TARTARA Guillaume on 04/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */


public class FDMMainFragment extends Fragment {

    private static String TAG = FDMMainFragment.class.getSimpleName();

    protected Context mContext;
    protected FDMSharedPrefManager mSharedPrefManager;
    protected FDMFragmentListener mFragmentListener;

    public void init() {
        mContext = getActivity();
        mSharedPrefManager = new FDMSharedPrefManager(mContext);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentListener = (FDMFragmentListener) getActivity();
    }


}
