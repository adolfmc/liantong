package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public enum EnumReaderType {
    READER_TYPE_SIM("SIM"),
    READER_TYPE_ESE("eSE"),
    READER_TYPE_SD("SD");
    
    private String value;

    EnumReaderType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
