package com.mob.mgs.impl;

import com.mob.MobSDK;
import com.mob.commons.CSCenter;
import com.mob.mcl.p234b.C5906a;
import com.mob.tools.utils.C6152DH;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.mgs.impl.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5965b {
    /* JADX WARN: Type inference failed for: r0v2, types: [com.mob.mgs.impl.b$1] */
    /* renamed from: a */
    public static void m11927a() {
        if (CSCenter.getInstance().isAppListDataEnable()) {
            new AbstractC6003h() { // from class: com.mob.mgs.impl.b.1
                @Override // com.mob.tools.utils.AbstractC6218i
                /* renamed from: a */
                public void mo10997a() throws Throwable {
                    boolean isInMainProcess = C6152DH.SyncMtd.isInMainProcess();
                    C5994e m11860a = C5994e.m11860a();
                    m11860a.m11859a("mgs init, main p: " + isInMainProcess);
                    if (isInMainProcess && !MobSDK.isForb()) {
                        C5995f.m11854a().m11852b();
                        C5969c.m11922a().m11900b();
                    }
                }
            }.start();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.mob.mgs.impl.b$2] */
    /* renamed from: a */
    public static void m11925a(final boolean z, final boolean z2) {
        new AbstractC6003h() { // from class: com.mob.mgs.impl.b.2
            @Override // com.mob.tools.utils.AbstractC6218i
            /* renamed from: a */
            public void mo10997a() throws Throwable {
                C5992d.m11862a(z, z2);
                C6004i.m11835b(z);
                String m11848f = C5995f.m11854a().m11848f();
                String m12094a = C5906a.m12094a();
                C5994e m11860a = C5994e.m11860a();
                m11860a.m11859a("[setDS] save buff DId: " + m11848f + ", GId: " + m12094a);
                C6004i.m11839a(m11848f);
                C6004i.m11836b(m12094a);
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.mob.mgs.impl.b$3] */
    /* renamed from: a */
    public static void m11926a(final boolean z) {
        new AbstractC6003h() { // from class: com.mob.mgs.impl.b.3
            @Override // com.mob.tools.utils.AbstractC6218i
            /* renamed from: a */
            public void mo10997a() throws Throwable {
                Boolean m11837b = C6004i.m11837b();
                if (m11837b == null) {
                    C5965b.m11925a(z, false);
                    return;
                }
                String m11848f = C5995f.m11854a().m11848f();
                String m11834c = C6004i.m11834c();
                String m12094a = C5906a.m12094a();
                String m11833d = C6004i.m11833d();
                C5994e m11860a = C5994e.m11860a();
                m11860a.m11859a("[setDS] currDId: " + m11848f + ", buffDId: " + m11834c);
                C5994e m11860a2 = C5994e.m11860a();
                m11860a2.m11859a("[setDS] currGId: " + m12094a + ", buffGId: " + m11833d);
                if (!m11834c.equals(m11848f) || !m11833d.equals(m12094a)) {
                    C5965b.m11925a(z, true);
                }
                if (z != m11837b.booleanValue()) {
                    C5965b.m11925a(z, false);
                }
            }
        }.start();
        C6004i.m11838a(z);
    }

    /* renamed from: b */
    public static boolean m11924b() {
        Boolean m11923c = m11923c();
        if (m11923c == null) {
            m11923c = true;
        }
        return m11923c.booleanValue();
    }

    /* renamed from: c */
    private static Boolean m11923c() {
        Boolean m11840a = C6004i.m11840a();
        Boolean m11837b = C6004i.m11837b();
        if (m11840a != null && (m11837b == null || m11840a != m11837b)) {
            m11925a(m11840a.booleanValue(), false);
        }
        return m11840a;
    }
}
