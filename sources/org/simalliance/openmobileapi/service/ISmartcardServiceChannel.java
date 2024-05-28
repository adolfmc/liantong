package org.simalliance.openmobileapi.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ISmartcardServiceChannel extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class Stub extends Binder implements ISmartcardServiceChannel {
        public static final String DESCRIPTOR = "org.simalliance.openmobileapi.service.ISmartcardServiceChannel";
        public static final int TRANSACTION_close = 1;
        public static final int TRANSACTION_getSelectResponse = 4;
        public static final int TRANSACTION_isBasicChannel = 3;
        public static final int TRANSACTION_isClosed = 2;
        public static final int TRANSACTION_selectNext = 6;
        public static final int TRANSACTION_transmit = 5;

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public static class Proxy implements ISmartcardServiceChannel {
            public IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceChannel
            public void close(SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        smartcardError.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "org.simalliance.openmobileapi.service.ISmartcardServiceChannel";
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceChannel
            public byte[] getSelectResponse() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceChannel
            public boolean isBasicChannel() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceChannel
            public boolean isClosed() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceChannel
            public boolean selectNext(SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    boolean z = obtain2.readInt() != 0;
                    if (obtain2.readInt() != 0) {
                        smartcardError.readFromParcel(obtain2);
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceChannel
            public byte[] transmit(byte[] bArr, SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    byte[] createByteArray = obtain2.createByteArray();
                    if (obtain2.readInt() != 0) {
                        smartcardError.readFromParcel(obtain2);
                    }
                    return createByteArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
        }

        public static ISmartcardServiceChannel asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartcardServiceChannel)) {
                return (ISmartcardServiceChannel) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                        SmartcardError smartcardError = new SmartcardError();
                        close(smartcardError);
                        parcel2.writeNoException();
                        parcel2.writeInt(1);
                        smartcardError.writeToParcel(parcel2, 1);
                        return true;
                    case 2:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                        boolean isClosed = isClosed();
                        parcel2.writeNoException();
                        parcel2.writeInt(isClosed ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                        boolean isBasicChannel = isBasicChannel();
                        parcel2.writeNoException();
                        parcel2.writeInt(isBasicChannel ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                        byte[] selectResponse = getSelectResponse();
                        parcel2.writeNoException();
                        parcel2.writeByteArray(selectResponse);
                        return true;
                    case 5:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                        byte[] createByteArray = parcel.createByteArray();
                        SmartcardError smartcardError2 = new SmartcardError();
                        byte[] transmit = transmit(createByteArray, smartcardError2);
                        parcel2.writeNoException();
                        parcel2.writeByteArray(transmit);
                        parcel2.writeInt(1);
                        smartcardError2.writeToParcel(parcel2, 1);
                        return true;
                    case 6:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
                        SmartcardError smartcardError3 = new SmartcardError();
                        boolean selectNext = selectNext(smartcardError3);
                        parcel2.writeNoException();
                        parcel2.writeInt(selectNext ? 1 : 0);
                        parcel2.writeInt(1);
                        smartcardError3.writeToParcel(parcel2, 1);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString("org.simalliance.openmobileapi.service.ISmartcardServiceChannel");
            return true;
        }
    }

    void close(SmartcardError smartcardError);

    byte[] getSelectResponse();

    boolean isBasicChannel();

    boolean isClosed();

    boolean selectNext(SmartcardError smartcardError);

    byte[] transmit(byte[] bArr, SmartcardError smartcardError);
}
