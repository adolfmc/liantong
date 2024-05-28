package com.baidu.lbsapi.auth;

import android.content.Context;
import java.util.Hashtable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.lbsapi.auth.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2595j implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f4983a;

    /* renamed from: b */
    final /* synthetic */ boolean f4984b;

    /* renamed from: c */
    final /* synthetic */ String f4985c;

    /* renamed from: d */
    final /* synthetic */ String f4986d;

    /* renamed from: e */
    final /* synthetic */ Hashtable f4987e;

    /* renamed from: f */
    final /* synthetic */ LBSAuthManager f4988f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2595j(LBSAuthManager lBSAuthManager, int i, boolean z, String str, String str2, Hashtable hashtable) {
        this.f4988f = lBSAuthManager;
        this.f4983a = i;
        this.f4984b = z;
        this.f4985c = str;
        this.f4986d = str2;
        this.f4987e = hashtable;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean m19684b;
        String[] strArr;
        Context context;
        String[] m19668b;
        boolean m19684b2;
        C2598m c2598m;
        C2598m c2598m2;
        StringBuilder sb = new StringBuilder();
        sb.append("status = ");
        sb.append(this.f4983a);
        sb.append("; forced = ");
        sb.append(this.f4984b);
        sb.append("checkAK = ");
        m19684b = this.f4988f.m19684b(this.f4985c);
        sb.append(m19684b);
        C2583a.m19676a(sb.toString());
        int i = this.f4983a;
        if (i != 601 && !this.f4984b && i != -1) {
            m19684b2 = this.f4988f.m19684b(this.f4985c);
            if (!m19684b2) {
                if (602 == this.f4983a) {
                    C2583a.m19676a("authenticate wait ");
                    c2598m = LBSAuthManager.f4954d;
                    if (c2598m != null) {
                        c2598m2 = LBSAuthManager.f4954d;
                        c2598m2.m19641b();
                    }
                } else {
                    C2583a.m19676a("authenticate else");
                }
                this.f4988f.m19688a((String) null, this.f4985c);
                return;
            }
        }
        C2583a.m19676a("authenticate sendAuthRequest");
        strArr = LBSAuthManager.f4961l;
        if (strArr != null) {
            m19668b = LBSAuthManager.f4961l;
        } else {
            context = LBSAuthManager.f4953a;
            m19668b = C2584b.m19668b(context);
        }
        String[] strArr2 = m19668b;
        if (strArr2 == null || strArr2.length <= 1) {
            this.f4988f.m19687a(this.f4984b, this.f4986d, this.f4987e, this.f4985c);
            return;
        }
        C2583a.m19676a("authStrings.length:" + strArr2.length);
        C2583a.m19676a("more sha1 auth");
        this.f4988f.m19686a(this.f4984b, this.f4986d, this.f4987e, strArr2, this.f4985c);
    }
}
