package com.android.client.asm.sdk;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AuthenticatorException extends Exception {
    private static final long serialVersionUID = 1;
    private StatusCode mStatusCode;

    public AuthenticatorException() {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class StatusCode {
        private final int mStatus;

        public StatusCode(int i) {
            this.mStatus = i;
        }

        public int getStatus() {
            return this.mStatus;
        }
    }

    public AuthenticatorException(StatusCode statusCode) {
        super(statusCode.toString());
        this.mStatusCode = statusCode;
    }

    public AuthenticatorException(String str) {
        super(str);
    }

    public AuthenticatorException(String str, Throwable th) {
        super(str, th);
    }

    public AuthenticatorException(Throwable th) {
        super(th);
    }

    public StatusCode getStatusCode() {
        return this.mStatusCode;
    }
}
