package com.huawei.hms.hatool;

import java.util.List;
import java.util.Map;

/* renamed from: com.huawei.hms.hatool.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4981f implements InterfaceRunnableC4984g {

    /* renamed from: a */
    private byte[] f11380a;

    /* renamed from: b */
    private String f11381b;

    /* renamed from: c */
    private String f11382c;

    /* renamed from: d */
    private String f11383d;

    /* renamed from: e */
    private String f11384e;

    /* renamed from: f */
    private List<C4971b1> f11385f;

    public C4981f(byte[] bArr, String str, String str2, String str3, String str4, List<C4971b1> list) {
        this.f11380a = (byte[]) bArr.clone();
        this.f11381b = str;
        this.f11382c = str2;
        this.f11384e = str3;
        this.f11383d = str4;
        this.f11385f = list;
    }

    /* renamed from: a */
    private C5009n0 m14740a(Map<String, String> map) {
        return AbstractC5031w.m14448a(this.f11381b, this.f11380a, map);
    }

    /* renamed from: a */
    private Map<String, String> m14741a() {
        return C4997k.m14644b(this.f11382c, this.f11384e, this.f11383d);
    }

    /* renamed from: b */
    private void m14739b() {
        C4968b0.m14793c().m14795a(new C4977d1(this.f11385f, this.f11382c, this.f11383d, this.f11384e));
    }

    @Override // java.lang.Runnable
    public void run() {
        C5029v.m14455c("hmsSdk", "send data running");
        int m14588b = m14740a(m14741a()).m14588b();
        if (m14588b != 200) {
            m14739b();
            return;
        }
        C5029v.m14457b("hmsSdk", "events PostRequest sendevent TYPE : %s, TAG : %s, resultCode: %d ,reqID:" + this.f11383d, this.f11384e, this.f11382c, Integer.valueOf(m14588b));
    }
}
