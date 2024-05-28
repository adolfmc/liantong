package com.bytedance.applog;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import com.bytedance.applog.C3713v3;
import com.bytedance.applog.InterfaceC3740y3;

/* renamed from: com.bytedance.applog.j3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3610j3 extends AbstractC3543a3<InterfaceC3740y3> {

    /* renamed from: com.bytedance.applog.j3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3611a implements C3713v3.InterfaceC3715b<InterfaceC3740y3, String> {
        public C3611a(C3610j3 c3610j3) {
        }

        @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
        /* renamed from: a */
        public InterfaceC3740y3 mo16998a(IBinder iBinder) {
            return InterfaceC3740y3.AbstractBinderC3741a.m16996a(iBinder);
        }

        @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
        /* renamed from: a */
        public String mo16997a(InterfaceC3740y3 interfaceC3740y3) {
            InterfaceC3740y3 interfaceC3740y32 = interfaceC3740y3;
            if (interfaceC3740y32 == null) {
                return null;
            }
            InterfaceC3740y3.AbstractBinderC3741a.C3742a c3742a = (InterfaceC3740y3.AbstractBinderC3741a.C3742a) interfaceC3740y32;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                c3742a.f8963a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public C3610j3() {
        super("com.zui.deviceidservice");
    }

    @Override // com.bytedance.applog.AbstractC3543a3
    /* renamed from: a */
    public C3713v3.InterfaceC3715b<InterfaceC3740y3, String> mo17000a() {
        return new C3611a(this);
    }

    @Override // com.bytedance.applog.AbstractC3543a3
    /* renamed from: c */
    public Intent mo16999c(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        return intent;
    }
}
