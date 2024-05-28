package com.huawei.hms.hatool;

import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;

/* renamed from: com.huawei.hms.hatool.m1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5006m1 {

    /* renamed from: b */
    private static C5006m1 f11463b = new C5006m1();

    /* renamed from: a */
    private C5007a f11464a = new C5007a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.hatool.m1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5007a {

        /* renamed from: a */
        String f11465a;

        /* renamed from: b */
        String f11466b;

        /* renamed from: c */
        long f11467c = 0;

        C5007a() {
        }

        /* renamed from: a */
        void m14596a(long j) {
            C5006m1.this.f11464a.f11467c = j;
        }

        /* renamed from: a */
        void m14595a(String str) {
            C5006m1.this.f11464a.f11466b = str;
        }

        /* renamed from: b */
        void m14594b(String str) {
            C5006m1.this.f11464a.f11465a = str;
        }
    }

    /* renamed from: d */
    public static C5006m1 m14597d() {
        return f11463b;
    }

    /* renamed from: a */
    public String m14602a() {
        return this.f11464a.f11466b;
    }

    /* renamed from: a */
    public void m14600a(String str, String str2) {
        long m14599b = m14599b();
        String m14442b = C5033w0.m14442b(str, str2);
        if (m14442b == null || m14442b.isEmpty()) {
            C5029v.m14452e("WorkKeyHandler", "get rsa pubkey config error");
            return;
        }
        if (m14599b == 0) {
            m14599b = System.currentTimeMillis();
        } else if (System.currentTimeMillis() - m14599b <= 43200000) {
            return;
        }
        String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(16);
        String m14689a = C4988h0.m14689a(m14442b, generateSecureRandomStr);
        this.f11464a.m14596a(m14599b);
        this.f11464a.m14594b(generateSecureRandomStr);
        this.f11464a.m14595a(m14689a);
    }

    /* renamed from: b */
    public long m14599b() {
        return this.f11464a.f11467c;
    }

    /* renamed from: c */
    public String m14598c() {
        return this.f11464a.f11465a;
    }
}
