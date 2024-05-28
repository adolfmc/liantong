package org.fidoalliance.aidl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface IUAFOperation extends IInterface {
    void process(Intent intent, IUAFResponseListener iUAFResponseListener);

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.fidoalliance.aidl.IUAFOperation$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class AbstractBinderC13419a extends Binder implements IUAFOperation {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: org.fidoalliance.aidl.IUAFOperation$a$a */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public static class C13420a implements IUAFOperation {

            /* renamed from: b */
            public static IUAFOperation f27384b;

            /* renamed from: a */
            private IBinder f27385a;

            C13420a(IBinder iBinder) {
                this.f27385a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f27385a;
            }

            @Override // org.fidoalliance.aidl.IUAFOperation
            public void process(Intent intent, IUAFResponseListener iUAFResponseListener) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.fidoalliance.aidl.IUAFOperation");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iUAFResponseListener != null ? iUAFResponseListener.asBinder() : null);
                    if (this.f27385a.transact(1, obtain, null, 1) || AbstractBinderC13419a.m228a() == null) {
                        return;
                    }
                    AbstractBinderC13419a.m228a().process(intent, iUAFResponseListener);
                } finally {
                    obtain.recycle();
                }
            }
        }

        /* renamed from: a */
        public static IUAFOperation m227a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("org.fidoalliance.aidl.IUAFOperation");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUAFOperation)) {
                return (IUAFOperation) queryLocalInterface;
            }
            return new C13420a(iBinder);
        }

        /* renamed from: a */
        public static IUAFOperation m228a() {
            return C13420a.f27384b;
        }
    }
}
