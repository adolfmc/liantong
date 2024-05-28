package com.huawei.hms.hatool;

import android.content.Context;

/* renamed from: com.huawei.hms.hatool.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4967b {

    /* renamed from: a */
    C5024s0 f11349a;

    /* renamed from: b */
    C5024s0 f11350b;

    /* renamed from: c */
    Context f11351c;

    /* renamed from: d */
    String f11352d;

    public C4967b(Context context) {
        if (context != null) {
            this.f11351c = context.getApplicationContext();
        }
        this.f11349a = new C5024s0();
        this.f11350b = new C5024s0();
    }

    /* renamed from: a */
    public C4967b m14801a(int i, String str) {
        C5024s0 c5024s0;
        C5029v.m14455c("hmsSdk", "Builder.setCollectURL(int type,String collectURL) is execute.TYPE : " + i);
        if (!C5018p1.m14547b(str)) {
            str = "";
        }
        if (i == 0) {
            c5024s0 = this.f11349a;
        } else if (i != 1) {
            C5029v.m14451f("hmsSdk", "Builder.setCollectURL(int type,String collectURL): invalid type!");
            return this;
        } else {
            c5024s0 = this.f11350b;
        }
        c5024s0.m14501b(str);
        return this;
    }

    /* renamed from: a */
    public C4967b m14800a(String str) {
        C5029v.m14455c("hmsSdk", "Builder.setAppID is execute");
        this.f11352d = str;
        return this;
    }

    @Deprecated
    /* renamed from: a */
    public C4967b m14799a(boolean z) {
        C5029v.m14455c("hmsSdk", "Builder.setEnableImei(boolean isReportAndroidImei) is execute.");
        this.f11349a.m14484j().m14658a(z);
        this.f11350b.m14484j().m14658a(z);
        return this;
    }

    /* renamed from: a */
    public void m14802a() {
        if (this.f11351c == null) {
            C5029v.m14458b("hmsSdk", "analyticsConf create(): context is null,create failed!");
            return;
        }
        C5029v.m14455c("hmsSdk", "Builder.create() is execute.");
        C5039z0 c5039z0 = new C5039z0("_hms_config_tag");
        c5039z0.m14403b(new C5024s0(this.f11349a));
        c5039z0.m14406a(new C5024s0(this.f11350b));
        C5004m.m14611a().m14610a(this.f11351c);
        C4985g0.m14735a().m14734a(this.f11351c);
        C5019q.m14543c().m14545a(c5039z0);
        C5004m.m14611a().m14609a(this.f11352d);
    }

    @Deprecated
    /* renamed from: b */
    public C4967b m14798b(boolean z) {
        C5029v.m14455c("hmsSdk", "Builder.setEnableSN(boolean isReportSN) is execute.");
        this.f11349a.m14484j().m14656b(z);
        this.f11350b.m14484j().m14656b(z);
        return this;
    }

    @Deprecated
    /* renamed from: c */
    public C4967b m14797c(boolean z) {
        C5029v.m14455c("hmsSdk", "Builder.setEnableUDID(boolean isReportUDID) is execute.");
        this.f11349a.m14484j().m14654c(z);
        this.f11350b.m14484j().m14654c(z);
        return this;
    }
}
