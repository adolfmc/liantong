package com.networkbench.agent.impl.p266l;

import android.content.Context;
import com.networkbench.agent.impl.crash.C6332j;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6636f;
import java.io.File;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.l.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6491a {

    /* renamed from: c */
    private C6332j f16427c;

    /* renamed from: d */
    private String f16428d;

    /* renamed from: e */
    private Context f16429e;

    /* renamed from: b */
    private static final InterfaceC6393e f16426b = C6394f.m10150a();

    /* renamed from: a */
    public static int f16425a = 5242880;

    public C6491a(Context context, String str) {
        this.f16427c = new C6332j(context, str);
        this.f16429e = context;
        this.f16428d = str;
    }

    /* renamed from: a */
    public void m9785a(String str, long j) {
        long m9782c = m9782c();
        InterfaceC6393e interfaceC6393e = f16426b;
        interfaceC6393e.mo10122a("current sp size " + m9782c);
        if (m9782c == -1) {
            C6332j c6332j = this.f16427c;
            c6332j.m10372b(j + "", str);
        } else if (str.length() >= f16425a) {
            InterfaceC6393e interfaceC6393e2 = f16426b;
            interfaceC6393e2.mo10122a("single store Info over max_sp_size, storeInfo length is " + str.length() + ", and max_sp_size is " + f16425a);
        } else {
            long j2 = 0;
            if (m9782c() + C6636f.m9093b(str).length() > f16425a) {
                while (j2 < str.length()) {
                    String mo10367e = this.f16427c.mo10367e();
                    if (mo10367e == null) {
                        return;
                    }
                    j2 += this.f16427c.m10366e(mo10367e).length();
                    this.f16427c.mo10376a(C6636f.m9092c(mo10367e));
                }
            }
            C6332j c6332j2 = this.f16427c;
            c6332j2.m10372b(j + "", str);
        }
    }

    /* renamed from: a */
    public Map<String, ?> m9788a() {
        return this.f16427c.mo10374b();
    }

    /* renamed from: a */
    public String m9787a(long j) {
        C6332j c6332j = this.f16427c;
        return c6332j.m10370c(j + "");
    }

    /* renamed from: a */
    public void m9786a(String str) {
        this.f16427c.mo10376a(str);
    }

    /* renamed from: b */
    public void m9783b(long j) {
        this.f16427c.mo10376a(String.valueOf(j));
    }

    /* renamed from: b */
    public void m9784b() {
        this.f16427c.mo10369d();
    }

    /* renamed from: c */
    private long m9782c() {
        InterfaceC6393e interfaceC6393e = f16426b;
        interfaceC6393e.mo10122a("sp file path:" + m9781d());
        File file = new File(m9781d());
        if (file.exists()) {
            return file.length();
        }
        return -1L;
    }

    /* renamed from: d */
    private String m9781d() {
        return "/data/data/" + this.f16429e.getPackageName() + "/shared_prefs/" + this.f16428d + ".xml";
    }
}
