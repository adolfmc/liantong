package com.fido.uaf.ver0100.message;

import com.fido.uaf.ver0100.types.OperationHeader;
import com.fido.uaf.ver0100.types.Policy;
import com.google.gson.annotations.Expose;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class RegistrationRequest extends UafRequest {
    @Expose
    public String challenge;
    @Expose
    public Policy policy;
    @Expose
    public String username;

    public RegistrationRequest(String str) {
        this.header.setOp(OperationHeader.OperationType.Reg);
        this.header.appID = str;
    }
}
