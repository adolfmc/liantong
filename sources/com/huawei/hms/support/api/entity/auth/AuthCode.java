package com.huawei.hms.support.api.entity.auth;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface AuthCode {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ErrorCode {
        public static final int CERT_FINGERPRINT_EMPTY = 907135702;
        public static final int GET_SCOPE_ERROR = 907135700;
        public static final int PERMISSION_LIST_EMPTY = 907135703;
        public static final int SCOPE_LIST_EMPTY = 907135701;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface StatusCode {
        public static final int AUTH_INFO_NOT_EXIST = 6002;
        public static final int CERT_FINGERPRINT_ERROR = 6003;

        /* renamed from: OK */
        public static final int f11719OK = 0;
        public static final int PERMISSION_EXPIRED = 6006;
        public static final int PERMISSION_NOT_AUTHORIZED = 6005;
        public static final int PERMISSION_NOT_EXIST = 6004;
        public static final int WAITING_CONNECT = 6001;
    }
}
