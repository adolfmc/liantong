package com.fido.uaf.ver0100.types;

import com.google.gson.annotations.Expose;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DeregisterAuthenticator {
    @Expose
    public String aaid;
    @Expose
    public String keyID;

    public DeregisterAuthenticator(String str, String str2) {
        this.aaid = str;
        this.keyID = str2;
    }
}
