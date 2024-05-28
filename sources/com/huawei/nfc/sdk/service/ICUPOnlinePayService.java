package com.huawei.nfc.sdk.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.nfc.sdk.service.ICUPOnlinePayCallBackService;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ICUPOnlinePayService extends IInterface {

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public abstract class Stub extends Binder implements ICUPOnlinePayService {
        private static final String DESCRIPTOR = "com.huawei.nfc.sdk.service.ICUPOnlinePayService";
        static final int TRANSACTION_getUnionOnlinePayStatus = 2;
        static final int TRANSACTION_supportCapacity = 1;

        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        class Proxy implements ICUPOnlinePayService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.huawei.nfc.sdk.service.ICUPOnlinePayService
            public void getUnionOnlinePayStatus(ICUPOnlinePayCallBackService iCUPOnlinePayCallBackService) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iCUPOnlinePayCallBackService != null ? iCUPOnlinePayCallBackService.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.ICUPOnlinePayService
            public boolean supportCapacity(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICUPOnlinePayService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ICUPOnlinePayService)) ? new Proxy(iBinder) : (ICUPOnlinePayService) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean supportCapacity = supportCapacity(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(supportCapacity ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    getUnionOnlinePayStatus(ICUPOnlinePayCallBackService.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void getUnionOnlinePayStatus(ICUPOnlinePayCallBackService iCUPOnlinePayCallBackService);

    boolean supportCapacity(String str);
}
