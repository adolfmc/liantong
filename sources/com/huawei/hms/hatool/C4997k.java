package com.huawei.hms.hatool;

import android.util.Pair;
import com.huawei.secure.android.common.encrypt.hash.SHA;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.k */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4997k extends C5028u0 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.hatool.k$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static /* synthetic */ class C4998a {

        /* renamed from: a */
        static final /* synthetic */ int[] f11436a;

        static {
            int[] iArr = new int[EnumC4976d0.values().length];
            f11436a = iArr;
            try {
                iArr[EnumC4976d0.SN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11436a[EnumC4976d0.IMEI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11436a[EnumC4976d0.UDID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: a */
    protected static C4982f0 m14646a(String str, String str2, String str3, String str4) {
        C4982f0 m14466a = C5028u0.m14466a(str, str2, str3, str4);
        String m14670a = C4993j.m14673a().m14670a(AbstractC4966a1.m14815c(str2, str3));
        long currentTimeMillis = System.currentTimeMillis();
        String sha256Encrypt = SHA.sha256Encrypt(AbstractC5020q0.m14532f() + m14670a + currentTimeMillis);
        m14466a.m14638f(String.valueOf(currentTimeMillis));
        m14466a.m14738g(sha256Encrypt);
        return m14466a;
    }

    /* renamed from: a */
    public static C4989h1 m14645a(List<C4971b1> list, String str, String str2, String str3, String str4) {
        C5029v.m14455c("hmsSdk", "generate UploadData");
        C4989h1 m14465b = C5028u0.m14465b(str, str2);
        if (m14465b == null) {
            return null;
        }
        m14465b.m14685a(m14646a(C5006m1.m14597d().m14602a(), str, str2, str3));
        m14465b.m14684a(m14648a(str, str2));
        m14465b.m14683a(m14647a(str2, str, str4));
        m14465b.m14682a(AbstractC4966a1.m14811g(str, str2));
        m14465b.m14681a(list);
        return m14465b;
    }

    /* renamed from: a */
    protected static C5001l m14648a(String str, String str2) {
        C5001l m14468a = C5028u0.m14468a(str, str2);
        C4990i m14668c = C4993j.m14673a().m14668c(str, str2);
        m14468a.m14625g(C4993j.m14673a().m14670a(AbstractC4966a1.m14815c(str, str2)));
        m14468a.m14626f(AbstractC4966a1.m14803o(str, str2));
        m14468a.m14629c(C4993j.m14673a().m14665f(str, str2));
        int i = C4998a.f11436a[m14668c.m14680a().ordinal()];
        if (i == 1) {
            m14468a.m14628d(m14668c.m14679b());
        } else if (i == 2) {
            m14468a.m14630b(m14668c.m14679b());
        } else if (i == 3) {
            m14468a.m14627e(m14668c.m14679b());
        }
        return m14468a;
    }

    /* renamed from: a */
    protected static C5037y0 m14647a(String str, String str2, String str3) {
        C5037y0 m14467a = C5028u0.m14467a(str, str2, str3);
        Pair<String, String> m14666e = C4993j.m14673a().m14666e(str2, str);
        m14467a.m14423f((String) m14666e.first);
        m14467a.m14422g((String) m14666e.second);
        m14467a.m14421h(AbstractC5011o.m14575b());
        m14467a.m14472d(C4993j.m14673a().m14667d(str2, str));
        return m14467a;
    }

    /* renamed from: b */
    public static Map<String, String> m14644b(String str, String str2, String str3) {
        Map<String, String> m14464c = C5028u0.m14464c(str, str3);
        Map<String, String> m14809i = AbstractC4966a1.m14809i(str, str2);
        if (m14809i == null) {
            return m14464c;
        }
        m14464c.putAll(m14809i);
        return m14464c;
    }
}
