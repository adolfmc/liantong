package com.huawei.hms.push;

import com.huawei.hms.aaid.constant.ErrorEnum;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class BaseException extends Exception {

    /* renamed from: a */
    private final int f11565a;

    /* renamed from: b */
    private final ErrorEnum f11566b;

    public BaseException(int i) {
        ErrorEnum fromCode = ErrorEnum.fromCode(i);
        this.f11566b = fromCode;
        this.f11565a = fromCode.getExternalCode();
    }

    public int getErrorCode() {
        return this.f11565a;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.f11566b.getMessage();
    }
}
