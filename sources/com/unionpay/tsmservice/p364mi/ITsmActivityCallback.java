package com.unionpay.tsmservice.p364mi;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.tsmservice.mi.ITsmActivityCallback */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ITsmActivityCallback extends IInterface {

    /* renamed from: com.unionpay.tsmservice.mi.ITsmActivityCallback$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public abstract class Stub extends Binder implements ITsmActivityCallback {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.unionpay.tsmservice.mi.ITsmActivityCallback$Stub$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public final class C10793a implements ITsmActivityCallback {

            /* renamed from: a */
            private IBinder f20780a;

            /* JADX INFO: Access modifiers changed from: package-private */
            public C10793a(IBinder iBinder) {
                this.f20780a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f20780a;
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmActivityCallback
            public final void startActivity(String str, String str2, int i, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmActivityCallback");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f20780a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.unionpay.tsmservice.mi.ITsmActivityCallback");
        }

        public static ITsmActivityCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.mi.ITsmActivityCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITsmActivityCallback)) ? new C10793a(iBinder) : (ITsmActivityCallback) queryLocalInterface;
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
                parcel2.writeString("com.unionpay.tsmservice.mi.ITsmActivityCallback");
                return true;
            }
            parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmActivityCallback");
            startActivity(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }
    }

    void startActivity(String str, String str2, int i, Bundle bundle);
}
