package com.gmrz.android.client.asm.api;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public enum AsmError {
    BAD_TOKEN(0),
    CANCELED(1),
    NO_MATCH(1),
    NOT_REGISTERED(1),
    NOT_SUPPORTED(1),
    SERVICE_UNAVAILABLE(1),
    SUCCESS(1),
    TRANSACTION_MODIFIED(1),
    UPDATE(1),
    USER_LOCKOUT(1),
    CHANGE_TOKEN(2),
    FAILURE(3),
    INVALID_MESSAGE(4),
    KEY_DISAPPEARED_PERMANENTLY(9),
    BIOMETRIC_USER_PREFERRED_IRIS(14),
    GM_NEED_REGISTER(18);
    

    /* renamed from: a */
    int f10146a;

    AsmError(int i) {
        this.f10146a = i;
    }

    public static AsmError createFromOstpCode(int i) {
        AsmError[] values;
        for (AsmError asmError : values()) {
            if (asmError.getOstpCode() == i) {
                return asmError;
            }
        }
        return null;
    }

    public final int getOstpCode() {
        return this.f10146a;
    }
}
