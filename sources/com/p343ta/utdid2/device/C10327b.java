package com.p343ta.utdid2.device;

import android.content.Context;
import com.p343ta.utdid2.p344a.p345a.C10311e;
import com.p343ta.utdid2.p344a.p345a.C10315g;
import java.util.zip.Adler32;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ta.utdid2.device.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10327b {

    /* renamed from: a */
    private static C10326a f19772a;

    /* renamed from: d */
    static final Object f19773d = new Object();

    /* renamed from: a */
    static long m6374a(C10326a c10326a) {
        if (c10326a != null) {
            String format = String.format("%s%s%s%s%s", c10326a.m6376f(), c10326a.getDeviceId(), Long.valueOf(c10326a.m6384a()), c10326a.getImsi(), c10326a.m6378e());
            if (C10315g.m6435a(format)) {
                return 0L;
            }
            Adler32 adler32 = new Adler32();
            adler32.reset();
            adler32.update(format.getBytes());
            return adler32.getValue();
        }
        return 0L;
    }

    /* renamed from: a */
    private static C10326a m6375a(Context context) {
        if (context != null) {
            synchronized (f19773d) {
                String value = C10328c.m6372a(context).getValue();
                if (C10315g.m6435a(value)) {
                    return null;
                }
                if (value.endsWith("\n")) {
                    value = value.substring(0, value.length() - 1);
                }
                C10326a c10326a = new C10326a();
                long currentTimeMillis = System.currentTimeMillis();
                String m6444a = C10311e.m6444a(context);
                String m6440c = C10311e.m6440c(context);
                c10326a.m6379d(m6444a);
                c10326a.m6381b(m6444a);
                c10326a.m6382b(currentTimeMillis);
                c10326a.m6380c(m6440c);
                c10326a.m6377e(value);
                c10326a.m6383a(m6374a(c10326a));
                return c10326a;
            }
        }
        return null;
    }

    /* renamed from: b */
    public static synchronized C10326a m6373b(Context context) {
        synchronized (C10327b.class) {
            if (f19772a != null) {
                return f19772a;
            } else if (context != null) {
                C10326a m6375a = m6375a(context);
                f19772a = m6375a;
                return m6375a;
            } else {
                return null;
            }
        }
    }
}
