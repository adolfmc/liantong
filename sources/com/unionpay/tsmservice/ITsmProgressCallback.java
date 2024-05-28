package com.unionpay.tsmservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ITsmProgressCallback extends IInterface {

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public abstract class Stub extends Binder implements ITsmProgressCallback {

        /* renamed from: com.unionpay.tsmservice.ITsmProgressCallback$Stub$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class C10774a implements ITsmProgressCallback {

            /* renamed from: a */
            private IBinder f20704a;

            C10774a(IBinder iBinder) {
                this.f20704a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f20704a;
            }

            @Override // com.unionpay.tsmservice.ITsmProgressCallback
            public final void onProgress(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmProgressCallback");
                    obtain.writeInt(i);
                    this.f20704a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.unionpay.tsmservice.ITsmProgressCallback");
        }

        public static ITsmProgressCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.ITsmProgressCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITsmProgressCallback)) ? new C10774a(iBinder) : (ITsmProgressCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.unionpay.tsmservice.ITsmProgressCallback");
                return true;
            }
            parcel.enforceInterface("com.unionpay.tsmservice.ITsmProgressCallback");
            onProgress(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void onProgress(int i);
}
