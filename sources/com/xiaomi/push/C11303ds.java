package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.manager.ClientReportClient;
import com.xiaomi.push.service.C11537ah;
import com.xiaomi.push.service.C11577az;
import com.xiaomi.push.service.C11579ba;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ds */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11303ds {

    /* renamed from: a */
    private static InterfaceC11304a f22010a;

    /* renamed from: a */
    private static Map<String, EnumC11414gp> f22011a;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.ds$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11304a {
        void uploader(Context context, C11408gj c11408gj);
    }

    /* renamed from: a */
    public static int m4132a(int i) {
        if (i > 0) {
            return i + 1000;
        }
        return -1;
    }

    /* renamed from: a */
    public static String m4131a(int i) {
        return i == 1000 ? "E100000" : i == 3000 ? "E100002" : i == 2000 ? "E100001" : i == 6000 ? "E100003" : "";
    }

    /* renamed from: a */
    public static void m4121a(InterfaceC11304a interfaceC11304a) {
        f22010a = interfaceC11304a;
    }

    /* renamed from: a */
    private static void m4125a(Context context, C11408gj c11408gj) {
        if (m4128a(context.getApplicationContext())) {
            C11579ba.m2584a(context.getApplicationContext(), c11408gj);
            return;
        }
        InterfaceC11304a interfaceC11304a = f22010a;
        if (interfaceC11304a != null) {
            interfaceC11304a.uploader(context, c11408gj);
        }
    }

    /* renamed from: a */
    public static EventClientReport m4119a(String str) {
        EventClientReport eventClientReport = new EventClientReport();
        eventClientReport.production = 1000;
        eventClientReport.reportType = 1001;
        eventClientReport.clientInterfaceId = str;
        return eventClientReport;
    }

    /* renamed from: a */
    public static PerfClientReport m4133a() {
        PerfClientReport perfClientReport = new PerfClientReport();
        perfClientReport.production = 1000;
        perfClientReport.reportType = 1000;
        perfClientReport.clientInterfaceId = "P100000";
        return perfClientReport;
    }

    /* renamed from: a */
    public static EventClientReport m4123a(Context context, String str, String str2, int i, long j, String str3) {
        EventClientReport m4119a = m4119a(str);
        m4119a.eventId = str2;
        m4119a.eventType = i;
        m4119a.eventTime = j;
        m4119a.eventContent = str3;
        return m4119a;
    }

    /* renamed from: a */
    public static PerfClientReport m4127a(Context context, int i, long j, long j2) {
        PerfClientReport m4133a = m4133a();
        m4133a.code = i;
        m4133a.perfCounts = j;
        m4133a.perfLatencies = j2;
        return m4133a;
    }

    /* renamed from: a */
    public static boolean m4128a(Context context) {
        return (context == null || TextUtils.isEmpty(context.getPackageName()) || !"com.xiaomi.xmsf".equals(context.getPackageName())) ? false : true;
    }

    /* renamed from: a */
    public static void m4122a(Context context, List<String> list) {
        if (list == null) {
            return;
        }
        try {
            for (String str : list) {
                C11408gj m4124a = m4124a(context, str);
                if (!C11577az.m2596a(m4124a, false)) {
                    m4125a(context, m4124a);
                }
            }
        } catch (Throwable th) {
            AbstractC11049b.m5268d(th.getMessage());
        }
    }

    /* renamed from: a */
    public static C11408gj m4124a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        C11408gj c11408gj = new C11408gj();
        c11408gj.m3649d("category_client_report_data");
        c11408gj.m3665a("push_sdk_channel");
        c11408gj.m3668a(1L);
        c11408gj.m3657b(str);
        c11408gj.m3662a(true);
        c11408gj.m3658b(System.currentTimeMillis());
        c11408gj.m3642g(context.getPackageName());
        c11408gj.m3646e("com.xiaomi.xmsf");
        c11408gj.m3644f(C11577az.m2598a());
        c11408gj.m3653c("quality_support");
        return c11408gj;
    }

    /* renamed from: a */
    public static void m4126a(Context context, Config config) {
        ClientReportClient.init(context, config, new C11301dq(context), new C11302dr(context));
    }

    /* renamed from: a */
    public static void m4129a(Context context) {
        ClientReportClient.updateConfig(context, m4130a(context));
    }

    /* renamed from: a */
    public static Config m4130a(Context context) {
        boolean m2716a = C11537ah.m2715a(context).m2716a(EnumC11409gk.PerfUploadSwitch.m3637a(), false);
        boolean m2716a2 = C11537ah.m2715a(context).m2716a(EnumC11409gk.EventUploadNewSwitch.m3637a(), false);
        int m2719a = C11537ah.m2715a(context).m2719a(EnumC11409gk.PerfUploadFrequency.m3637a(), 86400);
        return Config.getBuilder().setEventUploadSwitchOpen(m2716a2).setEventUploadFrequency(C11537ah.m2715a(context).m2719a(EnumC11409gk.EventUploadFrequency.m3637a(), 86400)).setPerfUploadSwitchOpen(m2716a).setPerfUploadFrequency(m2719a).build(context);
    }

    /* renamed from: a */
    public static int m4120a(Enum r1) {
        if (r1 != null) {
            if (r1 instanceof EnumC11404gf) {
                return r1.ordinal() + 1001;
            }
            if (r1 instanceof EnumC11414gp) {
                return r1.ordinal() + 2001;
            }
            if (r1 instanceof EnumC11317ed) {
                return r1.ordinal() + 3001;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public static EnumC11414gp m4118a(String str) {
        EnumC11414gp[] values;
        if (f22011a == null) {
            synchronized (EnumC11414gp.class) {
                if (f22011a == null) {
                    f22011a = new HashMap();
                    for (EnumC11414gp enumC11414gp : EnumC11414gp.values()) {
                        f22011a.put(enumC11414gp.f22745a.toLowerCase(), enumC11414gp);
                    }
                }
            }
        }
        EnumC11414gp enumC11414gp2 = f22011a.get(str.toLowerCase());
        return enumC11414gp2 != null ? enumC11414gp2 : EnumC11414gp.Invalid;
    }
}
