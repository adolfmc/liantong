package com.bytedance.applog;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC3548b extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.b$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static abstract class AbstractBinderC3549a extends Binder implements InterfaceC3548b {

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.bytedance.applog.b$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public static class C3550a implements InterfaceC3548b {

            /* renamed from: a */
            public IBinder f8367a;

            public C3550a(IBinder iBinder) {
                this.f8367a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f8367a;
            }
        }

        /* renamed from: a */
        public static InterfaceC3548b m17333a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.bun.lib.MsaIdInterface");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof InterfaceC3548b)) ? new C3550a(iBinder) : (InterfaceC3548b) queryLocalInterface;
        }
    }
}
