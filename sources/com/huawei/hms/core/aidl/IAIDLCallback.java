package com.huawei.hms.core.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IAIDLCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.core.aidl.IAIDLCallback";

    void call(DataBuffer dataBuffer) throws RemoteException;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IAIDLCallback {
        static final int TRANSACTION_call = 1;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.huawei.hms.core.aidl.IAIDLCallback$Stub$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class C4902a implements IAIDLCallback {

            /* renamed from: b */
            public static IAIDLCallback f11185b;

            /* renamed from: a */
            private IBinder f11186a;

            C4902a(IBinder iBinder) {
                this.f11186a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f11186a;
            }

            @Override // com.huawei.hms.core.aidl.IAIDLCallback
            public void call(DataBuffer dataBuffer) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.core.aidl.IAIDLCallback");
                    if (dataBuffer != null) {
                        obtain.writeInt(1);
                        dataBuffer.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.f11186a.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().call(dataBuffer);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.core.aidl.IAIDLCallback");
        }

        public static IAIDLCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.core.aidl.IAIDLCallback");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAIDLCallback)) {
                return (IAIDLCallback) queryLocalInterface;
            }
            return new C4902a(iBinder);
        }

        public static IAIDLCallback getDefaultImpl() {
            return C4902a.f11185b;
        }

        public static boolean setDefaultImpl(IAIDLCallback iAIDLCallback) {
            if (C4902a.f11185b == null) {
                if (iAIDLCallback != null) {
                    C4902a.f11185b = iAIDLCallback;
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
                parcel2.writeString("com.huawei.hms.core.aidl.IAIDLCallback");
                return true;
            } else if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceInterface("com.huawei.hms.core.aidl.IAIDLCallback");
                call(parcel.readInt() != 0 ? DataBuffer.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
        }
    }
}
