package com.networkbench.agent.impl.p266l;

import android.content.Context;
import com.networkbench.agent.impl.crash.C6332j;
import com.networkbench.agent.impl.harvest.ConfigurationName;
import com.networkbench.agent.impl.harvest.p261b.C6458a;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.util.C6636f;
import com.networkbench.agent.impl.util.C6653u;
import java.io.File;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.l.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6493c {

    /* renamed from: a */
    public static int f16433a = 10485760;

    /* renamed from: b */
    private C6332j f16434b;

    /* renamed from: c */
    private Context f16435c;

    public C6493c(Context context, String str) {
        this.f16434b = new C6332j(context, str);
        this.f16435c = context;
        if (C6653u.m8729b(context)) {
            this.f16434b.m10373b(str);
            return;
        }
        C6332j c6332j = this.f16434b;
        c6332j.m10373b(str + "_" + ConfigurationName.processName);
    }

    /* renamed from: a */
    public void m9773a(String str, long j) {
        C6458a.m9944a(C6458a.f16324c, 0);
        if (m9771c() == -1) {
            C6332j c6332j = this.f16434b;
            c6332j.m10372b(j + "", str);
        } else if (str.length() >= f16433a) {
            C6396h.m10125q("single store Info over max_sp_size, storeInfo length is " + str.length() + ", and max_sp_size is " + f16433a);
        } else {
            while (m9771c() + str.length() > f16433a) {
                C6332j c6332j2 = this.f16434b;
                c6332j2.m10368d(C6636f.m9092c(c6332j2.mo10367e()));
            }
            C6332j c6332j3 = this.f16434b;
            c6332j3.m10372b(j + "", str);
            C6458a.m9944a(C6458a.f16324c, 1);
        }
    }

    /* renamed from: a */
    public Map<String, ?> m9776a() {
        return this.f16434b.mo10374b();
    }

    /* renamed from: a */
    public String m9775a(long j) {
        C6332j c6332j = this.f16434b;
        return c6332j.m10370c(j + "");
    }

    /* renamed from: a */
    public void m9774a(String str) {
        this.f16434b.mo10376a(str);
    }

    /* renamed from: b */
    public void m9772b() {
        this.f16434b.mo10369d();
    }

    /* renamed from: c */
    private long m9771c() {
        C6396h.m10125q("sp file path:" + m9770d());
        File file = new File(m9770d());
        if (file.exists()) {
            return file.length();
        }
        return -1L;
    }

    /* renamed from: d */
    private String m9770d() {
        return "/data/data/" + this.f16435c.getPackageName() + "/shared_prefs/" + this.f16434b.m10365f() + ".xml";
    }
}
