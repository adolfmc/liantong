package com.alipay.android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.alipay.android.app.IRemoteServiceCallback;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IAlixPay extends IInterface {
    String Pay(String str) throws RemoteException;

    String prePay(String str) throws RemoteException;

    void registerCallback(IRemoteServiceCallback iRemoteServiceCallback) throws RemoteException;

    String test() throws RemoteException;

    void unregisterCallback(IRemoteServiceCallback iRemoteServiceCallback) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class Stub extends Binder implements IAlixPay {
        private static final String DESCRIPTOR = "com.alipay.android.app.IAlixPay";
        static final int TRANSACTION_Pay = 1;
        static final int TRANSACTION_prePay = 5;
        static final int TRANSACTION_registerCallback = 3;
        static final int TRANSACTION_test = 2;
        static final int TRANSACTION_unregisterCallback = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.alipay.android.app.IAlixPay");
        }

        public static IAlixPay asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.alipay.android.app.IAlixPay");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAlixPay)) {
                return (IAlixPay) queryLocalInterface;
            }
            return new C1918a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.alipay.android.app.IAlixPay");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.alipay.android.app.IAlixPay");
                    String Pay = Pay(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(Pay);
                    return true;
                case 2:
                    parcel.enforceInterface("com.alipay.android.app.IAlixPay");
                    String test = test();
                    parcel2.writeNoException();
                    parcel2.writeString(test);
                    return true;
                case 3:
                    parcel.enforceInterface("com.alipay.android.app.IAlixPay");
                    registerCallback(IRemoteServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.alipay.android.app.IAlixPay");
                    unregisterCallback(IRemoteServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.alipay.android.app.IAlixPay");
                    String prePay = prePay(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(prePay);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.alipay.android.app.IAlixPay$Stub$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        static class C1918a implements IAlixPay {

            /* renamed from: a */
            private IBinder f3379a;

            /* renamed from: a */
            public String m21141a() {
                return "com.alipay.android.app.IAlixPay";
            }

            C1918a(IBinder iBinder) {
                this.f3379a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3379a;
            }

            @Override // com.alipay.android.app.IAlixPay
            public String Pay(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.alipay.android.app.IAlixPay");
                    obtain.writeString(str);
                    this.f3379a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.alipay.android.app.IAlixPay
            public String test() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.alipay.android.app.IAlixPay");
                    this.f3379a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.alipay.android.app.IAlixPay
            public void registerCallback(IRemoteServiceCallback iRemoteServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.alipay.android.app.IAlixPay");
                    obtain.writeStrongBinder(iRemoteServiceCallback != null ? iRemoteServiceCallback.asBinder() : null);
                    this.f3379a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.alipay.android.app.IAlixPay
            public void unregisterCallback(IRemoteServiceCallback iRemoteServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.alipay.android.app.IAlixPay");
                    obtain.writeStrongBinder(iRemoteServiceCallback != null ? iRemoteServiceCallback.asBinder() : null);
                    this.f3379a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.alipay.android.app.IAlixPay
            public String prePay(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.alipay.android.app.IAlixPay");
                    obtain.writeString(str);
                    this.f3379a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
