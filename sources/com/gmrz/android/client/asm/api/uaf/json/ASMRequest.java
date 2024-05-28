package com.gmrz.android.client.asm.api.uaf.json;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ASMRequest {
    @Expose
    public JsonObject args;
    @Expose
    public Version asmVersion;
    @Expose
    public Short authenticatorIndex;
    @Expose
    public List<Extension> exts;
    @Expose
    public RequestType requestType;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum RequestType {
        GetInfo,
        Register,
        Authenticate,
        Deregister,
        GetRegistrations,
        OpenSettings
    }

    public String toString() {
        return "ASMRequest [requestType=" + this.requestType + ", asmVersion=" + this.asmVersion + ", authenticatorIndex=" + this.authenticatorIndex + ", args=" + this.args + ", exts=" + this.exts + "]";
    }
}
