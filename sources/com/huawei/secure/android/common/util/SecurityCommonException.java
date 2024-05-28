package com.huawei.secure.android.common.util;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SecurityCommonException extends Exception {

    /* renamed from: c */
    private static final long f12154c = 1;

    /* renamed from: a */
    private String f12155a;

    /* renamed from: b */
    private String f12156b;

    public SecurityCommonException() {
    }

    public String getMsgDes() {
        return this.f12156b;
    }

    public String getRetCd() {
        return this.f12155a;
    }

    public SecurityCommonException(Throwable th) {
        super(th);
    }

    public SecurityCommonException(String str, Throwable th) {
        super(str, th);
    }

    public SecurityCommonException(String str) {
        super(str);
        this.f12156b = str;
    }

    public SecurityCommonException(String str, String str2) {
        this.f12155a = str;
        this.f12156b = str2;
    }
}
