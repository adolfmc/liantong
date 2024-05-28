package com.baidu.platform.comapi.longlink;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum ELongLinkStatus {
    OK(0),
    SendFormatError(OK.getStatusCode() + 1),
    SendUnRegistered(OK.getStatusCode() + 2),
    SendLimited(OK.getStatusCode() + 3),
    SendDataLenLimited(OK.getStatusCode() + 4),
    SendInvalidReqID(OK.getStatusCode() + 5),
    ResultConnectError(OK.getStatusCode() + 6),
    ResultSendError(OK.getStatusCode() + 7),
    ResultTimeout(OK.getStatusCode() + 8),
    ResultServerError(OK.getStatusCode() + 9),
    CloudStop(OK.getStatusCode() + 10),
    CloudRestart(OK.getStatusCode() + 11);
    

    /* renamed from: a */
    private int f7553a;

    /* renamed from: b */
    private int f7554b;

    ELongLinkStatus(int i) {
        this.f7553a = i;
    }

    public int getRequestId() {
        return this.f7554b;
    }

    public int getStatusCode() {
        return this.f7553a;
    }

    public void setRequestId(int i) {
        this.f7554b = i;
    }
}
