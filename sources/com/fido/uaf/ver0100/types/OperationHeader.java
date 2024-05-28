package com.fido.uaf.ver0100.types;

import com.gmrz.android.client.asm.api.uaf.json.Extension;
import com.gmrz.android.client.asm.api.uaf.json.Version;
import com.google.gson.annotations.Expose;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class OperationHeader {
    @Expose
    public String appID = "";
    public List<Extension> exts;
    @Expose

    /* renamed from: op */
    public OperationType f10121op;
    public String serverData;
    @Expose
    public Version upv;

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
        return this.f10121op;
    }

    public void setOp(OperationType operationType) {
        this.f10121op = operationType;
    }

    public String getAppID() {
        return this.appID;
    }

    public void setAppID(String str) {
        this.appID = str;
    }

    public String getSessionID() {
        return this.serverData;
    }

    public void setSessionID(String str) {
        this.serverData = str;
    }
}
