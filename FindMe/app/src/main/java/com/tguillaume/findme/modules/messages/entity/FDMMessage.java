package com.tguillaume.findme.modules.messages.entity;
/**
 * @Project : AND_FindMe
 *
 * FDMMessage.java
 *
 * Created by TARTARA Guillaume on 01/05/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */


public class FDMMessage {

    private String mSender;
    private String mMessage;

    public FDMMessage(String sExpediteur, String sMessage) {
        this.mSender = sExpediteur;
        this.mMessage = sMessage;
    }

    public String getSender() {
        return mSender;
    }

    public String getMessage() {
        return mMessage;
    }
}
