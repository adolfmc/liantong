package com.p319ss.android.downloadlib.addownload.model;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.addownload.model.OpenAppResult */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class OpenAppResult {
    @Source

    /* renamed from: b */
    private String f19081b;
    @Type

    /* renamed from: mb */
    private int f19082mb;
    @Message

    /* renamed from: ox */
    private int f19083ox;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.model.OpenAppResult$Message */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface Message {
        public static final int DEFAULT_MARKET_UNINSTALLED = 15;
        public static final int LAUNCH_INTENT_NOT_EXIST = 22;
        public static final int MARKET_UNINSTALLED = 13;
        public static final int OPEN_URL_INVALID = 24;
        public static final int OPEN_URL_NOT_EXIST = 21;
        public static final int PACKAGE_NAME_EMPTY = 11;
        public static final int START_ACTIVITY_EXCEPTION = 23;
        public static final int START_EXCEPTION = 14;
        public static final int TEST_FAILED = 25;
        public static final int URI_ERROR = 12;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.model.OpenAppResult$Source */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface Source {
        public static final String AM_HW = "am_hw";
        public static final String AM_KLLK1 = "am_kllk1";
        public static final String AM_KLLK2 = "am_kllk2";
        public static final String AM_M1 = "am_m1";
        public static final String AM_M2 = "am_m2";
        public static final String AM_V1 = "am_v1";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.model.OpenAppResult$Type */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface Type {
        public static final int FAILED_BY_PACKAGE = 4;
        public static final int FAILED_BY_URL = 2;
        public static final int FAILED_MARKET = 6;
        public static final int IGNORE_MARKET = 7;
        public static final int NONE = 0;
        public static final int SUCCESS_BY_PACKAGE = 3;
        public static final int SUCCESS_BY_URL = 1;
        public static final int SUCCESS_MARKET = 5;
    }

    public OpenAppResult(@Type int i) {
        this(i, 0, null);
    }

    public OpenAppResult(@Type int i, @Message int i2) {
        this(i, i2, null);
    }

    public OpenAppResult(@Type int i, @Source String str) {
        this(i, 0, str);
    }

    public OpenAppResult(@Type int i, @Message int i2, @Source String str) {
        this.f19082mb = i;
        this.f19083ox = i2;
        this.f19081b = str;
    }

    public int getType() {
        return this.f19082mb;
    }

    /* renamed from: mb */
    public int m7500mb() {
        return this.f19083ox;
    }

    /* renamed from: ox */
    public String m7499ox() {
        return this.f19081b;
    }
}
