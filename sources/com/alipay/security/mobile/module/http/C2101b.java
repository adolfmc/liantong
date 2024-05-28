package com.alipay.security.mobile.module.http;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.AbstractC1955w;
import com.alipay.android.phone.mrpc.core.C1927aa;
import com.alipay.android.phone.mrpc.core.C1939h;
import com.alipay.security.mobile.module.p110a.C2081a;
import com.alipay.tscenter.biz.rpc.deviceFp.BugTrackMessageService;
import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.http.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2101b implements InterfaceC2100a {

    /* renamed from: d */
    private static C2101b f4005d;

    /* renamed from: e */
    private static DataReportResult f4006e;

    /* renamed from: a */
    private AbstractC1955w f4007a;

    /* renamed from: b */
    private BugTrackMessageService f4008b;

    /* renamed from: c */
    private DataReportService f4009c;

    private C2101b(Context context, String str) {
        this.f4007a = null;
        this.f4008b = null;
        this.f4009c = null;
        C1927aa c1927aa = new C1927aa();
        c1927aa.m21134a(str);
        this.f4007a = new C1939h(context);
        this.f4008b = (BugTrackMessageService) this.f4007a.mo21063a(BugTrackMessageService.class, c1927aa);
        this.f4009c = (DataReportService) this.f4007a.mo21063a(DataReportService.class, c1927aa);
    }

    /* renamed from: a */
    public static synchronized C2101b m20475a(Context context, String str) {
        C2101b c2101b;
        synchronized (C2101b.class) {
            if (f4005d == null) {
                f4005d = new C2101b(context, str);
            }
            c2101b = f4005d;
        }
        return c2101b;
    }

    @Override // com.alipay.security.mobile.module.http.InterfaceC2100a
    /* renamed from: a */
    public DataReportResult mo20473a(DataReportRequest dataReportRequest) {
        if (dataReportRequest == null) {
            return null;
        }
        if (this.f4009c != null) {
            f4006e = null;
            new Thread(new RunnableC2102c(this, dataReportRequest)).start();
            for (int i = 300000; f4006e == null && i >= 0; i -= 50) {
                Thread.sleep(50L);
            }
        }
        return f4006e;
    }

    @Override // com.alipay.security.mobile.module.http.InterfaceC2100a
    /* renamed from: a */
    public boolean mo20471a(String str) {
        BugTrackMessageService bugTrackMessageService;
        if (C2081a.m20577a(str) || (bugTrackMessageService = this.f4008b) == null) {
            return false;
        }
        String str2 = null;
        try {
            str2 = bugTrackMessageService.logCollect(C2081a.m20568f(str));
        } catch (Throwable unused) {
        }
        if (C2081a.m20577a(str2)) {
            return false;
        }
        return ((Boolean) new JSONObject(str2).get("success")).booleanValue();
    }
}
