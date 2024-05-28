package com.bytedance.applog;

import android.os.IBinder;
import android.os.Parcel;
import android.util.Pair;
import com.bytedance.applog.C3713v3;
import com.bytedance.applog.InterfaceC3689s3;

/* renamed from: com.bytedance.applog.i3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3604i3 implements C3713v3.InterfaceC3715b<InterfaceC3689s3, Pair<String, Boolean>> {
    @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
    /* renamed from: a */
    public InterfaceC3689s3 mo16998a(IBinder iBinder) {
        return InterfaceC3689s3.AbstractBinderC3690a.m17115a(iBinder);
    }

    @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
    /* renamed from: a */
    public Pair<String, Boolean> mo16997a(InterfaceC3689s3 interfaceC3689s3) {
        InterfaceC3689s3 interfaceC3689s32 = interfaceC3689s3;
        if (interfaceC3689s32 == null) {
            return null;
        }
        InterfaceC3689s3.AbstractBinderC3690a.C3691a c3691a = (InterfaceC3689s3.AbstractBinderC3690a.C3691a) interfaceC3689s32;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
            c3691a.f8811a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            String readString = obtain2.readString();
            obtain2.recycle();
            obtain.recycle();
            obtain = Parcel.obtain();
            obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                c3691a.f8811a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                boolean z = obtain2.readInt() != 0;
                obtain2.recycle();
                obtain.recycle();
                return new Pair<>(readString, Boolean.valueOf(z));
            } finally {
            }
        } finally {
        }
    }
}
