package com.bytedance.applog.util;

import com.bytedance.applog.UriConfig;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UriConstants {
    public static final int DEFAULT = 0;
    public static final int REGION_DEFAULT = 0;

    /* renamed from: a */
    public static final UriConfig f8856a;

    static {
        UriConfig.C3534b c3534b = new UriConfig.C3534b();
        c3534b.f8332a = "https://log.snssdk.com/service/2/device_register/";
        c3534b.f8333b = "https://ichannel.snssdk.com/service/2/app_alert_check/";
        c3534b.f8334c = new String[]{"https://log.snssdk.com/service/2/app_log/", "https://applog.snssdk.com/service/2/app_log/"};
        c3534b.f8335d = new String[]{"https://rtlog.snssdk.com/service/2/app_log/", "https://rtapplog.snssdk.com/service/2/app_log/"};
        c3534b.f8336e = "https://log.snssdk.com/service/2/log_settings/";
        c3534b.m17351a();
        UriConfig.C3534b c3534b2 = new UriConfig.C3534b();
        c3534b2.f8332a = "https://toblog.ctobsnssdk.com/service/2/device_register/";
        c3534b2.f8333b = "https://toblog.ctobsnssdk.com/service/2/app_alert_check/";
        c3534b2.f8334c = new String[]{"https://toblog.ctobsnssdk.com/service/2/app_log/", "https://tobapplog.ctobsnssdk.com/service/2/app_log/"};
        c3534b2.f8336e = "https://toblog.ctobsnssdk.com/service/2/log_settings/";
        c3534b2.f8337f = "https://toblog.ctobsnssdk.com/service/2/abtest_config/";
        c3534b2.f8339h = "https://success.ctobsnssdk.com/service/2/app_log/";
        f8856a = c3534b2.m17351a();
    }

    public static final UriConfig createUriConfig(int i) {
        return f8856a;
    }
}
