package com.tguillaume.findme.common.entity;
/**
 * @Project : AND_FindMe
 * <p>
 * < file name >.java
 * <p>
 * Created by TARTARA Guillaume on 25/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import java.util.ArrayList;

/**
 * Created by guillaume on 25/04/2017.
 */

public class FDMUser {

    private String mName;
    private ArrayList<String> mListClues;
    private int mIdClue = 0;


    public FDMUser (String sName){
        mName = sName;
        mListClues = new ArrayList<>();
    }

    public String getName(){
        return mName;
    }

    public void addClue(String sClue){
        if(mListClues != null){
            mListClues.add(sClue);
        }
    }

    public String getNextClue(){
        String rClue = "";
        if(mListClues != null){
            if(mIdClue < mListClues.size()){
               rClue = mListClues.get(mIdClue);
                mIdClue ++;
            }
        }
        return rClue;
    }


}
