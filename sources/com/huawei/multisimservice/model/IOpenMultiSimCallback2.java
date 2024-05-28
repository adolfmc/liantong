package com.huawei.multisimservice.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IOpenMultiSimCallback2 extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Default implements IOpenMultiSimCallback2 {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.multisimservice.model.IOpenMultiSimCallback2
        public void getDeviceMultiSimInfoAndPhoneNumber(MultiSimDeviceInfo multiSimDeviceInfo, String str) throws RemoteException {
        }
    }

    void getDeviceMultiSimInfoAndPhoneNumber(MultiSimDeviceInfo multiSimDeviceInfo, String str) throws RemoteException;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IOpenMultiSimCallback2 {
        private static final String DESCRIPTOR = "com.huawei.multisimservice.model.IOpenMultiSimCallback2";
        static final int TRANSACTION_getDeviceMultiSimInfoAndPhoneNumber = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOpenMultiSimCallback2 asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOpenMultiSimCallback2)) {
                return (IOpenMultiSimCallback2) queryLocalInterface;
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
            getDeviceMultiSimInfoAndPhoneNumber(parcel.readInt() != 0 ? MultiSimDeviceInfo.CREATOR.createFromParcel(parcel) : null, parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IOpenMultiSimCallback2 {
            public static IOpenMultiSimCallback2 sDefaultImpl;
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

            @Override // com.huawei.multisimservice.model.IOpenMultiSimCallback2
            public void getDeviceMultiSimInfoAndPhoneNumber(MultiSimDeviceInfo multiSimDeviceInfo, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (multiSimDeviceInfo != null) {
                        obtain.writeInt(1);
                        multiSimDeviceInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDeviceMultiSimInfoAndPhoneNumber(multiSimDeviceInfo, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOpenMultiSimCallback2 iOpenMultiSimCallback2) {
            if (Proxy.sDefaultImpl != null || iOpenMultiSimCallback2 == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOpenMultiSimCallback2;
            return true;
        }

        public static IOpenMultiSimCallback2 getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
