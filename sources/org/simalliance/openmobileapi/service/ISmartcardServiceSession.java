package org.simalliance.openmobileapi.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.simalliance.openmobileapi.service.ISmartcardServiceCallback;
import org.simalliance.openmobileapi.service.ISmartcardServiceChannel;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ISmartcardServiceSession extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class Stub extends Binder implements ISmartcardServiceSession {
        public static final String DESCRIPTOR = "org.simalliance.openmobileapi.service.ISmartcardServiceSession";
        public static final int TRANSACTION_close = 2;
        public static final int TRANSACTION_closeChannels = 3;
        public static final int TRANSACTION_getAtr = 1;
        public static final int TRANSACTION_isClosed = 4;
        public static final int TRANSACTION_openBasicChannel = 5;
        public static final int TRANSACTION_openLogicalChannel = 6;

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public static class Proxy implements ISmartcardServiceSession {
            public IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceSession
            public void close(SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        smartcardError.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceSession
            public void closeChannels(SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        smartcardError.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceSession
            public byte[] getAtr() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "org.simalliance.openmobileapi.service.ISmartcardServiceSession";
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceSession
            public boolean isClosed() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceSession
            public ISmartcardServiceChannel openBasicChannel(byte[] bArr, byte b, ISmartcardServiceCallback iSmartcardServiceCallback, SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                    obtain.writeByteArray(bArr);
                    obtain.writeByte(b);
                    obtain.writeStrongBinder(iSmartcardServiceCallback != null ? iSmartcardServiceCallback.asBinder() : null);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    ISmartcardServiceChannel asInterface = ISmartcardServiceChannel.Stub.asInterface(obtain2.readStrongBinder());
                    if (obtain2.readInt() != 0) {
                        smartcardError.readFromParcel(obtain2);
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceSession
            public ISmartcardServiceChannel openLogicalChannel(byte[] bArr, byte b, ISmartcardServiceCallback iSmartcardServiceCallback, SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                    obtain.writeByteArray(bArr);
                    obtain.writeByte(b);
                    obtain.writeStrongBinder(iSmartcardServiceCallback != null ? iSmartcardServiceCallback.asBinder() : null);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    ISmartcardServiceChannel asInterface = ISmartcardServiceChannel.Stub.asInterface(obtain2.readStrongBinder());
                    if (obtain2.readInt() != 0) {
                        smartcardError.readFromParcel(obtain2);
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "org.simalliance.openmobileapi.service.ISmartcardServiceSession");
        }

        public static ISmartcardServiceSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartcardServiceSession)) {
                return (ISmartcardServiceSession) queryLocalInterface;
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
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                        byte[] atr = getAtr();
                        parcel2.writeNoException();
                        parcel2.writeByteArray(atr);
                        return true;
                    case 2:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                        SmartcardError smartcardError = new SmartcardError();
                        close(smartcardError);
                        parcel2.writeNoException();
                        parcel2.writeInt(1);
                        smartcardError.writeToParcel(parcel2, 1);
                        return true;
                    case 3:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                        SmartcardError smartcardError2 = new SmartcardError();
                        closeChannels(smartcardError2);
                        parcel2.writeNoException();
                        parcel2.writeInt(1);
                        smartcardError2.writeToParcel(parcel2, 1);
                        return true;
                    case 4:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                        boolean isClosed = isClosed();
                        parcel2.writeNoException();
                        parcel2.writeInt(isClosed ? 1 : 0);
                        return true;
                    case 5:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                        byte[] createByteArray = parcel.createByteArray();
                        byte readByte = parcel.readByte();
                        ISmartcardServiceCallback asInterface = ISmartcardServiceCallback.Stub.asInterface(parcel.readStrongBinder());
                        SmartcardError smartcardError3 = new SmartcardError();
                        ISmartcardServiceChannel openBasicChannel = openBasicChannel(createByteArray, readByte, asInterface, smartcardError3);
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(openBasicChannel != null ? openBasicChannel.asBinder() : null);
                        parcel2.writeInt(1);
                        smartcardError3.writeToParcel(parcel2, 1);
                        return true;
                    case 6:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
                        byte[] createByteArray2 = parcel.createByteArray();
                        byte readByte2 = parcel.readByte();
                        ISmartcardServiceCallback asInterface2 = ISmartcardServiceCallback.Stub.asInterface(parcel.readStrongBinder());
                        SmartcardError smartcardError4 = new SmartcardError();
                        ISmartcardServiceChannel openLogicalChannel = openLogicalChannel(createByteArray2, readByte2, asInterface2, smartcardError4);
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(openLogicalChannel != null ? openLogicalChannel.asBinder() : null);
                        parcel2.writeInt(1);
                        smartcardError4.writeToParcel(parcel2, 1);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString("org.simalliance.openmobileapi.service.ISmartcardServiceSession");
            return true;
        }
    }

    void close(SmartcardError smartcardError);

    void closeChannels(SmartcardError smartcardError);

    byte[] getAtr();

    boolean isClosed();

    ISmartcardServiceChannel openBasicChannel(byte[] bArr, byte b, ISmartcardServiceCallback iSmartcardServiceCallback, SmartcardError smartcardError);

    ISmartcardServiceChannel openLogicalChannel(byte[] bArr, byte b, ISmartcardServiceCallback iSmartcardServiceCallback, SmartcardError smartcardError);
}
