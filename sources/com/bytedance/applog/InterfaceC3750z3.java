package com.bytedance.applog;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.z3 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC3750z3 extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.z3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static abstract class AbstractBinderC3751a extends Binder implements InterfaceC3750z3 {

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.bytedance.applog.z3$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public static class C3752a implements InterfaceC3750z3 {

            /* renamed from: a */
            public IBinder f9000a;

            public C3752a(IBinder iBinder) {
                this.f9000a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f9000a;
            }
        }

        /* renamed from: a */
        public static InterfaceC3750z3 m16987a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.asus.msa.SupplementaryDID.IDidAidlInterface");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof InterfaceC3750z3)) ? new C3752a(iBinder) : (InterfaceC3750z3) queryLocalInterface;
        }
    }
}
