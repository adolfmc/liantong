package com.baidu.mapframework.open.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapframework.open.aidl.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IOpenClientCallback extends IInterface {
    /* renamed from: a */
    void mo18559a(IBinder iBinder) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: IOpenClientCallback.java */
    /* renamed from: com.baidu.mapframework.open.aidl.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class AbstractBinderC2865a extends Binder implements IOpenClientCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC2865a() {
            attachInterface(this, "com.baidu.mapframework.open.aidl.IOpenClientCallback");
        }

        /* renamed from: b */
        public static IOpenClientCallback m18560b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IOpenClientCallback");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOpenClientCallback)) {
                return (IOpenClientCallback) queryLocalInterface;
            }
            return new C2866a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.baidu.mapframework.open.aidl.IOpenClientCallback");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.baidu.mapframework.open.aidl.IOpenClientCallback");
            mo18559a(parcel.readStrongBinder());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* compiled from: IOpenClientCallback.java */
        /* renamed from: com.baidu.mapframework.open.aidl.b$a$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class C2866a implements IOpenClientCallback {

            /* renamed from: a */
            public static IOpenClientCallback f7046a;

            /* renamed from: b */
            private IBinder f7047b;

            C2866a(IBinder iBinder) {
                this.f7047b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f7047b;
            }

            @Override // com.baidu.mapframework.open.aidl.IOpenClientCallback
            /* renamed from: a */
            public void mo18559a(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IOpenClientCallback");
                    obtain.writeStrongBinder(iBinder);
                    if (!this.f7047b.transact(1, obtain, obtain2, 0) && AbstractBinderC2865a.m18561a() != null) {
                        AbstractBinderC2865a.m18561a().mo18559a(iBinder);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        /* renamed from: a */
        public static IOpenClientCallback m18561a() {
            return C2866a.f7046a;
        }
    }
}
