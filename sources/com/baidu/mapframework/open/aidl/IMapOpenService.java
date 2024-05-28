package com.baidu.mapframework.open.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.baidu.mapframework.open.aidl.IOpenClientCallback;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapframework.open.aidl.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IMapOpenService extends IInterface {
    /* renamed from: a */
    void mo18562a(IOpenClientCallback iOpenClientCallback) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: IMapOpenService.java */
    /* renamed from: com.baidu.mapframework.open.aidl.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class AbstractBinderC2863a extends Binder implements IMapOpenService {
        /* renamed from: a */
        public static IMapOpenService m18563a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMapOpenService)) {
                return (IMapOpenService) queryLocalInterface;
            }
            return new C2864a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.baidu.mapframework.open.aidl.IMapOpenService");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
            mo18562a(IOpenClientCallback.AbstractBinderC2865a.m18560b(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* compiled from: IMapOpenService.java */
        /* renamed from: com.baidu.mapframework.open.aidl.a$a$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class C2864a implements IMapOpenService {

            /* renamed from: a */
            public static IMapOpenService f7044a;

            /* renamed from: b */
            private IBinder f7045b;

            C2864a(IBinder iBinder) {
                this.f7045b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f7045b;
            }

            @Override // com.baidu.mapframework.open.aidl.IMapOpenService
            /* renamed from: a */
            public void mo18562a(IOpenClientCallback iOpenClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IMapOpenService");
                    obtain.writeStrongBinder(iOpenClientCallback != null ? iOpenClientCallback.asBinder() : null);
                    if (!this.f7045b.transact(1, obtain, obtain2, 0) && AbstractBinderC2863a.m18564a() != null) {
                        AbstractBinderC2863a.m18564a().mo18562a(iOpenClientCallback);
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
        public static IMapOpenService m18564a() {
            return C2864a.f7044a;
        }
    }
}
