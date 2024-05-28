package com.huawei.hms.core.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hms.core.aidl.IAIDLCallback;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IAIDLInvoke extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.core.aidl.IAIDLInvoke";

    void asyncCall(DataBuffer dataBuffer, IAIDLCallback iAIDLCallback) throws RemoteException;

    void syncCall(DataBuffer dataBuffer) throws RemoteException;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IAIDLInvoke {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.huawei.hms.core.aidl.IAIDLInvoke$Stub$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class C4903a implements IAIDLInvoke {

            /* renamed from: b */
            public static IAIDLInvoke f11187b;

            /* renamed from: a */
            private IBinder f11188a;

            C4903a(IBinder iBinder) {
                this.f11188a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f11188a;
            }

            @Override // com.huawei.hms.core.aidl.IAIDLInvoke
            public void asyncCall(DataBuffer dataBuffer, IAIDLCallback iAIDLCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.core.aidl.IAIDLInvoke");
                    if (dataBuffer != null) {
                        obtain.writeInt(1);
                        dataBuffer.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iAIDLCallback != null ? iAIDLCallback.asBinder() : null);
                    if (this.f11188a.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().asyncCall(dataBuffer, iAIDLCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.core.aidl.IAIDLInvoke
            public void syncCall(DataBuffer dataBuffer) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.core.aidl.IAIDLInvoke");
                    if (dataBuffer != null) {
                        obtain.writeInt(1);
                        dataBuffer.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f11188a.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().syncCall(dataBuffer);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.core.aidl.IAIDLInvoke");
        }

        public static IAIDLInvoke asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.core.aidl.IAIDLInvoke");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAIDLInvoke)) {
                return (IAIDLInvoke) queryLocalInterface;
            }
            return new C4903a(iBinder);
        }

        public static IAIDLInvoke getDefaultImpl() {
            return C4903a.f11187b;
        }

        public static boolean setDefaultImpl(IAIDLInvoke iAIDLInvoke) {
            if (C4903a.f11187b == null) {
                if (iAIDLInvoke != null) {
                    C4903a.f11187b = iAIDLInvoke;
                    return true;
                }
                return false;
            }
            throw new IllegalStateException("setDefaultImpl() called twice");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.hms.core.aidl.IAIDLInvoke");
                return true;
            }
            if (i == 1) {
                parcel.enforceInterface("com.huawei.hms.core.aidl.IAIDLInvoke");
                syncCall(parcel.readInt() != 0 ? DataBuffer.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceInterface("com.huawei.hms.core.aidl.IAIDLInvoke");
                asyncCall(parcel.readInt() != 0 ? DataBuffer.CREATOR.createFromParcel(parcel) : null, IAIDLCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }
    }
}
