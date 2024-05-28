package org.simalliance.openmobileapi.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.simalliance.openmobileapi.service.ISmartcardServiceSession;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ISmartcardServiceReader extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class Stub extends Binder implements ISmartcardServiceReader {
        public static final String DESCRIPTOR = "org.simalliance.openmobileapi.service.ISmartcardServiceReader";
        public static final int TRANSACTION_closeSessions = 3;
        public static final int TRANSACTION_isSecureElementPresent = 1;
        public static final int TRANSACTION_openSession = 2;

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public static class Proxy implements ISmartcardServiceReader {
            public IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceReader
            public void closeSessions(SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceReader");
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

            public String getInterfaceDescriptor() {
                return "org.simalliance.openmobileapi.service.ISmartcardServiceReader";
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceReader
            public boolean isSecureElementPresent() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceReader");
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardServiceReader
            public ISmartcardServiceSession openSession(SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardServiceReader");
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    ISmartcardServiceSession asInterface = ISmartcardServiceSession.Stub.asInterface(obtain2.readStrongBinder());
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
            attachInterface(this, "org.simalliance.openmobileapi.service.ISmartcardServiceReader");
        }

        public static ISmartcardServiceReader asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("org.simalliance.openmobileapi.service.ISmartcardServiceReader");
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartcardServiceReader)) {
                return (ISmartcardServiceReader) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceReader");
                boolean isSecureElementPresent = isSecureElementPresent();
                parcel2.writeNoException();
                parcel2.writeInt(isSecureElementPresent ? 1 : 0);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceReader");
                SmartcardError smartcardError = new SmartcardError();
                ISmartcardServiceSession openSession = openSession(smartcardError);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(openSession != null ? openSession.asBinder() : null);
                parcel2.writeInt(1);
                smartcardError.writeToParcel(parcel2, 1);
                return true;
            } else if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("org.simalliance.openmobileapi.service.ISmartcardServiceReader");
                return true;
            } else {
                parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardServiceReader");
                SmartcardError smartcardError2 = new SmartcardError();
                closeSessions(smartcardError2);
                parcel2.writeNoException();
                parcel2.writeInt(1);
                smartcardError2.writeToParcel(parcel2, 1);
                return true;
            }
        }
    }

    void closeSessions(SmartcardError smartcardError);

    boolean isSecureElementPresent();

    ISmartcardServiceSession openSession(SmartcardError smartcardError);
}
