package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.InterfaceC11276df;

/* renamed from: com.xiaomi.mipush.sdk.i */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11101i implements InterfaceC11276df {

    /* renamed from: a */
    private Context f21390a;

    public C11101i(Context context) {
        this.f21390a = context;
    }

    @Override // com.xiaomi.push.InterfaceC11276df
    /* renamed from: a */
    public String mo4365a() {
        return C11087b.m5151a(this.f21390a).m5135d();
    }

    @Override // com.xiaomi.push.InterfaceC11276df
    /* renamed from: a */
    public void mo4364a(C11430he c11430he, EnumC11404gf enumC11404gf, C11417gs c11417gs) {
        C11118u.m5003a(this.f21390a).m4988a((C11118u) c11430he, enumC11404gf, c11417gs);
    }
}
