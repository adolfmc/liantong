package com.vivo.vms;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IPCCallback extends IInterface {
    public static final String DESCRIPTOR = "com.vivo.vms.IPCCallback";

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements IPCCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.vivo.vms.IPCCallback
        public void call(Bundle bundle) throws RemoteException {
        }
    }

    void call(Bundle bundle) throws RemoteException;

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IPCCallback {
        static final int TRANSACTION_call = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.vivo.vms.IPCCallback");
        }

        public static IPCCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.vivo.vms.IPCCallback");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPCCallback)) {
                return (IPCCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.vivo.vms.IPCCallback");
                return true;
            } else if (i == 1) {
                parcel.enforceInterface("com.vivo.vms.IPCCallback");
                Bundle bundle = new Bundle();
                call(bundle);
                parcel2.writeNoException();
                parcel2.writeInt(1);
                bundle.writeToParcel(parcel2, 1);
                return true;
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IPCCallback {
            public static IPCCallback sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.vivo.vms.IPCCallback";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.vivo.vms.IPCCallback
            public void call(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.vivo.vms.IPCCallback");
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().call(bundle);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPCCallback iPCCallback) {
            if (Proxy.sDefaultImpl == null) {
                if (iPCCallback != null) {
                    Proxy.sDefaultImpl = iPCCallback;
                    return true;
                }
                return false;
            }
            throw new IllegalStateException("setDefaultImpl() called twice");
        }

        public static IPCCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
