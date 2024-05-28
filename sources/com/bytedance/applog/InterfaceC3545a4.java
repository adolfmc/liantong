package com.bytedance.applog;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.a4 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface InterfaceC3545a4 extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.a4$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static abstract class AbstractBinderC3546a extends Binder implements InterfaceC3545a4 {

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.bytedance.applog.a4$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public static class C3547a implements InterfaceC3545a4 {

            /* renamed from: a */
            public IBinder f8366a;

            public C3547a(IBinder iBinder) {
                this.f8366a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f8366a;
            }
        }

        /* renamed from: a */
        public static InterfaceC3545a4 m17334a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof InterfaceC3545a4)) ? new C3547a(iBinder) : (InterfaceC3545a4) queryLocalInterface;
        }
    }
}
