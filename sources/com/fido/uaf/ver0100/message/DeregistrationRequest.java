package com.fido.uaf.ver0100.message;

import com.fido.uaf.ver0100.types.DeregisterAuthenticator;
import com.fido.uaf.ver0100.types.OperationHeader;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DeregistrationRequest extends UafRequest {
    @Expose
    public List<DeregisterAuthenticator> authenticators;

    public DeregistrationRequest(String str) {
        this.header.f10121op = OperationHeader.OperationType.Dereg;
        this.header.appID = str;
        this.authenticators = new ArrayList();
    }
}
