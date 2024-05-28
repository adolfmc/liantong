package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.secure.android.common.encrypt.hash.SHA;
import java.util.UUID;

/* renamed from: com.huawei.hms.hatool.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4993j {

    /* renamed from: b */
    private static C4993j f11422b;

    /* renamed from: a */
    private Context f11423a;

    /* renamed from: com.huawei.hms.hatool.j$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C4994a extends AbstractC4979e0 {

        /* renamed from: a */
        String f11424a;

        /* renamed from: b */
        String f11425b;

        public C4994a(String str, String str2) {
            this.f11424a = str;
            this.f11425b = str2;
        }

        @Override // com.huawei.hms.hatool.AbstractC4979e0
        /* renamed from: a */
        public String mo14664a() {
            return AbstractC5038z.m14417d(this.f11424a, this.f11425b);
        }

        @Override // com.huawei.hms.hatool.AbstractC4979e0
        /* renamed from: a */
        public String mo14663a(String str) {
            return SHA.sha256Encrypt(str);
        }

        @Override // com.huawei.hms.hatool.AbstractC4979e0
        /* renamed from: b */
        public String mo14662b() {
            return AbstractC5038z.m14414g(this.f11424a, this.f11425b);
        }

        @Override // com.huawei.hms.hatool.AbstractC4979e0
        /* renamed from: c */
        public String mo14661c() {
            return AbstractC5038z.m14411j(this.f11424a, this.f11425b);
        }

        @Override // com.huawei.hms.hatool.AbstractC4979e0
        /* renamed from: d */
        public int mo14660d() {
            return (AbstractC5038z.m14410k(this.f11424a, this.f11425b) ? 4 : 0) | 0 | (AbstractC5038z.m14416e(this.f11424a, this.f11425b) ? 2 : 0) | (AbstractC5038z.m14413h(this.f11424a, this.f11425b) ? 1 : 0);
        }
    }

    /* renamed from: a */
    public static C4993j m14673a() {
        C4993j c4993j;
        synchronized (C4993j.class) {
            if (f11422b == null) {
                f11422b = new C4993j();
            }
            c4993j = f11422b;
        }
        return c4993j;
    }

    /* renamed from: a */
    public String m14671a(String str, String str2) {
        return AbstractC4991i0.m14678a(this.f11423a, str, str2);
    }

    /* renamed from: a */
    public String m14670a(boolean z) {
        if (z) {
            String m14534e = AbstractC5020q0.m14534e();
            if (TextUtils.isEmpty(m14534e)) {
                m14534e = C4975d.m14767a(this.f11423a, "global_v2", "uuid", "");
                if (TextUtils.isEmpty(m14534e)) {
                    m14534e = UUID.randomUUID().toString().replace("-", "");
                    C4975d.m14763b(this.f11423a, "global_v2", "uuid", m14534e);
                }
                AbstractC5020q0.m14527h(m14534e);
            }
            return m14534e;
        }
        return "";
    }

    /* renamed from: a */
    public void m14672a(Context context) {
        if (this.f11423a == null) {
            this.f11423a = context;
        }
    }

    /* renamed from: b */
    public String m14669b(String str, String str2) {
        return AbstractC4991i0.m14677b(this.f11423a, str, str2);
    }

    /* renamed from: c */
    public C4990i m14668c(String str, String str2) {
        return new C4994a(str, str2).m14750a(this.f11423a);
    }

    /* renamed from: d */
    public String m14667d(String str, String str2) {
        return AbstractC4983f1.m14736b(str, str2);
    }

    /* renamed from: e */
    public Pair<String, String> m14666e(String str, String str2) {
        if (AbstractC5038z.m14415f(str, str2)) {
            String m14696p = C5023s.m14511c().m14512b().m14696p();
            String m14694q = C5023s.m14511c().m14512b().m14694q();
            if (TextUtils.isEmpty(m14696p) || TextUtils.isEmpty(m14694q)) {
                Pair<String, String> m14429e = C5035x0.m14429e(this.f11423a);
                C5023s.m14511c().m14512b().m14705k((String) m14429e.first);
                C5023s.m14511c().m14512b().m14703l((String) m14429e.second);
                return m14429e;
            }
            return new Pair<>(m14696p, m14694q);
        }
        return new Pair<>("", "");
    }

    /* renamed from: f */
    public String m14665f(String str, String str2) {
        return AbstractC4983f1.m14737a(str, str2);
    }
}
