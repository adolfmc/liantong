package com.vivo.vms;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.vivo.vms.IPCCallback;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IPCInvoke extends IInterface {
    public static final String DESCRIPTOR = "com.vivo.vms.IPCInvoke";

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements IPCInvoke {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.vivo.vms.IPCInvoke
        public Bundle asyncCall(Bundle bundle, IPCCallback iPCCallback) throws RemoteException {
            return null;
        }
    }

    Bundle asyncCall(Bundle bundle, IPCCallback iPCCallback) throws RemoteException;

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IPCInvoke {
        static final int TRANSACTION_asyncCall = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.vivo.vms.IPCInvoke");
        }

        public static IPCInvoke asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.vivo.vms.IPCInvoke");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPCInvoke)) {
                return (IPCInvoke) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.vivo.vms.IPCInvoke");
                return true;
            } else if (i == 1) {
                parcel.enforceInterface("com.vivo.vms.IPCInvoke");
                Bundle asyncCall = asyncCall(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, IPCCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                if (asyncCall != null) {
                    parcel2.writeInt(1);
                    asyncCall.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IPCInvoke {
            public static IPCInvoke sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.vivo.vms.IPCInvoke";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.vivo.vms.IPCInvoke
            public Bundle asyncCall(Bundle bundle, IPCCallback iPCCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.vivo.vms.IPCInvoke");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPCCallback != null ? iPCCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().asyncCall(bundle, iPCCallback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPCInvoke iPCInvoke) {
            if (Proxy.sDefaultImpl == null) {
                if (iPCInvoke != null) {
                    Proxy.sDefaultImpl = iPCInvoke;
                    return true;
                }
                return false;
            }
            throw new IllegalStateException("setDefaultImpl() called twice");
        }

        public static IPCInvoke getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
