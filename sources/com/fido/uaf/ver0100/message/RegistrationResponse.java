package com.fido.uaf.ver0100.message;

import com.fido.uaf.ver0100.types.AuthenticatorRegistrationAssertion;
import com.fido.uaf.ver0100.types.OperationHeader;
import com.google.gson.annotations.Expose;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class RegistrationResponse extends UafResponse {
    @Expose
    public List<AuthenticatorRegistrationAssertion> assertions;
    public String fcParams;

    public RegistrationResponse(String str) {
        this.header.setOp(OperationHeader.OperationType.Reg);
        this.header.appID = str;
    }
}
