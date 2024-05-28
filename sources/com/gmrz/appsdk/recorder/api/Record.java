package com.gmrz.appsdk.recorder.api;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Record {

    /* renamed from: a */
    public long f10345a;

    /* renamed from: b */
    public OPERATION f10346b;

    /* renamed from: c */
    public ExcType f10347c;

    /* renamed from: d */
    public String f10348d;

    /* renamed from: e */
    public String f10349e;

    /* renamed from: f */
    public String f10350f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum ExcType {
        NOT_INIT,
        PARAM_NULL,
        PARAM_ERROR,
        ILLEGAL_INVOKE,
        COMM_ASM_FAILED,
        GET_INFO_FAILED,
        GET_DEVICE_ID_FAILED,
        NO_SUITABLE_AUTHENTICATOR,
        UNKNOWN,
        AK_ERROR
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum OPERATION {
        Init,
        GetInfo,
        GetDeviceID,
        CheckPolicy,
        Process,
        Reg,
        Auth,
        CheckNetSupport,
        Dereg,
        Query
    }

    public Record() {
    }

    public Record(OPERATION operation, ExcType excType, String str, String str2, String str3) {
        this.f10346b = operation;
        this.f10347c = excType;
        this.f10348d = str;
        this.f10349e = str2;
        this.f10350f = str3;
    }
}
