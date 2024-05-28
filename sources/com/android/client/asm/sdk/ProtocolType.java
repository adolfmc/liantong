package com.android.client.asm.sdk;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum ProtocolType {
    OSTP,
    UAF;
    
    private static final String STR_OSTP = "ostp";
    private static final String STR_UAF = "uaf";

    public static String toString(ProtocolType protocolType) {
        return OSTP == protocolType ? "ostp" : "uaf";
    }

    public static ProtocolType fromString(String str) throws Exception {
        if (str.equalsIgnoreCase("ostp")) {
            return OSTP;
        }
        if (str.equalsIgnoreCase("uaf")) {
            return UAF;
        }
        throw new Exception("Invalid type " + str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Exception extends java.lang.Exception {
        private static final long serialVersionUID = 1;

        Exception(String str) {
        }
    }
}
