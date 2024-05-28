package com.fido.uaf.ver0100.message;

import com.fido.uaf.ver0100.types.OperationHeader;
import com.fido.uaf.ver0100.types.Policy;
import com.gmrz.android.client.asm.api.uaf.json.AuthenticateIn;
import com.google.gson.annotations.Expose;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AuthenticationRequest extends UafRequest {
    @Expose
    public String challenge;
    @Expose
    public Policy policy;
    @Expose
    public List<AuthenticateIn.Transaction> transaction;

    public AuthenticationRequest(String str) {
        this.header.appID = str;
        this.header.f10121op = OperationHeader.OperationType.Auth;
    }
}
