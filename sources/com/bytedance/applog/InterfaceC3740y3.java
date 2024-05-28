package com.bytedance.applog;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.y3 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC3740y3 extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.y3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static abstract class AbstractBinderC3741a extends Binder implements InterfaceC3740y3 {

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.bytedance.applog.y3$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public static class C3742a implements InterfaceC3740y3 {

            /* renamed from: a */
            public IBinder f8963a;

            public C3742a(IBinder iBinder) {
                this.f8963a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f8963a;
            }
        }

        /* renamed from: a */
        public static InterfaceC3740y3 m16996a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.zui.deviceidservice.IDeviceidInterface");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof InterfaceC3740y3)) ? new C3742a(iBinder) : (InterfaceC3740y3) queryLocalInterface;
        }
    }
}
