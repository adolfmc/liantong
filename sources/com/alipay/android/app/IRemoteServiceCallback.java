package com.alipay.android.app;

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
public interface IRemoteServiceCallback extends IInterface {
    boolean isHideLoadingScreen() throws RemoteException;

    void payEnd(boolean z, String str) throws RemoteException;

    void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class Stub extends Binder implements IRemoteServiceCallback {
        private static final String DESCRIPTOR = "com.alipay.android.app.IRemoteServiceCallback";
        static final int TRANSACTION_isHideLoadingScreen = 3;
        static final int TRANSACTION_payEnd = 2;
        static final int TRANSACTION_startActivity = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.alipay.android.app.IRemoteServiceCallback");
        }

        public static IRemoteServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.alipay.android.app.IRemoteServiceCallback");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRemoteServiceCallback)) {
                return (IRemoteServiceCallback) queryLocalInterface;
            }
            return new C1919a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.alipay.android.app.IRemoteServiceCallback");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
                    startActivity(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
                    payEnd(parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
                    boolean isHideLoadingScreen = isHideLoadingScreen();
                    parcel2.writeNoException();
                    parcel2.writeInt(isHideLoadingScreen ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.alipay.android.app.IRemoteServiceCallback$Stub$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        static class C1919a implements IRemoteServiceCallback {

            /* renamed from: a */
            private IBinder f3380a;

            /* renamed from: a */
            public String m21140a() {
                return "com.alipay.android.app.IRemoteServiceCallback";
            }

            C1919a(IBinder iBinder) {
                this.f3380a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3380a;
            }

            @Override // com.alipay.android.app.IRemoteServiceCallback
            public void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.alipay.android.app.IRemoteServiceCallback");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f3380a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.alipay.android.app.IRemoteServiceCallback
            public void payEnd(boolean z, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.alipay.android.app.IRemoteServiceCallback");
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str);
                    this.f3380a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.alipay.android.app.IRemoteServiceCallback
            public boolean isHideLoadingScreen() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.alipay.android.app.IRemoteServiceCallback");
                    this.f3380a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
