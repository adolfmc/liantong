package com.fido.uaf.ver0100.types;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UafError {
    public static Error get(short s) {
        Error[] values;
        for (Error error : Error.values()) {
            if (error.code() == s) {
                return error;
            }
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum Error {
        NO_ERROR(new C4330a(0, "No error")),
        WAIT_USER_ACTION(new C4330a(1, "Waiting for user action")),
        INSECURE_TRANSPORT(new C4330a(2, "Insecure transport")),
        USER_CANCELLED(new C4330a(3, "User cancelled")),
        UNSUPPORTED_VERSION(new C4330a(4, "Invalid UAF protocol version")),
        NO_SUITABLE_AUTHENTICATOR(new C4330a(5, "No Suitable authenticator")),
        PROTOCOL_ERROR(new C4330a(6, "Protocol error")),
        UNTRUSTED_FACET_ID(new C4330a(7, "Untrusted Faced ID")),
        KEY_DISAPPEARED_PERMANENTLY(new C4330a(9, "Key Disappeared Permanently")),
        BIOMETRIC_USER_PREFERRED_IRIS(new C4330a(14, "Biometric User Preferred IRIS")),
        GM_NEED_REGISTER(new C4330a(18, "gm need register")),
        USER_LOCKOUT(new C4330a(16, "User Lockout")),
        FINGER_SETS_CHANGE(new C4330a(19, "Finger Sets Change")),
        UNKNOWN(new C4330a(255, "Unknown Error"));
        

        /* renamed from: a */
        private final C4330a f10122a;

        Error(C4330a c4330a) {
            this.f10122a = c4330a;
        }

        public final short code() {
            return this.f10122a.f10124b;
        }

        public final String text() {
            return this.f10122a.f10123a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.fido.uaf.ver0100.types.UafError$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4330a {

        /* renamed from: a */
        final String f10123a;

        /* renamed from: b */
        final short f10124b;

        public C4330a(short s, String str) {
            this.f10124b = s;
            this.f10123a = str;
        }
    }
}
