package com.tguillaume.findme.modules.messages.entity;
/**
 * @Project : AND_FindMe
 * <p>
 * < file name >.java
 * <p>
 * Created by TARTARA Guillaume on 01/05/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

/**
 * Created by guillaume on 01/05/2017.
 */

public class FDMContactEntity {

    private String mName;
    private String mDate;
    private String mLastMessage;

    public FDMContactEntity(String sName,String sDate, String sLastMessage) {
        this.mDate = sDate;
        this.mLastMessage = sLastMessage;
        this.mName = sName;
    }

    public String getDate() {
        return mDate;
    }

    public String getLastMessage() {
        return mLastMessage;
    }

    public String getName() {
        return mName;
    }
}
