package com.bytedance.applog;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.x3 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC3732x3 extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.x3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static abstract class AbstractBinderC3733a extends Binder implements InterfaceC3732x3 {

        /* renamed from: com.bytedance.applog.x3$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class C3734a implements InterfaceC3732x3 {

            /* renamed from: a */
            public IBinder f8931a;

            public C3734a(IBinder iBinder) {
                this.f8931a = iBinder;
            }

            /* renamed from: a */
            public String m17028a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.deviceidservice.IDeviceIdService");
                    this.f8931a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f8931a;
            }
        }

        /* renamed from: a */
        public static InterfaceC3732x3 m17029a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.deviceidservice.IDeviceIdService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof InterfaceC3732x3)) ? new C3734a(iBinder) : (InterfaceC3732x3) queryLocalInterface;
        }
    }
}
