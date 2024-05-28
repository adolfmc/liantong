package com.bytedance.applog;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.s3 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC3689s3 extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.s3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static abstract class AbstractBinderC3690a extends Binder implements InterfaceC3689s3 {

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.bytedance.applog.s3$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public static class C3691a implements InterfaceC3689s3 {

            /* renamed from: a */
            public IBinder f8811a;

            public C3691a(IBinder iBinder) {
                this.f8811a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f8811a;
            }
        }

        /* renamed from: a */
        public static InterfaceC3689s3 m17115a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof InterfaceC3689s3)) ? new C3691a(iBinder) : (InterfaceC3689s3) queryLocalInterface;
        }
    }
}
