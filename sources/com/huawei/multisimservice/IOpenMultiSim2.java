package com.huawei.multisimservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.multisimservice.model.IOpenMultiSimCallback2;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IOpenMultiSim2 extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Default implements IOpenMultiSim2 {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim2
        public void downloadESimProfileAndPhonenumber(String str, String str2, String str3, String str4) throws RemoteException {
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim2
        public void getAttachedDeviceMultiSimInfo() throws RemoteException {
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim2
        public void registerCallback(IOpenMultiSimCallback2 iOpenMultiSimCallback2) throws RemoteException {
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim2
        public void unRegisterCallback(IOpenMultiSimCallback2 iOpenMultiSimCallback2) throws RemoteException {
        }
    }

    void downloadESimProfileAndPhonenumber(String str, String str2, String str3, String str4) throws RemoteException;

    void getAttachedDeviceMultiSimInfo() throws RemoteException;

    void registerCallback(IOpenMultiSimCallback2 iOpenMultiSimCallback2) throws RemoteException;

    void unRegisterCallback(IOpenMultiSimCallback2 iOpenMultiSimCallback2) throws RemoteException;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IOpenMultiSim2 {
        private static final String DESCRIPTOR = "com.huawei.multisimservice.IOpenMultiSim2";
        static final int TRANSACTION_downloadESimProfileAndPhonenumber = 4;
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

        public static IOpenMultiSim2 asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOpenMultiSim2)) {
                return (IOpenMultiSim2) queryLocalInterface;
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
                    registerCallback(IOpenMultiSimCallback2.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterCallback(IOpenMultiSimCallback2.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    getAttachedDeviceMultiSimInfo();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    downloadESimProfileAndPhonenumber(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IOpenMultiSim2 {
            public static IOpenMultiSim2 sDefaultImpl;
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

            @Override // com.huawei.multisimservice.IOpenMultiSim2
            public void registerCallback(IOpenMultiSimCallback2 iOpenMultiSimCallback2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOpenMultiSimCallback2 != null ? iOpenMultiSimCallback2.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallback(iOpenMultiSimCallback2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IOpenMultiSim2
            public void unRegisterCallback(IOpenMultiSimCallback2 iOpenMultiSimCallback2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOpenMultiSimCallback2 != null ? iOpenMultiSimCallback2.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterCallback(iOpenMultiSimCallback2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.IOpenMultiSim2
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

            @Override // com.huawei.multisimservice.IOpenMultiSim2
            public void downloadESimProfileAndPhonenumber(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().downloadESimProfileAndPhonenumber(str, str2, str3, str4);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOpenMultiSim2 iOpenMultiSim2) {
            if (Proxy.sDefaultImpl != null || iOpenMultiSim2 == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOpenMultiSim2;
            return true;
        }

        public static IOpenMultiSim2 getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
