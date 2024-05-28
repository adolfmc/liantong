package com.p210hp.hpl.sparta;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.DOMException */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DOMException extends Exception {
    public static final short DOMSTRING_SIZE_ERR = 2;
    public static final short HIERARCHY_REQUEST_ERR = 3;
    public static final short NOT_FOUND_ERR = 8;
    public short code;

    public DOMException(short s, String str) {
        super(str);
        this.code = s;
    }
}
