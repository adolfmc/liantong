package com.fido.uaf.ver0100.message;

import com.fido.uaf.ver0100.engine.UafEngine;
import com.fido.uaf.ver0100.types.OperationHeader;
import com.google.gson.annotations.Expose;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UafMessage extends UafJson {
    @Expose
    public OperationHeader header = new OperationHeader();
    public UafEngine uafEngine;

    public OperationHeader.OperationType getOperationType() {
        return this.header.f10121op;
    }
}
