package com.baidu.mapframework.open.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IComOpenClient extends IInterface {
    /* renamed from: a */
    String mo18566a(String str) throws RemoteException;

    /* renamed from: a */
    boolean mo18565a(String str, String str2, Bundle bundle) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapframework.open.aidl.IComOpenClient$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class AbstractBinderC2861a extends Binder implements IComOpenClient {
        /* renamed from: a */
        public static IComOpenClient m18567a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IComOpenClient");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IComOpenClient)) {
                return (IComOpenClient) queryLocalInterface;
            }
            return new C2862a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.baidu.mapframework.open.aidl.IComOpenClient");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.baidu.mapframework.open.aidl.IComOpenClient");
                    String a = mo18566a(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 2:
                    parcel.enforceInterface("com.baidu.mapframework.open.aidl.IComOpenClient");
                    boolean a2 = mo18565a(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(a2 ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.baidu.mapframework.open.aidl.IComOpenClient$a$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class C2862a implements IComOpenClient {

            /* renamed from: a */
            public static IComOpenClient f7042a;

            /* renamed from: b */
            private IBinder f7043b;

            C2862a(IBinder iBinder) {
                this.f7043b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f7043b;
            }

            @Override // com.baidu.mapframework.open.aidl.IComOpenClient
            /* renamed from: a */
            public String mo18566a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IComOpenClient");
                    obtain.writeString(str);
                    if (!this.f7043b.transact(1, obtain, obtain2, 0) && AbstractBinderC2861a.m18568a() != null) {
                        return AbstractBinderC2861a.m18568a().mo18566a(str);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.baidu.mapframework.open.aidl.IComOpenClient
            /* renamed from: a */
            public boolean mo18565a(String str, String str2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IComOpenClient");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f7043b.transact(2, obtain, obtain2, 0) && AbstractBinderC2861a.m18568a() != null) {
                        return AbstractBinderC2861a.m18568a().mo18565a(str, str2, bundle);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        /* renamed from: a */
        public static IComOpenClient m18568a() {
            return C2862a.f7042a;
        }
    }
}
