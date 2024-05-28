package com.mob.mgs.impl;

import android.text.TextUtils;
import com.mob.commons.C5831e;
import com.mob.tools.network.NetCommunicator;
import java.util.HashMap;

/* renamed from: com.mob.mgs.impl.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5995f {

    /* renamed from: a */
    private static volatile C5995f f14763a;

    /* renamed from: c */
    private volatile String f14765c;

    /* renamed from: e */
    private volatile boolean f14767e;

    /* renamed from: f */
    private volatile String f14768f;

    /* renamed from: b */
    private volatile boolean f14764b = false;

    /* renamed from: d */
    private byte[] f14766d = new byte[0];

    private C5995f() {
    }

    /* renamed from: a */
    public static C5995f m11854a() {
        if (f14763a == null) {
            synchronized (C5995f.class) {
                if (f14763a == null) {
                    f14763a = new C5995f();
                }
            }
        }
        return f14763a;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.mob.mgs.impl.f$1] */
    /* renamed from: b */
    public void m11852b() {
        if (!this.f14764b) {
            new AbstractC6003h() { // from class: com.mob.mgs.impl.f.1
                @Override // com.mob.tools.utils.AbstractC6218i
                /* renamed from: a */
                public void mo10997a() throws Throwable {
                    C5994e.m11860a().m11859a("MgsGlobal init: start");
                    C5995f.this.m11848f();
                    C5995f.this.f14764b = true;
                    C5994e.m11860a().m11859a("MgsGlobal init: done");
                }
            }.start();
        } else {
            C5994e.m11860a().m11859a("MgsGlobal already initialized");
        }
    }

    /* renamed from: c */
    public String m11851c() {
        if (TextUtils.isEmpty(this.f14765c)) {
            C5994e.m11860a().m11856b("WARNING: getDuidQuick got null!");
        }
        return this.f14765c;
    }

    /* renamed from: d */
    public boolean m11850d() {
        return this.f14767e;
    }

    /* renamed from: e */
    public String m11849e() {
        return this.f14768f;
    }

    /* renamed from: f */
    public String m11848f() {
        HashMap<String, Object> m12316b;
        if (TextUtils.isEmpty(this.f14765c)) {
            synchronized (this.f14766d) {
                if (TextUtils.isEmpty(this.f14765c) && (m12316b = C5831e.m12316b(null)) != null) {
                    this.f14765c = (String) m12316b.get(NetCommunicator.KEY_DUID);
                    this.f14767e = ((Boolean) m12316b.get(NetCommunicator.KEY_IS_MODIFIED)).booleanValue();
                    this.f14768f = (String) m12316b.get(NetCommunicator.KEY_DUID_PREVIOUS);
                    C5994e m11860a = C5994e.m11860a();
                    m11860a.m11859a("MC Global -> duid: " + this.f14765c + ", duidPre: " + this.f14768f + ", isModified: " + this.f14767e);
                }
            }
        }
        return this.f14765c;
    }
}
