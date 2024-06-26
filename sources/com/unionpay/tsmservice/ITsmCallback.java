package com.unionpay.tsmservice;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ITsmCallback extends IInterface {

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public abstract class Stub extends Binder implements ITsmCallback {

        /* renamed from: com.unionpay.tsmservice.ITsmCallback$Stub$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class C10773a implements ITsmCallback {

            /* renamed from: a */
            private IBinder f20703a;

            C10773a(IBinder iBinder) {
                this.f20703a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f20703a;
            }

            @Override // com.unionpay.tsmservice.ITsmCallback
            public final void onError(String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmCallback");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f20703a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.ITsmCallback
            public final void onResult(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmCallback");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f20703a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.unionpay.tsmservice.ITsmCallback");
        }

        public static ITsmCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.ITsmCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITsmCallback)) ? new C10773a(iBinder) : (ITsmCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.unionpay.tsmservice.ITsmCallback");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmCallback");
                    onResult(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    break;
                case 2:
                    parcel.enforceInterface("com.unionpay.tsmservice.ITsmCallback");
                    onError(parcel.readString(), parcel.readString());
                    break;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void onError(String str, String str2);

    void onResult(Bundle bundle);
}
