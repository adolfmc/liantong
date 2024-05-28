package com.sdk.p305u;

import android.content.Context;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.p289e.InterfaceC6991a;
import com.sdk.p290f.C6998d;
import com.sdk.p290f.InterfaceC6997c;
import com.sdk.p291g.C7005b;
import com.sdk.p302r.C7037a;
import java.util.List;

/* renamed from: com.sdk.u.a */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C7040a<T> extends C7005b<T> {
    public C7040a(Context context, InterfaceC6991a<T> interfaceC6991a, List<String> list, InterfaceC6997c interfaceC6997c) {
        super(context, interfaceC6991a, list, interfaceC6997c);
        m8124a();
    }

    /* renamed from: a */
    public final void m8124a() {
        if (C6998d.f18137c) {
            String statisticalTestHost = SDKManager.getStatisticalTestHost();
            String testHost = SDKManager.getTestHost();
            if (C7037a.m8130a(testHost).booleanValue()) {
                testHost = C6998d.EnumC7000b.f18140c.mo8168a();
            }
            C7037a.m8129b(statisticalTestHost).booleanValue();
            if (C7037a.m8129b(testHost).booleanValue()) {
                this.f18151g = testHost;
                return;
            }
        }
        this.f18151g = C6998d.EnumC7000b.f18139b.mo8168a();
    }
}
