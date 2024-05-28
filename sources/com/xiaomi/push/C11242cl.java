package com.xiaomi.push;

import android.content.Context;

/* renamed from: com.xiaomi.push.cl */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11242cl implements InterfaceC11362ff, InterfaceC11370fk {

    /* renamed from: a */
    private Context f21721a;

    @Override // com.xiaomi.push.InterfaceC11370fk
    /* renamed from: a */
    public boolean mo2829a(AbstractC11375fo abstractC11375fo) {
        return true;
    }

    public C11242cl(Context context) {
        this.f21721a = context;
    }

    @Override // com.xiaomi.push.InterfaceC11362ff
    /* renamed from: a */
    public void mo2830a(AbstractC11375fo abstractC11375fo) {
        C11253ct.m4458b(this.f21721a);
    }

    @Override // com.xiaomi.push.InterfaceC11362ff
    /* renamed from: a */
    public void mo2831a(C11339er c11339er) {
        if (c11339er != null && c11339er.m3968a() == 0 && "PING".equals(c11339er.m3966a())) {
            C11253ct.m4454d(this.f21721a);
        } else {
            C11253ct.m4458b(this.f21721a);
        }
    }
}
