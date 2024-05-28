package com.unionpay.tsmservice.p364mi;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.tsmservice.mi.ITsmCallback */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ITsmCallback extends IInterface {

    /* renamed from: com.unionpay.tsmservice.mi.ITsmCallback$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public abstract class Stub extends Binder implements ITsmCallback {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.unionpay.tsmservice.mi.ITsmCallback$Stub$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public final class C10794a implements ITsmCallback {

            /* renamed from: a */
            private IBinder f20781a;

            /* JADX INFO: Access modifiers changed from: package-private */
            public C10794a(IBinder iBinder) {
                this.f20781a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f20781a;
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmCallback
            public final void onError(String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmCallback");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f20781a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.p364mi.ITsmCallback
            public final void onResult(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmCallback");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f20781a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.unionpay.tsmservice.mi.ITsmCallback");
        }

        public static ITsmCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.mi.ITsmCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITsmCallback)) ? new C10794a(iBinder) : (ITsmCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.unionpay.tsmservice.mi.ITsmCallback");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmCallback");
                    onResult(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.unionpay.tsmservice.mi.ITsmCallback");
                    onError(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void onError(String str, String str2);

    void onResult(Bundle bundle);
}
