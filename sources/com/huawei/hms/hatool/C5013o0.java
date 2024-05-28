package com.huawei.hms.hatool;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.keystore.aes.AesGcmKS;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;

/* renamed from: com.huawei.hms.hatool.o0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5013o0 {

    /* renamed from: c */
    private static C5013o0 f11472c;

    /* renamed from: a */
    private String f11473a;

    /* renamed from: b */
    private String f11474b;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x003a, code lost:
        if (m14563f() != false) goto L10;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m14570a(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.String r0 = ""
            boolean r1 = r2.m14563f()
            if (r1 == 0) goto Le
            java.lang.String r0 = "analytics_keystore"
            java.lang.String r0 = com.huawei.secure.android.common.encrypt.keystore.aes.AesGcmKS.decrypt(r0, r3)
        Le:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L4e
            java.lang.String r0 = "hmsSdk"
            java.lang.String r1 = "deCrypt work key first"
            com.huawei.hms.hatool.C5029v.m14455c(r0, r1)
            java.lang.String r0 = r2.m14564e()
            java.lang.String r0 = com.huawei.hms.hatool.C5008n.m14592a(r3, r0)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L40
            r3 = 16
            java.lang.String r0 = com.huawei.secure.android.common.encrypt.utils.EncryptUtil.generateSecureRandomStr(r3)
            java.lang.String r3 = r2.m14568b(r0)
            r2.m14566c(r3)
            boolean r3 = r2.m14563f()
            if (r3 == 0) goto L4e
        L3c:
            com.huawei.hms.hatool.C5034x.m14432c()
            goto L4e
        L40:
            boolean r3 = r2.m14563f()
            if (r3 == 0) goto L4e
            java.lang.String r3 = r2.m14568b(r0)
            r2.m14566c(r3)
            goto L3c
        L4e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hatool.C5013o0.m14570a(java.lang.String):java.lang.String");
    }

    /* renamed from: b */
    private String m14568b(String str) {
        return m14563f() ? AesGcmKS.encrypt("analytics_keystore", str) : C5008n.m14590b(str, m14564e());
    }

    /* renamed from: c */
    private String m14567c() {
        String m14767a = C4975d.m14767a(AbstractC5020q0.m14526i(), "Privacy_MY", "PrivacyData", "");
        if (TextUtils.isEmpty(m14767a)) {
            String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(16);
            m14566c(m14568b(generateSecureRandomStr));
            return generateSecureRandomStr;
        }
        return m14570a(m14767a);
    }

    /* renamed from: c */
    private boolean m14566c(String str) {
        C5029v.m14455c("hmsSdk", "refresh sp aes key");
        if (TextUtils.isEmpty(str)) {
            C5029v.m14455c("hmsSdk", "refreshLocalKey(): encrypted key is empty");
            return false;
        }
        C4975d.m14763b(AbstractC5020q0.m14526i(), "Privacy_MY", "PrivacyData", str);
        C4975d.m14764b(AbstractC5020q0.m14526i(), "Privacy_MY", "flashKeyTime", System.currentTimeMillis());
        return true;
    }

    /* renamed from: d */
    public static C5013o0 m14565d() {
        if (f11472c == null) {
            m14562g();
        }
        return f11472c;
    }

    /* renamed from: e */
    private String m14564e() {
        if (TextUtils.isEmpty(this.f11474b)) {
            this.f11474b = new C5034x().m14440a();
        }
        return this.f11474b;
    }

    /* renamed from: f */
    private boolean m14563f() {
        return Build.VERSION.SDK_INT >= 23;
    }

    /* renamed from: g */
    private static synchronized void m14562g() {
        synchronized (C5013o0.class) {
            if (f11472c == null) {
                f11472c = new C5013o0();
            }
        }
    }

    /* renamed from: a */
    public String m14571a() {
        if (TextUtils.isEmpty(this.f11473a)) {
            this.f11473a = m14567c();
        }
        return this.f11473a;
    }

    /* renamed from: b */
    public void m14569b() {
        String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(16);
        if (m14566c(m14568b(generateSecureRandomStr))) {
            this.f11473a = generateSecureRandomStr;
        }
    }
}
