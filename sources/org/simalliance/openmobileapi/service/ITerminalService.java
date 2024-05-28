package org.simalliance.openmobileapi.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ITerminalService extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class Stub extends Binder implements ITerminalService {
        public static final String DESCRIPTOR = "org.simalliance.openmobileapi.service.ITerminalService";
        public static final int TRANSACTION_getAtr = 4;
        public static final int TRANSACTION_getSeStateChangedAction = 7;
        public static final int TRANSACTION_internalCloseLogicalChannel = 2;
        public static final int TRANSACTION_internalOpenLogicalChannel = 1;
        public static final int TRANSACTION_internalTransmit = 3;
        public static final int TRANSACTION_isCardPresent = 5;
        public static final int TRANSACTION_simIOExchange = 6;

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public static class Proxy implements ITerminalService {
            public IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // org.simalliance.openmobileapi.service.ITerminalService
            public byte[] getAtr() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ITerminalService");
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "org.simalliance.openmobileapi.service.ITerminalService";
            }

            @Override // org.simalliance.openmobileapi.service.ITerminalService
            public String getSeStateChangedAction() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ITerminalService");
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ITerminalService
            public void internalCloseLogicalChannel(int i, SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ITerminalService");
                    obtain.writeInt(i);
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

            @Override // org.simalliance.openmobileapi.service.ITerminalService
            public OpenLogicalChannelResponse internalOpenLogicalChannel(byte[] bArr, byte b, SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ITerminalService");
                    obtain.writeByteArray(bArr);
                    obtain.writeByte(b);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    OpenLogicalChannelResponse createFromParcel = obtain2.readInt() != 0 ? OpenLogicalChannelResponse.CREATOR.createFromParcel(obtain2) : null;
                    if (obtain2.readInt() != 0) {
                        smartcardError.readFromParcel(obtain2);
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ITerminalService
            public byte[] internalTransmit(byte[] bArr, SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ITerminalService");
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(3, obtain, obtain2, 0);
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

            @Override // org.simalliance.openmobileapi.service.ITerminalService
            public boolean isCardPresent() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ITerminalService");
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ITerminalService
            public byte[] simIOExchange(int i, String str, byte[] bArr, SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ITerminalService");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(6, obtain, obtain2, 0);
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
            attachInterface(this, "org.simalliance.openmobileapi.service.ITerminalService");
        }

        public static ITerminalService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("org.simalliance.openmobileapi.service.ITerminalService");
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITerminalService)) {
                return (ITerminalService) queryLocalInterface;
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
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ITerminalService");
                        byte[] createByteArray = parcel.createByteArray();
                        byte readByte = parcel.readByte();
                        SmartcardError smartcardError = new SmartcardError();
                        OpenLogicalChannelResponse internalOpenLogicalChannel = internalOpenLogicalChannel(createByteArray, readByte, smartcardError);
                        parcel2.writeNoException();
                        if (internalOpenLogicalChannel != null) {
                            parcel2.writeInt(1);
                            internalOpenLogicalChannel.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        parcel2.writeInt(1);
                        smartcardError.writeToParcel(parcel2, 1);
                        return true;
                    case 2:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ITerminalService");
                        int readInt = parcel.readInt();
                        SmartcardError smartcardError2 = new SmartcardError();
                        internalCloseLogicalChannel(readInt, smartcardError2);
                        parcel2.writeNoException();
                        parcel2.writeInt(1);
                        smartcardError2.writeToParcel(parcel2, 1);
                        return true;
                    case 3:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ITerminalService");
                        byte[] createByteArray2 = parcel.createByteArray();
                        SmartcardError smartcardError3 = new SmartcardError();
                        byte[] internalTransmit = internalTransmit(createByteArray2, smartcardError3);
                        parcel2.writeNoException();
                        parcel2.writeByteArray(internalTransmit);
                        parcel2.writeInt(1);
                        smartcardError3.writeToParcel(parcel2, 1);
                        return true;
                    case 4:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ITerminalService");
                        byte[] atr = getAtr();
                        parcel2.writeNoException();
                        parcel2.writeByteArray(atr);
                        return true;
                    case 5:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ITerminalService");
                        boolean isCardPresent = isCardPresent();
                        parcel2.writeNoException();
                        parcel2.writeInt(isCardPresent ? 1 : 0);
                        return true;
                    case 6:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ITerminalService");
                        int readInt2 = parcel.readInt();
                        String readString = parcel.readString();
                        byte[] createByteArray3 = parcel.createByteArray();
                        SmartcardError smartcardError4 = new SmartcardError();
                        byte[] simIOExchange = simIOExchange(readInt2, readString, createByteArray3, smartcardError4);
                        parcel2.writeNoException();
                        parcel2.writeByteArray(simIOExchange);
                        parcel2.writeInt(1);
                        smartcardError4.writeToParcel(parcel2, 1);
                        return true;
                    case 7:
                        parcel.enforceInterface("org.simalliance.openmobileapi.service.ITerminalService");
                        String seStateChangedAction = getSeStateChangedAction();
                        parcel2.writeNoException();
                        parcel2.writeString(seStateChangedAction);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString("org.simalliance.openmobileapi.service.ITerminalService");
            return true;
        }
    }

    byte[] getAtr();

    String getSeStateChangedAction();

    void internalCloseLogicalChannel(int i, SmartcardError smartcardError);

    OpenLogicalChannelResponse internalOpenLogicalChannel(byte[] bArr, byte b, SmartcardError smartcardError);

    byte[] internalTransmit(byte[] bArr, SmartcardError smartcardError);

    boolean isCardPresent();

    byte[] simIOExchange(int i, String str, byte[] bArr, SmartcardError smartcardError);
}
