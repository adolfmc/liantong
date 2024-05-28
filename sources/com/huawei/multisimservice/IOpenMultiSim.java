package com.huawei.multisimservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.multisimservice.model.IOpenMultiSimCalbcak;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IOpenMultiSim extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Default implements IOpenMultiSim {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim
        public void downloadESimProfile(String str) throws RemoteException {
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim
        public void getAttachedDeviceMultiSimInfo() throws RemoteException {
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim
        public void registerCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) throws RemoteException {
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim
        public void unRegisterCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) throws RemoteException {
        }
    }

    void downloadESimProfile(String str) throws RemoteException;

    void getAttachedDeviceMultiSimInfo() throws RemoteException;

    void registerCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) throws RemoteException;

    void unRegisterCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) throws RemoteException;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IOpenMultiSim {
        private static final String DESCRIPTOR = "com.huawei.multisimservice.IOpenMultiSim";
        static final int TRANSACTION_downloadESimProfile = 4;
        static final int TRANSACTION_getAttachedDeviceMultiSimInfo = 3;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_unRegisterCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOpenMultiSim asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOpenMultiSim)) {
                return (IOpenMultiSim) queryLocalInterface;
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
                    registerCallback(IOpenMultiSimCalbcak.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterCallback(IOpenMultiSimCalbcak.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    getAttachedDeviceMultiSimInfo();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    downloadESimProfile(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IOpenMultiSim {
            public static IOpenMultiSim sDefaultImpl;
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

            @Override // com.huawei.multisimservice.IOpenMultiSim
            public void registerCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOpenMultiSimCalbcak != null ? iOpenMultiSimCalbcak.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallback(iOpenMultiSimCalbcak);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IOpenMultiSim
            public void unRegisterCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOpenMultiSimCalbcak != null ? iOpenMultiSimCalbcak.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterCallback(iOpenMultiSimCalbcak);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IOpenMultiSim
            public void getAttachedDeviceMultiSimInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getAttachedDeviceMultiSimInfo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IOpenMultiSim
            public void downloadESimProfile(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().downloadESimProfile(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOpenMultiSim iOpenMultiSim) {
            if (Proxy.sDefaultImpl != null || iOpenMultiSim == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOpenMultiSim;
            return true;
        }

        public static IOpenMultiSim getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
