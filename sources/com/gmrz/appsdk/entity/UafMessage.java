package com.gmrz.appsdk.entity;

import com.gmrz.appsdk.entity.OperationHeader;
import com.google.gson.annotations.Expose;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UafMessage {
    @Expose
    public String challenge;
    @Expose
    public OperationHeader header = new OperationHeader();

    public OperationHeader.OperationType getOperationType() {
        return this.header.f10344op;
    }
}
