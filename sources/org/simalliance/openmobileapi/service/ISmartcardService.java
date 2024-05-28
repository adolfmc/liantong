package org.simalliance.openmobileapi.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.simalliance.openmobileapi.service.ISmartcardServiceReader;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ISmartcardService extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class Stub extends Binder implements ISmartcardService {
        public static final String DESCRIPTOR = "org.simalliance.openmobileapi.service.ISmartcardService";
        public static final int TRANSACTION_getReader = 2;
        public static final int TRANSACTION_getReaders = 1;

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public static class Proxy implements ISmartcardService {
            public IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "org.simalliance.openmobileapi.service.ISmartcardService";
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardService
            public ISmartcardServiceReader getReader(String str, SmartcardError smartcardError) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardService");
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    ISmartcardServiceReader asInterface = ISmartcardServiceReader.Stub.asInterface(obtain2.readStrongBinder());
                    if (obtain2.readInt() != 0) {
                        smartcardError.readFromParcel(obtain2);
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // org.simalliance.openmobileapi.service.ISmartcardService
            public String[] getReaders() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("org.simalliance.openmobileapi.service.ISmartcardService");
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "org.simalliance.openmobileapi.service.ISmartcardService");
        }

        public static ISmartcardService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("org.simalliance.openmobileapi.service.ISmartcardService");
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartcardService)) {
                return (ISmartcardService) queryLocalInterface;
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
                parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardService");
                String[] readers = getReaders();
                parcel2.writeNoException();
                parcel2.writeStringArray(readers);
                return true;
            } else if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("org.simalliance.openmobileapi.service.ISmartcardService");
                return true;
            } else {
                parcel.enforceInterface("org.simalliance.openmobileapi.service.ISmartcardService");
                String readString = parcel.readString();
                SmartcardError smartcardError = new SmartcardError();
                ISmartcardServiceReader reader = getReader(readString, smartcardError);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(reader != null ? reader.asBinder() : null);
                parcel2.writeInt(1);
                smartcardError.writeToParcel(parcel2, 1);
                return true;
            }
        }
    }

    ISmartcardServiceReader getReader(String str, SmartcardError smartcardError);

    String[] getReaders();
}
