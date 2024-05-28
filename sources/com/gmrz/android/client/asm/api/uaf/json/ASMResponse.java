package com.gmrz.android.client.asm.api.uaf.json;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ASMResponse {
    @Expose
    public List<Extension> exts;
    @Expose
    public JsonObject responseData;
    @Expose
    public short statusCode;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface StatusCode {
        public static final short SAMSUNG_ASM_STATUS_USER_LOCKOUT = 16383;
        public static final short UAF_ASM_STATUS_ACCESS_DENIED = 2;
        public static final short UAF_ASM_STATUS_AUTHENTICATOR_EXISTS = 4;
        public static final short UAF_ASM_STATUS_ERROR = 1;
        public static final short UAF_ASM_STATUS_KEY_DISAPPEARED_PERMANENTLY = 9;
        public static final short UAF_ASM_STATUS_OK = 0;
        public static final short UAF_ASM_STATUS_USER_CANCELLED = 3;
        public static final short UAF_ASM_STATUS_USER_LOCKOUT = 16;
    }
}
