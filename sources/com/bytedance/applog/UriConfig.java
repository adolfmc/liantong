package com.bytedance.applog;

import com.bytedance.applog.util.UriConstants;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UriConfig {
    public static final String PATH_AB = "/service/2/abtest_config/";
    public static final String PATH_ACTIVE = "/service/2/app_alert_check/";
    public static final String PATH_CONFIG = "/service/2/log_settings/";
    public static final String PATH_REGISTER = "/service/2/device_register/";
    public static final String PATH_SEND = "/service/2/app_log/";

    /* renamed from: a */
    public final String f8324a;

    /* renamed from: b */
    public final String f8325b;

    /* renamed from: c */
    public final String[] f8326c;

    /* renamed from: d */
    public final String[] f8327d;

    /* renamed from: e */
    public final String f8328e;

    /* renamed from: f */
    public final String f8329f;

    /* renamed from: g */
    public final String f8330g;

    /* renamed from: h */
    public final String f8331h;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.UriConfig$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3534b {

        /* renamed from: a */
        public String f8332a;

        /* renamed from: b */
        public String f8333b;

        /* renamed from: c */
        public String[] f8334c;

        /* renamed from: d */
        public String[] f8335d;

        /* renamed from: e */
        public String f8336e;

        /* renamed from: f */
        public String f8337f;

        /* renamed from: g */
        public String f8338g;

        /* renamed from: h */
        public String f8339h;

        /* renamed from: a */
        public UriConfig m17351a() {
            return new UriConfig(this, null);
        }
    }

    public /* synthetic */ UriConfig(C3534b c3534b, C3533a c3533a) {
        this.f8324a = c3534b.f8332a;
        this.f8325b = c3534b.f8333b;
        this.f8326c = c3534b.f8334c;
        this.f8327d = c3534b.f8335d;
        this.f8328e = c3534b.f8336e;
        this.f8329f = c3534b.f8337f;
        this.f8330g = c3534b.f8338g;
        this.f8331h = c3534b.f8339h;
    }

    public static UriConfig createByDomain(String str, String[] strArr) {
        C3534b c3534b = new C3534b();
        c3534b.f8332a = str + "/service/2/device_register/";
        c3534b.f8333b = str + "/service/2/app_alert_check/";
        if (strArr != null && strArr.length != 0) {
            String[] strArr2 = new String[strArr.length + 1];
            strArr2[0] = str + "/service/2/app_log/";
            for (int i = 1; i < strArr2.length; i++) {
                strArr2[i] = C3535a.m17348a(new StringBuilder(), strArr[i - 1], "/service/2/app_log/");
            }
            c3534b.f8334c = strArr2;
        } else {
            c3534b.f8334c = new String[]{str + "/service/2/app_log/"};
        }
        c3534b.f8336e = str + "/service/2/log_settings/";
        c3534b.f8337f = str + "/service/2/abtest_config/";
        return c3534b.m17351a();
    }

    public static UriConfig createUriConfig(int i) {
        return UriConstants.createUriConfig(i);
    }

    public String getAbUri() {
        return this.f8329f;
    }

    public String getActiveUri() {
        return this.f8325b;
    }

    public String getMonitorUri() {
        return this.f8331h;
    }

    public String getProfileUri() {
        return this.f8330g;
    }

    public String[] getRealUris() {
        return this.f8327d;
    }

    public String getRegisterUri() {
        return this.f8324a;
    }

    public String[] getSendUris() {
        return this.f8326c;
    }

    public String getSettingUri() {
        return this.f8328e;
    }
}
