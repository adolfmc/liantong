package com.gmrz.appsdk.entity;

import com.google.gson.annotations.Expose;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class OperationHeader {
    @Expose

    /* renamed from: op */
    public OperationType f10344op;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum OperationType {
        Reg,
        Auth,
        Dereg
    }

    public OperationType getOp() {
        return this.f10344op;
    }
}
