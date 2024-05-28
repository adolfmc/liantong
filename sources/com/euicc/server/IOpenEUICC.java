package com.euicc.server;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.euicc.server.model.IOpenEUICCCalbcak;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IOpenEUICC extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Default implements IOpenEUICC {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.euicc.server.IOpenEUICC
        public void downloadEUICCProfile(String str) throws RemoteException {
        }

        @Override // com.euicc.server.IOpenEUICC
        public void getAttachedDeviceEUICCInfo() throws RemoteException {
        }

        @Override // com.euicc.server.IOpenEUICC
        public void registerCallback(IOpenEUICCCalbcak iOpenEUICCCalbcak) throws RemoteException {
        }

        @Override // com.euicc.server.IOpenEUICC
        public void unRegisterCallback(IOpenEUICCCalbcak iOpenEUICCCalbcak) throws RemoteException {
        }
    }

    void downloadEUICCProfile(String str) throws RemoteException;

    void getAttachedDeviceEUICCInfo() throws RemoteException;

    void registerCallback(IOpenEUICCCalbcak iOpenEUICCCalbcak) throws RemoteException;

    void unRegisterCallback(IOpenEUICCCalbcak iOpenEUICCCalbcak) throws RemoteException;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IOpenEUICC {
        private static final String DESCRIPTOR = "com.euicc.server.IOpenEUICC";
        static final int TRANSACTION_downloadEUICCProfile = 4;
        static final int TRANSACTION_getAttachedDeviceEUICCInfo = 3;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_unRegisterCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOpenEUICC asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOpenEUICC)) {
                return (IOpenEUICC) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCallback(IOpenEUICCCalbcak.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterCallback(IOpenEUICCCalbcak.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    getAttachedDeviceEUICCInfo();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    downloadEUICCProfile(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IOpenEUICC {
            public static IOpenEUICC sDefaultImpl;
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

            @Override // com.euicc.server.IOpenEUICC
            public void registerCallback(IOpenEUICCCalbcak iOpenEUICCCalbcak) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOpenEUICCCalbcak != null ? iOpenEUICCCalbcak.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallback(iOpenEUICCCalbcak);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.euicc.server.IOpenEUICC
            public void unRegisterCallback(IOpenEUICCCalbcak iOpenEUICCCalbcak) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOpenEUICCCalbcak != null ? iOpenEUICCCalbcak.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterCallback(iOpenEUICCCalbcak);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.euicc.server.IOpenEUICC
            public void getAttachedDeviceEUICCInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getAttachedDeviceEUICCInfo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.euicc.server.IOpenEUICC
            public void downloadEUICCProfile(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().downloadEUICCProfile(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOpenEUICC iOpenEUICC) {
            if (Proxy.sDefaultImpl != null || iOpenEUICC == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOpenEUICC;
            return true;
        }

        public static IOpenEUICC getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
