package com.fido.uaf.ver0100.message;

import com.fido.uaf.ver0100.types.AuthenticatorSignAssertion;
import com.fido.uaf.ver0100.types.OperationHeader;
import com.google.gson.annotations.Expose;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AuthenticationResponse extends UafResponse {
    @Expose
    public List<AuthenticatorSignAssertion> assertions;
    @Expose
    public String fcParams;

    public AuthenticationResponse(String str) {
        this.header.f10121op = OperationHeader.OperationType.Auth;
        this.header.appID = str;
    }
}
