package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.clientreport.data.C11052a;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.manager.ClientReportClient;

/* renamed from: com.xiaomi.push.dt */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11305dt {

    /* renamed from: a */
    private static volatile C11305dt f22012a;

    /* renamed from: a */
    private Context f22013a;

    private C11305dt(Context context) {
        this.f22013a = context;
    }

    /* renamed from: a */
    public static C11305dt m4117a(Context context) {
        if (f22012a == null) {
            synchronized (C11305dt.class) {
                if (f22012a == null) {
                    f22012a = new C11305dt(context);
                }
            }
        }
        return f22012a;
    }

    /* renamed from: a */
    public void m4112a(String str, String str2, String str3, int i, long j, String str4) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        EventClientReport m4123a = C11303ds.m4123a(this.f22013a, str2, str3, i, j, str4);
        m4123a.setAppPackageName(str);
        m4123a.setSdkVersion("5_9_9-C");
        m4116a(m4123a);
    }

    /* renamed from: a */
    public void m4111a(String str, String str2, String str3, int i, String str4) {
        m4112a(str, str2, str3, i, System.currentTimeMillis(), str4);
    }

    /* renamed from: a */
    public void m4114a(String str, Intent intent, int i, String str2) {
        if (intent == null) {
            return;
        }
        m4112a(str, C11303ds.m4131a(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), i, System.currentTimeMillis(), str2);
    }

    /* renamed from: a */
    public void m4110a(String str, String str2, String str3, String str4) {
        m4112a(str, str2, str3, 5002, System.currentTimeMillis(), str4);
    }

    /* renamed from: b */
    public void m4109b(String str, String str2, String str3, String str4) {
        m4112a(str, str2, str3, 5001, System.currentTimeMillis(), str4);
    }

    /* renamed from: a */
    public void m4113a(String str, Intent intent, String str2) {
        if (intent == null) {
            return;
        }
        m4112a(str, C11303ds.m4131a(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), 5001, System.currentTimeMillis(), str2);
    }

    /* renamed from: c */
    public void m4108c(String str, String str2, String str3, String str4) {
        m4112a(str, str2, str3, 4002, System.currentTimeMillis(), str4);
    }

    /* renamed from: a */
    public void m4115a(String str, int i, long j, long j2) {
        if (i < 0 || j2 < 0 || j <= 0) {
            return;
        }
        PerfClientReport m4127a = C11303ds.m4127a(this.f22013a, i, j, j2);
        m4127a.setAppPackageName(str);
        m4127a.setSdkVersion("5_9_9-C");
        m4116a(m4127a);
    }

    /* renamed from: a */
    private void m4116a(C11052a c11052a) {
        if (c11052a instanceof PerfClientReport) {
            ClientReportClient.reportPerf(this.f22013a, (PerfClientReport) c11052a);
        } else if (c11052a instanceof EventClientReport) {
            ClientReportClient.reportEvent(this.f22013a, (EventClientReport) c11052a);
        }
    }
}
