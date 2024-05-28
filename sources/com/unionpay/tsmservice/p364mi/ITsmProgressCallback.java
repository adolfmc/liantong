package com.unionpay.tsmservice.p364mi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.tsmservice.mi.ITsmProgressCallback */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ITsmProgressCallback extends IInterface {

    /* renamed from: com.unionpay.tsmservice.mi.ITsmProgressCallback$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public abstract class Stub extends Binder implements ITsmProgressCallback {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.unionpay.tsmservice.mi.ITsmProgressCallback$Stub$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public final class C10795a implements ITsmProgressCallback {

            /* renamed from: a */
            private IBinder f20782a;

            /* JADX INFO: Access modifiers changed from: package-private */
            public C10795a(IBinder iBinder) {
                this.f20782a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f20782a;
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmProgressCallback
            public final void onProgress(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmProgressCallback");
                    obtain.writeInt(i);
                    this.f20782a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.unionpay.tsmservice.mi.ITsmProgressCallback");
        }

        public static ITsmProgressCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.mi.ITsmProgressCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITsmProgressCallback)) ? new C10795a(iBinder) : (ITsmProgressCallback) queryLocalInterface;
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
                parcel2.writeString("com.unionpay.tsmservice.mi.ITsmProgressCallback");
                return true;
            }
            parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmProgressCallback");
            onProgress(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void onProgress(int i);
}
