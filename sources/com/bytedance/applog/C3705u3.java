package com.bytedance.applog;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.bytedance.applog.C3713v3;
import com.bytedance.applog.InterfaceC3732x3;

/* renamed from: com.bytedance.applog.u3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3705u3 extends AbstractC3543a3<InterfaceC3732x3> {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.u3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C3706a implements C3713v3.InterfaceC3715b<InterfaceC3732x3, String> {
        public C3706a(C3705u3 c3705u3) {
        }

        @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
        /* renamed from: a */
        public InterfaceC3732x3 mo16998a(IBinder iBinder) {
            return InterfaceC3732x3.AbstractBinderC3733a.m17029a(iBinder);
        }

        @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
        /* renamed from: a */
        public String mo16997a(InterfaceC3732x3 interfaceC3732x3) {
            return ((InterfaceC3732x3.AbstractBinderC3733a.C3734a) interfaceC3732x3).m17028a();
        }
    }

    public C3705u3() {
        super("com.samsung.android.deviceidservice");
    }

    @Override // com.bytedance.applog.AbstractC3543a3
    /* renamed from: a */
    public C3713v3.InterfaceC3715b<InterfaceC3732x3, String> mo17000a() {
        return new C3706a(this);
    }

    @Override // com.bytedance.applog.AbstractC3543a3
    /* renamed from: c */
    public Intent mo16999c(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
        return intent;
    }
}
