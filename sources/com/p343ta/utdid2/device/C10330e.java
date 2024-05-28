package com.p343ta.utdid2.device;

import com.p343ta.utdid2.p344a.p345a.C10304a;
import com.p343ta.utdid2.p344a.p345a.C10305b;
import com.p343ta.utdid2.p344a.p345a.C10315g;

/* renamed from: com.ta.utdid2.device.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10330e {
    /* renamed from: d */
    public String m6355d(String str) {
        return C10304a.m6450b(str);
    }

    /* renamed from: e */
    public String m6354e(String str) {
        String m6450b = C10304a.m6450b(str);
        if (C10315g.m6435a(m6450b)) {
            return null;
        }
        try {
            return new String(C10305b.decode(m6450b, 0));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
