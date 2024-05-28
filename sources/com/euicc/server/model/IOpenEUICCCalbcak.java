package com.euicc.server.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IOpenEUICCCalbcak extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Default implements IOpenEUICCCalbcak {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.euicc.server.model.IOpenEUICCCalbcak
        public void getDeviceEUICCInfo(EUICCDeviceInfo eUICCDeviceInfo) throws RemoteException {
        }
    }

    void getDeviceEUICCInfo(EUICCDeviceInfo eUICCDeviceInfo) throws RemoteException;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IOpenEUICCCalbcak {
        private static final String DESCRIPTOR = "com.euicc.server.model.IOpenEUICCCalbcak";
        static final int TRANSACTION_getDeviceEUICCInfo = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOpenEUICCCalbcak asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOpenEUICCCalbcak)) {
                return (IOpenEUICCCalbcak) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            getDeviceEUICCInfo(parcel.readInt() != 0 ? EUICCDeviceInfo.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IOpenEUICCCalbcak {
            public static IOpenEUICCCalbcak sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.euicc.server.model.IOpenEUICCCalbcak
            public void getDeviceEUICCInfo(EUICCDeviceInfo eUICCDeviceInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (eUICCDeviceInfo != null) {
                        obtain.writeInt(1);
                        eUICCDeviceInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDeviceEUICCInfo(eUICCDeviceInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOpenEUICCCalbcak iOpenEUICCCalbcak) {
            if (Proxy.sDefaultImpl != null || iOpenEUICCCalbcak == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOpenEUICCCalbcak;
            return true;
        }

        public static IOpenEUICCCalbcak getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
